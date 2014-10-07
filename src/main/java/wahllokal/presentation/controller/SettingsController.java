package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import wahllokal.presentation.view.SettingsView;

/**
 * Controller, der die Nutzereingaben des SettingsView entgegennimmt und
 * alles weitere veranlasst.
 * @author Johann
 * <p>zuletzt bearbeitet: 18.06.2011</p>
 */
public final class SettingsController {
	
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Das SettingsView, dem dieser Controller gehoert.
   */
  public SettingsController(final SettingsView view) {

	view.getOk().addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		 
		  // Daten an das Model weitergeben
      view.dataToModel();
		  
		  // SettingsView schlieszen
		  view.getDialog().dispose();
		  
		  // Die Stimmzettel starten
      view.showStimmzettel();
		  
		}
	});

  } //Konstruktor
  
} //class end
