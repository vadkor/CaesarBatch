package rms.caesar.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import rms.caesar.batch.model.TextLine;

public class CaesarProcessor implements ItemProcessor<TextLine, TextLine> {

	@Value("${shift:13}")
	private int shift;

	public static String encode(String enc, int offset) {
		offset = offset % 26 + 26;
		StringBuilder encoded = new StringBuilder();
		for (char i : enc.toCharArray()) {
			if (Character.isLetter(i)) {
				if (Character.isUpperCase(i)) {
					encoded.append((char) ('A' + (i - 'A' + offset) % 26));
				} else {
					encoded.append((char) ('a' + (i - 'a' + offset) % 26));
				}
			} else {
				encoded.append(i);
			}
		}
		return encoded.toString();
	}

	@Override
	public TextLine process(final TextLine line) throws Exception {
		line.setText(encode(line.getText(), shift));
		return line;
	}
}
