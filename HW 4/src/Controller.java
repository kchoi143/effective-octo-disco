import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller extends JPanel implements ActionListener, MouseListener {
	private DrawingModel model;
	private boolean addLevel = true;

	// Connect controller and model (in the constructor)
	public Controller(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.printf("(x,y)=(%d,%d)\n", e.getX(), e.getY());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("Add")) {
			addLevel = true;
		} else if (e.getActionCommand().contains("Remove")) {
			addLevel = false;
		} else {
			model.reset();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (addLevel) {
			boolean success = model.addLevel(e.getX(), e.getY());
			if (!success) {
				JOptionPane.showMessageDialog(Controller.this, "Size limit has been reached", "Controller",
						JOptionPane.INFORMATION_MESSAGE);
				// display a message box stating that no more level can be added
				// look at JOptionePane.showMessageDialog
			}
		} else {
			boolean success = model.removeLevel(e.getX(), e.getY());
			if (!success) {
				JOptionPane.showMessageDialog(Controller.this, "Can't remove any more levels", "Controller",
						JOptionPane.INFORMATION_MESSAGE);
				// display a message stating that no more level can be removed
			}
		}

	}

	// Not used
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}