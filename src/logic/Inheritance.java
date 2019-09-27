package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import java.lang.*;

public class Inheritance {

	String fileLocation;
	String line = null;// holds the line which is read from the file
	int lineNumber = 1;
	int complexitySize;
	String words = "";
	public Boolean anyHits = false;

	public int FinalInheritance = 0;

	public static final Logger LOGGER = Logger.getLogger(SizeComplex.class.getName());

	ArrayList<SizeReturns> returns = new ArrayList<SizeReturns>();
	int Inhercomplexity = 2;
	int totalcomplexity = 0;
	int Inhercomplexity1;
	static int x;

	// constructor
	Inheritance(String fileLocation) {

		this.fileLocation = fileLocation;

	}

	public void CheckInheritance(String line) {

		try {
//			FileReader filereader = new FileReader(fileLocation);
//			BufferedReader bufferedreader = new BufferedReader(filereader);
//      
//			line = bufferedreader.readLine();
//			Inhercomplexity=2;
//			while(line!=null) {
			line = line.trim();

			StringTokenizer stringTokenizer = new StringTokenizer(line);

			while (stringTokenizer.hasMoreTokens()) {

				words = stringTokenizer.nextToken();

				Inhercomplexity1 = 1;

				if (line.contains("import")) {
					Inhercomplexity1 = 0;
					break;
				}

				if (line.contains("package")) {
					Inhercomplexity1 = 0;
					break;
				}

				if (line.contains("}") || line.isEmpty()) {
					Inhercomplexity1 = 0;
					// System.out.println(line);
					break;
				}

				if (line.contains("else")) {
					Inhercomplexity1 = 0;
					break;
				}

				if (line.contains("try")) {
					Inhercomplexity1 = 0;
					break;
				}

//				if (line.contains("//")) {
//					Inhercomplexity1 = 0;
//					break;
//				}

				//
//					if(line.trim().length()==0) {
//					Inhercomplexity1=0;
//					
//					break;
//				}

				if (line.contains(SizeContstants.CLASS[0].toString())) {

					for (int arithmetic = 0; arithmetic < SizeContstants.IHERITANCE.length; arithmetic++) {

						int occurence_count = StringUtils.countMatches(words, SizeContstants.IHERITANCE[arithmetic]);
						Inhercomplexity = Inhercomplexity + occurence_count;

						// System.out.println(occurence_count);

						anyHits = true;

					}

				}
				// totalcomplexity=Inhercomplexity;

				if (line.trim().length() == 0) {
					Inhercomplexity1 = 0;

					break;
				}
			}

			// line = bufferedreader.readLine();

			if (anyHits) {

				if (Inhercomplexity1 == 0) {
					returns.add(new SizeReturns(lineNumber, Inhercomplexity1));
					FinalInheritance = Inhercomplexity1;
				} else {
					returns.add(new SizeReturns(lineNumber, Inhercomplexity));
					FinalInheritance = Inhercomplexity;
				}

			}

			lineNumber++;
			// }

			// bufferedreader.close();

		} catch (Exception e) {
			LOGGER.info(e.toString());
		}

//		  for (int i = 0; i < returns.size(); i++) 
//	        { 
//	       
//	            SizeReturns data = returns.get(i); 
//	            
//	            if(data.CsValuePerLine==0) {
//	            	 System.out.println(data.lineNumber +" ");
//	            }else {
//	            	 System.out.println(data.lineNumber +" "+ data.CsValuePerLine); 
//	            }
//	            
//	           
//	        }

	}

}
