package rms.caesar.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import rms.caesar.batch.model.TextLine;
import rms.caesar.batch.model.TextStore;

public class EncipheredTextWriter implements ItemWriter<TextLine> {

	@Autowired
	private TextStore textStore;

	@Override
	public void write(List<? extends TextLine> lines) throws Exception {
		lines.forEach(line -> textStore.put(line.getLineNum(), line.getText()));
	}
}
