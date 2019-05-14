import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * FibonacciSquare, with the (x,y) being the upper-left corner.
 * The size of the square is the nth term of the Fibonacci sequence. 
 * Size does not use scaling.  
 */
public class FibonacciSquare extends AbstractShape {

	private int n, quadrant;
	/**
	 * 
	 * @param x coordinate upper-left corner of the square
	 * @param y coordinate upper-left corner of the square
	 * @param color of the shape 
	 * @param quadrant of the square. Affects how the arc is drawn.  
	 * @param n The size of the box will be the nth Fibonacci term
	 *  
	 */
	public FibonacciSquare(int x, int y, Color c, int quadrant, int n) {
		super(x, y, c, fibonacci(n));
		Random rand = new Random();
		this.quadrant = rand.nextInt(4)+1;
		children = new Shape[1];
	}

	//xₐ = (φⁿ - ψⁿ) / √5
	public static int fibonacci(int n) {
		int n1 = 0;
		int n2 = 1;
		int n3;
		for (int i = 0; i < n; i++) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}
		return n = n1;
	}
	
	@Override
	public void draw(Graphics g) {
		// draw a square
		g.setColor(c);
		g.drawRect(x, y, size, size);
		switch (quadrant) {
		case 1:
			g.drawArc(x-size, y, size*2, size*2, 0, 90);
			break;
		case 2:
			g.drawArc(x, y, size*2, size*2, 90, 90);
			break;
		case 3:
			g.drawArc(x, y-size, size*2, size*2, 180, 90);
			break;
		case 4:
			g.drawArc(x-size, y-size, size*2, size*2, 270, 90);
			break;
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o != null && o.getClass() == this.getClass()) {
			FibonacciSquare f = (FibonacciSquare) o;
			if(this.c == f.c && this.x == f.x && this.y == f.y 
					&& this.quadrant == f.quadrant && this.n == f.n) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	// Return a boolean to the model to tell it if a new level could be added.
	// The boolean value can be relayed to the controller to tell it if the operation was successful.
	// If a new level could not be added, then the controller displays a message box to the user explaining why
	// (e.g. "size limit has been reached.")
	@Override
	public void createChildren() {
		
	}
}