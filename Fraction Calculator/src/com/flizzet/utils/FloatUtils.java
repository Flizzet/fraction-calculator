package com.flizzet.utils;

/**
 * Converts values into Floats.
 * </br></br>
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version	 1.0
 */
public class FloatUtils {

    /** Suppress default constructor for noninstantiability */
    public FloatUtils() {
	throw new AssertionError();
    }

    public static float valueOf(String value) {
	value = value.replaceAll("\\s+", "");
	return Float.parseFloat(value);
    }
    
}
