package wahllokal.business.stimmzettel;

import java.util.ArrayList;

/**
 * Diese Klasse repraesentiert eine Zeile
 * im Stimmzettel mit all den benoetigten Daten
 * in dieser Zeile.
 * Eine Zeile enthaelt genau einmal einen Bereich
 * fuer die Erst- sowie genau einmal einen Bereich fuer
 * die Zweitstime.
 * @author Johann
 * <p>zuletzt bearbeitet: 17.06.2011 </p>
 */
public class StimmzettelZeile {

  /** Hier wird der Nachname des Kandidaten gespeichert. */
  private String nachName;
  
  /** Hier wird der Vorname des Kandidaten gespeichert. */
  private String vorName;
  
  /** Hier wird der Beruf des Kandidaten gespeichert. */
  private String beruf;
  
  /** Hier wird der Ort des Kandidaten gespeichert. */
  private String ort;
  
  /** Hier wird die Parteinamensabkuerzung gespeichert. */
  private String parteiKurz;
  
  /** Hier wird der volle Parteiname der Zeile gespeichert. */
  private String parteiLang;
  
  /** Enthaelt alle Namen der Kandidaten die in der
   * Zweitstimme gelistet sind.*/
  private ArrayList<String> zweitStimmeKandidaten;

  /**
   * @return the parteiKurz
   */
  public final String getParteiKurz() {
    return parteiKurz;
  }

  /**
   * @param parteiAbkurzung the parteiKurz to set
   */
  public final void setParteiKurz(String parteiAbkurzung) {
    this.parteiKurz = parteiAbkurzung;
  }

  /**
   * @return the parteiLang
   */
  public final String getParteiLang() {
    return parteiLang;
  }

  /**
   * @param parteiVollerName the parteiLang to set
   */
  public final void setParteiLang(String parteiVollerName) {
    this.parteiLang = parteiVollerName;
  }

  /**
   * @return the zweitStimmeKandidaten
   */
  public final ArrayList<String> getZweitStimmeKandidaten() {
    return zweitStimmeKandidaten;
  }

  /**
   * @param zweitKandidaten the zweitStimmeKandidaten to set
   */
  public final void setZweitStimmeKandidaten(
      ArrayList<String> zweitKandidaten) {
    
    this.zweitStimmeKandidaten = zweitKandidaten;
  }

  /**
   * @return the nachName
   */
  public final String getNachName() {
    return nachName;
  }

  /**
   * @param nachname the nachName to set
   */
  public final void setNachName(String nachname) {
    this.nachName = nachname;
  }

  /**
   * @return the vorName
   */
  public final String getVorName() {
    return vorName;
  }

  /**
   * @param vorname the vorName to set
   */
  public final void setVorName(String vorname) {
    this.vorName = vorname;
  }

  /**
   * @return the beruf
   */
  public final String getBeruf() {
    return beruf;
  }

  /**
   * @param kandidatBeruf the beruf to set
   */
  public final void setBeruf(String kandidatBeruf) {
    this.beruf = kandidatBeruf;
  }

  /**
   * @return the ort
   */
  public final String getOrt() {
    return ort;
  }

  /**
   * @param kandidatOrt the ort to set
   */
  public final void setOrt(String kandidatOrt) {
    this.ort = kandidatOrt;
  }
  
}

