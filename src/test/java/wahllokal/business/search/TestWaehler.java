
package wahllokal.business.search;

import java.util.ArrayList;
import org.junit.Test;
import junit.framework.Assert;


/**
 * Test class for {@link wahllokal.business.search.
 * Waehler}.
 * @author Johann
 * <p>zuletzt bearbeitet: 22.06.2011</p>
 */
public class TestWaehler {

  /**
   * Test method for {@link wahllokal.business.search.
   * Waehler#Waehler(java.lang.String)}.
   * 
   * Testet den Konstruktor mit mehreren tausend 
   * Waehlern.
   */
  @Test
  public final void testWaehler() {
    
    // Set up SUT
    ArrayList<Waehler> list = new ArrayList<Waehler>();
    final int waehlerAnzahl = 5500;  
    Waehler w;
    
    // Exercise SUT
    for (int i = 0; i < waehlerAnzahl; i++) {
      
      w = new Waehler(new Integer(i).toString());
      list.add(w);
    }
    
    // Verify Outcome
    for (int i = 0; i < list.size(); i++) {
      
      Assert.assertNotNull(list.get(i));
      Assert.assertTrue(list.get(i).getNummer().equals(
          new Integer(i).toString()));
      
      Assert.assertNull(list.get(i).getWaehlerKey());
      Assert.assertFalse(list.get(i).isHatGewahelt());
          
    }   
    // Tear Down
    
  }
  
  /**
   * Test method for {@link wahllokal.business.search.
   * Waehler#setWaehlerKey(java.lang.String)}.
   * 
   * Der WaehlerKey soll immer nur ein einziges Mal
   * gesetzt werden koennen. Jeder Weitere Aufruf
   * von setWaehlerKey hat keine Auswirkungen.
   */
  @Test
  public final void testSetWaehlerKey() {
    
    // Set up SUT  
    Waehler w = new Waehler("1");
    
    // Exercise SUT
    w.setWaehlerKey("200");
    w.setWaehlerKey("300");
    
    // Verify Outcome
    Assert.assertSame("200", w.getWaehlerKey());
    
    // Tear Down    
    
  }

} // testclass end
