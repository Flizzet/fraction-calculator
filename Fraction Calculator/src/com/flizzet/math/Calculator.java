package com.flizzet.math;

import java.math.BigDecimal;
import java.math.MathContext;

import com.flizzet.utils.FractionUtils;

/**
 * Performs mathematical calculations on both fractions and
 * integers.
 * </br></br>
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also 	 Fraction
 */
public class Calculator {

    /** Suppress constructor for noninstantiability */
    private Calculator() {
	throw new AssertionError();
    }

    /** Add one fraction to another */
    public static Fraction add(Fraction firstFraction, Fraction secondFraction) {
	Fraction[] fractions = FractionUtils.findCommonDenominator(firstFraction, secondFraction);
	int resultNumerator = fractions[0].getNumerator() + fractions[1].getNumerator();
	int resultDenominator = fractions[0].getDenominator();		/* Both are the same */
	
	return FractionUtils.reduce(new Fraction(resultNumerator, resultDenominator));
    }

    /** Add one integer to another */
    public static float add(float firstFloat, float secondFloat) {
	return firstFloat + secondFloat;
    }
    
    /** Subtract one fraction from another */
    public static Fraction subtract(Fraction firstFraction, Fraction secondFraction) {
	Fraction[] fractions = FractionUtils.findCommonDenominator(firstFraction, secondFraction);
	int resultNumerator = fractions[0].getNumerator() - fractions[1].getNumerator();
	int resultDenominator = fractions[0].getDenominator();		/* Both are the same */
	
	return FractionUtils.reduce(new Fraction(resultNumerator, resultDenominator));
    }
    
    /** Subtract one integer from another */
    public static float subtract(float firstFloat, float secondFloat) {
	return firstFloat - secondFloat;
    }
    
    /** Divide one fraction by another using the reciprocal formula */
    public static Fraction divide(Fraction firstFraction, Fraction secondFraction) {
	int secondNewNumerator = secondFraction.getDenominator();
	int secondNewDenominator = secondFraction.getNumerator();
	secondFraction.setNumerator(secondNewNumerator);
	secondFraction.setDenominator(secondNewDenominator);
	
	return multiply(firstFraction, secondFraction);
    }
    
    /** Divide one integer by another */
    public static float divide(float firstFloat, float secondFloat) {
	return (firstFloat / secondFloat);
    }
    
    /** Multiply one fraction by another */
    public static Fraction multiply(Fraction firstFraction, Fraction secondFraction) {
	Fraction result;
	int answerNumerator = firstFraction.getNumerator() * secondFraction.getNumerator();
	int answerDenominator = firstFraction.getDenominator() * secondFraction.getDenominator();
	result = FractionUtils.reduce(new Fraction(answerNumerator, answerDenominator));
	
	return result;
    }
    
    /** Multiply one integer by another */
    public static float multiply(float firstInt, float secondInt) {
	return firstInt * secondInt;
    }
    
    /** Returns a number to it's three significant figures. */
    public static float toSigFigs(float value, int sigFigs) {
	BigDecimal bd = new BigDecimal(value);
	bd = bd.round(new MathContext( sigFigs));
	float rounded = bd.floatValue();
	return rounded;
    }

}
