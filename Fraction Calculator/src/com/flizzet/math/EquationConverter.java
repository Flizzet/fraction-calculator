package com.flizzet.math;

import java.util.ArrayList;

import com.flizzet.gui.Menu;
import com.flizzet.utils.FractionUtils;
import com.flizzet.utils.IntUtils;
import com.flizzet.utils.Separator;

/**
 * Turns mixed numbers into single numbers for quicker calculation
 * </br></br>
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 FractionUtils
 */
public class EquationConverter {
    
    private static String[] operators = new String[] {
	    "+", "-", "/", "*"
    };

    /** Suppress default constructor for noninstantiability */
    private EquationConverter() {
	throw new AssertionError();
    }
    
    /** Converts a mixed number equation to a real equation */
    public static void buildFractionEquation(String text, ArrayList<Fraction> fractions, Separator separator) {
	
	// WHOLE NUMBERS 
	text.trim(); 					/* Trim the text to remove whitespace */
	
	String operator = "";
	for (String s : operators) {			/* Finding which operator is being used in the text */
	    if (text.contains(s)) {
		operator = s;
	    }
	}
	
	String[] splitWholesStr = text.split("\\" + operator);	
	if (splitWholesStr[0].trim().equals("")) {	/* Make certain that a number is in the String for conversion */
	    splitWholesStr[0] = "0";
	}
	if (splitWholesStr[1].trim().equals("")) {
	    splitWholesStr[1] = "0";
	}
	System.out.println("1: " + splitWholesStr[0] + " 2: " + splitWholesStr[1]);
	int[] splitWholes = new int[] { IntUtils.valueOf(splitWholesStr[0]), IntUtils.valueOf(splitWholesStr[1]) };
	
	// FRACTIONS
	Fraction leftFraction = null;			/* Holders */
	Fraction rightFraction = null;
	
	for (Fraction f : fractions) {
	    if (f.getX() < separator.getX()) {
		leftFraction = f;
	    } else if (f.getX() > separator.getX()) {
		rightFraction = f;
	    }
	}
	
	// COMBINATION
	float leftSide = (float) splitWholes[0];
	float rightSide = (float) splitWholes[1];
	float newLeftNumerator, newLeftDenominator;
	float newRightNumerator, newRightDenominator;
	if (leftFraction != null) {
	    newLeftNumerator = leftFraction.getNumerator() + (leftFraction.getDenominator() * leftSide);
	    newLeftDenominator = leftFraction.getDenominator();
	} else {
	    newLeftNumerator = leftSide;
	    newLeftDenominator = 1;
	}
	if (rightFraction != null) {
	    newRightNumerator = rightFraction.getNumerator() + (rightFraction.getDenominator() * rightSide);
	    newRightDenominator = rightFraction.getDenominator();
	} else {
	    newRightNumerator = rightSide;
	    newRightDenominator = 1;
	}
	Fraction newLeftFraction = new Fraction((int) newLeftNumerator, (int) newLeftDenominator);
	Fraction newRightFraction = new Fraction((int) newRightNumerator, (int) newRightDenominator);
	
	Menu.getInstance().getInputLine().clear();
	Menu.getInstance().getInputLine().getFractionFunctions().calculate(newLeftFraction, newRightFraction, operator);
	
//	if (leftFraction != null) {
//	    leftSide = combine(splitWholes[0], leftFraction);
//	}
//	if (rightFraction != null) {
//	    rightSide = combine(splitWholes[1], rightFraction);
//	}
//	
//	// CALCULATION
//	String newEquation = StringUtils.valueOf(leftSide) + operator + StringUtils.valueOf(rightSide);
//	result = FloatUtils.valueOf(Menu.getInstance().getInputLine().getFunctions().calculate(newEquation));
//	fractionResult = new Fraction(FractionUtils.toFraction(result)[0],FractionUtils.toFraction(result)[1]);
//	Menu.getInstance().getInputLine().addFractionResult(fractionResult);
    }
    
    /** Combines a whole number with a fraction and returns a float */
    public static float combine(int wholeNumber, Fraction fraction) {
	float result = 0f;
	float fracDecimal = FractionUtils.toDecimal(fraction);
	result = wholeNumber + fracDecimal;
	
	return result;
    }

}
