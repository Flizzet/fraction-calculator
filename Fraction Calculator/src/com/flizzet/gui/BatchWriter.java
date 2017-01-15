package com.flizzet.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.flizzet.math.Calculator;
import com.flizzet.math.Fraction;
import com.flizzet.utils.IntUtils;
import com.flizzet.utils.StringUtils;

public class BatchWriter {
    
    private static Scanner scanner;
    private static String path = "output/";
    private static ArrayList<String> equations = new ArrayList<String>();
    private static String[] operators = new String[] {
	    "+", "-", "/", "*"
    };

    /** Suppress default constructor for noninstantiability */
    private BatchWriter() {
	throw new AssertionError();
    }
    
    /** Goes through and turns the text into an equation */
    public static void convertToEquation(String fileName, String allText) {
	
	equations.removeAll(equations);				/* Clear equations array */
	scanner = new Scanner(allText);				/* String reader */
	
	BatchBuilder.getInstance().writeLine("\n" + "-------------------------");
	
	while (scanner.hasNextLine()) {				/* Add to equations list */
	    String line = scanner.nextLine();

	    equations.add(line);
	}
	
	for (String e : equations) {				/* Calculating each equation */
	    
	    writeAnswer(e, buildAndCalculate(e), fileName);	/* Writing each answer */
	    
	}
		
    }
    
    private static Fraction buildAndCalculate(String equation) {
	
	Fraction result = null;
	String[] sections = new String[2];
	
	equation = equation.replaceAll("\\s+","");; 		/* Remove all whitespace */
	
	for (String s : operators) {
	    if(equation.contains(s)) {
		sections = equation.split("\\" + s);		/* Split at operator */
		if (sections[0].contains("/") && sections[1].contains("/")) {
		    
		    sections[0] = sections[0].replaceAll("\\(", "");
		    sections[0] = sections[0].replaceAll("\\)", "");
		    sections[1] = sections[1].replaceAll("\\(", "");
		    sections[1] = sections[1].replaceAll("\\)", "");
		    
		    System.out.println("Made it");
		    String[] firstFraction = sections[0].split("\\/");
		    String[] secondFraction = sections[1].split("\\/");
		    switch(s) {
		    case "+":
			result = Calculator.add(new Fraction(IntUtils.valueOf(firstFraction[0]), IntUtils.valueOf(firstFraction[1])),
				new Fraction(IntUtils.valueOf(secondFraction[0]), IntUtils.valueOf(secondFraction[1])));
			break;
		    case "-":
			result = Calculator.subtract(new Fraction(IntUtils.valueOf(firstFraction[0]), IntUtils.valueOf(firstFraction[1])),
				new Fraction(IntUtils.valueOf(secondFraction[0]), IntUtils.valueOf(secondFraction[1])));
			break;
		    case "/":
			result = Calculator.divide(new Fraction(IntUtils.valueOf(firstFraction[0]), IntUtils.valueOf(firstFraction[1])),
				new Fraction(IntUtils.valueOf(secondFraction[0]), IntUtils.valueOf(secondFraction[1])));
			break;
		    case "*":
			result = Calculator.multiply(new Fraction(IntUtils.valueOf(firstFraction[0]), IntUtils.valueOf(firstFraction[1])),
				new Fraction(IntUtils.valueOf(secondFraction[0]), IntUtils.valueOf(secondFraction[1])));
			break;
		    }
		}
	    }
	}
	
	return result;
	
    }
    
    /** Writes the answers in the place of the equation */
    public static void writeAnswer(String equation, Fraction fraction, String fileName) {
	String newLine = "\n" + equation + " = " + "(" + StringUtils.valueOf(fraction.getNumerator()) + "/" +
		StringUtils.valueOf(fraction.getDenominator()) + ")";
	BatchBuilder.getInstance().writeLine(newLine);
    }
    
    /** Creates and writes to the output file */
    public static void write(String fileName, String allText) {
	try {			 /* Create the file */
	    PrintWriter writer = new PrintWriter(path + fileName, "UTF-8");
	    writer.print(allText);
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
