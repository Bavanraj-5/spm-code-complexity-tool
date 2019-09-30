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

	public static final Logger LOGGER = Logger.getLogger(SizeComplex.class.getName()); // for logger
	int complexity = 0;
	int totalcomplexity = 0;
	public int FinalSize = 0;

	SizeComplex(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	String line = null;// holds the line which is read from the file
	String fileLocation;
	public Boolean anyHits = false;
	int lineNumber = 1;
	int complexitySize;
	String words = "";
	List<String> variables = new ArrayList<String>();
	List<String> foundTokens = new ArrayList<String>();
	ArrayList<SizeReturns> returns = new ArrayList<SizeReturns>();


	public void checkSizeComplexity(String line) {

		try {

			// pattern for text within double quotes
			Pattern pattern = Pattern.compile("\"([^\"]*)\"");

			complexitySize = 0;

			StringTokenizer stringTokenizer = new StringTokenizer(line);

			while (stringTokenizer.hasMoreTokens()) {
				words = stringTokenizer.nextToken();

				Matcher matcher = pattern.matcher(words);

				if (line.contains("import")) {
					break;
				}

				if (line.contains("package")) {
					break;
				}

				while (matcher.find()) {
					complexitySize++; // complexity of text inside a pair of double quotes
					anyHits = true;
				}

				for (int arithmetic = 0; arithmetic < SizeContstants.ARITHMETIC_OPERATORS.length; arithmetic++) {

					int occurence_count = StringUtils.countMatches(words,
							SizeContstants.ARITHMETIC_OPERATORS[arithmetic]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int relation = 0; relation < SizeContstants.RELATION_OPERATORS.length; relation++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.RELATION_OPERATORS[relation]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int logical = 0; logical < SizeContstants.LOGICAL_OPERATORS.length; logical++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.LOGICAL_OPERATORS[logical]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int bitwise = 0; bitwise < SizeContstants.BITWISE_OPERATORS.length; bitwise++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.BITWISE_OPERATORS[bitwise]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int misc = 0; misc < SizeContstants.MISC_OPERATORS.length; misc++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.MISC_OPERATORS[misc]);
					if (occurence_count > 0) {
						complexitySize = complexitySize + occurence_count;
						anyHits = true;
					}

				}

				for (int assignment = 0; assignment < SizeContstants.ASSIGNMENT_OPERATORS.length; assignment++) {

					int occurence_count = StringUtils.countMatches(words,
							SizeContstants.ASSIGNMENT_OPERATORS[assignment]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int keywords = 0; keywords < SizeContstants.POSSIBLE_KEYWORDS.length; keywords++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.POSSIBLE_KEYWORDS[keywords]);
					complexitySize = complexitySize + occurence_count;

					anyHits = true;

				}

				for (int keywords = 0; keywords < SizeContstants.COMMON_CLASSES.length; keywords++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.COMMON_CLASSES[keywords]);
					complexitySize = complexitySize + occurence_count;

					// variables
					if (stringTokenizer.hasMoreElements()
							&& words.trim().equals(SizeContstants.COMMON_CLASSES[keywords])) {

						String words2 = stringTokenizer.nextToken().toString();
						if ((!StringUtils.containsAny(words2, "{")) || (!StringUtils.containsAny(words2, "("))
								|| (!StringUtils.containsAny(words2, ".")))
							complexitySize++;
						addVariables(words2);

					}
					anyHits = true;

				}

				for (int manipulators = 0; manipulators < SizeContstants.MANIPULATORS.length; manipulators++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.MANIPULATORS[manipulators]);
					complexitySize = complexitySize + occurence_count;
					anyHits = true;

				}

				for (int manipulators = 0; manipulators < SizeContstants.EXCEPTIONS.length; manipulators++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.EXCEPTIONS[manipulators]);
					complexitySize = complexitySize + occurence_count;

					anyHits = true;

				}

				for (int manipulators = 0; manipulators < SizeContstants.TWO_KEYWORDS.length; manipulators++) {

					int occurence_count = StringUtils.countMatches(words, SizeContstants.TWO_KEYWORDS[manipulators]);
					complexitySize = complexitySize + (occurence_count * 2);
					anyHits = true;

				}

//					if (line.contains("//")) {
//						break;
//					}

				// for variables and methods
				for (String elem : variables) {
					if (StringUtils.contains(words, elem)) {
						complexitySize++;
						break;
					}
				}

			}

			if (line.contains("();")) {
				complexitySize++;
			}

			String[] athal = line.split("");

			for (String singithi : athal) {
				if (singithi.contains("\"")) {
					break;
				} else if (StringUtils.isNumeric(singithi)) {
					complexitySize++;
				}
			}


			if (anyHits) {
				returns.add(new SizeReturns(lineNumber, complexitySize));
				FinalSize = complexitySize;
			}

			lineNumber++;
		

		} catch (Exception e) {
			LOGGER.info(e.toString());
		}


	}

	public void addVariables(String val) {
		this.variables.add(val);
	}

}
