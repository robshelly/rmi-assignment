import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerController implements ActionListener {
  
  private ServerModel model;
  private ServerView view;


  /**
   * Constructor for the Server Controller.
   */
  public ServerController() {
  }
  
  /*
   * Handle Action Performed in view 
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * Add a Server Model.
   * 
   * @param model The Server model to add.
   */
  public void addModel(ServerModel model) {
    this.model = model;
  }
  
  /**
   * Add a Server View.
   * 
   * @param view The server View to add.
   */
  public void addView(ServerView view) {
    this.view = view;
  }

}
