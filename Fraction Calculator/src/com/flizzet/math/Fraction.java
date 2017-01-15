package com.flizzet.math;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.flizzet.utils.FontUtils;
import com.flizzet.utils.StringUtils;

/**
 * Carries all properties of a fraction
 *
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 FractionUtils
 */
public class Fraction {

    private int x, y;
    private int rectX, rectY;
    private int rectYOffset = -27;
    private int rectWidth, rectHeight;
    private int textSpacing = 7;
    private int numerator, denominator; 
    private int numeratorX, numeratorY;
    private int denominatorX, denominatorY;
    private int yOffset = -30;
    private int fractionLength;
    
    /** Constructor. 
     * @params numerator: Upper number; denominator: Lower number */
    public Fraction(int numerator, int denominator) {
	this.numerator = numerator;
	this.denominator = denominator;
    }
    
    public void paint(Graphics g) {
	
	Graphics2D g2d = (Graphics2D) g.create();
	
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2d.setFont(FontUtils.getFractionFont());
	FontMetrics fm = g2d.getFontMetrics();
	String numeratorText = StringUtils.valueOf(numerator);
	String denominatorText = StringUtils.valueOf(denominator);
	
	rectHeight = 1;								/* Setting rect dimensions based on which is wider */
	if(fm.stringWidth(numeratorText) > fm.stringWidth(denominatorText)) {
	    rectWidth = (int) (fm.stringWidth(numeratorText));
	} else {
	    rectWidth = (int) (fm.stringWidth(denominatorText));
	}
	
	numeratorX = x;								/* Positioning */
	numeratorY = y + yOffset;
	denominatorX = x;
	denominatorY = y + yOffset + (fm.getAscent() + textSpacing );
	rectX = x;
	rectY = y + fm.getAscent() + yOffset + rectYOffset;
	
	g2d.setColor(Color.WHITE);
	g2d.drawString(numeratorText, numeratorX, numeratorY);			/* Drawing numerator */
	g2d.fillRect(rectX, rectY, rectWidth, rectHeight);			/* Drawing fraction line */
	g2d.drawString(denominatorText, denominatorX, denominatorY);		/* Drawing denominator */
	
    }
    
    public int getNumerator() { return numerator; }
    public int getDenominator() { return denominator; }
    public void setNumerator(int newNumerator) { this.numerator = newNumerator; }
    public void setDenominator(int newDenominator) { this.denominator = newDenominator; }
    
    public int getLength() {
	if(StringUtils.valueOf(numerator).length() > StringUtils.valueOf(denominator).length()) {
	    fractionLength = StringUtils.valueOf(numerator).length();
	} else {
	    fractionLength =  StringUtils.valueOf(denominator).length();
	}
	return fractionLength;
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setX(int newX) { this.x = newX; }
    public void setY(int newY) { this.y = newY; }
    

}
