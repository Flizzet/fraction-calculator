package com.flizzet.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FractionSelectionIndicator {
    
    private Color indicatorColor = Color.WHITE;
    private int width, height;
    private float x, y;					/* Floats for easing */

    public FractionSelectionIndicator() {
	this.width = 30;
	this.height = 30;
    }
    
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g.create();
	
	g2d.setColor(indicatorColor);
	g2d.setStroke(new BasicStroke(3.0f));
	g2d.drawRect((int) x, (int) y, width, height);
    }
    
    public void update() {
	
    }
    
    public void setX(float newX) {
	this.x = newX;
    }
    public void setY(float newY) {
	this.y = newY;
    }
    
    public float getY() {
	return y;
    }
    
    public int getHeight() {
	return height;
    }
    
    public int getWidth() {
	return width;
    }
    
    public void setWidth(int newWidth) {
	this.width = newWidth;
    }

}
