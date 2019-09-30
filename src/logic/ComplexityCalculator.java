package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComplexityCalculator {


	public static void main(String args[]) throws IOException {

		String fileLocation;
		
		String line = null;
		String line2 = null;
		
		int lineNo = 1;
		int Start, FinalCps, Count, ArrCount;
		
		List<Integer> CpsList = new ArrayList<>();
		List<Integer> FinalCpsList = new ArrayList<>();
		
		CpsList.add(0, 0);
		FinalCpsList.add(0, 0);
		
		int CS, CI, CTC, CNC, TW, CPS, CR = 0;
		int TotCS = 0, TotCI = 0, TotCTC = 0, TotCNC = 0, TotTW = 0, TotCPS = 0;
		int xx;
		int Rstart, Rend;

		fileLocation = "F:\\Sample Programs For Sprint 2 Evaluation\\Source Codes\\FibonacciMain\\FibonacciMain.java";

		FileReader filereader = new FileReader(fileLocation);
		BufferedReader bufferedreader = new BufferedReader(filereader);
		line = bufferedreader.readLine();

		FileReader filereader2 = new FileReader(fileLocation);
		BufferedReader bufferedreader2 = new BufferedReader(filereader2);
		line2 = bufferedreader2.readLine();

		SizeComplex Cs = new SizeComplex(fileLocation);
		Inheritance Ci = new Inheritance(fileLocation);
		ControlStructureComplex Csc = new ControlStructureComplex(fileLocation);
		RecursionComplex Cr = new RecursionComplex();

		// HTML
		File HTMLfile = new File("C:\\Users\\Bavan\\Desktop\\Complexity.html");
		BufferedWriter HTMLwriter = new BufferedWriter(new FileWriter(HTMLfile));
		String HTMLtable = "<html><body bgcolor='#E6E6FA' align = 'center' ><style>\r\n" + ".demo div {\r\n"
				+ "    float: left;\r\n" + "    clear: none;\r\n" + "}\r\n"
				+ "</style><div class='demo' align = 'center'><div><table><tr><th>Line_No</th><th>Statement</th><th>Cs</th><th>Ctc</th><th>Cnc</th><th>Ci</th><th>Tw</th><th>Cps</th></tr>";
		String HTMLvalues = "";
		String HTMLdisplay = null;

		System.out.println("LINE NO 	" + "Cs	" + "Ctc  " + "  Cnc	" + "Ci	" + "TW	" + "Cps");

		while (line != null) {

			if (!(line.contains("package") || line.contains("import") || (line.trim().equals("")))) {

				Cs.checkSizeComplexity(line);
				Ci.CheckInheritance(line);
				Csc.calcControlStructureComplexity(line);
				Cr.CheckRecursion(line);

				CS = Cs.FinalSize;
				CI = Ci.FinalInheritance;
				CTC = Csc.FinalCtrl;
				CNC = Csc.FinalNested;
				TW = CTC + CNC + CI;
				CPS = TW * CS;
				CpsList.add(lineNo, CPS);
				TotCS = TotCS + CS;
				TotCI = TotCI + CI;
				TotCTC = TotCTC + CTC;
				TotCNC = TotCNC + CNC;
				TotTW = TotTW + TW;

				HTMLvalues = HTMLvalues + "<tr><td>" + lineNo + "</td><td>" + line + "</td><td>" + CS + "</td><td>"
						+ CTC + "</td><td>" + CNC + "</td><td>" + CI + "</td><td>" + TW + "</td><td>" + CPS
						+ "</td></tr>";

				System.out
						.println(lineNo + "\t\t " + CS + "\t" + CTC + "\t " + CNC + "\t" + CI + "\t" + TW + "\t" + CPS);
				lineNo++;

			}
			line = bufferedreader.readLine();
		}

		TotCNC = Csc.calcNestedControlStructureComplexity();
		String HTMLtable2 = "<div><table><tr><th> CR </th></tr>";
		String HTMLvalues2 = "";
		Rstart = Cr.Rstart;
		Rend = Cr.Rend;
		lineNo = 1;
		while (line2 != null) {

			if (!(line2.contains("package") || line2.contains("import") || (line2.trim().equals("")))) {

				Cs.checkSizeComplexity(line2);
				Ci.CheckInheritance(line2);
				Csc.calcControlStructureComplexity(line2);
				// Cr.CheckRecursion(line);

				CS = Cs.FinalSize;
				CI = Ci.FinalInheritance;
				CTC = Csc.FinalCtrl;
				CNC = Csc.FinalNested;
				TW = CTC + CNC + CI;
				if (lineNo >= Rstart && lineNo <= Rend) {
					CPS = TW * CS * 2;
					CR = CPS;
				} else {
					CPS = TW * CS;
					CR = 0;
				}
				FinalCpsList.add(lineNo, CPS);
				TotCPS = TotCPS + CPS;

				HTMLvalues2 = HTMLvalues2 + "<tr><td>" + CR + "</td></tr>";

				System.out.println(lineNo + "\t\t " + CR);
				lineNo++;

			}
			line2 = bufferedreader2.readLine();
		}

		HTMLdisplay = HTMLtable + HTMLvalues + "</table></div>" + HTMLtable2 + HTMLvalues2
				+ "</table></div>" + "<h4>Total Program Cs: " + TotCS + "</h4>" + "<h4>Total Program Ci: " + TotCI + "</h4>" + "<h4>Total Program Ctc: " + TotCTC + "</h4>" + "<h4>Total Program Cnc: " + TotCNC + "</h4>"
				+ "<h3>Total Program Complexity: " + TotCPS + "</h3></div></body></html>";
		
		bufferedreader.close();
		bufferedreader2.close();
		
		HTMLwriter.write(HTMLdisplay);
		HTMLwriter.close();

		System.out.println("Total Complexity: " + TotCPS);
		System.out.println("FROM Line: " + Cr.CrStartList);
		System.out.println("UP TO Line: " + Cr.CrEndList);


	}

}