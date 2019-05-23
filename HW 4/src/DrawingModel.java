/**
 * This is the model that holds the state of the drawing, and will probably be the most complex class.  
 * It should maintain a collection of Shapes. The model should look at all of the shapes as having just a Shape type. 
 * It should not have to distinguish between Triangles and Squares (and other types of shapes if you have them). 
 * When requested by a view, the model should make a deep copy of the collection of its shapes 
 * ( use the deep copy method of the Shape interface implemented in the Triangle and Square classes). 
 * Other behaviors it should support are
 * A client should be able to add a Shape to the model
 *A viewer should be able to register with the model
 * It should notify all viewers when something in the model changes.  (For now, the only changes will be when a new shape is added to the drawing.)
 *
 */
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
	// the list of shapes
	private List<Shape> shapes = new ArrayList<Shape>();
	// the list of the views connected to this model
	private List<View> views = new ArrayList<View>();

	// A client should be able to add a Shape to the model
	public void addShape(Shape s) {
		shapes.add(s);
		updateAll();
	}

	// A viewer should be able to register with the model
	public void addView(View v) {
		views.add(v);
		v.update(this);
	}

	// It should notify all viewers when something in the model changes.
	public void updateAll() {
		for (View v : views) {
			v.update(this);
		}
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public boolean addLevel(int x, int y) {
		boolean b = true;
		for(Shape s : shapes) {
			// if (x,y) is within s, add a level to s
			if (s.contains(x,y)) {
				b = s.addLevel();
				if (b) {
					updateAll();
				}
			}
		}
		return b;
	}

	public boolean removeLevel(int x, int y) {
		boolean b = true;
		for(Shape s : shapes) {
			// if (x,y) is within s, remove a level from s
			if (s.contains(x,y)) {
				b = s.removeLevel();
				if (b) {
					updateAll();
				}
			}
		}
		return b;
	}
	
	public void reset() {
		
	}
}