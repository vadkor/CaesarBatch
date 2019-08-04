package rms.caesar.batch.model;

public class TextLine {

	private int lineNum;
	private String text;

	public TextLine() {
	}

	public TextLine(int lineNum, String text) {
		this.lineNum = lineNum;
		this.text = text;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
