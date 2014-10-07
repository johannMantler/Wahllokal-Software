package wahllokal.presentation.controller;

import wahllokal.presentation.view.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Controller, der immer dann zum Einsatz kommt, wenn die SearchView
 * geschlossen wird.
 * Ruft dann das SearchModel auf, welches alles weitere veranlasst.
 * @author Johann
 * <p>zuletzt bearbeitet: 17.06.2011</p>
 */
public final class SearchClosingController {

  /**
   * Einzieger Konstruktor dieser Klasse.
   * @param view Das SearchView, dem dieser Controller gehoert.
   */
  public SearchClosingController(final SearchView view) {

  	final JFrame frame = view.getMainFrame();
    frame.addWindowListener(new WindowAdapter() {
    	 
      @Override
      public void windowClosing(WindowEvent e) { //Wahlhelfer klickt auf X
      
        showConfirmDialog(view);   
      }
    });
    
    view.getCloseButton().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      
        showConfirmDialog(view);
      }
      
    });
    
  } //Konstruktor Ende
  
  /**
   * Zeigt einen Dialog, der den Nutzer nochmals abfragt
   * ob dieser auch wirklich das Lokal schlieszen moechte.
   * @param view Die view zudem dieser Dialog modal sein soll.
   */
  private void showConfirmDialog(SearchView view) {
    
    JFrame frame = view.getMainFrame();
    switch(JOptionPane.showConfirmDialog(
        
        frame,
        "Wenn die Wahllokal-Software geschlossen wird, " 
        +
        "wird die Stimmzählung durchgeführt und es " 
        +
        "können keine weiteren Stimmzettelabgaben mehr gemacht werden!\n" 
        +
        "Wahllokal-Software schließen?",
        "Wahllokal schließen",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE)) {
    
      case JOptionPane.CLOSED_OPTION: break;
      case JOptionPane.YES_OPTION: 
        
        view.closeWahllokal();    
        showEndMessage(); break;
        
      case JOptionPane.NO_OPTION: break;
      default: break;
    }
    
  }
  
  /**
   * Beim Beenden wird ein Abschluss info diaglog angezeigt.
   */
  private void showEndMessage() {
    
  	JOptionPane.showMessageDialog(
  			null, 
  			"Die Stimmzählung ist nun abgeschlossen." 
  			+
  			"\nDanke!",
  			"Stimmzählung erfolgreich!",
  	    JOptionPane.INFORMATION_MESSAGE);
  
  	System.exit(0);
  
  }
  
} //Klasse Ende
