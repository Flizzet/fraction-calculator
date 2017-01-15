package com.flizzet.utils;

/** 
 * Is placed when an operator is pressed to separate fractions for calculation.
 * 
 * Bugs: none known
 * 
 * @author 	 Pedro Dutra (2016)
 * @version 	 1.0
 * @see also 	 InputFunctions;
 */
public class Separator {
    
    private int x, y;

    /** Default instantiable constructor */
    public Separator() {
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    
    public void setX(int newX) { this.x = newX; }
    public void setY(int newY) { this.y = newY; }

}
