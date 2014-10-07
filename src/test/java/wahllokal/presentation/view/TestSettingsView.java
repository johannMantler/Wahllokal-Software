package wahllokal.presentation.view;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test class for {@link wahllokal.presentation.view.SettingsView}.
 * @author Johann
 * <p>Zuletzt bearbeitet: 12.06.2011</p>
 */
public class TestSettingsView {

  /**
   * Test method for {@link wahllokal.presentation.view.
   * SettingsView#getInstance(wahllokal.presentation.view.SearchView)}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Der erste Aufruf von diser Methode muss ein neues
   * SettingsView Objekt zurueck liefern. Jeder weitere Aufruf
   * dieser Methode muss immer wieder dasselbe Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance1() {
    
    // Set up SUT
    SearchView searchView;
    SettingsView settingsView1;
    SettingsView settingsView2;
    SettingsView settingsView3;
    
    // Exercise SUT
    searchView = SearchView.getInstance();
    settingsView1 = SettingsView.getInstance(searchView);
    settingsView2 = SettingsView.getInstance(searchView);
    settingsView3 = SettingsView.getInstance(searchView);
    
    // Verify Outcome
    Assert.assertNotNull(settingsView1);
    Assert.assertNotNull(settingsView2);
    Assert.assertNotNull(settingsView3);
    Assert.assertEquals(settingsView1, settingsView2);
    Assert.assertEquals(settingsView3, settingsView2);
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.presentation.view.
   * SettingsView#getInstance(wahllokal.presentation.view.SearchView)}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Was passiert, wenn der Methodenparameter null ist?
   * Dann muss getInstance() auch null zurueckgeben.
   */
  @Test
  public final void testGetInstance2() {
    
    // Set up SUT
    SettingsView settingsView;

    // Exercise SUT
    settingsView = SettingsView.getInstance(null);
    
    // Verify Outcome
    Assert.assertNull(settingsView);
    
    // Tear Down
    
  }
  
  /**
   * Test method for {@link wahllokal.presentation.view.
   * SettingsView#getInstance(wahllokal.presentation.view.SearchView)}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Die Methode wird 3 Mal aufgerufen. Beim zweiten Mal ist der Parameter
   * jedoch null. Was passiert?
   * getInstance() muss dennoch das einzige Objekt zuruckliefern, dass
   * es beim allerersten Aufruf erzeugt hat!
   */
  @Test
  public final void testGetInstance3() {
    
    // Set up SUT
    SearchView searchView;
    SettingsView settingsView1;
    SettingsView settingsView2;
    SettingsView settingsView3;

    // Exercise SUT
    searchView = SearchView.getInstance();
    settingsView1 = SettingsView.getInstance(searchView);
    settingsView2 = SettingsView.getInstance(null);
    settingsView3 = SettingsView.getInstance(searchView);
    
    
    // Verify Outcome
    Assert.assertEquals(settingsView1, settingsView3);
    Assert.assertNull(settingsView2);
    // Tear Down
    
  }
  
  /**
   * Test method for {@link wahllokal.presentation.view.
   * SettingsView#setDefaultInfoLabel()}.
   * Bei diesem Test wird getestet, ob der confirmButton
   * von dem SettingsView Objekt nach dem Aufruf der Methode
   * setDefaultInfoLabel() auch Enabled ist.
   * Der Rest, des Methodeninhalts kann nicht getestet werden.
   */
  @Test
  public final void testSetDefaultInfoLabel() {
    
    //Set up SUT
    SearchView searchView = SearchView.getInstance();
    SettingsView settingsView = SettingsView.getInstance(searchView);
    JButton confirmButton = settingsView.getOk();
    
    //Exercise SUT
    settingsView.setDefaultInfoLabel();
    
    //Verify Outcome
    Assert.assertTrue(confirmButton.isEnabled());
    
    //Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.presentation.view.
   * SettingsView#setErrorInfoLabel(java.lang.String)}.
   * Bei diesem Test wird getestet, ob der confirmButton
   * von dem SettingsView Objekt nach dem Aufruf der Methode
   * setErrorInfoLabel() auch nicht Enabled ist.
   * Der Rest, des Methodeninhalts kann nicht getestet werden.
   */
  @Test
  public final void testSetErrorInfoLabel() {
    
    //Set up SUT
    SearchView searchView = SearchView.getInstance();
    SettingsView settingsView = SettingsView.getInstance(searchView);
    JButton confirmButton = settingsView.getOk();
    
    //Exercise SUT
    settingsView.setErrorInfoLabel("");
    
    //Verify Outcome
    Assert.assertFalse(confirmButton.isEnabled());
    
    //Tear Down
    
  }

}
