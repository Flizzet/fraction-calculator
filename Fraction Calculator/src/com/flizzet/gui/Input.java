package com.flizzet.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JButton;

import com.flizzet.main.Main;
import com.flizzet.math.EquationConverter;
import com.flizzet.math.Fraction;
import com.flizzet.utils.FontUtils;
import com.flizzet.utils.Separator;

/**
 * Shows user-input at the top of the application.
 * </br></br>
 * Bugs: 250 lines ugh
 * </br>
 * Requires: InputFunctions
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 Menu, InputFunctions
 */
public class Input {
    
    private InputFunctions inputFunctions = new InputFunctions();
    private FractionInput fractionInput;
    private FractionFunctions fractionFunctions = new FractionFunctions();
    private static final int MAX_INPUT_CHARACTERS = 10;
    private int x = 10;
    private int y = 10;
    private int width = Main.getWidth() - 20;
    private int height = 100;
    private int textXOffset = 15;
    private int textYOffset = 5;
    private int focus = 0; // 0: Upper input line, 1: Bottom fraction input line
    private String text = "";
    private String subtext = "";
    private boolean loading = false;
    private int textLength;
    private int textDrop;
    private boolean equalsPressed = false;
    private boolean operatorAdded = false;
    private boolean fractionAdded = false;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<Fraction> fractions = new ArrayList<Fraction>();
    private ArrayList<Fraction> fractionResult = new ArrayList<Fraction>();
    private Separator operatorSeparator;

    /** Constructor. Instantiable */
    public Input() {
	this.text = "";
    }
    
    /** Add text to the input line */
    public void add(JButton button) {
	String buttonText = button.getText();
	
	if (focus == 0) {					/* If focus is on upper input */
	    if (equalsPressed) {
		clear();
		equalsPressed = false;
	    }

	    if (text.length() <= MAX_INPUT_CHARACTERS) { 	/* Limiting characters */

		switch (buttonText) {
		case "Frac":
		    addFractionInput();
		    button.setText("Insert");
		    break;
		case "Clear":
		    clear();
		    break;
		case "=":
		    if (!text.equals(null) || !text.equals("")) {
			equalsPressed = true;
			if (fractions.size() == 0) {
			    text = inputFunctions.calculate(this.text);
			} else {
			    EquationConverter.buildFractionEquation(text, fractions, operatorSeparator);
			    removeAllFractions();
			}
			removeAllFractions();
			operatorAdded = false;
		    } else {
			addError();
		    }
		    break;
		case "+": 					/* Fall through intended, limiting operators */
		case "-":
		case "/":
		case "*":
		    if (!operatorAdded) {
			operatorAdded = true;
			fractionAdded = false;
			int sepx = x + textXOffset + textLength;
			int sepy = y + textYOffset;
			addSeparator(sepx, sepy);
			enableFracButton();
			text = text + buttonText;
		    }
		    break;
		case "Batch":
		    BatchBuilder.getInstance().buildWindow();
		    BatchBuilder.getInstance().buildElements();
		    Main.addBatchThread();
		    break;
		default:
		    if(!fractionAdded) {
			text = text + buttonText;
			if (equalsPressed) {
			    clear();
			}
		    }
		}
	    } else {
		if (button.getText().equals("Clear")) {
		    text = "";
		    equalsPressed = false;
		    operatorAdded = false;
		} else if (button.getText().equals("=")) {
		    equalsPressed = true;
		    if (fractions.size() == 0) {
			inputFunctions.calculate(this.text);
		    } else {
			EquationConverter.buildFractionEquation(text, fractions, operatorSeparator);
		    }
		    operatorAdded = false;
		} else if (equalsPressed) {
		    text = "";
		    equalsPressed = false;
		    add(button);
		}
	    }
	} else if (focus == 1) {
	    
	    switch(buttonText) {
	    case "=":
	    case "+":
	    case "-":
	    case "/":
	    case "*":
		break;
	    case "Insert":
		
		fractionInput.addFraction();
		
		break;
	    case "Clear":
		fractionInput.getInputHolder().clear();
		break;
	    default:
		fractionInput.getInputHolder().add(buttonText);
	    }
	}
    }
    
    /** Draw text and input background */
    public void paint(Graphics g) {
	
	Graphics2D g2d = (Graphics2D) g.create();
	
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2d.setFont(FontUtils.getDefaultFont().deriveFont(60.0f));
	
	g2d.setColor(Color.GRAY);							/* Drawing background */
	g2d.fillRect(x, y, width, height);
	
	FontMetrics fm = g2d.getFontMetrics();				
	textDrop = fm.getAscent();
	g2d.setColor(Color.WHITE);
	
	if(loading) {
	    g2d.drawString(subtext, x + textXOffset, y + fm.getAscent() + textYOffset);
	} else {
	    g2d.drawString(text, x + textXOffset,  y + fm.getAscent() + textYOffset);	/* Drawing equation */
	}
	
	if(fractionInput != null) {			/* Drawing fraction input */
	    fractionInput.paint(g);
	}
	
	textLength = fm.stringWidth(text);		/* Adding fraction to length */
	for (Fraction f : fractions) {
	    textLength += f.getLength();
	    f.paint(g);					/* Drawing fractions */
	}
	
	for (Fraction f : fractionResult) {
	    f.paint(g);					/* Drawing fraction result */
	}
	
    }
    
    public void clear() {
	this.text = "";
	fractionAdded = false;
	operatorAdded = false;
	operatorSeparator = null;
	enableFracButton();
	removeAllFractions();
	removeFractionResults();
    }
    
    public void removeAllFractions() {
	for (int i = 0; i < fractions.size(); i++) {
	    fractions.remove(fractions.get(i));
	}
    }
    
    public void removeFractionResults() {
	for (int i = 0; i < fractionResult.size(); i++) {
	    fractionResult.remove(fractionResult.get(i));
	}
    }
    
    public void addFractionInput() {
	fractionInput = new FractionInput();
	focus = 1;
	disableBatchButton();
    }
    
    public void enableBatchButton() {
	for (JButton e : buttons) {
	    if(e.getText().equals("Batch")) {
		e.setEnabled(true);
		e.setVisible(true);
	    }
	}
    }
    
    public void disableBatchButton() {
	for (JButton e : buttons) {
	    if(e.getText().equals("Batch")) {
		e.setEnabled(false);
		e.setVisible(false);
	    }
	}
    }
    
    public void addFractionResult(Fraction newFraction) {
	newFraction.setX(x + textXOffset);
	newFraction.setY(y + textDrop + textYOffset);
	fractionResult.add(newFraction);
    }
    
    public FractionInput getFractionInput() {
	return fractionInput;
    }
    
    public void removeFractionInput() {
	this.fractionInput = null;
	this.focus = 0;
	enableBatchButton();
	for (JButton e : buttons) {
	    if(e.getText().equals("Insert")) {
		e.setText("Frac");
	    }
	}
    }
    
    public void addToFractions(Fraction newFraction) {
	
	if(!fractionAdded) {
	    newFraction.setX(x + textXOffset + textLength + 5);
	    newFraction.setY(y + textDrop + textYOffset);
	    fractionAdded = true;
	    fractions.add(newFraction);
	    disableFracButton();
	    for (int i = 0; i < newFraction.getLength(); i++) {
		text = text + "  ";
	    }
	}
    }
    
    public void addError() {			/* Adds "Error!" to input line */
	clear();
	text = "Error!";
	equalsPressed = true;
    }
    
    public void disableFracButton() {
	for (JButton e : buttons) {
	    if(e.getText().equals("Insert") || e.getText().equals("Frac")) {
		e.setEnabled(false);
		e.setVisible(false);
	    }
	}
    }
    
    public void enableFracButton() {
	for (JButton e : buttons) {
	    if(e.getText().equals("Insert") || e.getText().equals("Frac")) {
		e.setEnabled(true);
		e.setVisible(true);
	    }
	}
    }
    
    public void addSeparator(int x, int y) { 
	operatorSeparator = new Separator();
	operatorSeparator.setX(x);
	operatorSeparator.setY(y);
    };
    
    public InputFunctions getFunctions() {
	return inputFunctions;
    }
    
    public FractionFunctions getFractionFunctions() {
	return fractionFunctions;
    }
    
    public void setText(String newText) {
	this.text = newText;
    }
    
    public void setSubText(String newSubText) {
	this.subtext = newSubText;
    }
    
    public void setLoading(boolean isLoading) {
	this.loading = isLoading;
    }

    public void setFocus(int newFocus) {
	this.focus = newFocus;
    }

    public void setButtons(ArrayList<JButton> buttons) {
	this.buttons = buttons;
    }
}
