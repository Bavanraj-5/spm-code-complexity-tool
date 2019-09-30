package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Control_structures {
	
	String fileLocation;
    String words;
    String words2;
    String line = null;

    String[] cs1keywords = {"if","catch"}; // find the if and catch keywords
    String[] cs2keywords= {"for","while"};// find the for ,while and do-while keywords
    String[] cs3keywords= {"&&","and","||","&","|"}; //find the these operations  keywords
	String[] cs4keywords= {"case"}; ////find the case(switch = N number of case)  keywords
    
	int complexity = 0; // assign if and case complexity count
	int complexity2 = 0; // assign for ,while and do-while complexity count
	int complexity3 = 0; // assign if with operations values complexity count
	int complexity4 = 0; // assign for and while and do-while with operations values complexity count
	int complexity5=0;	 // assign  case (switch)  complexity count
	int totalcomplexity = 0; // assign total number of  complexity
	int switchComplexity=0;  // assign total number of switch (cases)
	
	Control_structures(String fileLocation){
		this.fileLocation = fileLocation;
	}

	public void CheckControlStructures() {	

		 try {
	        	
	            FileReader filereader = new FileReader(fileLocation);
	            BufferedReader bufferedreader = new BufferedReader(filereader);
	            line = bufferedreader.readLine(); // every line read it
	            
	            //if line is not null run the while 
	            while (line != null) {
	                StringTokenizer stringToken = new StringTokenizer(line); // div-line to words 
	                complexity = 0;
	                complexity2 = 0;
	                complexity3 = 0;
	                complexity4 = 0;
	                complexity5=0;
	                
	                while (stringToken.hasMoreTokens()) {
	                    words = stringToken.nextToken();
	                    
	                    // find the if and catch words form the line 
	                    for (int i = 0; i < cs1keywords.length; i++) {
	                        if (cs1keywords[i].equals("//")) {
	                            break;
	                        } else {
	                            if (cs1keywords[i].equals(words)) {
	                                complexity++;
	                                System.out.println("if,catch Complexity is: " + complexity);
	                               	String line2=line;
	               	                StringTokenizer stringToken2 = new StringTokenizer(line2);
	               	             
	               	                while (stringToken2.hasMoreTokens()) {
	               	                	words2 = stringToken2.nextToken();
	         	                    
	               	                	for (int x = 0; x < cs3keywords.length; x++) {
	               	                		if (cs3keywords[x].equals("//")) {
	               	                			break;
	               	                		} else {
	               	                			if (cs3keywords[x].equals(words2)) {
	               	                				complexity3++;
	               	                				System.out.println("if with in and && || Complexity is: " + complexity3);
	               	                			}
	               	                		}
	         	                	   
	               	                	}
	               	                }
	                                  
	                            }
	                           
	                        }
	                    }
	                    
	                    // find the for ,while from the line 
	                    for (int i = 0; i < cs2keywords.length; i++) {
	                        if (cs2keywords[i].equals("//")) {
	                            break;
	                        } else {
	                            if (cs2keywords[i].equals(words)) {
	                                complexity2 = complexity2 + 2;
	                                System.out.println("for while Complexity is: " + complexity2);
	                                
	                                //find the operations with in the for loop or while loop
	                                String line2=line;
		               	            StringTokenizer stringToken2 = new StringTokenizer(line2);
		               	            
		               	             while (stringToken2.hasMoreTokens()) {
		         	                    words2 = stringToken2.nextToken();
		         	                    
		         	                   for (int x = 0; x < cs3keywords.length; x++) {
		         	                	  if (cs3keywords[x].equals("//")) {
		      	                            break;
		         	                	  } else {
		      	                        		if (cs3keywords[x].equals(words2)) {
		      	                        			complexity4=complexity4+2;
		      	                        			System.out.println("for with in and && || Complexity is: " + complexity4);
		      	                        		}
		         	                	  }
		         	                	   
		         	                   }
		               	             }
	                            }
	                        }
	                    }
	                    
	                    // find the cases from the line 
	                    for (int i = 0; i < cs4keywords.length; i++) {
	                        if (cs4keywords[i].equals("//")) {
	                            break;
	                        } else {
	                            if (cs4keywords[i].equals(words)) {
	                                complexity5++;
	                            }
	                        }
	                        
	                    }
	
	                }
	               
	                //calculate the total number of complexity
	                totalcomplexity =( totalcomplexity + complexity+complexity2+complexity3+complexity4+complexity5);
	               // calculate the total number of cases=switch complexity
	                switchComplexity=switchComplexity+complexity5;
	                
	                
	                line = bufferedreader.readLine(); // go the next line  to read 
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    System.out.println("Switch Complexity is: " + switchComplexity );
	        System.out.println("Total Complexity due to control structures: " + totalcomplexity);
		}
		
}