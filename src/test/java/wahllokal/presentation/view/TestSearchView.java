
package wahllokal.presentation.view;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;



/**
 * Test class for {@link wahllokal.presentation.view.SearchView}.
 * @author Johann
 * <p>Zuletzt bearbeitet: 12.06.2011</p>
 */
public class TestSearchView {


  /**
   * Test method for {@link wahllokal.presentation.view.SearchView#getInstance()}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Der erste Aufruf von diser Methode muss ein neues
   * SearchView Objekt zurueck liefern. Jeder weitere Aufruf
   * dieser Methode muss immer wieder dasselbe Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance() {
 
    // Set up SUT
    SearchView view1;
    SearchView view2;
    SearchView view3;
    
    // Exercise SUT
    view1 = SearchView.getInstance();
    view2 = SearchView.getInstance();
    view3 = SearchView.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(view1);
    Assert.assertNotNull(view2);
    Assert.assertNotNull(view3);
    Assert.assertEquals(view1, view2);
    Assert.assertEquals(view3, view2);
    
    // Tear Down
    view1.getMainFrame().dispose();
  }

  /**
   * Test method for {@link wahllokal.presentation.view.SearchView#setDefaultInfoLabel()}.
   * Bei diesem Test wird getestet, ob der checkButton
   * von dem SearchView Objekt nach dem Aufruf der Methode
   * setDefaultInfoLabel() auch Enabled ist.
   * Der Rest, des Methodeninhalts kann nicht getestet werden.
   */
  @Test
  public final void testSetDefaultInfoLabel() {
    
    //Set up SUT
    SearchView view = SearchView.getInstance();
    JButton checkButton = view.getCheckButton();
    
    //Exercise SUT
    view.setDefaultInfoLabel();
    
    //Verify Outcome
    Assert.assertTrue(checkButton.isEnabled());
    
    //Tear Down
    view.getMainFrame().dispose();
  }

  /**
   * Test method for {@link wahllokal.presentation.view.SearchView#setBerechtigtInfoLabel(java.lang.String, boolean)}.
   * Bei diesem Test wird getestet, ob der checkButton
   * von dem SearchView Objekt nach dem Aufruf der Methode
   * setBerechtigtInfoLabel() auch Enabled ist.
   * Der Rest, des Methodeninhalts kann nicht getestet werden.
   */
  @Test
  public final void testSetBerechtigtInfoLabel() {
    
    //Set up SUT
    SearchView view = SearchView.getInstance();
    JButton checkButton = view.getCheckButton();
    
    //Exercise SUT
    view.setBerechtigtInfoLabel("", false);
    view.setBerechtigtInfoLabel("", true); //der 2. Aufruf darf 
                                           //am Ergebis nichts aendern
    
    //Verify Outcome
    Assert.assertTrue(checkButton.isEnabled());
    
    //Tear Down
    view.getMainFrame().dispose();
  }

  /**
   * Test method for {@link wahllokal.presentation.view.SearchView#setErrorInfoLabel(java.lang.String)}.
   * Bei diesem Test wird getestet, ob der checkButton
   * von dem SearchView Objekt nach dem Aufruf der Methode
   * setErrorInfoLabel() auch nicht Enabled ist.
   * Der Rest, des Methodeninhalts kann nicht getestet werden.
   */
  @Test
  public final void testSetErrorInfoLabel() {
    
    //Set up SUT
    SearchView view = SearchView.getInstance();
    JButton checkButton = view.getCheckButton();
    
    //Exercise SUT
    view.setErrorInfoLabel("");
    view.setErrorInfoLabel(""); //der 2. Aufruf darf 
                                //am Ergebis nichts aendern
    
    //Verify Outcome
    Assert.assertFalse(checkButton.isEnabled());
    
    //Tear Down
    view.getMainFrame().dispose();
  }

} //class end
