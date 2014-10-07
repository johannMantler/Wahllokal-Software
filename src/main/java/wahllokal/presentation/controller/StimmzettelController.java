package wahllokal.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import wahllokal.presentation.view.StimmzettelStartView;
import wahllokal.presentation.view.StimmzettelView;

/**
 * Controller, der die Nutzereingaben in der Stimmzettel-GUI
 * entgegennimmt, und die notwendigen Schritte weiterleitet.
 * @author Johann
 * <p>Zuletzt bearbeitet: 21.06.2011</p>
 */
public class StimmzettelController {

  /** Referenz der Ansicht(view) zu diesem Controller. */
  private StimmzettelView view;
  
  /** Hilfsvariable, um zu ermitteln ob die Auswahl getroffen wurde. */
  private boolean erstAuswahlGetroffen = false;
  
  /** Hilfsvariable, um zu ermitteln ob die Auswahl getroffen wurde. */
  private boolean zweitAuswahlGetroffen = false;
  
  /**
   * Einziger Konstruktor dieser Klasse.
   * @param stimmzettelView Der Stimmzettel.
   */
  public StimmzettelController(final StimmzettelView stimmzettelView) {
  
    this.view = stimmzettelView;
    
    this.addButtonGroup(this.view.getErstStimmeButtonList());
    this.addButtonGroup(this.view.getZweitStimmeButtonList());
    
    this.addListenerToConfirmButton();
    
    // Nutzer darf erst Auswahl bestaetigen wenn
    // er den Stimmzettel korrekt ausgefuellt hat.
    this.view.getConfirmButton().setEnabled(false);
   
    // Den RadioButtons der Erststimmen einen Listener hinzufuegen
    this.addListenerToRadioButtons(
         true,
         this.view.getErstStimmeButtonList());
    
    // Den RadioButtons der Zweitstimmen einen Listener hinzufuegen
    this.addListenerToRadioButtons(
         false,
         this.view.getZweitStimmeButtonList());
      
  }
  /**
   * Fuegt jedem Button in der Arraylist einer ButtonGroup hinzu,
   * damit man immer nur einen RadioButton zu einer Zeit aktiv
   * haben kann.
   * @param list die ArrayList mit den RadioButtons
   */
  private void addButtonGroup(ArrayList<JRadioButton> list) {
    
    ButtonGroup group = new ButtonGroup();
    
    for (int i = 0; i < list.size(); i++) {
      
      group.add(list.get(i));
      
    }
  }
  
  /**
   * Gibt dem Confirmbutton der view einen ActionListener.
   * Bei Knopfdruck wird der Stimmzettel ausgewertet, indem
   * die Auswahlen der Erst- und Zweitstimme dem MainModel
   * uebergeben werden.
   */
  private void addListenerToConfirmButton() {
    
    view.getConfirmButton().addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
           
        //Inkrementiere den Zaehler von den Zeilen dessen
        //RadioButtons selektiert sind
        view.count(getAuswahl(
                view.getErstStimmeButtonList()), true);
        
        view.count(getAuswahl(
                view.getZweitStimmeButtonList()), false);
        
        //Entziehe dem Waehler die Berechtigung
        view.deleteActiveWaehler();
        
        //StimmzettelGUI beenden, andere GUI starten
        new StimmzettelStartView();
        view.getMainFrame().dispose();
        
        
      }
    });
    
  }
  
  /** 
   * Holt die Nutzerauswahl, also die Partei/Kandidat die der
   * Nutzer angekreuzt hat, als Zeilenangabe.
   * Es werden alle RadioButtons geprueft, ob diese selected sind.
   * Da aber nur ein Button selected sein kann (Buttongroup)
   * wird auch nur eine Zeilenangabe zurueckgegeben.
   * @param list Die ArrayList mit den JRadioButtons.
   * @return die Zeilenangabe als int.
   */
  private int getAuswahl(ArrayList<JRadioButton> list) {
    
    int zeile = -1;
    
    for (int i = 0; i < list.size(); i++) {
      
      if (list.get(i).isSelected()) {
        
        zeile = i;
      }
      
    }
    return zeile;
  }
  
  /**
   * Fuegt den einzelnen RadioButtons einen ActionListener hinzu.
   * Wenn ein RadioButton aktiviert wird, wird die boolean Variable
   * erstAuswahlGetroffen oder zweitAuswahlGetroffen, je nachdem ob der
   * Button zur Erst - oder Zweitstimme gehoert, auf true gesetzt.
   * Sobald der Nutzer beide Stimmen vergeben hat, also beide boolean
   * Variablen auf true gesetzt sind, wird der 
   * Confirmbutton klickbar(enable).
   * @param erstStimme Angabe, ob die Liste zur Erststimme oder Zweitstimme
   * gehoert.
   * @param liste die ArrayList mit den RadioButtons.
   */
  private void addListenerToRadioButtons(final boolean erstStimme,
                                         final ArrayList<JRadioButton> liste) {
   
   
    for (int i = 0; i < liste.size(); i++) {
      
      liste.get(i).addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
          if (erstStimme) {
            
            erstAuswahlGetroffen = true;
            
          } else {
            
            zweitAuswahlGetroffen = true;
            
          }        
              
          if (erstAuswahlGetroffen && zweitAuswahlGetroffen) {
            
            view.getConfirmButton().setEnabled(true);
            
          }         
        } //actionPerformed(..       
      });
    } //for(..
    
  }

} //class end
