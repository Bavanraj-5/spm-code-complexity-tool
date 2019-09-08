package logic;

import java.util.ArrayList;
import java.util.List;

public class SizeReturns {
	
	public int lineNumber;
	public List<String> foundTokens;
	public int CsValuePerLine;
	
	public SizeReturns(int lineNumber,int csValuePerLine) {
		super();
		this.lineNumber = lineNumber;
		CsValuePerLine = csValuePerLine;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public List<String> getFoundTokens() {
		return foundTokens;
	}
	public void setFoundTokens(List<String> foundTokens) {
		this.foundTokens = foundTokens;
	}
	public int getCsValuePerLine() {
		return CsValuePerLine;
	}
	public void setCsValuePerLine(int csValuePerLine) {
		CsValuePerLine = csValuePerLine;
	}
	


}
