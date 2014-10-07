package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import wahllokal.presentation.view.StimmzettelStartView;

/**
 * Controller der immer dann zum Einsatzt kommt, wenn
 * der Nutzer den eingegebenen BerechtigungsCode per
 * Button bestaetigen will.
 * @author Johann
 *<p>Zuletzt bearbeitet: 23.06.2011</p>
 */
public class StimmzettelStartController {

  /**
   * Einziger Konstruktor dieser Klasse.
   * @param view Das View, zu dem dieser Controller gehoert.
   */
  public StimmzettelStartController(final StimmzettelStartView view) {
    
    view.getWeiter().addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
        
        boolean find = view.searchActiveWaehler(
            view.getCodeInputField().getText());
        if (find) {
          
          view.showStimmzettel();
          view.getMainFrame().dispose();
          
        } else {
          
          view.showErrorLabel("Falscher Code", true);
        }

        
      }
    });
    
  }
  
}
