package rms.caesar.batch.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import rms.caesar.batch.model.TextLine;
import rms.caesar.batch.model.TextStore;

public class TextReader implements ItemReader<TextLine> {

	@Autowired
	private TextStore textStore;

	@Value("${name}")
	private String fullName;

	@PostConstruct
	public void inti() {
		try {
			Files.lines(Paths.get(fullName)).forEachOrdered(line -> textStore.put(line));
			textStore.rewind();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TextLine read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return textStore.getTextLine();
	}
}
