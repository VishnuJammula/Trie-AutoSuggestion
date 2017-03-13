import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JList;
public class GUI {
	static AutoSuggest auto = new AutoSuggest();  // initialisng trie data structure;
	static JList list = new JList(); // creating list for displaying suggestions
	
    public static void main(String args[]) 
    {
    final JFrame frame = new JFrame("Auto Suggest Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JTextField textField = new JTextField();
    frame.add(textField, BorderLayout.NORTH);
	

    DocumentListener documentListener = new DocumentListener() 
    {
      public void changedUpdate(DocumentEvent documentEvent) 
      {
        printIt(documentEvent);
      }
      public void insertUpdate(DocumentEvent documentEvent) 
      {
        printIt(documentEvent);
      }
      public void removeUpdate(DocumentEvent documentEvent) 
      {
        printIt(documentEvent);
      }
      private void printIt(DocumentEvent documentEvent) 
      {
    	  String s = textField.getText();
    	  int length = s.length();
    	  if(length==0)
    	  {
    		  AutoSuggest.model.clear();
    		  list.removeAll();
    	  }
    	  else if(length>0)
    	  {
    		  auto.printAllWordsWithPrefix(AutoSuggest.root,s);
    		  list.setModel(AutoSuggest.model);
        	  frame.add(list, BorderLayout.CENTER);
    	  }
    	  	  
    	}
    };
    
    textField.getDocument().addDocumentListener(documentListener);
    frame.setPreferredSize(new Dimension(450,450));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}