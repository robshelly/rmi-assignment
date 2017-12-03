

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;

public class ServerProgram extends UnicastRemoteObject implements Calculator {
  
  private ServerGUI view;
  
  /**
   * Constructor for Calculator Server.
   * 
   * @throws RemoteException
   */
  public ServerProgram(ServerGUI view ) throws RemoteException {
    this.view = view;
  }
  
  /**
   * Constructor for ServerModel without setting up RMI.
   * Used for running Junit tests. 
   *  
   * @throws RemoteException
   */
  public ServerProgram() throws RemoteException {}
  
  /**
   * Main method for Server.
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {
      ServerProgram model = new ServerProgram(new ServerGUI());
      Naming.rebind("RMIServer", model);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  

  @Override
  public String getMessage() throws RemoteException {
    try {
      view.update("Client connected at IP: " + getClientHost());
    } catch (ServerNotActiveException e) {
      view.update("Client connected. Unable to determine client IP" + e.getMessage());
    }
    
    return "Welcome to the Remote Calculator";
  }

  @Override
  public int sum(int augend, int addend) throws RemoteException {
    logRequest(augend, addend, "+", (augend + addend));
    return augend + addend;
  }

  @Override
  public int difference(int minuend, int subtrahend) throws RemoteException {
    logRequest(minuend, subtrahend, "-", (minuend- subtrahend));
    return minuend- subtrahend;
  }

  @Override
  public int product(int multiplicand, int multiplier) throws RemoteException {
    logRequest(multiplicand, multiplier, "*", (multiplicand * multiplier));
    return multiplicand * multiplier;
  }

  @Override
  public int quotient(int dividend, int divisor) throws RemoteException {
    if ( divisor != 0) {
      logRequest(dividend, divisor, "/", (dividend/divisor));
      return dividend / divisor;
    } else {
      view.update("Client request error! Requested division by zero");
      throw new IllegalArgumentException();
    }
  }
  
  /**
   * Log Client requests to the GUI.
   * 
   * 
   * @param opnd1 The first operand of the client request.
   * @param opnd2 The second operand of the client request.
   * @param oprtr The operator of the client request.
   * @param response The response to the clients request.
   */
  private void logRequest(int opnd1, int opnd2, String oprtr, int response) {
    view.update(
        "Opnd1: " + opnd1 + "\n" +
        "Opnd2: " + opnd2 + "\n" +
        "Oprtr: " + oprtr + "\n" +
        "Data to Client :" + response);
  }
}
