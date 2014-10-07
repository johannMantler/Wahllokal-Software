package wahllokal.presentation.controller;


import wahllokal.presentation.view.SettingsView;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Controller, der einem SettingsView gehoert und von diesem
 * die Nutzereingaben ueberprueft.
 * @author Johann
 * Zuletzt bearbeitet: 08.06.2011
 */
public final class SettingsCheckInputController {


  /**
   * Einziger Konstruktor dieser Klassen, der allen Textfeldern
   * einen DocumentListener hinzufuegt.
   * @param view Das SettingsView von dem die Textfelder kommen.
   */
  public SettingsCheckInputController(final SettingsView view) {

    view.getOk().setEnabled(false); //Anfangs ist der Button nicht klickbar.
                                    //Jetzt die Listener den Textfeldern die
                                    //geprueft werden sollen hier hinzufuegen:
    view.getKreisNr().getDocument().addDocumentListener(
        documentListener(view));

    view.getBezirkNr().getDocument().addDocumentListener(
        documentListener(view));

    view.getKabinenZahl().getDocument().addDocumentListener(
        documentListener(view));
  }

  /**
   * Erzeugt und liefert einen Listener, der auf Veraenderungen in
   * allen Textfeldern des SettingsView reagiert.
   * @param view Referenz auf das SettingsView.
   * @return dL der DocumentListener.
   */
  private DocumentListener documentListener(final SettingsView view) {

    DocumentListener dL = new DocumentListener() {

      public void changedUpdate(final DocumentEvent documentEvent) {
        //leer
      }

  	  public void insertUpdate(final DocumentEvent documentEvent) {

  	    this.check();
  	    
  	  } // insertUpdate(..

  	  public void removeUpdate(final DocumentEvent documentEvent) {
  	    
        this.check();
        
  	  } // removeUpdate(..
      
  	  private void check() {
  	    
        view.setDefaultInfoLabel(); //Annahme: anfangs ist alles Ok
        
        if (!(this.isNumberCheck(view.getKreisNr()) //irgendwo keine Zahl?
              &&
              this.isNumberCheck(view.getBezirkNr())
              &&
              this.isNumberCheck(view.getKabinenZahl()))) {
              
              //falls in irgendeinem Textfeld keine Zahl ist
              //(leeres Textfeld wird ignoriert), dann gib Fehlermeldung
              view.setErrorInfoLabel("Nur Zahlen 0-9 sind erlaubt");
            
        } else  //überall sind Zahlen, aber irgendwo ist leer: 
            if (view.getKreisNr().getText().isEmpty()
                ||
                view.getBezirkNr().getText().isEmpty()
                ||
                view.getKabinenZahl().getText().isEmpty()) {
                
                  //Button kann nicht geklickt werden:
                  view.getOk().setEnabled(false);
            
            }
  	  } //void check() {...
      /**
       * Prueft, ob im Textfeld nur Zahlen sind.
       */
      private boolean isNumberCheck(JTextField textField) {

        boolean result = false;
        
        if (textField.getText().matches("[0-9]*")) {
          
          result = true;
        
         }
        return result;
      }

  	}; // end Innerclass
    return dL;
  }

} //class end
