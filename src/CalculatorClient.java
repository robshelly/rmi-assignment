import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Observable;

public class CalculatorClient extends Observable {
  
  private String serverURL;
  private Calculator remoteServer;
  
  private int operand1;
  private int operand2;
  private String operator;
  
  private static String[] operators = {"+","-","*","/"};
  
  /**
   * Constructor for Calculator Client 
   * @throws NotBoundException 
   * @throws RemoteException 
   * @throws MalformedURLException 
   */
  public CalculatorClient() throws MalformedURLException, RemoteException, NotBoundException {
    serverURL = "rmi://localhost/RMIServer";
    remoteServer = (Calculator) Naming.lookup(serverURL);
  }
  
  /**
   * Constructor for running tests as Junit can't handle
   * constructor that throws NotBoundException.
   * 
   * @param test
   */
  public CalculatorClient(String test) {
    
  }
  
  //TODO delete test method
  private void test() {
    setOperand1(5);
    setOperand2(2);
    setOperator("+");
    try {
      System.out.println(submit());
      setOperator("-");
      System.out.println(submit());
      setOperator("*");
      System.out.println(submit());
      setOperator("/");
      System.out.println(submit());
      
    } catch (RemoteException e) {
      System.out.println("What happened?");
    }
  }
  
  
  // TODO Delete this when GUI is implemented
  public static void main(String[] args) {
    try {
      CalculatorClient calculator = new CalculatorClient();
      System.out.println(calculator.remoteServer.getMessage());
      calculator.test();
      calculator.test();
    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      System.out.println("Error contacting remote server!");
    }
  }

  /**
   * Getter for first operand.
   * 
   * @return The first operand.
   */
  public int getOperand1() {
    return operand1;
  }


  /**
   * Setter for the first operand.
   * 
   * @param operand1 The value to set as the first operand.
   */
  public void setOperand1(int operand1) {
    this.operand1 = operand1;
  }


  /**
   * Getter for the second operand.
   * 
   * @return The second operand.
   */
  public int getOperand2() {
    return operand2;
  }


  /**
   * Setter for the second operand.
   * 
   * @param operand2 The value to set as the second operand.
   */
  public void setOperand2(int operand2) {
    this.operand2 = operand2;
  }


  /**
   * Getter for the operator.
   * 
   * @return The operator.
   */
  public String getOperator() {
    return operator;
  }


  /**
   * Setter for the operand.
   * 
   * @param operator The operator to set.
   */
  public void setOperator(String operator) {
    if (Arrays.asList(operators).contains(operator))
      this.operator = operator;
    else throw new InputMismatchException();
  }
  
  /**
   * Get the welcome message from the server.
   * 
   * @return The welcome message from the remote server.
   * @throws RemoteException
   */
  public String getMessage() throws RemoteException {
    return remoteServer.getMessage();
  }
  
  
  /**
   * Get the answer from the server
   * @throws RemoteException 
   */
  public int submit() throws RemoteException {
    
    if (operator.equals("+")) {
      return remoteServer.sum(operand1, operand2);
      
    } else if (operator.equals("-")) {
      return remoteServer.difference(operand1, operand2);
      
    } else if (operator.equals("*")) {
      return remoteServer.product(operand1, operand2);
      
    } else if (operator.equals("/")) {
      return remoteServer.quotient(operand1, operand2);
      
    } else {
      throw new NullPointerException();
    }
  }
}
