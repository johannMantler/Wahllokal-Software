package wahllokal.business.close;

import java.util.ArrayList;
import java.util.Hashtable;

import wahllokal.business.settings.SettingsModel;
import wahllokal.business.stimmzettel.CounterModel;
import wahllokal.business.stimmzettel.StimmzettelModel;

/**
 * Diese Klasse fasst das Endergebnis der Stimmzettelauswertung
 * in einer Datenstruktur zusammen und stellt dieses Ergebnis
 * anderern Fremdsystemen zur Verfuegung.
 * @author Johann
 * <p>zuletzt bearbeitet: 26.06.2011</p>
 */
public class CloseModel implements ITeilergebnis {
  
  /** Datenstruktur, welche das Teilergebniss der Erststimmen fuer dieses
   * Wahllokal zusammenfasst. */
  private ArrayList<Hashtable<String, String>> erstStimmenResult;
  
  /** Datenstruktur, welche das Teilergebniss der Zweitstimmen fuer dieses
   * Wahllokal zusammenfasst. */
  private ArrayList<Hashtable<String, String>> zweitStimmenResult;
  
  /**
   * Einziger Konstruktor dieser Klasse, der die Initialisierung
   * und das setzen der Variablen veranlasst.
   */
  public CloseModel() {
    
    this.erstStimmenResult = new ArrayList<Hashtable<String, String>>();
    this.zweitStimmenResult = new ArrayList<Hashtable<String, String>>();
    
    this.setFirstVotes(this.erstStimmenResult);
    this.setSecondVotes(this.zweitStimmenResult);
    
  }
  
  /**
   * Liefert das Endergebnis der Erststimmenauswahl von diesem 
   * Wahllokal als Datenstruktur einer ArrayList von HashTables.
   * @return Das Ergebnis.
   */
  public final ArrayList<Hashtable<String, String>> getFirstVotes() {
    
    return this.erstStimmenResult;
  }

  /**
   * Diese Methode setzt die, im Parameter uebergebene Datenstruktur,
   * auf das Endergebnis der Erststimmenauswahl fuer dieses Wahllokal.
   * @param firstVotes Die Datenstruktur als ArrayList von HashTables
   */
  public final void setFirstVotes(
      ArrayList<Hashtable<String, String>> firstVotes) {
    
    this.setVotes(firstVotes, true);
  }

  /**
   * Liefert das Endergebnis der Zweitstimmenauswahl von diesem 
   * Wahllokal als Datenstruktur einer ArrayList von HashTables.
   * @return Das Ergebnis.
   */
  public final ArrayList<Hashtable<String, String>> getSecondVotes() {
    
    return this.zweitStimmenResult;
  }

  /**
   * Diese Methode setzt die, im Parameter uebergebene Datenstruktur,
   * auf das Endergebnis der Zweitstimmenauswahl fuer dieses Wahllokal.
   * @param secondVotes Die Datenstruktur als ArrayList von HashTables
   */
  public final void setSecondVotes(
      ArrayList<Hashtable<String, String>> secondVotes) {
    
    this.setVotes(secondVotes, false);
  }
  
  /**
   * Interne Hilfsmethode, die das Endergebnis von Erst-oder Zweitstimmen-
   * auswahl mit Hilfe anderer Modelklassen zusammenfasst.
   * @param votes Die Datenstruktur als ArrayList von HashTables
   * @param erstStimme Wenn true, handelt es sich um die Erststimme, bei
   * false um die Zweitstimme.
   */
  private void setVotes(ArrayList<Hashtable<String, String>> votes,
                        boolean erstStimme) {
    
    StimmzettelModel stimmzettelModel = StimmzettelModel.getInstance();
    int zeilenAnzahl = stimmzettelModel.getZeilenAnzahl();
    CounterModel counterModel = CounterModel.getInstance();
    SettingsModel settingsModel = SettingsModel.getInstance();
    String wkNr = settingsModel.getKreisNr();
    String wbNr = settingsModel.getBezirkNr(); 
    Hashtable<String, String> hashTable;
    ArrayList<Integer> erstStimmen = counterModel.getCounter(true);
    ArrayList<Integer> zweitStimmen = counterModel.getCounter(false);
    
    for (int i = 0; i < zeilenAnzahl; i++) {
      
      hashTable = new Hashtable<String, String>();
      
      if (erstStimme) {
      
          hashTable.put("direktkandidat", stimmzettelModel.getNachNamen(i)
                                          +
                                          ", " 
                                          +
                                          stimmzettelModel.getVorNamen(i));
          hashTable.put("stimmen", erstStimmen.get(i).toString());
      
      } else {
        
          hashTable.put("stimmen", zweitStimmen.get(i).toString());
      }
      hashTable.put("partei", stimmzettelModel.getParteiKurz(i));
      hashTable.put("wk", wkNr);
      hashTable.put("wb", wbNr);
      votes.add(hashTable);
    
    } // for (..
  }

} //class end
  
