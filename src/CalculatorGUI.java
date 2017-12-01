

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.Response;

/**
 * GUI for the Calculator Client
 * 
 * @author Rob Shelly
 *
 */
public class CalculatorGUI implements ActionListener {
  
  private CalculatorClient client;
  private static String[] operators = {"+","-","*","/"};
  
  private JFrame frame;
  private JTextArea serverResponse;
  private Boolean firstOperandSet;

  /**
   * Main method to nun the GUI
   */
  public static void main(String[] args) {
    CalculatorGUI c = new CalculatorGUI();
  }
  
  public CalculatorGUI () {
    
    makeFrame();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    try {
      client = new CalculatorClient();
    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      serverResponse.append("Error contacting remote server");
    }
    firstOperandSet = false;
  }
  
  private void makeFrame()
  {
    frame = new JFrame("Calculator");
    
    JPanel contentPane = (JPanel)frame.getContentPane();
    contentPane.setLayout(new BorderLayout(8, 8));
    contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));


//    JPanel display = new JPanel();
//    display.setLayout(new BorderLayout());
//    
//    expression = new JTextField();
//    // TODO Enable this line for handup
//    // expression.setEditable(false);
//    expression.setBackground(Color.WHITE);
//    display.add(expression, BorderLayout.NORTH);
    
    
    serverResponse = new JTextArea("Welcome\n");
    serverResponse.setEditable(false);
    serverResponse.setRows(5);
    serverResponse.setLineWrap(true);
    serverResponse.setWrapStyleWord(true);
    
    JScrollPane scroll = new JScrollPane(serverResponse);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(scroll, BorderLayout.CENTER);
        
    contentPane.add(serverResponse, BorderLayout.SOUTH);

    JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
    // Top Row
    addButton(buttonPanel, "/");  
    addButton(buttonPanel, "7");
    addButton(buttonPanel, "8");
    addButton(buttonPanel, "9");
    // New Row
    addButton(buttonPanel, "*");
    addButton(buttonPanel, "4");
    addButton(buttonPanel, "5");
    addButton(buttonPanel, "6");
    

    // New Row
    addButton(buttonPanel, "-");
    addButton(buttonPanel, "1");
    addButton(buttonPanel, "2");
    addButton(buttonPanel, "3");
    
    //New Row
    addButton(buttonPanel, "+");
    addButton(buttonPanel, "0");
    addButton(buttonPanel, "Submit");
    contentPane.add(buttonPanel, BorderLayout.CENTER);
    
    frame.pack();
  }

  /**
   * Add a button to the button panel.
   */
  private void addButton(Container panel, String buttonText)
  {
    JButton button = new JButton(buttonText);
    button.addActionListener(this);
    panel.add(button);
  }
  
  
  
  /**
   * Handle interface action.
   */
  public void actionPerformed(ActionEvent event)
  {
    String command = event.getActionCommand();
    
    if (command == "Submit") {
      try {
        serverResponse.setText(String.valueOf(client.submit()));
      } catch (RemoteException e) {
        serverResponse.append("Error contacting remote server");
      }
    } else if (Arrays.asList(operators).contains(command)) {
      client.setOperator(command);
    } else {
      if (!firstOperandSet) {
        client.setOperand1(Integer.parseInt(command));
        firstOperandSet = true;
      }
      else {
        client.setOperand2(Integer.parseInt(command));
      }
    }
    
    
  }
}
