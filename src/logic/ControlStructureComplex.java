package logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ControlStructureComplex {

	public static final Logger LOG = Logger.getLogger(ControlStructureComplex.class.getName());
	int control_complexity = 0;
	int total_control_complexity = 0;
	String fileLocation;

	public int FinalCtrl = 0;
	public int FinalNested = 0;

	ControlStructureComplex(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	String control_line = null;

	int lineNumber = 1;
	int complexityControlStructure;
	int nestedComplexityControlStructure;
	String words = null;
	public boolean anyMatches = false;

	int case_count = 0;

	List<String> tokensFound = new ArrayList<String>();
	ArrayList<ControlStructureReturns> returns = new ArrayList<ControlStructureReturns>();

	public void calcControlStructureComplexity(String control_line) {

		try {

			// read file
//			FileReader fileReader = new FileReader(fileLocation);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			BufferedReader bufferedReader1 = new BufferedReader(fileReader);

//			control_line = bufferedReader.readLine();
//			
//			while (control_line != null) {

			StringTokenizer stringTokenizer = new StringTokenizer(control_line);

			while (stringTokenizer.hasMoreTokens()) {
				complexityControlStructure = 0;
				words = stringTokenizer.nextToken();

				// if code include "SWITCH_CASE"
				if (StringUtils.contains(control_line, ControlStructureConstants.CASE)) {
					complexityControlStructure++;
					anyMatches = true;
				}

				// if code include "import"
				if (control_line.contains("import")) {
					break;
				}

				// if code include packages
				if (control_line.contains("package")) {
					break;
				}

				// if code include print statements
				if (control_line.contains("System.out.")) {
					break;
				}

				/*
				 * if(control_line.contains("//")) { break; }
				 */

				// if code includes "IF-ELSE"
				if (control_line.contains(ControlStructureConstants.IF)) {
					complexityControlStructure++;

					for (int i = 0; i < ControlStructureConstants.LOGICAL_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
						complexityControlStructure += x;
					}

					for (int i = 0; i < ControlStructureConstants.BITWISE_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
						complexityControlStructure += x;
					}

					anyMatches = true;
				}

				// if code includes "FOR loop"
				if (StringUtils.contains(control_line, ControlStructureConstants.FOR)) {
					complexityControlStructure += 2;

					for (int i = 0; i < ControlStructureConstants.LOGICAL_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					for (int i = 0; i < ControlStructureConstants.BITWISE_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					anyMatches = true;
				}

				// if code includes "WHILE loop"
				if (StringUtils.contains(control_line, ControlStructureConstants.WHILE)) {
					complexityControlStructure += 2;

					for (int i = 0; i < ControlStructureConstants.LOGICAL_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					for (int i = 0; i < ControlStructureConstants.BITWISE_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					anyMatches = true;
				}

				// if code includes "DO-WHILE loop"
				if (StringUtils.contains(control_line, ControlStructureConstants.DO)) {
					complexityControlStructure = complexityControlStructure + 2;

					for (int i = 0; i < ControlStructureConstants.LOGICAL_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.LOGICAL_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					for (int i = 0; i < ControlStructureConstants.BITWISE_OPERATORS.length; i++) {
						int x = StringUtils.countMatches(control_line, ControlStructureConstants.BITWISE_OPERATORS[i]);
						int val = x * 2;
						complexityControlStructure += val;
					}

					anyMatches = true;
				}

				// if code includes "CATCH"
				if (control_line.contains(" ")) {
					String[] parts = control_line.split(" ");
					parts[0] = parts[0].trim();

					if (StringUtils.contains(control_line, ControlStructureConstants.CATCH)) {
						complexityControlStructure = complexityControlStructure + 1;

						anyMatches = true;
					}
				} // this calculates the lines after 'catch' also

			}

			// control_line = bufferedReader.readLine();

			if (anyMatches) {
				returns.add(new ControlStructureReturns(lineNumber, complexityControlStructure,
						nestedComplexityControlStructure));
			}
			FinalCtrl = complexityControlStructure;
			FinalNested = nestedComplexityControlStructure;
			lineNumber++;

//			}
//			
//			bufferedReader.close();

		} catch (Exception e) {
			LOG.info(e.toString());
		}

//		for (int i = 0; i < returns.size(); i++) 
//        { 
//       
//            ControlStructureReturns value = returns.get(i); 
//            System.out.println(value.lineNumber +" "+ value.CtcValue); 
//        }

	}

	public int calcNestedControlStructureComplexity(){
		int nestedCount=0; //initialize count
		List<Integer> nestedCountList=new ArrayList<Integer>();
		int totalNestedCount=0;
		//states
		int bState=0;
		
		
		int finalNestedCount=0;
		
		//regex for loops
	    String loopsRegex = "\\b((for|if)(\\s+|\\().*\\{)";
	    String cBracketRegex="\\}";
	    String normalLineRegex="^(\\s*\\}\\s*)|^(\\s*)$";


		try {
	
			InputStream is= new FileInputStream(fileLocation);
			BufferedReader bf= new BufferedReader(new InputStreamReader(is));
			
			String line =bf.readLine();
			
			while(line!=null) {
				//create patterns for loops and brackets
				Pattern loopsPattern=Pattern.compile(loopsRegex);
				Pattern cBracketPatterns=Pattern.compile(cBracketRegex);
		        Pattern normalPatterns = Pattern.compile(normalLineRegex);

				
				Matcher matcher1= loopsPattern.matcher(line);
				
				if(matcher1.find()) {
					bState++;
				}
				
				nestedCount=bState;
				
				Matcher matcher2=cBracketPatterns.matcher(line);
				while(matcher2.find()) {
					if(bState>0) {
						bState--;
					}
				}
				nestedCount=bState;

				Matcher matcher3=normalPatterns.matcher(line);
				if (matcher3.find()) {
					nestedCount = 0;
	            }
				nestedCountList.add(nestedCount);

				//System.out.println(line + " " + nestedCount);
				line=bf.readLine();
				
			}
			
			
			
			bf.close();
		}catch(IOException e) {
			LOG.info(e.toString());
		}
		
		for(int x:nestedCountList) {
			totalNestedCount+=x;
		}
		//System.out.println("Total Nested Count = " + totalNestedCount/3);
		finalNestedCount=totalNestedCount/3;
		return finalNestedCount;
	}

}
