
package wahllokal.business.stimmzettel;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Johann
 *<p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestStimmzettelModel {

  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * StimmzettelModel#getInstance()}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Der erste Aufruf von diser Methode muss ein neues
   * StimmzettelModel Objekt zurueck liefern. Jeder weitere Aufruf
   * dieser Methode muss immer wieder dasselbe Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance() {
    
    // Set up SUT
    StimmzettelModel model1;
    StimmzettelModel model2;
    StimmzettelModel model3;
    
    // Exercise SUT
    model1 = StimmzettelModel.getInstance();
    model2 = StimmzettelModel.getInstance();
    model3 = StimmzettelModel.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(model1);
    Assert.assertNotNull(model2);
    Assert.assertNotNull(model3);
    Assert.assertEquals(model1, model2);
    Assert.assertEquals(model3, model2);
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * StimmzettelModel#getBeruf(int)}.
   * 
   * Dieser Test, testet die Methode mit den StimmzettelDummyDaten!
   */
  @Test
  public final void testGetBeruf() {
    
    // Set up SUT
    StimmzettelModel model = StimmzettelModel.getInstance();
    String berufZeile1;
    String berufZeile2;
    
    // Exercise SUT
    berufZeile1 = model.getBeruf(0);
    berufZeile2 = model.getBeruf(1);
    
    // Verify Outcome
    Assert.assertTrue(berufZeile1.equals("Geschäftsführer"));
    Assert.assertTrue(berufZeile2.equals("Betriebswirt"));
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * StimmzettelModel#getZweitKandidaten(int)}.
   * 
   * Dieser Test, testet die Methode mit den StimmzettelDummyDaten!
   */
  @Test
  public final void testGetZweitKandidaten() {
    
    // Set up SUT
    StimmzettelModel model = StimmzettelModel.getInstance();
    ArrayList<String> listZeile1;
    ArrayList<String> listZeile2;
    
    String name1Zeile1;
    String name2Zeile2;
    
    // Exercise SUT
    listZeile1 = model.getZweitKandidaten(0);
    listZeile2 = model.getZweitKandidaten(1);
    
    name1Zeile1 = listZeile1.get(0);
    name2Zeile2 = listZeile2.get(1);
    
    // Verify Outcome
    Assert.assertTrue(name1Zeile1.equals("Peter Harry Carstensen"));
    Assert.assertTrue(name2Zeile2.equals("Dr. Gitta Trauernicht"));
    
    // Tear Down
    
  }

} // testclass end
