package wahllokal.presentation.controller;

import wahllokal.presentation.view.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Horcht auf Suchanfragen des Wahlhelfers.
 * Delegiert die Suchanfrage an das MainModel und
 * gibt dem SearchView das Ergebnis bekannt.
 * @author Johann
 * <p>zuletzt bearbeitet: 17.06.2011</p>
 */
public final class SearchController {

	/**
	 * Einziger Konstruktor dieser Klasse.
	 * @param view Das SearchView dem dieser Controller gehoert.
	 */
	public SearchController(final SearchView view) {

    view.getCheckButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        
        String []daten = view.getWaehlerDaten();
        
        if (null != daten) {  //Waehler wurde gefunden...
          
          view.setBerechtigtInfoLabel("Person ist wahlberechtigt", true);
          view.showPerson(daten);
          
        } else {                 // Waehler nicht gefunden
          
            view.setBerechtigtInfoLabel("Person gehört nicht zum Wahllokal.",
                                        false);
        }
      }
    });
    
    view.getHelpButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        view.showHelp();
      }
    });
    
    view.getInfoButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        view.showInfo();
      }
    });
    
	} // Konstruktor ende

} // class end

