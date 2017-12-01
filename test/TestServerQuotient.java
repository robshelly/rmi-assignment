

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;
import org.junit.jupiter.api.Test;

class TestServerQuotient {
  
  private ServerModel calculator;

  @Test
  void testDivisonByzero() throws RemoteException {
    
    calculator = new ServerModel();

    
    try {
      assertEquals(calculator.quotient(2,0), 0);
    } catch (RemoteException e1) {
      System.out.print("Error: " + e1.getMessage());
    } catch (IllegalArgumentException e2) {
      assertTrue(true);
      return;
    }
    fail("Division by zero is undefined");
  }
  
  //@Test
  void testDivion() throws RemoteException {
    
    calculator = new ServerModel();
    
    try {
      assertEquals(calculator.quotient(0, 1), 0);
      assertEquals(calculator.quotient(1, 1), 1);
      assertEquals(calculator.quotient(2, 1), 2);
      assertEquals(calculator.quotient(0, 2), 0);
      assertEquals(calculator.quotient(1, 2), 0);
      assertEquals(calculator.quotient(2, 2), 1);
    } catch (RemoteException e1) {
      System.out.print("Error: " + e1.getMessage());
    }
      
  }

}