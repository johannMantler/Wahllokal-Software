
package wahllokal.presentation.view;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Johann
 *<p>zuletzt bearbeitet: 24.06.2011</p>
 */
public class TestHelpView {

  /**
   * Test method for {@link wahllokal.presentation.
   * view.HelpView#HelpView(wahllokal.presentation.view.SearchView)}.
   * 
   * Einfacher Konstruktortest.
   */
  @Test
  public final void testHelpView() {
    
    // Set up SUT
    HelpView view;
    
    //Exercise SUT
    view = new HelpView(SearchView.getInstance());
    
    // Verify Outcome
    Assert.assertNotNull(view);
    
    // Tear Down
    
  }

} //testclass end
