
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
   * 
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
   * 
   * @return get the children array of the shape
   */
  Shape[] getChildren();
  
  /**
   * check if the given (x,y) coordinates are in the shape
   * @param x
   * @param y
   * @return
   */
  boolean contains(int x, int y);
  
  /**
   * returns an int of how many levels the shape has
   */
  int getLevel();

}
