package com.flizzet.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Loads and holds fonts.
 * 
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 */
public class FontUtils {

    private static Font defaultFont;
    private static Font fractionFont;

    /** Suppress constructor to encourage noninstantiability */
    private FontUtils() {
	throw new AssertionError();
    }

    /** Create font for future use */
    public static void loadFonts() {
	try {
	    defaultFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/SEGOEUI.ttf"));
	    fractionFont = defaultFont.deriveFont(35.0f);
	} catch (FontFormatException | IOException e) {
	    e.printStackTrace();
	}
    }

    public static Font getDefaultFont() {
	return defaultFont;
    }
    
    public static Font getFractionFont() {
	return fractionFont;
    }

}
