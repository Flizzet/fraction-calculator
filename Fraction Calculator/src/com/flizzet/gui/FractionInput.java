package com.flizzet.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.flizzet.buttons.FractionInputCloseButton;
import com.flizzet.buttons.FractionInputMoveButton;
import com.flizzet.main.Main;
import com.flizzet.math.Fraction;
import com.flizzet.utils.FontUtils;

/**
 * Gets input and turns it into fractions.
 * </br>
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 Fraction
 */
public class FractionInput {
    
    private int x = 10;
    private int y = 570;
    private int width = 340;
    private int height = 100;
    private FractionInputCloseButton closeButton = new FractionInputCloseButton(Main.getFrame());
    private FractionInputMoveButton moveButton = new FractionInputMoveButton(Main.getFrame());
    private FractionInputHolder inputHolder = new FractionInputHolder();
    private int closeButtonMargin = 4;
    private int moveUpButtonMargin = 50;

    public FractionInput() {
	closeButton.setX(this.x + this.width - (closeButton.getWidth() / 2) - closeButtonMargin);
	closeButton.setY(this.y + closeButtonMargin + (closeButton.getHeight() / 2));
	
	moveButton.setX(this.x + this.width - moveButton.getWidth() - moveUpButtonMargin);
	moveButton.setY(this.y + closeButtonMargin + (closeButton.getHeight() / 2));
	
	inputHolder.setX(this.x + 175);
	inputHolder.setY(this.y + 30);
	
    }
    
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g.create();
	g2d.setFont(FontUtils.getDefaultFont().deriveFont(30.0f));
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	FontMetrics fm = g2d.getFontMetrics();
	
	g2d.setColor(Color.GRAY);
	g2d.fillRect(x, y, width, height);
	
	g2d.setColor(Color.WHITE);
	g2d.drawString("New", x + 13, y + fm.getAscent() + 10);
	g2d.drawString("Fraction", x + 13, y + fm.getAscent() * 2 + 10);

	inputHolder.paint(g);
	closeButton.paint(g);
	moveButton.paint(g);
	
    }
    
    public void remove() {
	closeButton.removeThis();
	moveButton.removeThis();
	Menu.getInstance().getInputLine().removeFractionInput();
    }
    
    public FractionInputHolder getInputHolder() {
	return inputHolder;
    }

    public void addFraction() {
	if (inputHolder.getDenominatorText().equals(null) || inputHolder.getNumeratorText().equals(null)
		|| inputHolder.getDenominatorText().equals("") || inputHolder.getNumeratorText().equals("")) {
	    Menu.getInstance().getInputLine().addError();
	} else {
	    Fraction newFraction = new Fraction(inputHolder.getNumeratorNum(), inputHolder.getDenominatorNum()); 
	    Menu.getInstance().getInputLine().addToFractions(newFraction);
	}
	Menu.getInstance().getInputLine().getFractionInput().remove();
    }

}
