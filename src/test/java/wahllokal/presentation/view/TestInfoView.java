
package wahllokal.presentation.view;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Johann
 *<p>zuletzt bearbeitet: 26.06.2011</p>
 */
public class TestInfoView {

  /**
   * Test method for {@link wahllokal.presentation.view.
   * InfoView#InfoView(wahllokal.presentation.view.SearchView)}.
   * 
   * Einfacher Konstruktotest.
   */
  @Test
  public final void testInfoView() {
    
    // Set up SUT
    InfoView view;
    
    //Exercise SUT
    view = new InfoView(SearchView.getInstance());
    
    // Verify Outcome
    Assert.assertNotNull(view);
    
    // Tear Down
    
  }

} //testclass end
