package com.flizzet.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.flizzet.gui.Menu;

/**
 * The up and down triangle buttons in the FractionInput.
 *
 * Bugs: none known 
 * Requires: FractionInput
 *
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also 	 FractionInput
 */
public class FractionInputMoveButton extends JComponent implements MouseListener, MouseMotionListener {
    
    private static final long serialVersionUID = 1L;
    private int x = 0;
    private int y = 0;
    private int x1, x2, x3, y1, y2, y3;
    private int x4, x5, x6, y4, y5, y6;
    private int buttonSpacing = 45;
    private int width = 30;
    private int height = 30;
    private int boundsHeight = 75;
    private int boundsWidth = 30;
    private int mx, my;
    private Rectangle bounds = new Rectangle(x, y, boundsWidth, boundsHeight);
    private boolean combining = false;
    private float combineMargin = 0;
    private int topCombine = 5;
    
    private JFrame frame;

    public FractionInputMoveButton(JFrame frame) {
	this.frame = frame;
	this.frame.addMouseListener(this);
	this.frame.addMouseMotionListener(this);
	
    }
    
    public void paintComponent(Graphics g) {
	
	Graphics2D g2d = (Graphics2D) g.create();
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);repaint();
	
	if (combining) {
	    combineMargin += (topCombine - combineMargin) / 3;
	} else {
	    combineMargin += (0 - combineMargin) / 2;
	}
	
	this.x1 = x;						/* Triangle positions */
	this.x2 = x + width;
	this.x3 = x + (width / 2);
	this.x4 = x;
	this.x5 = x + width;
	this.x6 = x + (width / 2);
	this.y1 = y + height + (int) combineMargin;
	this.y2 = y + height + (int) combineMargin;
	this.y3 = y + (int) combineMargin;
	this.y4 = y + buttonSpacing - (int) combineMargin;;
	this.y5 = y + buttonSpacing - (int) combineMargin;
	this.y6 = y + height + buttonSpacing - (int) combineMargin;
	
	g2d.setColor(Color.WHITE);				/* Drawing up triangle */
	Path2D.Double triangleUp = new Path2D.Double();
	triangleUp.moveTo(x1, y1);
	triangleUp.lineTo(x2, y2);
	triangleUp.lineTo(x3, y3);
	triangleUp.closePath();
	g2d.fill(triangleUp);
	
	g2d.setColor(Color.WHITE);				/* Drawing down triangle */
	Path2D.Double triangleDown = new Path2D.Double();
	triangleDown.moveTo(x4, y4);
	triangleDown.lineTo(x5, y5);
	triangleDown.lineTo(x6, y6);
	triangleDown.closePath();
	g2d.fill(triangleDown);
	
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	if (bounds.contains(mx, my)) {
	    Menu.getInstance().getInputLine().getFractionInput().
	    getInputHolder().changePosition();
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (bounds.contains(mx, my)) {
	    combining = true;
	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	combining = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
	
    }
    
    public void setX(int newX) {
	this.x = newX;
	bounds = new Rectangle(newX, y, boundsWidth, boundsHeight);
    }
    public void setY(int newY) {
	this.y = newY;
	bounds = new Rectangle(x, newY, boundsWidth, boundsHeight);
    }
    
    public int getWidth() {
	return this.width;
    }
    
    public int getHeight() {
	return this.height;
    }
    
    public void removeThis() {
	frame.removeMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	mx = e.getX();
	my = e.getY() - 25;					/* Used to add offset to mouseY because of component position */
    }
}
