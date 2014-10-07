
package wahllokal.business;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test class for {@link wahllokal.business.MainModel}.
 * Hier werden auch nur die wirklich testenswerten Methoden
 * getestet.
 * @author Johann
 * <p>zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestMainModel {

  /**
   * Test method for {@link wahllokal.business.MainModel#getInstance()}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Der erste Aufruf von diser Methode muss ein neues
   * MainModel Objekt zurueck liefern. Jeder weitere Aufruf
   * dieser Methode muss immer wieder dasselbe Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance() {
    
    // Set up SUT
    MainModel model1;
    MainModel model2;
    MainModel model3;
    
    // Exercise SUT
    model1 = MainModel.getInstance();
    model2 = MainModel.getInstance();
    model3 = MainModel.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(model1);
    Assert.assertNotNull(model2);
    Assert.assertNotNull(model3);
    Assert.assertEquals(model1, model2);
    Assert.assertEquals(model3, model2);
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.
   * MainModel#getZweitKandidaten}.
   * Pruefe ob der Erste, sowie der Letzte Kandidat
   * erfolgreich zurueckgegeben wird.
   */
  @Test
  public final void testGetZweitKandidaten() {
    
    // Set up SUT
    MainModel model = MainModel.getInstance();
    String []zeile1;
    
    
    // Exercise SUT
    zeile1 = model.getZweitKandidaten(0);
    
    final int indexEnd = zeile1.length - 1;
    final int arrayLength = 5;
    
    // Verify Outcome
    Assert.assertTrue(zeile1[0].equals("Peter Harry Carstensen"));
    Assert.assertTrue(zeile1[indexEnd].equals("Frank Sauter"));
    Assert.assertTrue(arrayLength == zeile1.length);
    
    // Tear Down
    
  }
  
  /**
   * Test method for {@link wahllokal.business.
   * MainModel#getWaehlerDaten(java.lang.String)}.
   * 
   * Testet die Methode einmal mit einer gueltigen
   * PersonalNr und einmal mit einer ungueltigen.
   */
  @Test
  public final void testGetWaehlerDaten() {
    
    // Set up SUT
    MainModel model = MainModel.getInstance();
    String []w1;
    String []w2;
    
    final int nNamePosition = 0;
    final int vNamePosition = 1;
    final int gebDatumPosition = 2;
    
    // Exercise SUT
    w1 = model.getWaehlerDaten("1");
    w2 = model.getWaehlerDaten("DieseNummerGibtEsNicht");
    
    // Verify Outcome
    Assert.assertTrue(w1[nNamePosition].equals("Müller"));
    Assert.assertTrue(w1[vNamePosition].equals("Hans"));
    Assert.assertTrue(w1[gebDatumPosition].equals("20.05.1971"));
    
    Assert.assertNull(w2);
    // Tear Down
    
  }

} // testclass end
