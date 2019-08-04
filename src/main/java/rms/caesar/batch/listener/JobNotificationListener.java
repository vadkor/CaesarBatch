package rms.caesar.batch.listener;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import rms.caesar.batch.model.TextStore;

public class JobNotificationListener extends JobExecutionListenerSupport {

	@Autowired
	private TextStore textStore;

	@Value("${out:encrypted.txt}")
	private String outFile;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			Path path = Paths.get(outFile);
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
				for (int i = 0; i < textStore.size(); i++) {
					fileWriter.write(textStore.get(i));
					fileWriter.newLine();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
