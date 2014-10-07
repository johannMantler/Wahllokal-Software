package wahllokal.business.search;

import java.util.ArrayList;


/**
 * Klasse, die BeispielWaehler bereitstellt.
 * @author Johann
 * <p>zuletzt bearbeitet: 16.06.2011</p>
 */
public class WaehlerDummyDaten {

  /** die "Dummy"-Waehler werden in dieser Liste gespeichert.*/
  private ArrayList<Waehler> list;
  
  /**
   * Konstruktor, der ein paar DummyWaehler erzeugt und 
   * der ArrayList hinzufügt. Die Liste kann dann mit
   * getDummyWaehler() geholt werden.
   */
  public WaehlerDummyDaten() {
  
    Waehler w1 = new Waehler("1");
    w1.setGeburtsDatum("20.05.1971");
    w1.setNachName("Müller");
    w1.setVorName("Hans");
    
    Waehler w2 = new Waehler("2");
    w2.setGeburtsDatum("04.01.1985");
    w2.setNachName("Dilke");
    w2.setVorName("Lisa");
    
    Waehler w3 = new Waehler("5");
    w3.setGeburtsDatum("07.07.1981");
    w3.setNachName("Fettling");
    w3.setVorName("Jürgen");
    
    Waehler w4 = new Waehler("77");
    w4.setGeburtsDatum("04.01.1820");
    w4.setNachName("Seitjahren");
    w4.setVorName("Tot");
    
    Waehler w5 = new Waehler("10");
    w5.setGeburtsDatum("13.09.1983");
    w5.setNachName("Stoswlewskya");  // =)
    w5.setVorName("Iwan");
    
    list = new ArrayList<Waehler>();
    
    list.add(w4);
    list.add(w5);
    list.add(w2);
    list.add(w3);
    list.add(w1);
    
  }
  
  /**
   * Diese Methode liefert ein paar Beispiel Waehler.
   * @return Die Waehler kommen in einer ArrayList gespeichert, zurueck.
   */
  public final ArrayList<Waehler> getDummyWaehler() {
    
    return this.list;
  }
  
} //class end
