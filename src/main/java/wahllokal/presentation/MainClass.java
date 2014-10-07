
package wahllokal.presentation;

import wahllokal.presentation.view.SearchView;

/**
 * Klasse die lediglich die main() enthaelt.
 * @author Johann
 * Zuletzt bearbeitet: 08.06.2011
 */
public final class MainClass {

  //////////////////////////// main() //////////////////
  /**
   * Hauptfunktion main().
   * @param args wird nicht benutzt.
   */
  public static void main(final String[] args) {

    SearchView.getInstance();

  }
  ////////////////////////////////////////////////////

  /**
   * Einziger und leerer Konstruktor, damit es keinen
   * public default Konstruktor gibt.
   */
  private MainClass() {
    //leer
  }
}
