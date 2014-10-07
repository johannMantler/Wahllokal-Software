/**
 * 
 */
package wahllokal.business.search;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;


/**
 * @author Johann
 *<p>zuletzt bearbeitet: 16.06.2011</p>
 */
public class TestWaehlerDummyDaten {

  /**
   * Test method for {@link wahllokal.business.search.
   * WaehlerDummyDaten#WaehlerDummyDaten()}.
   */
  @Test
  public final void testWaehlerDummyDaten() {
    
    // Set up SUT
    WaehlerDummyDaten wdd;
    
    // Exercise SUT
    wdd = new WaehlerDummyDaten();
    
    // Verify Outcome
    Assert.assertNotNull(wdd);
    
    // Tear Down
  }

  /**
   * Test method for {@link wahllokal.business.
   * search.WaehlerDummyDaten#getDummyWaehler()}.
   */
  @Test
  public final void testGetDummyWaehler() {
    
    // Set up SUT
    WaehlerDummyDaten wdd;
    ArrayList<Waehler> list;
    
    // Exercise SUT
    wdd = new WaehlerDummyDaten();
    list = wdd.getDummyWaehler();
    
    // Verify Outcome
    Assert.assertNotNull(list);
    
    // Tear Down
    
  }

}
