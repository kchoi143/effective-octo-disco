import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MainClass {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      buildGUI();
    });
  }

  private static void buildGUI() {
    JFrame frame = new JFrame("H's and Fibonacci squares");
    frame.setSize(1000, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // What goes in the frame
    Viewer panel = new Viewer();
    frame.add(panel);

    // Buttons at the top of the frame
    JRadioButton add = new JRadioButton("Add level");
    JRadioButton remove = new JRadioButton("Remove level");
    JButton reset = new JButton("Reset");
    JPanel northPanel = new JPanel();
    northPanel.setBackground(Color.WHITE);
    northPanel.add(add);
    northPanel.add(remove);
    northPanel.add(reset);
    frame.add(northPanel, BorderLayout.NORTH);
    frame.setVisible(true);
    
	// Connect the two radio buttons and select the add button
	ButtonGroup group = new ButtonGroup();
	group.add(add);
	group.add(remove);
	add.setSelected(true);

	// Connect the view to a model
	DrawingModel model = new DrawingModel();
	model.addView(panel);
	
	// Create a controller
	Controller controller = new Controller(model);
	
	// The controller listens to the button clicks
	add.addActionListener(controller);
	remove.addActionListener(controller);
	reset.addActionListener(controller);
	// The controller listens to the mouse clicks on the display panel
	panel.addMouseListener(controller);

	// Show it (execute this line last)
	frame.setVisible(true);
	
	// Create 10 shapes
	// (possibly create another shape if one created goes beyond boundaries)
	Random rand = new Random();
	for(int i = 1; i <= 10; i ++) {
		if (Math.random() >= 0.5) {
			model.addShape(new HShape(rand.nextInt(panel.getWidth())+1, rand.nextInt(panel.getHeight())+1, 
					new Color(rand.nextInt(0xFFFFFF)), rand.nextInt(100)+1));
		} else {
			model.addShape(new FibonacciSquare(rand.nextInt(panel.getWidth())+1, rand.nextInt(panel.getHeight())+1,
					new Color(rand.nextInt(0xFFFFFF)), rand.nextInt(4)+1, rand.nextInt(7)+5));
		}
	}
  }
}
