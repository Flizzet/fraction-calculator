package com.flizzet.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Allows conversion of references to Image objects.
 *
 * Bugs: none known
 *
 * @author       Pedro Dutra (2016)
 * @version      1.0
 * @see also	 ImageContainer
 */
public class ImageUtils {
    
    private static BufferedImage IMAGE_HOLDER;

    /** Suppress default constructor for noninstantiability.*/
    private ImageUtils() {
	System.err.println("Attempted to instantiate a utility");
	throw new AssertionError();
    }
    
    /** Converts a reference (users/.../example.png) to a BufferedImage */
    public static BufferedImage changeReferenceToImage(String reference) {
	
	try {
	    IMAGE_HOLDER = ImageIO.read(new File(reference));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return IMAGE_HOLDER;
    }

}
