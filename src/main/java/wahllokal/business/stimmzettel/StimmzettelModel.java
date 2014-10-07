package wahllokal.business.stimmzettel;

import java.util.ArrayList;

/**
* Holt die Daten, welche zum Aufbereiten der Stimmzettel-GUIs gebraucht werden,
* vom Fremdsystem Wahllisten und speichert diese im RAM,
* damit Aenderungen der Daten auszerhalb des Systems keine Auswirkungen
* auf die Stimmzettel haben.
* Diese Klasse ist Singelton, damit die Daten auch wirklich nur ein mal
* vom Fremdsystem geholt werden um Inkonsistenzen zu vermeiden.
* @author Johann
  <p>Zuletzt bearbeitet: 18.06.2011</p>
*/
public final class StimmzettelModel {

  /** Die Daten liegen in einer ArrayList. */
  private ArrayList<StimmzettelZeile> daten;

  // Hier sind lediglich lesender Zugriffe auf die Daten.
  // Sollte sich das aendern muss man eine synchronisierte
  // Methode verwenden.
  // List list = Collections.synchronizedList(new ArrayList(...));
  
  
  /** Referenz auf einzige Instanz. */
  private static StimmzettelModel soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized StimmzettelModel getInstance() {

    if (soleInstance == null) {

      soleInstance = new StimmzettelModel();

    }
    return soleInstance;
  }

  /**
   * Einziger Konstruktor dieser Klasse.
   * Hier werden auch die Daten geholt.
   * Solange kein Fremdystem mit diesem System
   * kooperiert, wird die DummyDaten Klasse
   * benutzt.
   */
  private StimmzettelModel() {
    
    this.getDaten();
    
  }
  
  /**
   * Methode, welche die Daten von der Schnittstelle
   * IStimmzettel holt, und in eine fuer dieses System
   * relevante Datenstruktur konvertiert.
   */
  private synchronized void getDaten() {
    
    this.daten = new ArrayList<StimmzettelZeile>();
    IStimmzettel externDaten = new StimmzettelDummyDaten();
    
    StimmzettelZeile zeile;
    ArrayList<String> list; //Hilfsvariable fuer zweitKandidaten
    boolean finish = false;
    
    //Da die Anzahl der Daten vom Fremdsystem ungewiss ist
    //(es ist keine anzahlMethode in der Schnittstellenbeschreibung
    //definiert), werden die Daten solange eingelesen, bis
    // eine Exception geworfen wird.
    for (int i = 0; !finish; i++) {
      
      try {
        
        zeile = new StimmzettelZeile();
        zeile.setVorName(externDaten.getVorname(i));
        zeile.setNachName(externDaten.getNachname(i));
        zeile.setBeruf(externDaten.getBeruf(i));
        zeile.setOrt(externDaten.getOrt(i));
        zeile.setParteiKurz(externDaten.getParteiKurz(i));
        zeile.setParteiLang(externDaten.getParteiLang(i));
        
        list = new ArrayList<String>();
        for (int j = 0; 
          j < externDaten.getZweitStimmenKandidaten(i).size(); j++) {
          
          list.add(externDaten.getZweitStimmenKandidaten(i).get(j));
        }
        zeile.setZweitStimmeKandidaten(list);
        this.daten.add(zeile);
        
      } catch (ArrayIndexOutOfBoundsException ex) {
        
          finish = true;
      }
    } //for (..
    
  }
  /**
   * Gibt die Anzahl der StimmzettelZeilen
   * wieder.
   * @return die Anzahl als int.
   */
  public int getZeilenAnzahl() {
    return daten.size();
  }
  
  /**
   * Gibt den Nachnamen des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return Nachname als String.
   */
  public String getNachNamen(int zeile) {
   
    return daten.get(zeile).getNachName();    
  }
  
  /**
   * Gibt den Vornamen des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return Vorname als String.
   */
  public String getVorNamen(int zeile) {
    
    return daten.get(zeile).getVorName();   
  }
  
  /**
   * Gibt den Beruf des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return Beruf als String.
   */
  public String getBeruf(int zeile) {
    
    return daten.get(zeile).getBeruf();
  }
  
  /**
   * Gibt den Ort des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return Ort als String.
   */
  public String getOrt(int zeile) {
    
    return daten.get(zeile).getOrt();
  }
  
  /**
   * Gibt die Parteiabkuerzung des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return Parteiabkuerzung als String.
   */
  public String getParteiKurz(int zeile) {
    
    return daten.get(zeile).getParteiKurz();
  }
  
  /**
   * Gibt den vollen Parteinamen des Kandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return ganzen Parteinamen als String.
   */
  public String getParteiLang(int zeile) {
    
    return daten.get(zeile).getParteiLang();
  }
  
  /**
   * Gibt alle Zweitkandidaten zurueck.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return alle Zweitkandidaten als ArrayList von Strings.
   */
  public ArrayList<String> getZweitKandidaten(int zeile) {
    
    return daten.get(zeile).getZweitStimmeKandidaten();  
  }


} // class end
