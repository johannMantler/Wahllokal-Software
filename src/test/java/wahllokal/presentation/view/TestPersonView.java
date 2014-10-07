package wahllokal.presentation.view;

import org.junit.Assert;
import org.junit.Test;

import wahllokal.business.MainModel;


/**
 * Test class for {@link wahllokal.presentation.view.PersonView}.
 * @author Johann
 * <p>Zuletzt bearbeitet: 17.06.2011</p>
 */
public class TestPersonView {

  /**
   * Test method for {@link wahllokal.presentation.view.PersonView#getInstance(wahllokal.presentation.view.SearchView)}.
   * Pruefe die Methode mit korrekten Parameter.
   * Die Methode muss ein neues PersonView-Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance1() {
    
    // Set up SUT
    SearchView searchView;
    PersonView personView;
    String []daten = MainModel.getInstance().getWaehlerDaten("1");
    
    // Exercise SUT
    searchView = SearchView.getInstance();
    personView = PersonView.getInstance(searchView, daten);
    
    // Verify Outcome
    Assert.assertNotNull(personView);
    
    // Tear Down
    
  }
  
  /**
   * Test method for {@link wahllokal.presentation.view.PersonView#getInstance(wahllokal.presentation.view.SearchView)}.
   * Wenn der Methode null als Parameter uebergeben wird,
   * so muss die Methode auch wieder null zurueckliefer.
   */
  @Test
  public final void testGetInstance2() {
    
    // Set up SUT
    PersonView personView;
    String []daten = null;

    // Exercise SUT
    personView = PersonView.getInstance(null, daten);
    
    // Verify Outcome
    Assert.assertNull(personView);
    
    // Tear Down
    
  }
  

} //end class
