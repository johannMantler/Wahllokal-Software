package wahllokal.presentation.view;

import javax.swing.JButton;
import javax.swing.JFrame;

import junit.framework.Assert;
import org.junit.Test;


/**
 * @author Johann
 * <p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestStimmzettelView {

  /**
   * Test method for {@link wahllokal.presentation.
   * view.StimmzettelView#StimmzettelView()}.
   * 
   * Einfacher Konstruktortest.
   */
  @Test
  public final void testStimmzettelView() {
    
    // Set up SUT
    StimmzettelView view;
    
    // Exercise SUT
    view = new StimmzettelView("");
    
    // Verify Outcome
    Assert.assertNotNull(view);
    
    // Tear Down
  }
  
  /**
   * Test method for {@link wahllokal.presentation.
   * view.StimmzettelView#getConfirmButton()}.
   */
  @Test
  public final void testGetConfirmButton() {
    
    // Set up SUT
    StimmzettelView view;
    JButton button;
    
    // Exercise SUT
    view = new StimmzettelView("");
    button = view.getConfirmButton();
    
    // Verify Outcome
    Assert.assertNotNull(button);
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.presentation.
   * view.StimmzettelView#getFrame()}.
   */
  @Test
  public final void testGetFrame() {
    
    // Set up SUT
    StimmzettelView view;
    JFrame frame;
    
    // Exercise SUT
    view = new StimmzettelView("");
    frame = view.getMainFrame();
    
    // Verify Outcome
    Assert.assertNotNull(frame);
    
    // Tear Down 
  }

} // testclass end
