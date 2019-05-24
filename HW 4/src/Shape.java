
import java.awt.Graphics;

/**
 * Interface Shape. The minimal interface of a shape.  
 * Includes draw() method for drawing the shape
 * and a deepCopy() method to return a deep copy of the shape.
 */
public interface Shape {

	/**
	 * draw the shape onto the view
	 * @param g Graphics object to be drawn
	 */
	void draw(Graphics g);

	/**
	 * initializes the children array
	 * Use addLevel to add an entire level of children
	 * @return a boolean to the model to tell it if a new level could be added.
	 * The boolean value can be relayed to the controller to tell it if the operation was successful.
	 * If a new level could not be added, then the controller displays a message box 
	 * to the user explaining why.
	 */
	boolean createChildren();

	/**
	 * adds a level of children to the bottom level 
	 */
	boolean addLevel();

	/**
	 * removes a level of children to the bottom level
	 */
	boolean removeLevel();

	/**
	 * @return get the children array of the shape
	 */
	Shape[] getChildren();

	/**
	 * check if the given (x,y) coordinates are in the shape
	 * @param x mouse pointer x
	 * @param y mouse pointer y
	 * @return true if in shape, false if not in shape
	 */
	boolean contains(int x, int y);

	/**
	 * @return get how many levels the shape has
	 */
	int getLevel();

}
