/**
 * Prints out the class, x, y, color, and level of the parent shapes
 * to the console whenever they are updated by the viewer.
 */
public class TextViewer implements View {

	@Override
	public void update(DrawingModel model) {
		if (!model.getShapes().isEmpty()) {
			System.out.println("------------\nUpdate Called\n------------");
		}
		for (Shape s : model.getShapes()) {
			System.out.println(s.toString());
		}
	}

}
