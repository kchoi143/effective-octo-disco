import java.awt.BorderLayout;
import java.awt.Color;

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
		//Connect frame to model
		model.setFrame(frame);


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

		// Initialize 1st fibonacci shape and connect it to the model
		Shape gp = new FibonacciSquare(350, 250, Color.BLUE, 2, 1);
		model.addShape(gp);

		// Initialize H shape and connect it to the model
		Shape h1 = new HShape(500, 200, Color.GREEN, 400);
		model.addShape(h1);

		// Create a text viewer and connect it to model
		TextViewer text = new TextViewer();
		model.addView(text);

	}

}