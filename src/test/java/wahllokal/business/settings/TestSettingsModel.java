
package wahllokal.business.settings;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Johann
 * <p>Zuletzt bearbeitet: 18.06.2011</p>
 */
public class TestSettingsModel extends TestCase {

  /**
   * Test method for {@link wahllokal.business.
   * settings.SettingsModel#getInstance()}.
   */
  @Test
  public final void testGetInstance() {
    
    // Set up SUT
    SettingsModel model1;
    SettingsModel model2;
    SettingsModel model3;
    
    // Exercise SUT
    model1 = SettingsModel.getInstance();
    model2 = SettingsModel.getInstance();
    model3 = SettingsModel.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(model1);
    Assert.assertNotNull(model2);
    Assert.assertNotNull(model3);
    Assert.assertEquals(model1, model2);
    Assert.assertEquals(model3, model2);
    
    // Tear Down
    
  }

}
