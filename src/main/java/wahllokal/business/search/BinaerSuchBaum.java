package wahllokal.business.search;


/**
 * Repraesentiert einen Binaerenbaum in dem Daten
 * eingefuegt, gesucht werden
 * koennen.
 * @param <E> generischer Datentyp, der den Inhalt der Zelle repraesentiert.
 * @author Johann
 * <p>erstellt am: 06.06.2010</p>
 * <p>zuletzt bearbeitet: 15.06.2011</p>
 */
public class BinaerSuchBaum <E extends Comparable<E>> {

  /** Wurzel des Baumes.           */
  private Knoten<E> wurzel = null;             
 
  /**
   * Gibt die Wurzel des Baumes zurueck.
   * @return die Wurzel
   */
  public final Knoten<E> getWurzel() { 
    return this.wurzel;
  }
  
  /**
   * Prueft, ob ein Baum existiert.
   * @return true, wenn Baum nicht existiert, sonst false.
   */
  public final boolean istLeer() {
    
    return this.wurzel == null;                    
  }

  /**
   * Fuegt beliebig viele Knoten in den Baum ein.
   * @param e Die Knoten
   */
  public final void einfuegen(E ... e) {
 
    for (E el : e) {
      einfuegen(el); 
    }
  }
 
  /**
   * Fuegt genau ein Element ein den Baum ein.
   * Vorgehensweise:
   * <p>Ist e <= Wurzelelement, dann
   * ist der linke Teilbaum leer, so erzeuge einen linken Sohn
   * mit e als Inhalt, ansonsten fuege e in den linken Teilbaum ein.</p>
   * <p>Ist e > Wurzelelement, dann
   * ist der rechte Teilbaum leer, so erzeuge einen rechten Sohn mit e
   * als Inhalt, ansonsten fuege e in den rechten Teilbaum ein.</p>
   * @param e das Element e, das eingefuegt wird.
   */
  public final void einfuegen(E e) {
   
    if (this.wurzel == null) {
    
      this.wurzel = new Knoten<E>(e);
    
    } else {
        this.rekEinfuegen(e, this.wurzel);
    }
    
  }
  
  /**
   * Rekursive "Hilfsmethode" fuer einfuegen().
   * Vorgehensweise:
   * <p>Ist e <= Wurzelelement, dann
   * ist der linke Teilbaum leer, so erzeuge einen linken Sohn
   * mit e als Inhalt, ansonsten fuege e in den linken Teilbaum ein.</p>
   * <p>Ist e > Wurzelelement, dann
   * ist der rechte Teilbaum leer, so erzeuge einen rechten Sohn mit e
   * als Inhalt, ansonsten fuege e in den rechten Teilbaum ein.</p>
   * @param e das Element e, das eingefuegt wird.
   * @param k ist zu Anfang die Wurzel.
   */  
  private void rekEinfuegen(E e, Knoten<E> k) {
    
    if (e.compareTo(k.getInhalt()) <= 0) { // e <= Wurzelelement?
      
      if (k.getLinks() == null) { //ist der linke Teilbaum leer..
                                  //so erzeuge linken Sohn mit e als Inhalt
        k.setLinks(new Knoten<E>(e, k));
        
      } else {               //ansonsten fuege e in den linken Teilbaum ein
          this.rekEinfuegen(e, k.getLinks());
      }
      
    } else { // e > Wurzelelement
         
      if (k.getRechts() == null) { //ist der rechte Teilbaum leer..
                                  //so erzeuge rechten Sohn mit e als Inhalt
        k.setRechts(new Knoten<E>(e, k));

      } else {               //ansonsten fuege e in den rechten Teilbaum ein
       
          this.rekEinfuegen(e, k.getRechts());
      }
    }
    
  }

  /**
   * Sucht einen Knoten mit einem bestimmten Inhalt in dem Baum.
   *
   * @param e Der Inhalt, welcher gesucht wird.
   * @return Der Knoten mit dem gesuchten Inhalt.
   */
  public final Knoten<E> suche(E e) {          
   
    Knoten<E> ergebnis = null;              /* Rückgabewert, ist ggf. null */
 
    if (wurzel != null) {                   /* Baum überhaupt vorhanden??  */
    
      Knoten<E> k = wurzel;                 /* k ist ein Hilfsknoten       */
      boolean ready = false;                /* Var. für Schleifensteuerung */
   
      do {                                  /* bisGefundenOderBaumDurchIst */
     
        if (k == null) {                    /* bed.: Baum wurde Durchlaufen*/
        
          ready = true;                    /* Dann SchleifenAbbruch       */
                                           /* Inhalt identisch mit k??..  */
        } else if (e.compareTo(k.getInhalt()) == 0) {
          
           ergebnis = k;                    /* Knoten merken für Rückgabe  */
           ready = true;                    /* Dann SchleifenAbbruch       */
                                            /* Inhalt kleiner als akt. k?..*/
        } else if (e.compareTo(k.getInhalt()) < 0) {
          
           k = k.getLinks();                /* weitersuchen linken teilbaum*/
         
        } else {                            /* Inhalt größer als akt. k?.. */
          
           k = k.getRechts();               /* weitersuchen rechten teilbau*/
         
        }
      } while (!ready);
      
    } // if( wurzel != null ) { ..                                          
 
    return ergebnis; 
  }

} // end class

