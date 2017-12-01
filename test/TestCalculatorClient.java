import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.InputMismatchException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestCalculatorClient {
  
  private CalculatorClient calculator = new CalculatorClient("test");
 
  @Test
  void testValidOperatorSum() {
    calculator.setOperator("+");
    assertEquals(calculator.getOperator(), "+");
  }
  
  @Test
  void testValidOperatorDifference() {
    calculator.setOperator("-");
    assertEquals(calculator.getOperator(), "-");
  }
  
  @Test
  void testValidOperatorProduct() {
    calculator.setOperator("*");
    assertEquals(calculator.getOperator(), "*");
  }
  
  @Test
  void testValidOperatorQuotient() {
    calculator.setOperator("/");
    assertEquals(calculator.getOperator(), "/");
  }
  
  @Test
  void testInvalidOperator() {
    Assertions.assertThrows(InputMismatchException.class, () -> {
      calculator.setOperator("^");
    });
  }
  
  @Test
  void testNoOperator() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      calculator.submit();
    });
  }

  

}
