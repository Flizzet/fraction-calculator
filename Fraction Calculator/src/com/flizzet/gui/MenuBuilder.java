package com.flizzet.gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.flizzet.buttons.FlizzetButton;
import com.flizzet.utils.FontUtils;
import com.flizzet.utils.StringUtils;

/**
 * Holds creation of elements for more organization.
 * </br></br>
 * Bugs: none known
 * </br>
 * Requires: Menu, MenuListener
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 Menu
 */
public class MenuBuilder {
    
    private final int totalButtons = 19;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    public MenuBuilder() {}
    
    /** Builds buttons and adds them to frame */
    public void createButtons(JFrame frame) {
	
	/* Loop to create buttons and set text */
	for (int i = 1; i < totalButtons; i++) {
	    
	    JButton button = new JButton();
	    button.setFont(FontUtils.getDefaultFont().deriveFont(30.0f));
	    button.setForeground(Color.GRAY);
	    button.setUI(new FlizzetButton());
	    
	    switch(i) {
	    case 4:					/* Addition symbol is the 4th button */
		button.setText("+");
		break;
	    case 5:					/* Falling through intended */
	    case 6:					
	    case 7:
		button.setText(StringUtils.valueOf(i - 1));
		break;
	    case 8:					/* Subtraction symbol is the eigth button */
		button.setText("-");	
		break;
	    case 9:					/* Falling through intended */
	    case 10:					
	    case 11:
		button.setText(StringUtils.valueOf(i - 2));
		break;
	    case 12:					/* Division symbol is the twelfth button */
		button.setText("/");
		break;
	    case 13:					/* Fraction text is the thirteenth button */
		button.setText("Frac");	
		break;
	    case 14:					
		button.setText(StringUtils.valueOf(0));
		break;
	    case 15:					/* Clear symbol is the fifteenth button */
		button.setText("Clear");
		break;
	    case 16:					/* Multiplication symbol is the sixteenth symbol */
		button.setText("*");
		break;
	    case 17:					/* Equals symbol is the seventeenth symbol */
		for (int j = 0; j < 2; j++) {		/* Add 3 empty components to give space for equals symbol */
		    JPanel emptyPanel = new JPanel();
		    emptyPanel.setVisible(false);
		    frame.add(emptyPanel);
		}
		button.setText("Batch");
		break;
	    case 18:
		button.setText("=");
		break;
	    default:					/* i is default for all buttons below 4 */
		button.setText(StringUtils.valueOf(i));
		break;
	    }

	buttons.add(button);
	frame.add(button);
	    
	}
	
	addButtonListeners(buttons);			/* Add ActionListeners to buttons */

    }
    
    /** Calls addButtonListeners in the MenuListener to add button ActionListeners */
    private void addButtonListeners(ArrayList<JButton> buttons) {
	Menu.getInstance().getListener().addButtonListeners(buttons);
	Menu.getInstance().getInputLine().setButtons(buttons);
    }
}
