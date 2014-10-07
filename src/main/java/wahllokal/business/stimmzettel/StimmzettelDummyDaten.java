package wahllokal.business.stimmzettel;


import java.util.Vector;

/**
 * Klasse, die Beispieldaten zum Aufbereiten eines
 * Stimmzettels bereitstellt, falls kein Fremdsystem diese
 * liefern kann.
 * 
 * <p>
 * Hinweis:<br/>
 * Dies ist lediglich eine Beispiel-Implementierung der 
 * Schnittstelle IStimmzettel, die vom Fremdsystem Wahllisten
 * geliefert wird. Diese Implementierung ist so eingerichtet, dass
 * sie der Schnittstellendefinition gerecht wird.
 * Die Implementation kann auch eleganter sein!
 * </p>
 * @author Johann
 * <p>Zuletzt bearbeitet: 22.06.2011</p>
 */
public class StimmzettelDummyDaten implements IStimmzettel {

  /**  Der Vorname des Kandidaten. */
  private Vector<String> vorNamen;
  
  /**  Der Nachname des Kandidaten. */
  private Vector<String> nachNamen;
  
  /**  Der Beruf des Kandidaten. */
  private Vector<String> berufe;
  
  /**  Der Ort des Kandidaten. */
  private Vector<String> orte;
  
  /**  Die Parteikurzbeschreibung. */
  private Vector<String> parteienKurz;
  
  /** Der Parteiname in voller Laenge. */
  private Vector<String> parteienLang;
  
  /**  Alle Namen der Zweitstimmenkandidaten. */
  private Vector<Vector<String>> zweitStimmenKandidaten;
   
  
  /*  Diese Daten werden nicht gebraucht!
   * ----------------------------------------- 
  private Vector<String> ersatzBewerberVorname;
  private Vector<String> ersatzBewerberNachname;
  private Vector<String> ersatzBewerberOrt;
  private Vector<String> ersatzBewerberBeruf;
  private Vector<String> ZweitStimmenKandidatParteiKurz;
  private Vector<String> ZweitStimmenKandidatParteiLang;*/
  
  /**
   * Einziger Konstruktor dieser Klasse, der auch
   * die notwendigen Variablen initialisiert und die
   * DummyDaten definiert.
   */
  public StimmzettelDummyDaten() {
    
    //Initialisierung
    this.vorNamen = new Vector<String>();
    this.nachNamen = new Vector<String>();
    this.orte = new Vector<String>();
    this.berufe = new Vector<String>();
    this.parteienKurz = new Vector<String>();
    this.parteienLang = new Vector<String>();
    this.zweitStimmenKandidaten = new Vector<Vector<String>>();
    
    //mit DummyDaten fuellen
    this.vorNamen.add("Daniel");
    this.nachNamen.add("Günther");
    this.berufe.add("Geschäftsführer");
    this.orte.add("Eckernförde, Berliner Str. 156");
    this.parteienKurz.add("CDU");
    this.parteienLang.add("Christlich Demokratische Union Deutschlands");
    
    Vector<String> vector1 = new Vector<String>(); //fuer zweitkandidaten
    vector1.add("Peter Harry Carstensen");
    vector1.add("Dr. Christian von Boetticher");
    vector1.add("Herlich Marie Todsen-Reese");
    vector1.add("Rainer Wiegard");
    vector1.add("Frank Sauter");
    this.zweitStimmenKandidaten.add(vector1);
    
    ////////////////
    this.vorNamen.add("Martin");
    this.nachNamen.add("Klimach-Dreger");
    this.berufe.add("Betriebswirt");
    this.orte.add("Eckernförde, Margarethe-Kruse-Str. 11");
    this.parteienKurz.add("SPD");
    this.parteienLang.add("Sozialdemokratische Partei Deutschlands");
    
    Vector<String> vector2 = new Vector<String>(); //fuer zweitkandidaten
    vector2.add("Dr. Ralf Stegner");
    vector2.add("Dr. Gitta Trauernicht");
    vector2.add("Lothar Hay");
    vector2.add("Birgit Herdejürgen");
    vector2.add("Jürgen Weber");
    this.zweitStimmenKandidaten.add(vector2);
    
    ////////////////
    this.vorNamen.add("Oliver");
    this.nachNamen.add("Fink");
    this.berufe.add("Unternehmer");
    this.orte.add("Eckernförde, Richard-Vosgerau-Str. 20");
    this.parteienKurz.add("FDP");
    this.parteienLang.add("Freie Demokratische Partei");
    
    Vector<String> vector3 = new Vector<String>(); //fuer zweitkandidaten
    vector3.add("Wolfgang Kubicki");
    vector3.add("Dr. Ekkehard Klug");
    vector3.add("Dr. Heinrich Garg");
    vector3.add("Günther Hildebrand");
    vector3.add("Anita Klahn");
    this.zweitStimmenKandidaten.add(vector3);
    
    ////////////////
    this.vorNamen.add("Detlef");
    this.nachNamen.add("Matthiessen");
    this.berufe.add("Tierarzt, MdL");
    this.orte.add("Osterby, Dorfstr. 33");
    this.parteienKurz.add("GRÜNE");
    this.parteienLang.add("BÜNDNIS 90/DIE GRÜNEN");
    
    Vector<String> vector4 = new Vector<String>(); //fuer zweitkandidaten
    vector4.add("Monika Heinold");
    vector4.add("Dr. Robert Habeck");
    vector4.add("Maria-Elisabeth Fritzen");
    vector4.add("Andreas Tietze");
    vector4.add("Anke Erdmann");
    this.zweitStimmenKandidaten.add(vector4);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Vorname fuer Kandidat mit der nummer
   */
  public final String getVorname(int indexnummer) {
    
    return this.vorNamen.elementAt(indexnummer);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Nachname fuer Kandidat mit der nummer
   */
  public final String getNachname(int indexnummer) {
    
    return this.nachNamen.elementAt(indexnummer);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Beruf fuer Kandidat mit der nummer
   */
  public final String getBeruf(int indexnummer) {
    
    return this.berufe.elementAt(indexnummer);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Wohnort fuer Kandidat mit der nummer
   */
  public final String getOrt(int indexnummer) {
    
    return this.orte.elementAt(indexnummer);
  }
  
  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Partei fuer Kandidat mit der nummer (In Kurzform)
   */
  public final String getParteiKurz(int indexnummer) {
    
    return this.parteienKurz.elementAt(indexnummer);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Partei fuer Kandidat mit der nummer (In Langform)
   */
  public final String getParteiLang(int indexnummer) {
    
    return this.parteienLang.elementAt(indexnummer);
  }

  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Array mit Zweitstimmenkandidaten fuer Partei mit der nummer
   */
  public final Vector<String> getZweitStimmenKandidaten(int indexnummer) {
    
    return this.zweitStimmenKandidaten.elementAt(indexnummer);
  }
  
  //-------------------------------------------------------
  // ab hier folgen nur noch leere Implementationen der Schnittstelle,
  // da diese Methoden nicht gebraucht werden.
  
  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Vorname des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  public final String getErsatzbewerberVorname(int indexnummer) {
    
    return null;
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Nachname des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  public final String getErsatzbewerberNachname(int indexnummer) {
    
    return null;
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Beruf des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  public final String getErsatzbewerberBeruf(int indexnummer) {
    
    return null;
  }

  /**
   * @param indexnummer
   *          Indexnummer des Kandidatens
   * @return Wohnort des Ersatzkandidatens fuer Kandidat mit der nummer
   */
  public final String getErsatzbewerberOrt(int indexnummer) {
    
    return null;
  }

  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Parteiname fuer Partei mit der nummer (In Langform)
   */
  public final String getZweitStimmenKandidatParteiKurz(int indexnummer) {
    
    return null;
  }

  /**
   * @param indexnummer
   *          Indexnummer des Zweitstimmenkandidatens
   * @return Parteiname fuer Partei mit der nummer (In Langform)
   */
  public final String getZweitStimmenKandidatParteiLang(int indexnummer) {
    
    return null;
  }
 
  
} //end class

