package wahllokal.business.stimmzettel;

import java.util.Vector;

/**
*
* @author 190910
* @version 0.1
*
*          Interface zum Definieren der Schnittstelle
*<p>          
*------------------------------------------------- <br/>
*Anmerkung:<br/>
*Diese Schnittstelle wird vom Fremdsystem Wahllisten geliefert.<br/>
*Fuer den Inhalt bin ich nicht verantwortlich.<br/>
*
* Das Interface wird hier ausschlieszlich fuer die DummyDaten
* verwendet. Sobald alle Teilsystem zusammengefuehrt werden,
* kann dieses Interface hier entfallen.
*<br/>
*Johann<br/>
*Wahllokal-Team ;-)<br/>
*-------------------------------------------------<br/>
*</p>
*/
public interface IStimmzettel {

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Vorname fuer Kandidat mit der nummer
   */
  String getVorname(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Nachname fuer Kandidat mit der nummer
   */
  String getNachname(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Beruf fuer Kandidat mit der nummer
   */
  String getBeruf(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Wohnort fuer Kandidat mit der nummer
   */
  String getOrt(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Vorname des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  String getErsatzbewerberVorname(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Nachname des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  String getErsatzbewerberNachname(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Beruf des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  String getErsatzbewerberBeruf(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Wohnort des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  String getErsatzbewerberOrt(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Partei fuer Kandidat mit der nummer (In Kurzform)
   */
  String getParteiKurz(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Partei fuer Kandidat mit der nummer (In Langform)
   */
  String getParteiLang(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Array mit Zweitstimmenkandidaten fuer Partei mit der nummer
   */
  Vector<String> getZweitStimmenKandidaten(int indexnummer);

  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Parteiname fuer Partei mit der nummer (In Langform)
   */
  String getZweitStimmenKandidatParteiKurz(int indexnummer);
  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Parteiname fuer Partei mit der nummer (In Langform)
   */
  String getZweitStimmenKandidatParteiLang(int indexnummer);

}
