package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import wahllokal.presentation.view.InfoView;

/**
 * Controller, der dem InfoView gehoert
 * und immer dann zum Einsatz kommt, wenn der Nutzer
 * Interaktionen mit der InfoView-GUI taetigt.
 * @author Johann 
 * <p>Zuletzt bearbeitet: 26.06.2011</p>
 */
public class InfoController {

  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Die InfoView, dem dieser Controller gehoert.
   */
  public InfoController(final InfoView view) {
    
    view.getMainFrame().addWindowListener(new WindowAdapter() {
       
      @Override
      public void windowClosing(WindowEvent e) { //Wahlhelfer klickt auf X
      
        view.getMainFrame().dispose();
      }
      
    });
    
    view.getAbort().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      
        view.getMainFrame().dispose();
      }
      
    });
    
  } //Konstruktor
  
} //class end
