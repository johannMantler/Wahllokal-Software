package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import wahllokal.presentation.view.HelpView;

/**
 * Controller, der immer dann zum Einsatz kommt, wenn
 * das HelpView geschlossen wird.
 * @author Johann
 * Zuletzt bearbeitet: 25.06.2011
 */
public final class HelpController {
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Die HelpView, dem dieser Controller gehoert.
   */
  public HelpController(final HelpView view) {
      
    
    view.getAbort().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        view.getMainFrame().dispose();
      }
    });
  
    view.getMainFrame().addWindowListener(new WindowAdapter() {
 
      @Override
      public void windowClosing(WindowEvent e) {
        view.getMainFrame().dispose();
      }
    });
  
  } // Konstruktor ende
  

} // class end
