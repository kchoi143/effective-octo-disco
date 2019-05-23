import java.awt.Color;
import java.awt.Graphics;


/**
 * H shape
 */
public class HShape extends AbstractShape {
	
	int i, j;
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
		gen=1;
	}

	@Override
	public void draw(Graphics g) {
	  //if at the last level draw the shapes
	  if(children[0]==null) {
  		if (i == 2 && j == 2) {
  			g.setColor(c);
  			g.drawRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			g.fillRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			return;
  		} else if ((i == 1 && j == 0) || (i == 1 && j == 2)) {
  			i++;
  			draw(g);
  		} else if (i == 2) {
  			g.setColor(c);
  			g.drawRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			g.fillRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			j++;
  			i = 0;
  			draw(g);
  		} else {
  			g.setColor(c);
  			g.drawRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			g.fillRect(x+(i*(size/3)), y+(j*(size/3)), size/3, size/3);
  			i++;
  			draw(g);
  		}
	  }else { 
	    //if not at the last level, all shapes in level call draw() method 
	    //for all children in the next level 
	    for(Shape s: children) {
	      s.draw(g);
	    }
	  }
	}

/**
 *  Use addLevel to create an entire level of children
 *  you should not call this method outside testing purposes
 *  Return a boolean to the model to tell it if a new level could be added.
 *  The boolean value can be relayed to the controller to tell it if the operation was successful.
 *  If a new level could not be added, then the controller displays a message box to the user explaining why
 *  (e.g. "size limit has been reached.")
 */
	
  @Override
  public boolean createChildren() {
    if (children[6] == null) {
      int childSize = this.size/3;
      //minimum size 6px
      if (childSize > 5) {
        //top left
        children[0] = new HShape(x, y, c, childSize+1);
        //middle left
        children[1] = new HShape(x, y+childSize,c,childSize+1);
        //bottom left
        children[2] = new HShape(x, y+childSize*2, c, childSize+1);
        //middle middle
        children[3] = new HShape(x+childSize, y+childSize, c, childSize+1);
        //top right
        children[4] = new HShape(x+childSize*2,y,c,childSize+1);
        //middle right
        children[5] = new HShape(x+childSize*2,y+childSize,c,childSize+1);
        //middle bottom
        children[6] = new HShape(x+childSize*2,y+childSize*2,c,childSize+1);
        //give children +1 level field
        for(Shape s : children) {
          HShape child = (HShape)s;
          child.gen += this.gen;
        }
        return true;
      } else {
        //children will be too small
        return false;
      }
    } else {
      //children are already initialized
      return false;
    }
}
	//
}