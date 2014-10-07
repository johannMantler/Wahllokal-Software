
package wahllokal.business.stimmzettel;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Johann
 *<p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestCounterModel {

  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * CounterModel#getInstance()}.
   */
  @Test
  public final void testGetInstance() {
    
    // Set up SUT
    CounterModel model1;
    CounterModel model2;
    CounterModel model3;
    
    // Exercise SUT
    model1 = CounterModel.getInstance();
    model2 = CounterModel.getInstance();
    model3 = CounterModel.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(model1);
    Assert.assertNotNull(model2);
    Assert.assertNotNull(model3);
    Assert.assertEquals(model1, model2);
    Assert.assertEquals(model3, model2);
    
    // Tear Down
    
  }
  
  /**
   * Test fuer initCounterArrayList()
   * und copyList() und getCounter().
   * Hier wird geprueft, ob auch wirklich alle
   * Zaehlstaende am Anfang mit 0 initialisiert werden.
   */
  @Test
  public final void initCounterArrayList() {
    
    // Set up SUT
    CounterModel model;
    ArrayList<Integer> erstList;
    ArrayList<Integer> zweitList;
    
    // Exercise SUT
    model = CounterModel.getInstance();
    erstList = model.getCounter(true);
    zweitList = model.getCounter(false);
    
    // Verify Outcome
    Assert.assertTrue(0 == erstList.get(0));
    Assert.assertTrue(0 == zweitList.get(0));
    Assert.assertTrue("0".equals(erstList.get(0).toString()));
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * CounterModel#counter(int, boolean)}.
   */
  @Test
  public final void testCounter() {
    
    // Set up SUT
    CounterModel model;
    final int zeile1 = 1;
    final int zeile2 = 2;
    final int result1 = 4;
    final int result2 = 1;
    
    // Exercise SUT
    model = CounterModel.getInstance();
    
    model.count(zeile1, true);
    model.count(zeile1, true);
    model.count(zeile1, true);
    model.count(zeile1, true); //die erstimme an stelle 1 wird auf 4 erhoeht.
    model.count(zeile2, false); //zweitstimme stelle 2 auf 1
                               //alle andern stimmen sind noch auf 0
    
    // Verify Outcome
    Assert.assertTrue(result1 == model.getCounter(true).get(zeile1));
    Assert.assertTrue(result2 == model.getCounter(false).get(zeile2));
    Assert.assertTrue("0".equals(model.getCounter(true).get(0).toString())); 
    
    // Tear Down 
    
  }

}
