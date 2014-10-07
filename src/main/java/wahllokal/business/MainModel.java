package wahllokal.business;

import java.util.ArrayList;

import wahllokal.business.close.CloseModel;
import wahllokal.business.search.SearchModel;
import wahllokal.business.search.Waehler;
import wahllokal.business.settings.SettingsModel;
import wahllokal.business.stimmzettel.CounterModel;
import wahllokal.business.stimmzettel.StimmzettelModel;


/**
 * Diese Klasse fungiert als Fassade zwischen der Presentationsschicht
 * und der Geschaeftslogik, den Models. Hier werden viele
 * Funktionalitaeten der Presentationsschicht dargeboten, welche
 * von dem MainModel an die FachModels delegiert wird.
 * @author Johann
 * <p>Zuletzt bearbeitet: 23.06.2011</p>
 */
public final class MainModel {
 
  /** Das Model zum Suchen eines Waehlers. */
  private SearchModel searchModel;
  
  /** Das Model fuer den Stimmzettel. */
  private StimmzettelModel stimmzettelModel;
  
  /** Das Model fuer die Einstellungen. */
  private SettingsModel settingsModel;
  
  /** Das Model zum Zaehlen der Stimmzettelabgaben. */
  private CounterModel counterModel;
  
  /** Referenz auf einzige Instanz. */
  private static MainModel soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized MainModel getInstance() {

    if (soleInstance == null) {

      soleInstance = new MainModel();

    }
    return soleInstance;
  }
  
  /**
   * Einziger Konstruktor dieser Klasse.
   */
  private MainModel() {
   
   searchModel = SearchModel.getInstance();
   stimmzettelModel = StimmzettelModel.getInstance();
   settingsModel = SettingsModel.getInstance();
   counterModel = CounterModel.getInstance();
  }
  
  /**
   * Sucht anhand einer uebergebenen Personalnummer
   * nach dem zugehoerigen Waehler und gibt seine Personalien
   * mit einem Stringarray zurueck.
   * @param personalNr die Personalnummer als String.
   * @return Das Stringarray mit den Personaldaten.
   */
  public String[] getWaehlerDaten(String personalNr) {
    
    String[] daten = null;
    Waehler w = this.searchModel.searchWaehler(personalNr);
    
    if (null != w) { //Falls Waehler gefunden
      
      daten = new String[] {w.getNachName(),
                            w.getVorName(),
                            w.getGeburtsDatum()};
    }
    
    return daten;
  }
  
  /**
   * Gibt den Berechtigungscode (WaehlerKey) des Waehlers
   * zurueck, der zuletzt gesucht wurde.
   * @return Den Code als String wenn der Waehler zuvor gesucht wurde,
   * sonst null.
   */
  public String getBerechtigungsCode() {
    
    return this.searchModel.getAktWaehler().getWaehlerKey();
  }
  
  /**
   * Zeigt mit einer booleschen Rueckgabe ob der Waehler, der
   * zuletzt gesucht wurde, schon gewaehlt hat oder nicht.
   * @return true, wenn er gewaehlt hat, sonst false.
   */
  public boolean getGewaehlt() {
    
    return this.searchModel.getAktWaehler().isHatGewahelt();
  }
  /**
   * Hole den Nachnamen des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Nachname als String.
   */
  public String getNachNamen(int zeile) {
    
    return this.stimmzettelModel.getNachNamen(zeile);
    
  }

  /**
   * Hole den Vornamen des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Vorname als String.
   */
  public String getVorNamen(int zeile) {
    
    return this.stimmzettelModel.getVorNamen(zeile);
    
  }
  
  /**
   * Hole den Beruf des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Beruf als String.
   */
  public String getBeruf(int zeile) {
    
    return this.stimmzettelModel.getBeruf(zeile);
    
  }
  
  /**
   * Hole den Ort des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Ort als String.
   */
  public String getOrt(int zeile) {
    
    return this.stimmzettelModel.getOrt(zeile);
    
  }
  
  /**
   * Hole die Parteinamkuerzung des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Parteiabkuerzung als String.
   */
  public String getParteiKurz(int zeile) {
    
    return this.stimmzettelModel.getParteiKurz(zeile);
    
  }

  /**
   * Hole den vollen Parteinamen des Kandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return der Parteiname als String.
   */
  public String getParteiLang(int zeile) {
    
    return this.stimmzettelModel.getParteiLang(zeile);
    
  }
  
  /**
   * Hole die Zweitkandidaten.
   * @param zeile Zeilenzahl des Kandidaten.
   * @return die Zweitkandidaten als Stringarray
   */
  public String[] getZweitKandidaten(int zeile) {
    
    ArrayList<String> list = this.stimmzettelModel.getZweitKandidaten(zeile);
    
    String[] array = new String[list.size()];
    
    for (int i = 0; i < list.size(); i++) {
     
      array[i] = list.get(i);
      
    }
    
    return array;   
  }
  
  /**
   * Gibt die Anzahl der StimmzettelZeilen
   * wieder.
   * @return die Anzahl als int.
   */
  public int zeilenAnzahl() {
    return stimmzettelModel.getZeilenAnzahl();
  }
  
  /**
   * @return the kreisNr
   */
  public String getKreisNr() {
    return this.settingsModel.getKreisNr();
  }

  /**
   * Fasst die Endergebnisse der Stimmzettelauswertung
   * zusammen und stellt diese anderen Fremdystemen
   * zur Verfuegung.
   */
  public void lokalSchluss() {
    
    new CloseModel();
  }
  
  /**
   * @return the bezirkNr
   */
  public String getBezirkNr() {
    return this.settingsModel.getBezirkNr();
  }

  /**
   * @return the kabinenZahl
   */
  public String getKabinenZahl() {
    return this.settingsModel.getKabinenZahl();
  }

  /**
   * @param kreisNummer the kreisNr to set
   */
  public void setKreisNr(String kreisNummer) {
    this.settingsModel.setKreisNr(kreisNummer);
  }

  /**
   * @param bezirkNummer the bezirkNummer to set
   */
  public void setBezirkNr(String bezirkNummer) {
    this.settingsModel.setBezirkNr(bezirkNummer);
  }

  /**
   * @param kabinenAnzahl the kabinenZahl to set
   */
  public void setKabinenZahl(String kabinenAnzahl) {
    this.settingsModel.setKabinenZahl(kabinenAnzahl);
  }
  
  /**
   * @param date the dateString to set
   */
  public void setDateString(String date) {
    this.settingsModel.setDateString(date);
  }

  /**
   * @param time the timeString to set
   */
  public void setTimeString(String time) {
    this.settingsModel.setTimeString(time);
  }
  
  /**
   * Gibt die Anzahl der Waehler zurueck die fuer dieses
   * Wahllokal registriert sind.
   * @return Die Anzahl als int.
   */
  public int getGesamtWaehlerAnzahl() {
    return this.searchModel.getGesamtWaehlerAnzahl();
  }
  
  /**
   * Liefert die Anzahl aller zurzeit aktiven Waehler.
   * @return Die Anzahl als int.
   */
  public int getAktiveWaehlerAnzahl() {
    return this.searchModel.getAktiveWaehlerAnzahl();
  }
  
  /**
   * Liefert das Datum, wann das Wahllokal eroeffnet wurde.
   * @return Das Datum als String.
   */
  public String getDate() {
    return this.settingsModel.getDateString();
  }
  
  /**
   * Liefert die Zeit, wann das Wahllokal eroeffnet wurde.
   * @return die Zeit als String.
   */
  public String getTime() {
    return this.settingsModel.getTimeString();
  }
  /**
   * Erhoet den Zaehlerstand eines bestimmten Kandidaten bzw.
   * einer bestimmten Partei. Diese/r Partei/Kandidat wird durch
   * die Zeilenangabe bestimmt.
   * @param zeile Zeilenangabe, um die Partei/Kandidat zu bestimmen.
   * @param erstStimme Wenn true, wird der Kandidat in der angegebenen
   * Zeile erhoet, sonst die Partei.
   */
  public void count(int zeile, boolean erstStimme) {
         
      this.counterModel.count(zeile, erstStimme);
  }
  
  /**
   * Setzt den aktuellen Waehler in die Liste der aktiven Waehler.
   */
  public void setWaehlerActive() {
    
    this.searchModel.insertActiveWaehler(this.searchModel.getAktWaehler());
  }
  
  /**
   * Sucht einen aktiven Waehler mit Hilfe seines BerechtigungsCode.
   * @param key der BerechtigungsCode, mit Hilfe dessen der Waehler
   * gesucht wird.
   * @return true wenn Waehler gefunden, sonst false.
   */
  public boolean searchActiveWaehler(String key) {
    
    return this.searchModel.searchKey(key);
  }
  
  /**
   * Loescht den Waehler mit dem BerechtigungsCode
   *  aus der Liste der aktiven Waehler.
   * @param key der BerechtigungsCode von dem Waehler, der
   * aus der Liste der aktiven Waehler entfernt werden soll.
   */
  public synchronized void deleteActiveWaehler(String key) {
    

    this.searchModel.deleteActiveWaehler(
        this.searchModel.searchActiveWaehler(key));
  }
  
  /**
   * Gibt die Anzahl aller Waehler zurueck, die schon gewaehlt haben.
   * @return Die Anzahl als int.
   */
  public int getGewaehltAnzahl() {
    return this.searchModel.getGewaehltAnzahl();

/*    this.searchModel.deleteActiveWaehler(
        this.searchModel.searchActiveWaehler(key));*/

  }
  
} //class end
