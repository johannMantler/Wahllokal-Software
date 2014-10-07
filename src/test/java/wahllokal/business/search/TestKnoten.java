
package wahllokal.business.search;

import junit.framework.Assert;

import org.junit.Test;


/**
 * Testet die Klasse Knoten, die man fuer
 * die Klasse BinaerSuchBaum braucht.
 * @author Johann
 *<p>zuletzt bearbeitet: 14.06.2011</p>
 */
public class TestKnoten {

  /**
   * Test method for {@link wahllokal.business.search.
   * Knoten#Knoten(java.lang.Comparable)}.
   */
  @Test
  public final void testKnotenE() {
    
    // Set up SUT
    Knoten<String> k;
    String str = "Hallo";
    
    // Exercise SUT
    k = new Knoten<String>(str);
    
    // Verify Outcome
    Assert.assertSame(str, k.getInhalt());
    
    // Tear Down
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * Knoten#Knoten(java.lang.Comparable, 
   * wahllokal.business.search.Knoten)}.
   */
  @Test
  public final void testKnotenEKnotenOfE() {
    
    // Set up SUT
    String inhaltOben = "Hallo";
    Knoten<String> oben = new Knoten<String>(inhaltOben);
    
    String inhaltUnten = "Welt";
    Knoten<String> unten;
    
    // Exercise SUT
    unten = new Knoten<String>(inhaltUnten, oben);
    
    // Verify Outcome
    Assert.assertSame(inhaltUnten, unten.getInhalt());
    Assert.assertSame(inhaltOben, unten.getOben().getInhalt());
    
    // Tear Down
    
  }

} //testclass end
