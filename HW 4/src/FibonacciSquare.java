import java.awt.Color;
import java.awt.Graphics;

/**
 * FibonacciSquare, with the (x,y) being the upper-left corner.
 * The size of the square is the nth term of the Fibonacci sequence. 
 * Size uses a scaling of 5 and cannot exceed n=10th Fibonacci term.
 * 
 */
public class FibonacciSquare extends AbstractShape {

	int quadrant;
	int n;

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
		super(x,y,c,FibonacciNumber(n));
		this.quadrant = quadrant;
		this.n = n;
		children = new Shape[1];
		gen = 1;
	}

	/**
	 * @param n as in the nth Fibonacci term
	 * @return the nth Fibonacci term scaled by 5. 
	 */

	public static int FibonacciNumber(int n) {
		int n1 = 0;
		int n2 = 1;
		int n3;
		for (int i = 0; i < n; i++) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}
		n = n1;
		// scaling: n * scale
		return n*10;
	}

	@Override
	public void draw(Graphics g) {
		// draw shapes in every level
		// base case no more levels.
		if(children[0]==null) {
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
			return;
		} else {
			// else draw the shape
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
			// call children's draw() method.
			children[0].draw(g);
		}
	}

	/**
	 * initializes the children array
	 * Use addLevel to add an entire level of children
	 * @Return a boolean to the model to tell it if a new level could be added.
	 * The boolean value can be relayed to the controller to tell it if the operation was successful.
	 * If a new level could not be added, then the controller displays 
	 * a message box to the user explaining why.
	 */
	public boolean createChildren() {
		int childQ = (this.quadrant+1)%5;
		if(childQ == 0) {
			childQ=1;
		}
		int childX=this.x;
		int childY=this.y;
		int childSize=FibonacciNumber(this.n+1);

		switch(childQ) {
		case 1:
			// "center" the X 
			childX-=(childSize-this.size);
			childY-=childSize;
			break;
		case 2:
			childX-=childSize;
			break;
		case 3:
			childY+=this.size;
			break;
		case 4:
			childX+=this.size;
			// "center" the Y
			childY-=(childSize-this.size);
			break;
		}

		children[0] = new FibonacciSquare(childX, childY, this.c, childQ,this.n+1);
		FibonacciSquare child = (FibonacciSquare) children[0];
		child.gen+=this.gen;
		return true;
	}

	/**
	 * get the boundary of the entire spiral including children.
	 * @return an array of int[4] [left,right,top,bottom] 
	 *  [0] = left
	 *  [1] = right
	 *  [2] = top
	 *  [3] = bottom
	 */
	public int[] getBoundary(){
		Shape box = this;
		FibonacciSquare castedBox = (FibonacciSquare)box; 

		// boundary of the fibonacciSquare
		int leftBoundary=this.x;
		int rightBoundary=this.x+this.size;
		int topBoundary=this.y;
		int bottomBoundary=this.y+this.size;
		// level of shape
		int level = getLevel();
		// loop though all children
		for(int i=0;i<level-1;i++) {
			box=box.getChildren()[0];
			castedBox = (FibonacciSquare)box;

			switch(castedBox.quadrant){
			case 1:
				// replace top
				topBoundary = castedBox.y;
				break;
			case 2:
				// replace left
				leftBoundary = castedBox.x;
				break;
			case 3:
				// replace bottom
				bottomBoundary = castedBox.y + castedBox.size;
				break;
			case 4:
				// replace right
				rightBoundary = castedBox.x + castedBox.size;
				break;
			}
		}
		// [left,right,top,bottom]
		int[] boundary = {leftBoundary,rightBoundary,topBoundary,bottomBoundary};    
		return boundary;
	}

	/**
	 * check if the given (x,y) coordinates are in the shape
	 * @param x mouse pointer x
	 * @param y mouse pointer y
	 * @return true if in shape, false if not in shape
	 */  @Override
	 public boolean contains(int pX, int pY) {
		 int[] boundary = this.getBoundary();
		 // boundary = [0 left, 1 right, 2 top, 3 bottom]
		 if(boundary[0]<pX && boundary[1]>pX && boundary[2]<pY && boundary[3]>pY) {
			 return true;
		 }
		 // if pX or pY are outside boundary
		 return false;
	 }

	 public Shape[] getChildren() {
		 return children;
	 }

	 @Override
	 public boolean equals(Object o) {
		 if(o!=null&&o.getClass()==this.getClass()) {
			 FibonacciSquare f = (FibonacciSquare)o;
			 if(this.c == f.c && this.x == f.x && this.y == f.y 
					 && this.quadrant == f.quadrant && this.n == f.n) {
				 return true;
			 }
		 }
		 return false;
	 }
}