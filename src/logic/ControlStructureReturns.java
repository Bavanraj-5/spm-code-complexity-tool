package logic;

import java.util.List;

public class ControlStructureReturns {
	
	public int lineNumber;
	public List<String> tokenFound;
	public int CtcValue;
	public int CncValue;

	
	//constructor
	public ControlStructureReturns(int lineNumber,int ctcValue,int cncValue) {
		super();
		this.lineNumber = lineNumber;
		CtcValue = ctcValue;
		CncValue = cncValue;
	}


	//getters & setters
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public List<String> getTokenFound() {
		return tokenFound;
	}

	public void setTokenFound(List<String> tokenFound) {
		this.tokenFound = tokenFound;
	}

	public int getCtcValue() {
		return CtcValue;
	}

	public void setCtcValue(int ctcValue) {
		CtcValue = ctcValue;
	}

	public int getCncValue() {
		return CncValue;
	}


	public void setCncValue(int cncValue) {
		CncValue = cncValue;
	}
		
}
