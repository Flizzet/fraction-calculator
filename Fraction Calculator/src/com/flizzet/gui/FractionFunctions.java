package com.flizzet.gui;

import com.flizzet.math.Calculator;
import com.flizzet.math.Fraction;

/**
 * Calls mathematical calculations on fractions
 * </br></br>
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also 	 Calculator
 */
public class FractionFunctions {

    /** Intended single use constructor */
    public FractionFunctions() {
    }
    
    public void calculate(Fraction firstFraction, Fraction secondFraction, String operator) {
	if (operator.equals("+")) {
	    Fraction answer = Calculator.add(firstFraction, secondFraction);
	    Menu.getInstance().getInputLine().addFractionResult(answer);
	} else if (operator.equals("-")) {
	    Fraction answer = Calculator.subtract(firstFraction, secondFraction);
	    Menu.getInstance().getInputLine().addFractionResult(answer);
	} else if (operator.equals("/")) {
	    Fraction answer = Calculator.divide(firstFraction, secondFraction);
	    Menu.getInstance().getInputLine().addFractionResult(answer);
	} else if (operator.equals("*")) {
	    Fraction answer = Calculator.multiply(firstFraction, secondFraction);
	    Menu.getInstance().getInputLine().addFractionResult(answer);
	}
    }

}