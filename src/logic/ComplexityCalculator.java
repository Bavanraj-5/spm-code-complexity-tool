package logic;

import java.io.IOException;

public class ComplexityCalculator {

	public static void main(String[] args) throws IOException {
		
		String fileLocation;
		fileLocation = "D:\\Eclipse Workspace\\SPM Final\\src\\TestFiles\\FibonacciMain.java";

		SizeComplex Cs = new SizeComplex(fileLocation);
		
		
		//Inheritance c1=new Inheritance();
		Inheritance Ci = new Inheritance(fileLocation);
		

			Cs.checkSizeComplexity();
		//Cs.checkSizeComplexity();
		
		//Ci.CheckInheritance();
//		Ctc.CheckControlStructures();
		
		/*Check complexity due to Control Structures*/
		ControlStructureComplex Csc = new ControlStructureComplex(fileLocation);
		//Csc.calcControlStructureComplexity();

	}

}