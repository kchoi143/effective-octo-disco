
/**
 * The View interface. The minimal information a viwer needs for DrawingModel
 * to interact with it. 
 * Includes:
 * update() for updating models to the view.
 *
 */
public interface View {
  
  void update(DrawingModel model);
}