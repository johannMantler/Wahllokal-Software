
package wahllokal.business.stimmzettel;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;



/**
 * @author Johann
 * <p>Zuletzt bearbeitet: 17.06.2011</p>
 */
public class TestStimmzettelZeile {

  /**
   * Testet den Konstruktor.
   */
  @Test
  public final void testStimmzettelZeile() {
    
    // Set up SUT
    StimmzettelZeile sz;
    
    // Exercise SUT
    sz = new StimmzettelZeile();
    
    // Verify Outcome
    Assert.assertNotNull(sz);
    
    // Tear Down    
    
  }
  
  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * StimmzettelZeile#getParteiKurz()}
   * and for {@link wahllokal.business.stimmzettel.
   * StimmzettelZeile#setParteiKurz(java.lang.String)}.
   * 
   * Zuerst wird die Variable ParteiKurz gesetzt und dann
   * geprueft ob die get-Methode genau das wieder zurueck
   * liefert.
   */
  @Test
  public final void testSetGetParteiKurz() {
    
    // Set up SUT
    StimmzettelZeile sz = new StimmzettelZeile();
    String input = "hallo";
    String result;
    
    // Exercise SUT
    sz.setParteiKurz(input);
    result = sz.getParteiKurz();
    
    // Verify Outcome
    Assert.assertTrue(input.equals(result));

    // Tear Down
    
  }

  
  /**
   * Test method for {@link wahllokal.business.stimmzettel.
   * StimmzettelZeile#getZweitStimmeKandidaten()} 
   * and for {@link wahllokal.business.stimmzettel.
   * StimmzettelZeile#setZweitStimmeKandidaten
   * (java.util.ArrayList)}.
   */
  @Test
  public final void testSetGetZweitStimmeKandidaten() {
    
    // Set up SUT
    StimmzettelZeile sz = new StimmzettelZeile();
    
    ArrayList<String> resultList;
    
    ArrayList<String> list1 = new ArrayList<String>();
    list1.add("Peter Harry Carstensen");
    list1.add("Dr. Christian von Boetticher");
    list1.add("Herlich Marie Todsen-Reese");
    list1.add("Rainer Wiegard");
    list1.add("Frank Sauter");
    
    int resultListSize;
    int list1Size;
    
   // Exercise SUT
   sz.setZweitStimmeKandidaten(list1);
   resultList = sz.getZweitStimmeKandidaten();
   
   resultListSize = resultList.size();
   list1Size = list1.size();
   
   // Verify Outcome
   Assert.assertTrue(resultList.get(0).equals(list1.get(0)));
   
   Assert.assertEquals(resultListSize, list1Size);
   
   Assert.assertTrue(resultList.get(resultListSize - 1).equals(
                                list1.get(list1Size - 1)));
   
   // Tear Down
    
  }

} // testclass end
