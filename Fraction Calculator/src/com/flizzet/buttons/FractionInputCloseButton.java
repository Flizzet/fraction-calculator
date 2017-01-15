package com.flizzet.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.flizzet.gui.Menu;
import com.flizzet.utils.FontUtils;

/**
 * The close button for the FractionInput class.
 *
 * Bugs: none known 
 * Requires: FractionInput
 *
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also 	 FractionInput
 */
public class FractionInputCloseButton extends JComponent implements MouseListener, MouseMotionListener {
    
    private static final long serialVersionUID = 1L;
    private int x = 0;
    private int y = 0;
    private int width = 15;
    private int height = 15;
    private Rectangle bounds = new Rectangle(x, y, width, height);
    int mx, my;
    
    private JFrame frame;

    public FractionInputCloseButton(JFrame frame) {
	this.frame = frame;
	this.frame.addMouseListener(this);
	this.frame.addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g) {
	
	Graphics2D g2d = (Graphics2D) g.create();
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2d.setFont(FontUtils.getDefaultFont().deriveFont(10.0f));
	FontMetrics fm = g2d.getFontMetrics();
	
	g2d.setColor(Color.DARK_GRAY.brighter());				/* Drawing close background */
	Shape bgCircle = new Ellipse2D.Double(x - (width / 2), y - (height / 2), 2.0 * (width / 2), 2.0 * (height / 2));
	g2d.fill(bgCircle);
	
	g2d.setColor(Color.WHITE);						/* Drawing close icon */
	g2d.drawString("x", x + (fm.stringWidth("x")) - (width / 2), y + fm.getAscent() - (height / 2));
	
	bounds = new Rectangle(x - (width / 2), y - (height / 2), width, height);
	
	this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	
	if (bounds.contains(mx, my)) {
	    Menu.getInstance().getInputLine().getFractionInput().remove();
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
	
    }
    
    public void setX(int newX) {
	this.x = newX;
	bounds = new Rectangle(newX, y, width, height);
    }
    public void setY(int newY) {
	this.y = newY;
	bounds = new Rectangle(x, newY, width, height);
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
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	mx = e.getX();
	my = e.getY() - 25;
    }
}
