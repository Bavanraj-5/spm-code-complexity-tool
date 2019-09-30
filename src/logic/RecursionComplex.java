package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecursionComplex {
	
	String fileLocation;
	String[] words = null;
	String methodLine = null;
	String methodName = null;
	int LineCount = 1;
	int RecursiveLine = 0;
	int OpenBracket = 0;
	int CloseBracket = 0;
	int Check = 0;
	public int Rstart, Rend;
	Boolean RecursiveMethod = false;
	public List<Integer> CrStartList = new ArrayList<>();
	public List<Integer> CrEndList = new ArrayList<>();

	public void CheckRecursion(String line) {


		try {

				if ((line.contains("public") || line.contains("static") || line.contains("void"))
						&& !(line.contains("class")) && (line.contains("("))) {

					OpenBracket = 1;
					RecursiveLine = LineCount;

					methodLine = line;
					methodLine = methodLine.replaceAll("public", "");
					methodLine = methodLine.replaceAll("static", "");
					methodLine = methodLine.replaceAll("int", "");
					methodLine = methodLine.replaceAll("float", "");
					methodLine = methodLine.replaceAll("double", "");
					methodLine = methodLine.replaceAll("long", "");
					methodLine = methodLine.replaceAll("void", "");
					methodLine = methodLine.replaceAll("String", "");
					methodLine = methodLine.replaceAll("args", "");
					methodLine = methodLine.replaceAll("\\[(.*?)\\]", "");
					methodLine = methodLine.replaceAll("\\((.*?)\\)", "");
					methodLine = methodLine.replaceAll("\\<(.*?)\\>", "");
					methodLine = methodLine.replaceAll("\\{", "");
					methodLine = methodLine.replaceAll(" ", "");
					methodLine = methodLine.replaceAll("	", "");
	
					methodName = methodLine;


				}

				if (!((line.contains("public") || line.contains("static") || line.contains("void"))
						&& !(line.contains("class")) && (line.contains("("))) && (OpenBracket != 0)) {
					if (line.contains("{")) {
						OpenBracket++;
					}
					if ((OpenBracket != CloseBracket) && (line.contains(methodName))) {
						if(Check != RecursiveLine) {
							CrStartList.add(RecursiveLine);
							Rstart = RecursiveLine;
						}
						RecursiveMethod = true;
						Check = RecursiveLine;
					}
					if (line.contains("}")) {
						CloseBracket++;
					}
					if (OpenBracket == CloseBracket) {
						methodName = null;
						OpenBracket = 0;
						CloseBracket = 0;
						if (RecursiveMethod) {
							CrEndList.add(LineCount);
							RecursiveMethod = false;
							Rend = LineCount;
						}
					}

				}


				LineCount++;
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
