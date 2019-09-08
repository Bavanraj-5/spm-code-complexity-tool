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
        String words;
        String line = null;
        String methodLine = null;
        String[] firstWord;
        String[] methodName;
        String[] returnType = {"int", "float", "double", "long", "void", "String"};
        System.out.println("Hellow");
        
        try {

        fileLocation = "D:\\Eclipse Workspace\\SPM tool\\src\\TestPackage\\MyException.java";
        FileReader filereader = new FileReader(fileLocation);
        BufferedReader bufferedreader = new BufferedReader(filereader);
        line = bufferedreader.readLine();
        
        while (line != null) {
        	StringTokenizer stringToken = new StringTokenizer(line);
        	firstWord = line.split(" ");
        	firstWord[0] = firstWord[0].trim();
        	
        	System.out.println(firstWord[0]);
        	
            
//            while (stringToken.hasMoreTokens()) {
//                words = stringToken.nextToken();
                
                if(firstWord[0].equals("public") || firstWord[0].equals("static") || firstWord[0].equals(returnType) ) {
                	methodLine = line;
                	
                	methodLine = methodLine.replaceAll("public", " ");
                	methodLine = methodLine.replaceAll("static", " ");
                	methodLine = methodLine.replaceAll("int", " ");
                	methodLine = methodLine.replaceAll("float", " ");
                	methodLine = methodLine.replaceAll("double", " ");
                	methodLine = methodLine.replaceAll("long", " ");
                	methodLine = methodLine.replaceAll("void", " ");
                	methodLine = methodLine.replaceAll("String", " ");
                	
                	methodName = methodLine.split(" ");
                	methodName[0] = methodName[0].trim();
                	
                	System.out.println("methodName[0]" + methodName );
                	System.out.println(methodLine);
                	System.out.println("After***");

                }
            //}
            line = bufferedreader.readLine();
        }
        
        
        }catch (IOException e) {
        	e.printStackTrace();
        }
        
	}
	
//	public static void main(String ss[]) {
//    	
//    	RecursionComplex RC = new RecursionComplex();
//        
//        RC.CheckRecursion();
//    }
}
