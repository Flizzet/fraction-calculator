										package com.flizzet.utils;

import com.flizzet.math.Fraction;

/**
 * Useful functions for fraction conversion.
 * </br></br>
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also 	 Fraction
 */
public class FractionUtils {

    /** Suppress constructor to prevent noninstantiability */
    public FractionUtils() {
	throw new AssertionError();
    }
    
    public static float toDecimal(Fraction fraction) {
	return (float) fraction.getNumerator() / (float) fraction.getDenominator();
    }
    
    /** Finding greatest common denominator */
    public static Fraction[] findCommonDenominator(Fraction firstFraction, Fraction secondFraction) {
	Fraction[] result;
	int newDenominator = 0;
	int newLeftNumerator = 0;
	int newRightNumerator = 0;
	
	newDenominator = firstFraction.getDenominator() * secondFraction.getDenominator();
	newLeftNumerator = firstFraction.getNumerator() * secondFraction.getDenominator();
	newRightNumerator = secondFraction.getNumerator() * firstFraction.getDenominator();
	
	result = new Fraction[] { new Fraction(newLeftNumerator, newDenominator),
				new Fraction(newRightNumerator, newDenominator) };
	
	return result;
    }

    /** Euclid algorithm for finding greatest common denominator */
    private static int gcd(int p, int q) {
	int tmp;
	while (q != 0) {
	    tmp = q;
	    q = p % q;
	    p = tmp;
	}
	return p;
    }

    /** Simplifies fraction */
    public static Fraction reduce(Fraction newFraction) {
	int tmp = 1;
	Fraction frac = newFraction;
	int numerator = frac.getNumerator();
	int denominator = frac.getDenominator();
	if (numerator < 0) {
	    tmp = -1;
	    denominator *= -1;
	}

	int gcd_value = gcd(numerator, denominator);
	frac.setNumerator(frac.getNumerator() / gcd_value);
	frac.setDenominator(frac.getDenominator() / gcd_value);
	frac.setNumerator(frac.getNumerator() * tmp);
	
	return frac;
    }
    
}
