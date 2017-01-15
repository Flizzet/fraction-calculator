package com.flizzet.main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.flizzet.gui.BatchBuilder;
import com.flizzet.gui.Menu;
import com.flizzet.utils.FontUtils;

/**
 * Builds window and runs application.
 *
 * Bugs: none known 
 * Requires: Menu
 *
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 */
public class Main {

    private static JFrame frame;
    
    private static Thread loopThread;
    private static Thread batchThread;
    private static boolean batchStopped = false;

    public static final GridLayout GRIDLAYOUT = new GridLayout(0, 4, 10, 10);

    private static final int WIDTH = 480;
    private static final int HEIGHT = 700;

    /** Launch the application. */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    @SuppressWarnings("unused")		/* Is used to create the window */
		    Main window = new Main();
		    Main.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});

    }

    /** Create the application. */
    private Main() {
	initialize();
    }

    /** Initialize the contents of the frame. */
    private void initialize() {
	frame = new JFrame("Fraction Calculator");
	frame.setBounds(800, 50, 480, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setResizable(false);
	frame.setContentPane(new DrawPane()); 		/* Create pane that can be drawn on */
	frame.getContentPane().setLayout(GRIDLAYOUT);

	FontUtils.loadFonts(); 				/* Loading */

	for (int i = 0; i < 4; i++) { 			/* Add 4 empty components to give space for input line */
	    JPanel emptyPanel = new JPanel();
	    emptyPanel.setVisible(false);
	    frame.getContentPane().add(emptyPanel);
	}

	Menu.getInstance().buildGui(frame);
	
	loopThread = new Thread() { 			/* Loop thread */
	    public void run() {
		System.out.println("Loop thread initiated");

		while (true) { 				/* Main loop */
		    
		    frame.repaint();
		    
		    try {
			Thread.sleep(1000 / 60);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	};

	loopThread.start();
    }

    /** Pane that can be drawn on */
    private class DrawPane extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
	    Menu.getInstance().getInputLine().paint(g);
	}
    }
    
    /** Batch Pane that can be drawn on */
    public static class BatchDrawPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g) {
	    BatchBuilder.getInstance().paint(g);
	}
    }
    
    public static void addBatchThread() {
	batchThread = new Thread() { 		/* Batch thread */
	    public void run() {

		while (!batchStopped) { 			/* Batch loop */
		    
		    BatchBuilder.getInstance().getFrame().repaint();
		    
		    try {
			Thread.sleep(1000 / 60);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	};
	
	batchThread.start();
    }
    
    public static void stopBatchThread(boolean isBatchStopped) {
	batchStopped = isBatchStopped;
    }
    
    public static GridLayout getLayout() {
	return GRIDLAYOUT;
    }

    public static int getWidth() {
	return WIDTH;
    }

    public static int getHeight() {
	return HEIGHT;
    }
    
    public static JFrame getFrame() {
	return frame;
    }

}