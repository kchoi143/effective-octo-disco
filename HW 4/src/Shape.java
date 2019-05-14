
import java.awt.Graphics;

/**
 * Interface Shape. The minimal interface of a shape.  
 * Includes draw() method for drawing the shape
 * and a deepCopy() method to return a deep copy of the shape.
 */
public interface Shape {
  void draw(Graphics g);
  boolean contains(int x, int y);
  boolean addLevel();
  boolean removeLevel();
  void createChildren();
}
