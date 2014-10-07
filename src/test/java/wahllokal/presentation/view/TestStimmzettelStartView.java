
package wahllokal.presentation.view;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Johann
 *<p>Zuletzt bearbeitet: 22.06.2011</p>
 */
public class TestStimmzettelStartView {

  /**
   * Test method for {@link wahllokal.presentation.
   * view.StimmzettelStartView#StimmzettelStartView()}.
   * 
   * Einfacher Konstruktortest.
   */
  @Test
  public final void testStimmzettelStartView() {
    
    // Set up SUT
    StimmzettelStartView view;
    
    //Exercise SUT
    view = new StimmzettelStartView();
    
    // Verify Outcome
    Assert.assertNotNull(view);
    
    // Tear Down
  }

}
