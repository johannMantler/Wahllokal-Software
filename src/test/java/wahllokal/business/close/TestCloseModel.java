
package wahllokal.business.close;

import java.util.ArrayList;
import java.util.Hashtable;

import junit.framework.Assert;
import org.junit.Test;

import wahllokal.business.MainModel;

/**
 * Testklasse fuer die Klasse CloseModel.
 * Die Tests basieren zum Teil auf den StimmzettelDummyDaten.
 * Sollten diese Veraendert werden, werden dementsprechend auch
 * die Tests beeintraechtigt.
 * @author Johann
 *<p>zuletzt bearbeitet: 26.06.2011</p>
 */
public class TestCloseModel {

  /**
   * Test method for {@link wahllokal.business.close.
   * CloseModel#getFirstVotes()}.
   */
  @Test
  public final void testGetFirstVotes() {
    
    // Set up SUT
    
    //fuer den Test muessen die Einstellungen gesetzt sein.
    MainModel mainModel = MainModel.getInstance();
    String bezirkNr = "2";
    String kreisNr = "15";
    mainModel.setBezirkNr(bezirkNr);
    mainModel.setKreisNr(kreisNr);
    
    CloseModel model = new CloseModel();
    ArrayList<Hashtable<String, String>> list;
    
    // Exercise SUT
    list = model.getFirstVotes();
    
    // Verify Outcome
    Assert.assertNotNull(list);
    
  }

  /**
   * Test method for {@link wahllokal.business.close.
   * CloseModel#setFirstVotes(java.util.ArrayList)}.
   */
  @Test
  public final void testSetFirstVotes() {
   
    // Set up SUT
    MainModel mainModel = MainModel.getInstance();
    String bezirkNr = "2";
    String kreisNr = "10";
    mainModel.setBezirkNr(bezirkNr);
    mainModel.setKreisNr(kreisNr);
    CloseModel model;
    
    // Exercise SUT
    model = new CloseModel();
    ArrayList<Hashtable<String, String>> list = model.getFirstVotes();
    
    // Verify Outcome
    for (int i = 0; i < list.size(); i++) {
      
      Assert.assertSame(bezirkNr, list.get(i).get("wb"));
      Assert.assertSame(kreisNr, list.get(i).get("wk"));
      Assert.assertTrue("0".equals(list.get(i).get("stimmen")));
    }
    
    Assert.assertSame("CDU", list.get(0).get("partei"));
    
  }

  /**
   * Test method for {@link wahllokal.business.close.
   * CloseModel#getSecondVotes()}.
   */
  @Test
  public final void testGetSecondVotes() {
    
    // Set up SUT
    
    //fuer den Test muessen die Einstellungen gesetzt sein.
    MainModel mainModel = MainModel.getInstance();
    String bezirkNr = "400";
    String kreisNr = "42342";
    mainModel.setBezirkNr(bezirkNr);
    mainModel.setKreisNr(kreisNr);
    
    CloseModel model = new CloseModel();
    ArrayList<Hashtable<String, String>> list;
    
    // Exercise SUT
    list = model.getSecondVotes();
    
    // Verify Outcome
    Assert.assertNotNull(list);
    
  }

  /**
   * Test method for {@link wahllokal.business.close.
   * CloseModel#setSecondVotes(java.util.ArrayList)}.
   */
  @Test
  public final void testSetSecondVotes() {
    
    // Set up SUT
    MainModel mainModel = MainModel.getInstance();
    String bezirkNr = "99";
    String kreisNr = "1";
    mainModel.setBezirkNr(bezirkNr);
    mainModel.setKreisNr(kreisNr);
    CloseModel model;
    
    // Exercise SUT
    model = new CloseModel();
    ArrayList<Hashtable<String, String>> list = model.getSecondVotes();
    
    // Verify Outcome
    for (int i = 0; i < list.size(); i++) {
      
      Assert.assertSame(bezirkNr, list.get(i).get("wb"));
      Assert.assertSame(kreisNr, list.get(i).get("wk"));
      Assert.assertTrue("0".equals(list.get(i).get("stimmen")));
    }
    
    Assert.assertSame("CDU", list.get(0).get("partei"));
    
  }

}
