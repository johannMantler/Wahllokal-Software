package wahllokal.business.search;

import java.util.ArrayList;
import java.util.Random;


/**
 * Klasse, die die Waehlerdaten holt, und eine Suchfunktion
 * fuer diese Daten bereitstellt.
 * Die Klasse ist Singelton, damit garantiert wird, dass die Daten wirklich
 * nur ein mal geholt werden koennen. So werden Inkonsistenzen
 * vermieden.
 * @author Johann
 * <p>zuletzt bearbeitet: 16.06.2011</p>
 */
public final class SearchModel {


  /** Der Baum, indem die Waehlerobjekte gespeichert werden.*/
  private BinaerSuchBaum<Waehler> alleWaehler;
  
  /** Die Liste, in dem nur die Waehler sind, die sich auch zurzeit im
   * Wahllokal befinden. Diese Liste enthaelt eine sehr kleine Teilmenge der
   * Daten, die im Baum alleWaehler sind.
   * Darum reicht es auch aus eine Liste zu nehmen, denn die Laufzeit von
   * O(n) ist sogut wie garnicht spuerbar. */
  private ArrayList<Waehler> aktiveWaehler;
  
  /** Referenz auf den Waehler der zuletzt gesucht wurde. */
  private Waehler aktWaehler = null;
  
  /** Hier wird die Anzahl der fuer dieses Wahllokal registrierten
   * Waehler gespeichert. */
  private int waehlerAnzahl = 0;
  
  /** Hier wird die Anzahl der Waehler gespeichert die 
   * schon ihre Stimme abgegeben haben. */
  private int gewaehltAnzahl = 0;
  
  /** Referenz auf einzige Instanz. */
  private static SearchModel soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized SearchModel getInstance() {

    if (soleInstance == null) {

      soleInstance = new SearchModel();

    }
    return soleInstance;
  }
  
  /**
   * Einziger Konstruktor dieser Klasse, der ein SearchModel
   * objekt initialisiert.
   */
  private SearchModel() {
    
    this.alleWaehler = new BinaerSuchBaum<Waehler>();
    this.aktiveWaehler = new ArrayList<Waehler>();
    this.getDaten();
    
  }
  
  /**
   * Holt die Waehlerobjekte.
   * (Solange diese Software eigenstaendig, dh. ohne Fremdsysteme
   * laeuft, werden die Daten aus der WaehlerDummyDaten Klasse geholt)
   * Diese Methode ist bereits Threadsicher, da diese Methode nur
   * vom Konstruktor aufgerufen wird, und dieser Konstruktor wiederrum
   * von der synchronisierten Methode getInstance() aufgerufen wird.
   */
  private void getDaten() {
    
    WaehlerDummyDaten  daten = new WaehlerDummyDaten();
    ArrayList<Waehler> list = daten.getDummyWaehler();
    
    for (int i = 0; i < list.size(); i++) {
    
      this.alleWaehler.einfuegen(list.get(i));
      this.waehlerAnzahl++;
    }
    
  }
  
  /**
   * Sucht einen Waehler nach seiner Personalnummer.
   * @param nummer Die Nummer als String
   * @return den Waehler wenn gefunden, sonst null.
   */
  public Waehler searchWaehler(String nummer) {
    
    this.aktWaehler = null;
    Knoten<Waehler> k = this.alleWaehler.suche(new Waehler(nummer));
    
    if (k != null) {
      
      this.aktWaehler = k.getInhalt();
    }
    
    return this.aktWaehler;
    
  }
  
  /**
   * Fuegt einen Waehler zu der Liste der aktiven Waehler
   * hinzu. Dabei bekommt er einen waehlerKey durch eine Zufallszahl,
   * die kein anderer waehler hat.
   * <p>
   * Voraussetzung: Waehler exisitiert(!= null) und hat auch noch nicht
   * gewaehlt.</p>
   * <p>
   * Vorgehensweise:<br/>
   * 1. Erzeuge Zufallszahl.<br/>
   * 2. Suche, ob es diese Zahl schon gibt.<br/>
   * 3. Wenn Ja, zurueck zu 1.<br/>
   * 4. Waehler bekommt diesen Key.<br/>
   * 5. Fuege Waehler zu den aktiven Waehlern hinzu.<br/>
   * </p>
   * @param w der Waehler.
   */
  public void insertActiveWaehler(Waehler w) {
    
    if (w != null && !w.isHatGewahelt()) {
      
      final int randomLimit = 9999;
      Random ran = new Random();
      int newKey;
      do {
      
        newKey = ran.nextInt(randomLimit);
    
      } while (this.searchKey(new Integer(newKey).toString()));
    
      w.setWaehlerKey(new Integer(newKey).toString());
      this.aktiveWaehler.add(w);
    }
  }
  
  /**
   * Sucht den key in der Liste der aktiven Waehler.
   * @param key der gesucht wird.
   * @return true falls key gefunden, sonst false.
   */
  public boolean searchKey(String key) {
    
    boolean result = false;
    
    if (key != null) {
      
      for (Waehler w : this.aktiveWaehler) {
        
        if (key.equals(w.getWaehlerKey())) {
          
          result = true;
        }
      }
      
    }
    return result;
  }

  /**
   * Gibt den Waehler zurueck, der zuletzt gesucht wurde.
   * @return the aktWaehler
   */
  public Waehler getAktWaehler() {
    return aktWaehler;
  }
  
  /**
   * Loescht einen Waehler aus der Liste der aktiven Waehler.
   * Diese Methode wird immer dann aufgerufen, wenn der Waehler
   * seinen Stimmzettel abgeben hat.
   * <p>Hinweis:<br/>
   * Bevor der Waehler aus dieser Liste entfernt wird, verliert
   * er endgueltig seine Wahlberechtigung</p>
   * @param w Der Waehler der entfernt werden soll.
   */
  public void deleteActiveWaehler(Waehler w) {
    
    if (w != null) {
      
      w.setHatGewahelt(); //zuvor Wahlberechtigung entfernen
      this.gewaehltAnzahl++;
      this.aktiveWaehler.remove(w);
      
    }   
  }
  
  /**
   * Sucht nach einem Waehler mit Hilfe seines Berechtigungscodes
   * in der Liste der aktiven Waehler.
   * <p>Voraussetzung: <br/>
   * Der Schluessel existiert, dh. ist != null</p>
   * @param key Der Schluessel, mit Hilfe dessen der Waehler gesucht wird.
   * @return Referenz auf den gefundenen Waehler, sonst null.
   */
  public Waehler searchActiveWaehler(String key) {
    
    Waehler result = null;
    
    if (key != null) {
      
      for (Waehler w : this.aktiveWaehler) {
        
        if (key.equals(w.getWaehlerKey())) {
          
          result = w;
        }
      }
    
    }
    return result;
  }

  /**
   * Liefert die Anzahl der zurzeit aktiven Waehler.
   * @return Die aktiveWaehler zahl
   */
  public int getAktiveWaehlerAnzahl() { 
    
    int result = 0;
    if (this.aktiveWaehler != null) {
      
      result = this.aktiveWaehler.size();
    }
    
    return result;
  }

  /**
   * Gibt die Anzahl aller fuer dieses Wahllokal registrierten
   * Waehler zurueck.
   * @return the waehlerAnzahl
   */
  public int getGesamtWaehlerAnzahl() {
    return waehlerAnzahl;
  }

  /**
   * Gibt die Anzahl aller Waehler zurueck die gewaehlt haben.
   * @return the gewaehltAnzahl
   */
  public int getGewaehltAnzahl() {
    return gewaehltAnzahl;
  }
  
} //class end
