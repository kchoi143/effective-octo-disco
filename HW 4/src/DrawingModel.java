/**
 * This is the model that holds the state of the drawing.
 * Maintains a collection of Shapes.
 * A client should be able to add a Shape to the model.
 * A viewer should be able to register with the model.
 * Capable of adding, removing, and resetting levels when called upon.
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DrawingModel {
	// the list of shapes
	private List<Shape> shapes = new ArrayList<Shape>();
	// the list of the views connected to this model
	private List<View> views = new ArrayList<View>();

	private JFrame frame;
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

	public void setFrame(JFrame f) {
		if(frame ==null) {
			frame = f;
		}else {
			System.out.println("Frame is already set");
		}
	}

	/** 
	 *  Updates all views in model
	 *  It should notify all viewers when something in the model changes.
	 */
	public void updateAll() {
		for (View v : views) {
			v.update(this);
		}
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	/** 
	 * Adds a level if any shapes are present given mouse click coordinates,
	 * and specifically checks for the Fibonacci spiral becoming too large
	 * 
	 * @return true if able to add a level, false if not
	 */
	public boolean addLevel(int x, int y) {
		boolean b = true;
		for(Shape s : shapes) {
			// if (x,y) is within s, add a level to s
			if (s.contains(x,y)) {
				b = s.addLevel();
				if (b) {
					// fibonacci square DYNAMIC SIZE BOUNDARY CHECK
					// get frame boundary
					// check if frame boundaries meet size requirements of added 
					// fib box and update
					if(s.getClass() == new FibonacciSquare(1,1,Color.BLACK,1,1).getClass()) {
						int frameX = frame.getWidth();
						int frameY = frame.getHeight();
						FibonacciSquare castedFib = (FibonacciSquare)s;
						int[] fibBoundary = castedFib.getBoundary();
						// if the frame size is too small for the fibSquare
						if(frameX<fibBoundary[1] || frameY<fibBoundary[3]
								||0>fibBoundary[0]||0>fibBoundary[2]) {
							s.removeLevel();
							return false;
						}
					}
					updateAll();
				}
			}
		}
		return b;
	}

	/** 
	 * Removes a level if any shapes are present given mouse click coordinates
	 * @return true if able to remove a level, false if not
	 */
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

	/**
	 * Removes levels by calling removeLevel until it reaches the first level
	 */
	public void reset() {
		for(Shape s : shapes) {
			int level = s.getLevel();
			for(int i=0; i<level; i++) {
				s.removeLevel();
			}
		}
		updateAll();
	}
}