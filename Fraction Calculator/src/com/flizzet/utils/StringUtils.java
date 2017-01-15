package com.flizzet.utils;

/**
 * Converts values into Strings.
 *
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 */
public class StringUtils {

    /** Suppress default constructor for noninstantiability. */
    private StringUtils() {
	throw new AssertionError();
    }

    /** Converts an integer to a String */
    public static String valueOf(int value) {
	return Integer.toString(value);
    }
    
    /** Converts a double to a String */
    public static String valueOf(double value) {
	return Double.toString(value);
    }
    
    /** Converts a float to a String */
    public static String valueOf(float value) {
	return Float.toString(value);
    }

}
