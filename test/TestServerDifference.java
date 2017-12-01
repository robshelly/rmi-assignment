

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;
import org.junit.jupiter.api.Test;

class TestServerDifference {
  
  private ServerModel calculator;
  
  @Test
  void testDifference() throws RemoteException {
    
    calculator = new ServerModel();
    
    try {
      assertEquals(calculator.difference(0,0), 0);
      assertEquals(calculator.difference(0,1), -1);
      assertEquals(calculator.difference(0,2), -2);
      assertEquals(calculator.difference(1,0), 1);
      assertEquals(calculator.difference(1,1), 0);
      assertEquals(calculator.difference(1,2), -1);
      assertEquals(calculator.difference(2,0), 2);
      assertEquals(calculator.difference(2,1), 1);
      assertEquals(calculator.difference(2,2), 0);
    } catch (RemoteException e) {
      System.out.print("Error: " + e.getMessage());
    }
  }

}
