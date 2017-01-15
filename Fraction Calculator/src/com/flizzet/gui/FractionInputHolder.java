package com.flizzet.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.flizzet.utils.FontUtils;
import com.flizzet.utils.IntUtils;

public class FractionInputHolder {
    
    private int x;
    private int y;
    private int position = 1;			/* 1 or 2 = top or bottom respectively */
    private String numeratorText = "";
    private String denominatorText = "";
    private FractionSelectionIndicator indicator = new FractionSelectionIndicator();
    
    private int lineX, lineY, lineWidth = 30, lineHeight = 2;
    private int lineMargin = 3;

    public FractionInputHolder() {
	
    }
    
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g.create();
	g2d.setFont(FontUtils.getDefaultFont().deriveFont(15.0f));
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	FontMetrics fm = g2d.getFontMetrics();
	
	lineX = x;
	lineY = y + fm.getHeight();
	
	g2d.setColor(Color.WHITE);
	g2d.drawString(numeratorText, x + (lineWidth / 2) - fm.stringWidth(numeratorText) / 2,
		y + fm.getAscent());									/* Numerator */
	g2d.fillRect(lineX, lineY, lineWidth, lineHeight);
	g2d.drawString(denominatorText, x + (lineWidth / 2) - fm.stringWidth(denominatorText) / 2,
		y + ((fm.getAscent() * 2) + lineHeight + lineMargin));					/* Denominator */
	
	float newY;
	if(position == 1) {										/* Changing indicator position */
	    newY = ((lineY - indicator.getHeight() + 1) - indicator.getY()) / 5;
	} else {
	    newY = ((lineY) - indicator.getY()) / 5;
	}
	
	if(position == 1) {
	    float newWidth = ((fm.stringWidth(numeratorText) + 6) - indicator.getWidth()) / 3;
	    indicator.setWidth(indicator.getWidth() + (int) newWidth);
	} else if (position == 2) {
	    float newWidth = ((fm.stringWidth(denominatorText) + 6) - indicator.getWidth()) / 3;
	    indicator.setWidth(indicator.getWidth() + (int) newWidth);
	}
	indicator.setX(lineX + (lineWidth / 2) - indicator.getWidth() / 2);
	indicator.setY(indicator.getY() + newY);
	
	indicator.paint(g);
	indicator.update();
    }
    
    public void changePosition() {
	switch (position) {
	case 1: 
	    position = 2;
	    break;
	case 2:
	    position = 1;
	    break;
	}
    }
    
    public void add(String number) {
	switch(position) {
	case 1:
	    numeratorText = numeratorText + number;
	    break;
	case 2:
	    denominatorText = denominatorText + number;
	    break;
	}
    }
    
    public void clear() {
	numeratorText = "";
	denominatorText = "";
    }
    
    public void setX(int newX) {
	this.x = newX;
    }
    public void setY(int newY) {
	this.y = newY;
    }
    
    public String getNumeratorText() {
	return numeratorText;
    }
    
    public String getDenominatorText() {
	return denominatorText;
    }
    
    public int getNumeratorNum() {
	return IntUtils.valueOf(numeratorText);
    }
    
    public int getDenominatorNum() {
	return IntUtils.valueOf(denominatorText);
    }
}
