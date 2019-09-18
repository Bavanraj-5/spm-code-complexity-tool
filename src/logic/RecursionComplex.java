package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecursionComplex {
	
	
	public void CheckRecursion() {
		String fileLocation;
        String[] words = null;
        String line = null;
        String methodLine = null;
        String methodName = null;
        int LineCount = 0;
        int RecursiveLine = 0;
        int OpenBracket = 0;
        int CloseBracket = 0;
        Boolean RecursiveMethod = false;
//        String[] firstWord;
//        String[] methodName;
//        String[] returnType = {"int", "float", "double", "long", "void", "String"};
//        System.out.println("Hellow");
        
        try {

        fileLocation = "D:\\Eclipse Workspace\\SPM Final\\src\\TestFiles\\FibonacciMain.java";
        FileReader filereader = new FileReader(fileLocation);
        BufferedReader bufferedreader = new BufferedReader(filereader);
        line = bufferedreader.readLine();
        LineCount = 1;
        
        while (line != null) {
        	//StringTokenizer stringToken = new StringTokenizer(line);
        	//firstWord = line.split(" ");
        	//firstWord[0] = firstWord[0].trim();
        	
        	//System.out.println(firstWord[0]);
        	
            
//            while (stringToken.hasMoreTokens()) {
//                words = stringToken.nextToken();
                
                //if(firstWord[0].equals("public") || firstWord[0].equals("static") || firstWord[0].equals(returnType) ) {
        	if( (line.contains("public") || line.contains("static") || line.contains("void")) && !(line.contains("class")) && (line.contains("("))) {
                	
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
                	//methodName = methodLine.split(" ");
                	//methodName[0] = methodName[0].trim();
                	//if()
                	//words = methodLine.split("\\s");
                	methodName = methodLine;
                	
                	System.out.println("methodName:" + methodName );
                	
//                	for(String WordsInLine : words) {
//                		if(WordsInLine.contains("(")) {
//                			methodName = WordsInLine;
//                			//methodName = methodName.replaceAll("(","");
//                			System.out.println(methodName);
//                		}
//                		System.out.println(WordsInLine);
//                		
//                	}
                	//System.out.println(methodLine);
                	//System.out.println("After***");

                }
        	
        	if (!((line.contains("public") || line.contains("static") || line.contains("void")) && !(line.contains("class")) && (line.contains("("))) && (OpenBracket != 0)) {
        		if(line.contains("{")) {
        			OpenBracket++;      
        		}
        		if((OpenBracket != CloseBracket) && (line.contains(methodName))) {
        			System.out.println("FROM Line: " + RecursiveLine);
        			RecursiveMethod = true ;
        		}
        		if(line.contains("}")) {
        			CloseBracket++;      
        		}
        		if(OpenBracket == CloseBracket) {
        			methodName = null;
        			OpenBracket = 0;
        			CloseBracket = 0;
        			if(RecursiveMethod) {
        				System.out.println("UPTO Line: " + LineCount);
        				RecursiveMethod = false;
        			}       			
        		}
        	
        	}
        	
        	
        	
        	
        	
            //}
            line = bufferedreader.readLine();
            LineCount++;
        }
        
        
        }catch (IOException e) {
        	e.printStackTrace();
        }
        
	}
	
	public static void main(String args[]) {
    	
    	RecursionComplex RC = new RecursionComplex();
        
        RC.CheckRecursion();
    }
}
