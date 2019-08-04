package rms.caesar.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import rms.caesar.batch.listener.JobNotificationListener;
import rms.caesar.batch.model.TextLine;
import rms.caesar.batch.model.TextStore;
import rms.caesar.batch.processor.CaesarProcessor;
import rms.caesar.batch.reader.TextReader;
import rms.caesar.batch.writer.EncipheredTextWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("${threads:1}")
	private int threadCount;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public TextStore textStore() {
		return new TextStore();
	}

	@Bean
	public TextReader textReader() {
		return new TextReader();
	}

	@Bean
	public CaesarProcessor textEncipheringProcessor() {
		return new CaesarProcessor();
	}

	@Bean
	public EncipheredTextWriter encipheredTextWriter() {
		return new EncipheredTextWriter();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobNotificationListener();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("Caesar Job").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(step()).end().build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("CaesarBatch");
		asyncTaskExecutor.setConcurrencyLimit(threadCount);
		return asyncTaskExecutor;
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("input --> Caesar Cipher --> output").<TextLine, TextLine>chunk(10)
				.reader(textReader()).processor(textEncipheringProcessor()).writer(encipheredTextWriter())
				.taskExecutor(taskExecutor()).build();
	}
}
