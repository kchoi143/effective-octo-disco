import java.awt.Color;

/**
 * the parent class of all shapes
 * Includes the fields: 
 * int x,y for (x,y) coordinates,
 * Color c for the color of the shape, and
 * int size used to calculate the height and width values of the instantiated shape.
 */
public abstract class AbstractShape implements Shape {

	protected int x, y, size;
	Color c;

	// the children of this shape
	protected Shape[] children;

	public AbstractShape(int x, int y, Color c, int size) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.size = size;
	}

	/**
	 * Since the model has only a reference to the top level shapes, 
	 * the method will iterate to get to the last level of the shape 
	 * that was selected by the mouse click. Do so by using recursion 
	 * (don't use any loop, except to loop over the elements of the array 
	 * of children in a current level.
	 * iterate by using recursion (that is don't use any loop, 
	 * except to loop over the elements of the array of children in a current level.) 
	 */
	@Override
	public boolean addLevel() {
		if (children[0] == null) {
			createChildren();
		} else {
			for (Shape child : children) {
				child.addLevel();
			}
		}
		return false;
	}

	/**
	 * It will do so by setting to null the elements of the array of children for the last current level.
	 * Contrary to the addLevel method, removeLevel won't iterate to the last level of the selected shape.
	 * It will iterate to the level preceding the last level. 
	 * This is because to remove the last level, the method needs to set to null the elements of the 
	 * array of children that refer to that last level. 
	 * If the shape that is displayed is at level 1, a level can't be removed.
	 * In that case, return false to the model. In all other cases, return true to the model.
	 * The boolean value will be passed on to the controller so that it can display a message box 
	 * if a level could not be removed.
	 */
	@Override
	public boolean removeLevel() {
		// TODO Auto-generated method stub
		return false;
	}

	// It should give the type of the shape (FibonacciSquare or HShape available by calling getClass()), 
	// the coordinates of the shape, its color and its current level.
	@Override
	public String toString() {
		return (getClass() + ": {" + "x: " + this.x + ", y: " + this.y + ", color: " + this.c.toString()
		+ ", level: " +  "}");
	}
}
