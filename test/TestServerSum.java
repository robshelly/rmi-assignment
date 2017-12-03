

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;
import org.junit.jupiter.api.Test;

class TestServerSum {
  
  private ServerProgram calculator;
  
  @Test
  void testSum() throws RemoteException {
    
    calculator = new ServerProgram(new ServerGUI());
    
    try {
      assertEquals(calculator.sum(0,0), 0);
      assertEquals(calculator.sum(0,1), 1);
      assertEquals(calculator.sum(0,2), 2);
      assertEquals(calculator.sum(1,0), 1);
      assertEquals(calculator.sum(1,1), 2);
      assertEquals(calculator.sum(1,2), 3);
      assertEquals(calculator.sum(2,0), 2);
      assertEquals(calculator.sum(2,1), 3);
      assertEquals(calculator.sum(2,2), 4);
    } catch (RemoteException e) {
      System.out.print("Error: " + e.getMessage());
    }
  }

}
