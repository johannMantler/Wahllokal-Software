package wahllokal.business.stimmzettel;

import java.util.ArrayList;

/**
 * Model, welches fuer das Zaehlen der Stimmzettelauswahlen
 * zustaendig ist.
 * @author Johann
 * <p>zuletzt bearbeitet: 21.06.2011</p>
 */
public final class CounterModel {

  /** Referenz auf einzige Instanz. */
  private static CounterModel soleInstance = null;

  /** Liefere Referenz auf einzige Instanz der Klasse.
  * @return Referenz auf Instanz der Klasse
  */
  public static synchronized CounterModel getInstance() {

    if (soleInstance == null) {

      soleInstance = new CounterModel();

    }
    return soleInstance;
  }
  
  /** Beinhaltet die Stimmzaehlung fuer die Kandidaten. */
  private ArrayList<Integer> kandidatenCounter;
  
  /** Beinhaltet die Stimmzaehlung fuer die Parteien. */
  private ArrayList<Integer> parteiCounter;
  
  /** StimmzettelModel Referenz, um auf die Daten zuzugreifen. */
  private StimmzettelModel stimmzettelModel;
  
  /**
   * Einziger Konstruktor dieser Klasse.
   */
  private CounterModel() {
    
    this.kandidatenCounter = new ArrayList<Integer>();
    this.parteiCounter = new ArrayList<Integer>();
    this.stimmzettelModel = StimmzettelModel.getInstance();
    
    this.initCounterArrayList(kandidatenCounter);
    this.initCounterArrayList(parteiCounter);
  }
 
  /**
   * Initialisiert die ArrayList-Zaehlerstaende mit 0.
   * @param list Die ArrayList, dessen Werte initialisiert werden sollen.
   */
  private void initCounterArrayList(ArrayList<Integer> list) {
    
    int count = 0;
    
    for (int i = 0; i < stimmzettelModel.getZeilenAnzahl(); i++) {
      
      list.add(count);
    }
  }
  
  /**
   * Erhoet den Zaehlerstand eines bestimmten Kandidaten bzw.
   * einer bestimmten Partei. Diese/r Partei/Kandidat wird durch
   * die Zeilenangabe bestimmt.
   * @param zeile Zeilenangabe, um die Partei/Kandidat zu bestimmen.
   * @param erstStimme Wenn true, wird der Kandidat in der angegebenen
   * Zeile erhoet, sonst die Partei.
   */
  public synchronized void count(int zeile, boolean erstStimme) {
    
    if (zeile >= 0) {
    
      if (erstStimme) {
        
        Integer newValue = 1 + this.kandidatenCounter.get(zeile);
        this.kandidatenCounter.set(zeile, newValue);
        
      } else {
        
        Integer newValue = 1 + this.parteiCounter.get(zeile);
        this.parteiCounter.set(zeile, newValue);
      }
    }
  }

  /**
   * Gibt eine Kopie der Zaehlstaende von der Erst- oder Zweitstimme als 
   * ArrayList zurueck. 
   * @return the kandidatenCounter
   * @param erstStimme Angabe, ob es sich um die Erst oder Zweitstimme
   * handel.
   */
  public ArrayList<Integer> getCounter(boolean erstStimme) {
    
    ArrayList<Integer> listCopy = new ArrayList<Integer>();
      
      if (erstStimme) {
        
        listCopy = this.copyList(kandidatenCounter);
        
      } else {

        listCopy = this.copyList(parteiCounter);
      } 

       
    return listCopy;
  }
  
  /**
   * Fertigt eine tiefe Kopie einer ArrayList aus Integerobjekten an.
   * @param list Die ArrayList die kopiert werden soll.
   * @return die Kopie der uebergebenen ArrayList.
   */
  private ArrayList<Integer> copyList(ArrayList<Integer> list) {
    
    ArrayList<Integer> listCopy = new ArrayList<Integer>();
    
    for (int i = 0; i < list.size(); i++) {
      
      listCopy.add(list.get(i));
    }
    
    return listCopy;
  }
  
  
} //class end
