package wahllokal.business.stimmzettel;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Johann
 * <p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestStimmzettelDummyDaten {

  /**
   * Test method for {@link wahllokal.business.
   * stimmzettel.StimmzettelDummyDaten#StimmzettelDummyDaten()}.
   * 
   * Testet den Konstruktor.
   */
  @Test
  public final void testStimmzettelDummyDaten() {
    
    // Set up SUT
    StimmzettelDummyDaten sdd;
    
    // Exercise SUT
    sdd = new StimmzettelDummyDaten();
    
    // Verify Outcome
    Assert.assertNotNull(sdd);
    
    // Tear Down
    
  }

  /**
   * Testet, ob alle Variablen auch gefuellt wurden.
   */
  @Test
  public final void testGetDummyDaten() {
    
    // Set up SUT
    StimmzettelDummyDaten sdd = new StimmzettelDummyDaten();
    int zeile = 2;
    
    // Exercise SUT
    String vorName = sdd.getVorname(zeile);
    String nachName = sdd.getNachname(zeile);
    String beruf = sdd.getBeruf(zeile);
    String ort = sdd.getOrt(zeile);
    Vector<String> vector = sdd.getZweitStimmenKandidaten(zeile);
    
    // Verify Outcome
    Assert.assertNotNull(vector);
    Assert.assertNotNull(ort);
    Assert.assertNotNull(beruf);
    Assert.assertNotNull(nachName);
    Assert.assertNotNull(vorName);
    
    // Tear Down
    
  }

} //testclass end
