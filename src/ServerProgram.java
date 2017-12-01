import java.net.MalformedURLException;
import java.rmi.*;

/**
 * This is the Calculator ServerProgram.
 * It creates and instantiates the model, view and controller.
 * 
 * @author Rob Shelly
 *
 */
public class ServerProgram {
  
  private ServerModel model;
  private ServerView view;
  private ServerController controller;
  
  
  /**
   * Contructor for ServerProgram.
   */
  public ServerProgram() {
    
    view = new ServerView();
    
    try {
      model = new ServerModel(view);
        Naming.rebind("RMIServer", model);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    controller = new ServerController();
    controller.addModel(model);
    controller.addView(view);
    
  }
  
  
  /**
   * Main method for ServerProgram.
   * 
   * @param args
   */
  public static void main(String[] args) {
    ServerProgram server = new ServerProgram();
  }
  
//  public static void main(String[] args) {
//    try {
//      ServerModel calculator = new ServerModel();
//      Naming.rebind("RMIServer", calculator);
//    } catch (Exception e) {
//      System.out.println("Exception: " + e);
//    }
//  }
}
