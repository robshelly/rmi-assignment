

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
  
  /**
   * Returns a Hello message from the server.
   * 
   * @return A hello message for a client.
   * @throws RemoteException
   */
  String getMessage() throws RemoteException;  
  
  /**
   * Calculates the sum of two integers.
   * 
   * @param augend The first integer.
   * @param addend The second integer.
   * @return The sum of the augent and the addend.
   * @throws RemoteException
   */
  int sum(int augend, int addend) throws RemoteException;
  
  /**
   * Calculate difference between two integers.
   * 
   * @param minuend The first integer.
   * @param subtrahend The second integer.
   * @return The difference between the minuend and subtrahend.
   * @throws RemoteException
   */
  int difference(int minuend, int subtrahend) throws RemoteException;
  
  /**
   * Calculates the product of two integers.
   * 
   * @param multiplicand  The first of the integers.
   * @param multiplier The second of the integers.
   * @return The product of the multiplicand and the multiplier.
   * @throws RemoteException
   */
  int product(int multiplicand, int multiplier) throws RemoteException;
  
  /**
   * The quotient of two integers.
   * 
   * @param dividend The first of the two integers.
   * @param divisor The second of the two integers. 
   * @return The dividend divided by the divisor.
   * @throws RemoteException
   */
  int quotient(int dividend, int divisor) throws RemoteException;
}
