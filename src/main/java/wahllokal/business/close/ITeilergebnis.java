package wahllokal.business.close;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Definiert die Schnittstelle zwischen diesem System und
 * dem Fremdsystem Wahlergebnis.
 * Das Wahlergebnis bekommt von diesem System Daten gemaesz
 * dieser Schnittstellendefinition.
 * @author Johann
 * <p>zuletzt bearbeitet: 26.06.2011</p>
 */
public interface ITeilergebnis {

  //HashtableStruktur (key,value):
  
  //  direktkandidat, "name"
  //  partei, "name"
  //  stimmen, "anzahl"
  //  wk, "WahlkreisNr"
  //  wb, "WahlbezirkNr"
  
  /**
   * Liefert das Endergebnis der Erststimmenauswahl von diesem 
   * Wahllokal als Datenstruktur einer ArrayList von HashTables.
   * @return Das Ergebnis.
   */
  ArrayList<Hashtable<String, String>> getFirstVotes();
  
  /**
   * Diese Methode setzt die, im Parameter uebergebene Datenstruktur,
   * auf das Endergebnis der Erststimmenauswahl fuer dieses Wahllokal.
   * @param firstVotes Die Datenstruktur als ArrayList von HashTables
   */
  void setFirstVotes(ArrayList<Hashtable<String, String>> firstVotes);
  
  /**
   * Liefert das Endergebnis der Zweitstimmenauswahl von diesem 
   * Wahllokal als Datenstruktur einer ArrayList von HashTables.
   * @return Das Ergebnis.
   */
  ArrayList<Hashtable<String, String>> getSecondVotes();
  
  /**
   * Diese Methode setzt die, im Parameter uebergebene Datenstruktur,
   * auf das Endergebnis der Zweitstimmenauswahl fuer dieses Wahllokal.
   * @param secondVotes Die Datenstruktur als ArrayList von HashTables
   */
  void setSecondVotes(ArrayList<Hashtable<String, String>> secondVotes);
  
} //interface end
