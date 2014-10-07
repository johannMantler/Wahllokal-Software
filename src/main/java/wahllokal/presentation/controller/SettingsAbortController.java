package wahllokal.presentation.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import wahllokal.presentation.view.SettingsView;


/**
 * Controller, der immer dann zum Einsatz kommt, wenn
 * das SettingsView geschlossen wird.
 * @author Johann
 * Zuletzt bearbeitet: 08.06.2011
 */
public final class SettingsAbortController {
	
	/**
	 * Einziger Konstruktor dieser Klasse.
	 * @param view Die SettingsView, dem dieser Controller gehoert.
	 */
  public SettingsAbortController(final SettingsView view) {
		  
    
    view.getAbort().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	      System.exit(0);
      }
    });
	
    view.getDialog().addWindowListener(new WindowAdapter() {
 
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  
  } // Konstruktor ende
  

} // class end
