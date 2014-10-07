/**
 * 
 */
package wahllokal.presentation.controller;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import wahllokal.presentation.view.StimmzettelStartView;

/**
 * @author Johann
 * <p>Zuletzt bearbeitet: 22.06.2011</p>
 */
public class SStartCheckInputController {
  
  
  /**
   * Der einzige Konstruktor dieser Klasse.
   * @param view Das StimmzettelStartView, dem dieser Controller gehoert.
   */
  public SStartCheckInputController(final StimmzettelStartView view) {

    //erlaubte Eingaben: Zahlen only
    view.getCodeInputField().getDocument().
         addDocumentListener(documentListener(view.getCodeInputField(),
                                              view));
  }
  
  /**
   * Erzeugt und liefert einen Listener, der auf Veraenderungen im
   * EingabeTextfeld des StimmzettelStartView reagiert.
   * @param textField Das Textfeld, welches ueberwacht werden soll.
   * @param view Referenz auf das StimmzettelView.
   * @return dL der DocumentListener.
   */
  private DocumentListener documentListener(final JTextField textField,
                                            final StimmzettelStartView view) {

    DocumentListener dL = new DocumentListener() {

      public void changedUpdate(final DocumentEvent documentEvent) {
        //leer
      }

      public void insertUpdate(final DocumentEvent documentEvent) {
      
        this.isNumberCheck(); //nur Zahlen im Textfeld?
        
      } // insertUpdate(..

      public void removeUpdate(final DocumentEvent documentEvent) {
        
 
        if (!textField.getText().equals("")) {   //Feld nicht leer?
              
          this.isNumberCheck(); //nur Zahlen?
            
        } else {
          
          isOK(true); //errormessage entfernen
          view.getWeiter().setEnabled(false); //aber trotzdem deablen
        }

      } // removeUpdate(..
      
      /**
       * Prueft, ob im Textfeld nur Zahlen sind.
       */
      private void isNumberCheck() {
        
        if (textField.getText().matches("[0-9]*")) {
          
          this.isOK(true);
        
        } else {
            
            this.isOK(false);
          
        }
      }
      /**
       * Wertet die Veraenderungen aus.
       * @param ok boolscher Wert fuer Eingabe-ok-oder-nicht Angabe.
       */
      private void isOK(final boolean ok) {

        if (ok) {
           view.showErrorLabel("you don't see me", false);
           view.getWeiter().setEnabled(true);

        } else {
          
            view.showErrorLabel("Nur Zahlen 0-9 sind erlaubt", true);
            view.getWeiter().setEnabled(false);
        }
      }
    }; // end Innerclass
    return dL;
  }

}
