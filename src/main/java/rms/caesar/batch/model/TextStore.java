package rms.caesar.batch.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TextStore {

	private ConcurrentHashMap<Integer, String> textLines = new ConcurrentHashMap<>();
	private AtomicInteger currentNum = new AtomicInteger();

	public void put(int lineNum, String text) {
		textLines.put(lineNum, text);
	}

	public void put(String text) {
		textLines.put(currentNum.getAndIncrement(), text);
	}

	public TextLine getTextLine() {
		int lineNum = currentNum.getAndIncrement();
		return (lineNum >= textLines.size() ? null : new TextLine(lineNum, textLines.get(lineNum)));
	}

	public String get(int lineNum) {
		return textLines.get(lineNum);
	}

	public int size() {
		return textLines.size();
	}

	public void rewind() {
		currentNum.set(0);
	}
}
