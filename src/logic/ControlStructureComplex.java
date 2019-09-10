package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class ControlStructureComplex {

	public static final Logger LOG = Logger.getLogger(ControlStructureComplex.class.getName());
	int control_complexity = 0;
	int total_control_complexity = 0;
	String fileLocation;
	
	
	ControlStructureComplex(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	
	String control_line = null;
	
	int lineNumber = 1;
	int complexityControlStructure;
	String words = "";
	public boolean anyMatches = false;
	
	List<String> tokensFound = new ArrayList<String>();
	ArrayList<ControlStructureReturns> returns = new ArrayList<ControlStructureReturns>();
	
	
	public void calcControlStructureComplexity() {
		
		try {
			
			//read file
			FileReader fileReader = new FileReader(fileLocation);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			control_line = bufferedReader.readLine();
			
			while (control_line != null) {
				
				
				
				StringTokenizer stringTokenizer = new StringTokenizer(control_line);
				
				while(stringTokenizer.hasMoreTokens()) {
					complexityControlStructure = 0;
					words = stringTokenizer.nextToken();
					
					if(control_line.contains("import")) {
						break;
					}
					if(control_line.contains("//")) {
						break;
					}
					if(control_line.contains("package")) {
						break;
					}
					if(control_line.contains("System.out.")) {
						break;
					}

									
					//if code includes "IF-ELSE"
					if(control_line.contains(ControlStructureConstants.IF)) {
						complexityControlStructure++;
						
						for(int i=0;i<ControlStructureConstants.LOGICAL_OPERATORS.length;i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
							complexityControlStructure += x;	
						}
						
						for(int i=0;i<ControlStructureConstants.BITWISE_OPERATORS.length;i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
							complexityControlStructure += x;
						}
						
						anyMatches = true;
					}

					
					//if code includes "FOR loop"
					if(control_line.contains(ControlStructureConstants.FOR)) {
						complexityControlStructure += 2;
						
						for(int i=0 ; i<ControlStructureConstants.LOGICAL_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
						
						for(int i=0 ; i<ControlStructureConstants.BITWISE_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
						
						anyMatches = true;
					}

					
					//if code includes "WHILE loop"
					if(words.equals(ControlStructureConstants.WHILE)) {
						complexityControlStructure += 2;
							
						for(int i=0 ; i<ControlStructureConstants.LOGICAL_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
							
						for(int i=0 ; i<ControlStructureConstants.BITWISE_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
							
						anyMatches = true;
					}
					
					
					//if code includes "DO-WHILE loop"
					if(words.equals(ControlStructureConstants.DO)) {
						complexityControlStructure += 2;
						
						for(int i=0 ; i<ControlStructureConstants.LOGICAL_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
						
						for(int i=0 ; i<ControlStructureConstants.BITWISE_OPERATORS.length ; i++) {
							int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
							int val = x*2;
							complexityControlStructure += val;
						}
						
						anyMatches = true;
					}
		
					
				
				}
				
				control_line = bufferedReader.readLine();
				
				if(anyMatches) {
					returns.add(new ControlStructureReturns(lineNumber, complexityControlStructure));
				}
				lineNumber++;
				
			}
			
			bufferedReader.close();
			
		}catch (IOException e) {
			LOG.info(e.toString());
		}
		
		for (int i = 0; i < returns.size(); i++) 
        { 
       
            ControlStructureReturns value = returns.get(i); 
            System.out.println(value.lineNumber +" "+ value.CtcValue); 
        }
		
	}
	
}
