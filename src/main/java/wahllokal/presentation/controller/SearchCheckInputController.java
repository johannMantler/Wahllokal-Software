package wahllokal.presentation.controller;

import wahllokal.presentation.view.SearchView;



import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Controller der die Nutzereingaben auf der GUI: SearchView ueberprueft.
 * @author Johann
 * zuletzt bearbeitet: 07.06.2011
 */
public final class SearchCheckInputController {

  /**
   * Der einzige Konstruktor dieser Klasse.
   * @param view Das SearchView, dem dieser Controller gehoert.
   */
  public SearchCheckInputController(final SearchView view) {

    //erlaubte Eingaben: Zahlen only
    view.getInputTextField().getDocument().
         addDocumentListener(documentListener(view.getInputTextField(),
        		                                  view));
	}

	/**
	 * Erzeugt und liefert einen Listener, der auf Veraenderungen im
	 * EingabeTextfeld des SearchView reagiert.
	 * @param textField Das Textfeld, welches ueberwacht werden soll.
	 * @param view Referenz auf das SearchView.
	 * @return dL der DocumentListener.
	 */
  private DocumentListener documentListener(final JTextField textField,
  		                                      final SearchView view) {

  	DocumentListener dL = new DocumentListener() {

  	  public void changedUpdate(final DocumentEvent documentEvent) {
  	    //leer
  	  }

  	  public void insertUpdate(final DocumentEvent documentEvent) {
      
  	    this.isNumberCheck(); //nur Zahlen im Textfeld?
  	    
  	  } // insertUpdate(..

  	  public void removeUpdate(final DocumentEvent documentEvent) {
  	    view.setDefaultInfoLabel();
 
        if (!textField.getText().equals("")) {   //Feld nicht leer?
  		        
          this.isNumberCheck(); //nur Zahlen?
  		      
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
  	    	view.setDefaultInfoLabel();

  	    } else {
  	    	  view.setErrorInfoLabel("Nur Zahlen 0-9 sind erlaubt");
  	    }
  	  }
  	}; // end Innerclass
    return dL;
  }
} // end class
