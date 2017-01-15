package com.flizzet.utils;

/**
 * Converts values into Integers.
 * </br></br>
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version	 1.0
 */
public class IntUtils {
    
    /** Suppress default constructor for noninstantiability. */
    private IntUtils() {
	throw new AssertionError();
    }
    
    public static int valueOf(String value) {
	value = value.replaceAll("\\s+","");	/* Remove whitespace */
	return Integer.parseInt(value);
    }

}
