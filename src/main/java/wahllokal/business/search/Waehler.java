package wahllokal.business.search;

/**
 * Klasse, die einen Waehler mit all seinen Daten
 * repraesentiert.
 * @author Johann
 * <p>zuletzt bearbeitet: 22.06.2011</p>
 */
public class Waehler implements Comparable<Waehler> {

  /** Die PersonalNr, die den Waehler eindeutig identifiziert. */
  private String nummer;
  
  /** Das Geburtsdatum des Waehlers.                          */
  private String geburtsDatum;
  
  /** Der Nachname des Waehlers.                          */
  private String nachName;
  
  /** Der Vorname des Waehlers.                          */
  private String vorName;
  
  /** Speichert ob der Waehler schon gewaehlt hat.*/
  private boolean hatGewahelt = false;
  
  /** Speichert den WahlberechtigungsCode, welchen der 
   * Waehler vor der Stimmzettelwahl eingeben muss. */
  private String waehlerKey = null;
  
  /**
   * Die Waehler sind untereinander durch ihre Personalnummer
   * vergleichbar.
   * @param w Der Waehler mit dem verglichen wird.
   * @return das Vergleichsergebnis
   */
  public final int compareTo(Waehler w) {
    
    return this.nummer.compareTo(w.getNummer());
  }
  
  /**
   * Erzeugt einen Waehler mit der angegebenen
   * Personalnummer.
   * @param personalNr Die Personalnummer des Waehlers.
   */
  public Waehler(String personalNr) {
    
    this.nummer = personalNr;
    
  }
  
  
  /**
   * @return the nummer
   */
  public final String getNummer() {
    return nummer;
  }

  /**
   * @return the geburtsDatum
   */
  public final String getGeburtsDatum() {
    return geburtsDatum;
  }

  /**
   * @param gebDatum the geburtsDatum to set
   */
  public final void setGeburtsDatum(String gebDatum) {
    this.geburtsDatum = gebDatum;
  }

  /**
   * @return the nachName
   */
  public final String getNachName() {
    return nachName;
  }

  /**
   * @param nName the nachName to set
   */
  public final void setNachName(String nName) {
    this.nachName = nName;
  }

  /**
   * @return the vorName
   */
  public final String getVorName() {
    return vorName;
  }

  /**
   * @param vName the vorName to set
   */
  public final void setVorName(String vName) {
    this.vorName = vName;
  }

  /**
   * @return the hatGewahelt
   */
  public final boolean isHatGewahelt() {
    return hatGewahelt;
  }

  /**
   * Wenn die Variable hatGewaehlt einmal auf true
   * gesetzt ist, kann man es nicht mehr rueckgaengig machen.
   */
  public final void setHatGewahelt() {
    
    this.hatGewahelt = true;
  }

  /**
   * @return the waehlerKey
   */
  public final String getWaehlerKey() {
    return waehlerKey;
  }

  /**
   * Setzt den Berechtigungscode vom Waehler, aber
   * nur wenn dieser Waehler noch keinen Key hat.
   * Sonst hat dieser Methodenaufruf keine Auswirkungen.
   * @param berechtigungsCode the waehlerKey to set
   */
  public final void setWaehlerKey(String berechtigungsCode) {
    
    if (this.waehlerKey == null) {
      
      this.waehlerKey = berechtigungsCode;
    }
  }
  
}
