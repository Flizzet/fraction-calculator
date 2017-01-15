package com.flizzet.gui;

import com.flizzet.math.Calculator;
import com.flizzet.utils.FloatUtils;
import com.flizzet.utils.StringUtils;

/**
 * Splits up Input for organization. Does input calculations upon
 * equal being pressed.
 * </br></br>
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also	 Input
 */
public class InputFunctions {

    public InputFunctions() {
    }
    
    /** Runs when equals button is pressed. Checks input to set up calculation. */
    public String calculate(String text) {
	
	String[] comparedNumbers;
	float result = 0;
	String resultString = "";
	
	if (text.contains("+")) {					/* Addition */
	    comparedNumbers = text.split("\\+");
	    if (checkError(comparedNumbers)) {
		resultString = "Error!";
	    } else {
		result = Calculator.add(FloatUtils.valueOf(comparedNumbers[0]), FloatUtils.valueOf(comparedNumbers[1]));
		result = Calculator.toSigFigs(result, 10);
	    }
	} else if (text.contains("-")) {				/* Subtraction */
	    comparedNumbers = text.split("\\-");
	    if (checkError(comparedNumbers)) {
		resultString = "Error!";
	    } else {
		result = Calculator.subtract(FloatUtils.valueOf(comparedNumbers[0]), FloatUtils.valueOf(comparedNumbers[1]));
		result = Calculator.toSigFigs(result, 10);
	    }
	} else if (text.contains("/")) {				/* Division */
	    
	    comparedNumbers = text.split("\\/");
	    if (checkError(comparedNumbers) || comparedNumbers[1].equals("0")) {
		resultString = "Error!";					/* Prevent division by 0 */
	    } else {
		result = Calculator.divide(FloatUtils.valueOf(comparedNumbers[0]), FloatUtils.valueOf(comparedNumbers[1]));
		result = Calculator.toSigFigs(result, 10);
	    }
	} else if (text.contains("*")) {				/* Multiplication */
	    comparedNumbers = text.split("\\*");
	    if (checkError(comparedNumbers)) {
		resultString = "Error!";
	    } else {
		result = Calculator.multiply(FloatUtils.valueOf(comparedNumbers[0]), FloatUtils.valueOf(comparedNumbers[1]));
		result = Calculator.toSigFigs(result, 10);
	    }
	}
	
	if(!resultString.equals("Error!")) {
	    resultString = removeExcessDecimal(StringUtils.valueOf(result));
	}
	return resultString;
    }
    
    /** Checks if no second or first value was entered */
    private boolean checkError(String[] comparedNumbers) {
	if(comparedNumbers.length == 2 && !comparedNumbers[0].equals("")) {
	    return false;
	}
	return true;
    }
    
    private String removeExcessDecimal(String result) {
	if (result.split("\\.").length > 0) {
	    if (result.split("\\.")[1].equals("0")) {
		System.out.println(result.split("\\.")[0].toString());
		return result.split("\\.")[0].toString();
	    }
	}
	return result;
    }

}
