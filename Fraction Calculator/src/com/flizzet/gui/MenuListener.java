package com.flizzet.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Holds creation of button listeners for organization
 *
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 Menu, MenuBuilder
 */
public class MenuListener {

    public MenuListener() {}
    
    /** Adds ActionListeners to all buttons */
    public void addButtonListeners(ArrayList<JButton> buttons) {
	for (JButton e : buttons) {
	    e.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent a) {
		    buttonClicked(e, buttons);
		}
	    });
	}
    }
    
    private void buttonClicked(JButton button, ArrayList<JButton> buttons) {
	Menu.getInstance().getInputLine().add(button);
    }

}
