

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;
import org.junit.jupiter.api.Test;

class TestServerProduct {
  
  private ServerModel calculator;
  
  @Test
  void testMultiplcationByZero() throws RemoteException {
    
    calculator = new ServerModel();
    
    try {
      assertEquals(calculator.product(0,0), 0);
      assertEquals(calculator.product(0,1), 0);
      assertEquals(calculator.product(0,2), 0);
    } catch (RemoteException e) {
      System.out.print("Error: " + e.getMessage());
    }
  }
  
  @Test
  void testMultiplicativeIdentity() throws RemoteException {
    
    calculator = new ServerModel();
    
    try {
      assertEquals(calculator.product(1,0), 0);
      assertEquals(calculator.product(1,1), 1);
      assertEquals(calculator.product(1,2), 2);
    } catch (RemoteException e) {
      System.out.print("Error: " + e.getMessage());
    }
  }
  
  @Test
  void testProducts() throws RemoteException {
    
    calculator = new ServerModel();
    
    try {
      assertEquals(calculator.product(2,0), 0);
      assertEquals(calculator.product(2,1), 2);
      assertEquals(calculator.product(2,2), 4);
      assertEquals(calculator.product(2,3), 6);
      assertEquals(calculator.product(2,4), 8);
    } catch (RemoteException e) {
      System.out.print("Error: " + e.getMessage());
    }
  }

}
