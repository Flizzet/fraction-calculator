package com.flizzet.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.flizzet.main.Main;
import com.flizzet.main.Main.BatchDrawPane;

/**
 * Builds the batch window
 * </br></br>
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 */
public class BatchBuilder {
    
    private static final BatchBuilder INSTANCE = new BatchBuilder();
    private static JTextArea inputArea;
    
    private static JFrame batchFrame;
    private static final int BATCH_WIDTH = 300;
    private static final int BATCH_HEIGHT = 400;

    /** Private constructor for single use */
    private BatchBuilder() {
    }
    
    /** Returns an instance of BatchBuilder */
    public static BatchBuilder getInstance() {
	return INSTANCE;
    }
    
    /** Builds the batch window */
    public void buildWindow() {
	batchFrame = new JFrame("Batch Mode");
	batchFrame.setBounds(100, 100, BATCH_WIDTH, BATCH_HEIGHT);
	batchFrame.setDefaultCloseOperation(closeWindow());
	
	batchFrame.setResizable(false);
	batchFrame.setContentPane(new BatchDrawPane());
	batchFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
	batchFrame.setVisible(true);
    }
    
    public void paint(Graphics g) {
	//Graphics2D g2d = (Graphics2D) g.create();
    }
    
    /** Builds the batch menu */
    public void buildElements() {
	
	batchFrame.add(new JLabel("Output file:"));					/* Output file label */
	
	JTextField outputTextField = new JTextField("FractionOutput.txt");		/* Output file name text field */
	outputTextField.setPreferredSize(new Dimension(290, 30));
	batchFrame.add(outputTextField);
	outputTextField.setVisible(true);
	
	batchFrame.add(new JLabel("Write a fraction surrounded by parenthesis"));	/* Instructions labels */
	batchFrame.add(new JLabel("then write the operator, then your second"));
	batchFrame.add(new JLabel("fraction, and click run. Example problem:"));
	batchFrame.add(new JLabel("(1/5) * (2/3)"));
	
	inputArea = new JTextArea("(1/5) + (2/5)", 5, 10);
	inputArea.setPreferredSize(new Dimension(290, 190));
	inputArea.setLineWrap(true);
	inputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	
	batchFrame.add(inputArea);
	
	JButton runButton = new JButton("Run");
	runButton.setPreferredSize(new Dimension(290, 30));
	runButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		BatchWriter.write(outputTextField.getText(), inputArea.getText());
		BatchWriter.convertToEquation(outputTextField.getText(), inputArea.getText());
		BatchWriter.write(outputTextField.getText(), inputArea.getText());
	    }
	});
	
	batchFrame.add(runButton);
	
    }
    
    public void writeLine(String line) {
	inputArea.append(line);
    }
    
    public JFrame getFrame() {
	return batchFrame;
    }

    private int closeWindow() {
	Menu.getInstance().getInputLine().setText("");
	Main.stopBatchThread(true);
	
	return JFrame.DISPOSE_ON_CLOSE;
    }
    
}
