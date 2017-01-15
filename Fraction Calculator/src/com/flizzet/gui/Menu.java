package com.flizzet.gui;

import javax.swing.JFrame;

/**
 * Main UI, contains all buttons and input.
 *
 * Bugs: none known
 * Requires: MenuBuilder
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 MenuBuilder, MenuListener
 */
public class Menu {

    private static final Menu INSTANCE = new Menu();
    private Input inputLine = new Input();
    private MenuBuilder menuBuilder = new MenuBuilder();
    private MenuListener menuListener = new MenuListener();

    /** Private constructor to encourage single use */
    private Menu() {
    }

    /** Returns an instance of the menu */
    public static Menu getInstance() {
	return INSTANCE;
    }

    /** Creates and adds all buttons to the window */
    public void buildGui(JFrame frame) {
	menuBuilder.createButtons(frame);
    }

    public MenuListener getListener() {
	return menuListener;
    }

    public Input getInputLine() {
	return inputLine;
    }

}
