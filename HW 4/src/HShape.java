import java.awt.Color;
import java.awt.Graphics;


/**
 * H shape
 */
public class HShape extends AbstractShape {

	/**
	 * 
	 * @param x x coordinate of top left corner 
	 * @param y y coordinate of top left corner
	 * @param c the color of the H
	 * @param size the length and height of the square containing the H
	 */
	public HShape(int x, int y, Color c, int size) {
		super(x, y, c, size);
		children = new Shape[7];
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 1 & j == 0) || i == 1 & j == 2) {
				} else {
					g.setColor(c);
					g.drawRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
					g.fillRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3); 
				}
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o!=null && o.getClass() == this.getClass()) {
			HShape h = (HShape) o;
			if(this.c == h.c && this.x == h.x && this.y == h.y 
					&& this.size == h.size) {
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