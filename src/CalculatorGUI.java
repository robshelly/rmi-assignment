
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * GUI for the Calculator Client
 * 
 * @author Rob Shelly
 *
 */
public class CalculatorGUI implements ActionListener {

  private CalculatorClient client;
  private static String[] operators = { "+", "-", "*", "/" };

  private JFrame frame;
  private JTextArea serverResponse;
  
  // Display components
  private String[] labels = {"operator", "operand", "space", "answer"};
  private Map<String, JLabel> inputs = new HashMap<String, JLabel>();
  
  // Buttons
  private String[] btns = {"/","7","8","9","*","4","5","6","-","1","2","3","+","0"};
  
  
  private Boolean clearOnNextInput;
  
   /**
   * Main method for the GUI.
   */
   public static void main(String[] args) {
   CalculatorGUI c = new CalculatorGUI();
   }
  
  public CalculatorGUI() {

    makeFrame();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Create client.
    try {
      client = new CalculatorClient();
    } catch (MalformedURLException | RemoteException | NotBoundException e) {
      serverResponse.append("Error contacting remote server\n");
    }
    
    // Get welcome message from server.
    try {
      serverResponse.append(client.getMessage() + "\n");
    } catch (RemoteException e) {
      serverResponse.append("Error contacting remote server. Server unavailable\n.");
    }
    inputs.get("operand").setText("");
    clearOnNextInput = false;
  }

  /**
   * Build the UI. 
   */
  private void makeFrame() {
    frame = new JFrame("Calculator");

    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BorderLayout(8, 8));
    contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    
    // Panel for displaying the maths expression
    JPanel inputPanel = new JPanel(new GridLayout(1, 5));
    contentPane.add(inputPanel, BorderLayout.NORTH);
    
    for(int i=0; i< labels.length; i++){
      JLabel lbl = new JLabel(" ");
      lbl.setFont (lbl.getFont().deriveFont (16.0f));
      inputs.put(labels[i], lbl);
      inputPanel.add(lbl);
    }
    
    
    // Text Area for messages from server
    serverResponse = new JTextArea();
    serverResponse.setEditable(false);
    serverResponse.setRows(5);
    serverResponse.setLineWrap(true);
    serverResponse.setWrapStyleWord(true);

    JScrollPane scroll = new JScrollPane(serverResponse);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(scroll, BorderLayout.CENTER);

    contentPane.add(serverResponse, BorderLayout.SOUTH);
    
    // Want button panel grid in 4 rows with 4 columns,
    int r = 0;
    int c = 0;
    
    // Button Panel
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    // Add single cell buttons to the  button panel
    for(int i=0; i< btns.length; i++){      
      // Create button
      JButton button = new JButton(btns[i]);
      button.addActionListener(this);
      // Create Grid bag contraints for button
      GridBagConstraints gbc_btn = new GridBagConstraints();
      gbc_btn.fill = GridBagConstraints.HORIZONTAL;
      gbc_btn.gridx = r;
      gbc_btn.gridy = c;
      // Update grid cell,
      r++;
      if (r ==4) {
        r = 0;
        c ++;
      }
      // Add button
      buttonPanel.add(button, gbc_btn); 
    }    
    // Now add the submit button to take 2 grid cells.
    JButton submit = new JButton("Submit");
    submit.addActionListener(this);
    GridBagConstraints gbc_submit = new GridBagConstraints();
    //gbc_submit.fill = GridBagConstraints.HORIZONTAL;
    gbc_submit.gridwidth = 2;
    gbc_submit.gridx = r;
    gbc_submit.gridy = c;
    buttonPanel.add(submit, gbc_submit);
        
    contentPane.add(buttonPanel, BorderLayout.CENTER);
    frame.pack();
  }

  /**
   * Handle interface action.
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();

    // Submit Button Hit
    if (command == "Submit") {
      // Set second operator
      setClientOperand(2);
      // Clear operator and operand display
      inputs.get("operand").setText("");
      inputs.get("operator").setText("");
      // Leave answer displayed, but clear it when new input begins
      clearOnNextInput = true;
      // Get the answer
      try {
        inputs.get("answer").setText(String.valueOf(client.submit()));
      } catch (RemoteException e1) {
        serverResponse.append("Error contacting remote server\n");
      } catch (IllegalArgumentException e2) {
        serverResponse.append("Error: Division by Zero\n");
      }
      
    //  Operator Hit
    } else if (Arrays.asList(operators).contains(command)) {
      // If beginning new input, clear the answer display.
      if (clearOnNextInput) clearAnswer();
      // Set first operand then clear the operator display
      setClientOperand(1);
      inputs.get("operand").setText("");
      // Set the operator.
      setOperator(command);
      
    // Operand Hit
    } else {
      if (clearOnNextInput) clearAnswer();
      inputs.get("operand").setText(
          inputs.get("operand").getText() + command);
    }
  }
  
  /**
   * Set operator, both the display and client.
   *
   * @param operator The value to set.
   */
  private void setOperator(String operator) {
    client.setOperator(operator);
    inputs.get("operator").setText(operator);
  }
  
  /**
   * Set an operand.
   * 
   * @param operandNum The operand to set, 1 or 2.
   */
  private void setClientOperand(int operandNum) {
     int operand;
     if (inputs.get("operand").getText() == "") {
       operand = 0;       
     } else {
       operand = Integer.parseInt(inputs.get("operand").getText());
     }
     if (operandNum == 1) client.setOperand1(operand);
     else client.setOperand2(operand);
     
  }

  /**
   * Clear the answer.
   */
  private void clearAnswer() {
    inputs.get("answer").setText("");
    clearOnNextInput = false;
  }
  
 
}
