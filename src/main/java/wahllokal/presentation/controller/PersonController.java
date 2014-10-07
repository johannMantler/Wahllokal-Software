package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import wahllokal.presentation.view.PersonView;

/**
 * Controller, der dem PersonView gehoert
 * und immer dann zum Einsatz kommt, wenn der Nutzer
 * Interaktionen mit der PersonView-GUI taetigt.
 * @author Johann 
 * <p>erstellt am: 05.06.2011</p>
 * <p>Zuletzt bearbeitet: 17.06.2011</p>
 */
public class PersonController {
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Die PersonView, dem dieser Controller gehoert.
   */
  public PersonController(final PersonView view) {
    
    view.getDialog().addWindowListener(new WindowAdapter() {
       
      @Override
      public void windowClosing(WindowEvent e) { //Wahlhelfer klickt auf X
      
        view.getDialog().dispose(); //beendet den Dialog
      }
      
    });
    
    view.getAbort().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      
        view.getDialog().dispose(); //beendet den Dialog
      }
      
    });
    
    view.getWeiter().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
      
        view.setWaehlerActive();
        view.showPersonKey(true);
        view.getWeiter().setEnabled(false);
        view.getAbort().setText("OK");
      }
      
    });
    
  } //Konstruktor
  
} //end class
