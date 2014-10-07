package wahllokal.business.search;

/**
 * Repraesentiert einen Knoten in einem Baum.
 * @param <E> generischer Datentyp, der den Inhalt der Zelle repraesentiert.
 * @author Johann
 * <p>erstellt am: 06.06.2010</p>
 * <p>zuletzt bearbeitet: 14.06.2011</p>
 */
public class Knoten<E extends Comparable<E>> {
  
  /** Inhalt des Knotens.         */
  private E inhalt;
  
  /** Referenz auf oberen Nachbarknoten. */
  private Knoten<E> oben;
  
  /** Referenz auf linken Nachbarknoten. */
  private Knoten<E> links;
  
  /** Referenz auf rechten Nachbarknoten. */
  private Knoten<E> rechts;
  
  /**
   * Konstruktor, der einen Blattknoten erzeugt.
   * @param element Der Knoteninhalt.
   */
  public Knoten(E element) {
    
    this.inhalt = element;
  }
  
  /**
   * Konstruktor, der einen Knoten mit Referenz auf sein Vaterknoten
   * erzeugt.
   * @param element Der Inhalt des Knotens.
   * @param obererNachbar Referenz auf Vaterknoten.
   */
  public Knoten(E element, Knoten<E> obererNachbar) {
    
    this.inhalt = element;
    this.oben = obererNachbar;
  }
  
  /**
   * Methode, die den Inhalt des Knotens als String-Repraesentation
   * ausgibt.
   * @return Den Inhalt als String.
   * <p>Der Knoteninhalt muss dafuer auch eine 
   * toString Methode definieren</p>
   */
  @Override
  public final String toString() {
  
    return this.inhalt.toString();
  }

  /**
   * @return the inhalt
   */
  public final E getInhalt() {
    return inhalt;
  }

  /**
   * @return the oben
   */
  public final Knoten<E> getOben() {
    return oben;
  }

  /**
   * @return the links
   */
  public final Knoten<E> getLinks() {
    return links;
  }

  /**
   * @return the rechts
   */
  public final Knoten<E> getRechts() {
    return rechts;
  }

  /**
   * @param e the inhalt to set
   */
  public final void setInhalt(E e) {
    this.inhalt = e;
  }

  /**
   * @param obererNachbar the oben to set
   */
  public final void setOben(Knoten<E> obererNachbar) {
    this.oben = obererNachbar;
  }

  /**
   * @param linkerNachbar the links to set
   */
  public final void setLinks(Knoten<E> linkerNachbar) {
    this.links = linkerNachbar;
  }

  /**
   * @param rechterNachbar the rechts to set
   */
  public final void setRechts(Knoten<E> rechterNachbar) {
    this.rechts = rechterNachbar;
  }

} //class end


