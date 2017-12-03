import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


@SuppressWarnings("serial")
public class ServerGUI extends JFrame {
  
  private JFrame frame; 
  private JTextArea log ;
  
  /**
   * Create the Server GUI.
   * 
   * @param engine
   */
  public ServerGUI() {
    initialize();
    
  }
  
  /**
   * Initialize the contents of the GUI.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    log = new JTextArea();
    log.setEditable(false);
    // Always scroll log to bottom as new text appears.
    DefaultCaret caret = (DefaultCaret)log.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    frame.getContentPane().add(new JScrollPane(log), BorderLayout.CENTER);

    frame.setTitle("Server");
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
  
  public void update(String str) {
    appendToLog(str);
  }
  
  
  /**
   * Append text to the log.
   * 
   * @param txt The text to append.
   */
  public void appendToLog(String txt) {
    log.append(txt + "\n");
  }

}
