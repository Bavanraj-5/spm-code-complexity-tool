package logic;

import java.io.BufferedReader;
import java.lang.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class SizeComplex {
	

	public static final Logger LOGGER=Logger.getLogger(SizeComplex.class.getName()); //for logger
	int complexity = 0;
	int totalcomplexity = 0;

	//test comment
	
	SizeComplex(String fileLocation){
		this.fileLocation = fileLocation;
	}
	
	
	//variables by Manoj START
	    String line = null;//holds the line which is read from the file	
		String fileLocation;
		public Boolean anyHits=false;
		int lineNumber=1;
		int complexitySize;
		String words="";
		List<String> foundTokens=new ArrayList<String>();		
		ArrayList<SizeReturns> returns=new ArrayList<SizeReturns>();
		
	//variables by Manoj END
		
		
		
		
	public void checkSizeComplexity() {
		
		
		try {
		
			//pattern for text within double quotes
			Pattern pattern = Pattern.compile("\"([^\"]*)\"");
			
			//read the file
			FileReader filereader = new FileReader(fileLocation);
			BufferedReader bufferedreader = new BufferedReader(filereader);
      
			line = bufferedreader.readLine();
			
			while(line!=null) {
				complexitySize=0;
				
				StringTokenizer stringTokenizer=new StringTokenizer(line);
				
			
				while(stringTokenizer.hasMoreTokens()) {
					words=stringTokenizer.nextToken();
					
					if(words.contains("()")) {
						complexitySize++;
					}
					
					Matcher matcher = pattern.matcher(words);
					
//					if(line.contains("import")) {
//						break;
//					}

//					if(line.contains("package")) {
//						break;
//					}
					
					while(matcher.find()) {
						complexitySize++; //complexity of text inside a pair of double quotes
						anyHits=true;
					}
								
					for(int arithmetic=0;arithmetic<SizeContstants.ARITHMETIC_OPERATORS.length;arithmetic++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.ARITHMETIC_OPERATORS[arithmetic]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
						
					}
					
					for(int relation=0;relation<SizeContstants.RELATION_OPERATORS.length;relation++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.RELATION_OPERATORS[relation]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					
					}
					

					for(int logical=0;logical<SizeContstants.LOGICAL_OPERATORS.length;logical++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.LOGICAL_OPERATORS[logical]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					
					}
					
					for(int bitwise=0;bitwise<SizeContstants.BITWISE_OPERATORS.length;bitwise++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.BITWISE_OPERATORS[bitwise]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
						
					}
					
					for(int misc=0;misc<SizeContstants.MISC_OPERATORS.length;misc++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.MISC_OPERATORS[misc]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					
					}
					
					for(int assignment=0;assignment<SizeContstants.ASSIGNMENT_OPERATORS.length;assignment++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.ASSIGNMENT_OPERATORS[assignment]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					
					}
					
					for(int keywords=0;keywords<SizeContstants.POSSIBLE_KEYWORDS.length;keywords++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.POSSIBLE_KEYWORDS[keywords]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					}
					
					for(int manipulators=0;manipulators<SizeContstants.MANIPULATORS.length;manipulators++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.MANIPULATORS[manipulators]);
						complexitySize =complexitySize+ occurence_count;
						anyHits=true;
						if(matcher.find()) {
							break;
						}
					}
					
					for(int manipulators=0;manipulators<SizeContstants.EXCEPTIONS.length;manipulators++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.EXCEPTIONS[manipulators]);
						complexitySize =complexitySize+ occurence_count;
						
						anyHits=true;
						
					}
					
					for(int manipulators=0;manipulators<SizeContstants.TWO_KEYWORDS.length;manipulators++) {
						
						int occurence_count = StringUtils.countMatches(words,SizeContstants.TWO_KEYWORDS[manipulators]);
						complexitySize =complexitySize+ (occurence_count*2);
						anyHits=true;
						
					}
				}
				
				if(line.contains("//")) {
				break;
			}
				
				if(line.contains("")) {}
				
				String[] athal=line.split("");

				for(String singithi:athal) {
					if(singithi.contains("\"")) {
						break;
					}else if(StringUtils.isNumeric(singithi)) {
						complexitySize++;
					}
				}
				line = bufferedreader.readLine();
				
				if(anyHits) {
					returns.add(new SizeReturns(lineNumber, complexitySize));
				}
				System.out.println(line);
				
				
				lineNumber++;
			}
			
		
			bufferedreader.close();
			
			
		}catch(IOException e) {
			LOGGER.info(e.toString());
		}
		
	
//		for (int i = 0; i < returns.size(); i++){ 
//	    
//	        SizeReturns data = returns.get(i); 
//	        if(data.CsValuePerLine==0) {
//	        	System.out.println(data.lineNumber +" "+ " "); 
//
//	        }else {
//	        	System.out.println(data.lineNumber +" "+ data.CsValuePerLine); 
//	        }
//        }
		
		  
		  
	}
	

	
	
}
