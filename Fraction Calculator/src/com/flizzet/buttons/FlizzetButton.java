package com.flizzet.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Custom button for displaying custom elements
 *
 * Bugs: none known
 * Requires: BasicButtonUI, MouseListener, KeyListener
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 BasicButtonUI
 */
public class FlizzetButton extends BasicButtonUI implements MouseListener, KeyListener {

    private final static FlizzetButton m_buttonUI = new FlizzetButton();

    protected Border m_borderRaised = null;			/* Bevel shows permanently after click */
    protected Border m_borderLowered = null;			/* Bevel shows on click */
    protected Color m_backgroundNormal = null;			/* Shows after click */
    protected Color m_backgroundPressed = Color.WHITE;		/* Shows on press */
    protected Color m_foregroundNormal = Color.GRAY;		/* Shows permanently after mouseOver */
    protected Color m_foregroundActive = Color.BLACK;		/* Shows during mouseOver */
    protected Color m_focusBorder = null;			/* ? */

    public static ComponentUI createUI(JComponent c) {
	return m_buttonUI;
    }

    public void installUI(JComponent c) {
	super.installUI(c);

	c.addMouseListener(this);
	c.addKeyListener(this);
	c.setBackground(null);
    }

    public void uninstallUI(JComponent c) {
	super.uninstallUI(c);
	c.removeMouseListener(this);
	c.removeKeyListener(this);
    }

    public void paint(Graphics g, JComponent c) {
	
	c.setOpaque(true);					/* Set to opaque to prevent underlying pixels */

	Graphics2D g2d = (Graphics2D) g.create();
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2d.setFont(c.getFont());

	FontMetrics fm = g.getFontMetrics();
	AbstractButton b = (AbstractButton) c;
	Dimension d = b.getSize();
	String caption = b.getText();
	int x = (d.width - fm.stringWidth(caption));
	int y = (d.height + fm.getAscent());
	
	g2d.setColor(b.getForeground());
	g2d.drawString(caption, x / 2, y / 2); 			/* Writing button text */
    }

    public Dimension getPreferredSize(JComponent c) {
	Dimension d = super.getPreferredSize(c);
	if (m_borderRaised != null) {
	    Insets ins = m_borderRaised.getBorderInsets(c);
	    d.setSize(d.width + ins.left + ins.right, d.height + ins.top + ins.bottom);
	}
	return d;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	JComponent c = (JComponent) e.getComponent();
	c.setBorder(m_borderLowered);
	c.setBackground(m_backgroundPressed);
	
    }

    public void mouseReleased(MouseEvent e) {
	JComponent c = (JComponent) e.getComponent();
	c.setBorder(m_borderRaised);
	c.setBackground(m_backgroundNormal);
    }

    public void mouseEntered(MouseEvent e) {
	JComponent c = (JComponent) e.getComponent();
	c.setForeground(m_foregroundActive);
	c.repaint();
    }

    public void mouseExited(MouseEvent e) {
	JComponent c = (JComponent) e.getComponent();
	c.setForeground(m_foregroundNormal);
	c.repaint();
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
	int code = e.getKeyCode();
	if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	    JComponent c = (JComponent) e.getComponent();
	    c.setBorder(m_borderLowered);
	    c.setBackground(m_backgroundPressed);
	}
    }

    public void keyReleased(KeyEvent e) {
	int code = e.getKeyCode();
	if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	    JComponent c = (JComponent) e.getComponent();
	    c.setBorder(m_borderRaised);
	    c.setBackground(m_backgroundNormal);
	}
    }

}
