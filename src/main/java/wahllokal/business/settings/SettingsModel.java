package wahllokal.business.settings;


/**
 * Diese Klasse verwaltet die Einstellungen die, der Nutzer
 * beim Eroeffnen des Wahllokals gemacht hat.
 * Erweiterung: Speicherung dieser Daten in der DB
 * um Persistenz zu erzielen.
 * Diese Klasse ist Singelton, damit spaeter die Daten
 * auch wirklich nur einmal in der DB gepeichert werden.
 * @author Johann
 * <p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class SettingsModel {

  /** Referenz auf einzige Instanz. */
  private static SettingsModel soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized SettingsModel getInstance() {

    if (soleInstance == null) {

      soleInstance = new SettingsModel();

    }
    return soleInstance;
  }
  
  /**Speichert die eingegebene Kreisnummer.*/
  private String kreisNr;

  /**Speichert die eingegebene Bezirksnummer.*/
  private String bezirkNr;

  /**Speichert die eingegebene Kreisnummer.*/
  private String kabinenZahl;
  
  /**Speichert das Datum, wann das Wahllokal eroeffnet wurde.*/
  private String dateString;
  
  /**Speichert die Zeit, wann das Wahllokal eroeffnet wurde.*/
  private String timeString;
  
  /**
   * @return the kreisNr
   */
  public final String getKreisNr() {
    return kreisNr;
  }

  /**
   * @return the bezirkNr
   */
  public final String getBezirkNr() {
    return bezirkNr;
  }

  /**
   * @return the kabinenZahl
   */
  public final String getKabinenZahl() {
    return kabinenZahl;
  }

  /**
   * @return the dateString
   */
  public final String getDateString() {
    return dateString;
  }

  /**
   * @return the timeString
   */
  public final String getTimeString() {
    return timeString;
  }

  /**
   * @param kreisNummer the kreisNr to set
   */
  public final void setKreisNr(String kreisNummer) {
    this.kreisNr = kreisNummer;
  }

  /**
   * @param bezirkNummer the bezirkNummr to set
   */
  public final void setBezirkNr(String bezirkNummer) {
    this.bezirkNr = bezirkNummer;
  }

  /**
   * @param kabinenAnzahl the kabinenZahl to set
   */
  public final void setKabinenZahl(String kabinenAnzahl) {
    this.kabinenZahl = kabinenAnzahl;
  }

  /**
   * @param date the dateString to set
   */
  public final void setDateString(String date) {
    this.dateString = date;
  }

  /**
   * @param time the timeString to set
   */
  public final void setTimeString(String time) {
    this.timeString = time;
  }
  
}
