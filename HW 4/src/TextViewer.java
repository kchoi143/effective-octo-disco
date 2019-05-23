
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
