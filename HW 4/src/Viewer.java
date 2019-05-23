import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Viewer extends JPanel implements View {
  
  // The model this View is connected to
  private DrawingModel model;
  
  public Viewer() {
    setBackground(Color.WHITE);
  }

  @Override
  public void update(DrawingModel model) {
	  this.model = model;
	  repaint(); // will eventually call paintComponent
	  }
  
  //Never call paintComponent directly as the interaction with the OS is unclear. 
  //Always use repaint()
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // display of the shapes
    if (model != null) {
      for (Shape s : model.getShapes()) {
        s.draw(g);
      }
    }
  }
  
}
