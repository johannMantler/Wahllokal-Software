package wahllokal.business.search;

import org.junit.Test;

import junit.framework.Assert;


/**
 * Test class for {@link wahllokal.business.search.BinaerSuchBaum}.
 * @author Johann
 * <p>Zuletzt bearbeitet: 16.06.2011</p>
 */
public class TestBinaerSuchBaum {
  
  /**
   * Test method for {@link wahllokal.business.search.
   * BinaerSuchBaum#getWurzel()}.
   * Erstelllt einen Baum mit und einen ohne Wurzel.
   */
  @Test
  public final void testGetWurzel() {
    
    // Set up SUT
    BinaerSuchBaum<String> bsb1;
    BinaerSuchBaum<String> bsb2;
    
    Knoten<String> bsb1Wurzel;
    Knoten<String> bsb2Wurzel;
    
    // Exercise SUT
    bsb1 = new BinaerSuchBaum<String>();
    bsb2 = new BinaerSuchBaum<String>();
    
    bsb2.einfuegen("Hallo");
    
    bsb1Wurzel = bsb1.getWurzel(); //muss null sein
    bsb2Wurzel = bsb2.getWurzel(); //muss nicht null sein
    
    //Verify Outcome
    Assert.assertNotNull(bsb2Wurzel);
    Assert.assertNull(bsb1Wurzel);
    
    //Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.search.BinaerSuchBaum#istLeer()}.
   * Erstelllt einen Baum mit und einen ohne Wurzel.
   */
  @Test
  public final void testIstLeer() {
    
    // Set up SUT
    BinaerSuchBaum<String> bsb1;
    BinaerSuchBaum<String> bsb2;
    
    boolean result1;
    boolean result2;
    
    // Exercise SUT
    bsb1 = new BinaerSuchBaum<String>();
    bsb2 = new BinaerSuchBaum<String>();
    
    bsb2.einfuegen("Hallo");
    
    result1 = bsb1.istLeer();
    result2 = bsb2.istLeer();
    
    //Verify Outcome
    Assert.assertFalse(result2);
    Assert.assertTrue(result1);
    
    //Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * BinaerSuchBaum#einfuegen(E[])}
   * and for {@link wahllokal.business.search.BinaerSuchBaum#einfuegen
   * (java.lang.Comparable)}
   * and for {@link wahllokal.business.search.BinaerSuchBaum#suche
   * (java.lang.Comparable)}.
   *  
   */
  @Test
  public final void testEinfuegenEArray() {
  
    // Set up SUT
    BinaerSuchBaum<Character> buchstabenBaum = new BinaerSuchBaum<Character>();
    
    
    // Exercise SUT
    buchstabenBaum.einfuegen('B', 'A', 'U', 'M', 'B', 'E', 'I', 'S', 
                             'P', 'I', 'E', 'L');
    
    
    Knoten<Character> suchL = buchstabenBaum.suche(new Character('L'));
    Knoten<Character> suchP = buchstabenBaum.suche(new Character('P'));
    Knoten<Character> suchM = buchstabenBaum.suche(new Character('M'));
    Knoten<Character> suchU = buchstabenBaum.suche(new Character('U'));
    Knoten<Character> suchA = buchstabenBaum.suche(new Character('A'));
    
    //Verify Outcome
    
    //So muss das aussehen:
    
    //               B
    //              / \
    //             /   \  
    //            /     \
    //           /       U
    //          A        /
    //           \      M 
    //            B   / \
    //               /   \
    //              /     \
    //             E       S
    //            / \     /
    //           E   I   P
    //                \
    //                 L

    
    Assert.assertNotNull(buchstabenBaum);
    
    Assert.assertTrue(suchL.getOben().getInhalt().compareTo('I') == 0);
    Assert.assertNull(suchL.getLinks());
    Assert.assertNull(suchL.getRechts());
    
    Assert.assertTrue(suchP.getOben().getInhalt().compareTo('S') == 0);
    Assert.assertNull(suchP.getLinks());
    Assert.assertNull(suchP.getRechts()); 
    
    Assert.assertTrue(suchM.getOben().getInhalt().compareTo('U') == 0);
    Assert.assertTrue(suchM.getLinks().getInhalt().compareTo('E') == 0);
    Assert.assertTrue(suchM.getRechts().getInhalt().compareTo('S') == 0);
    
    Assert.assertTrue(suchU.getOben().getInhalt().compareTo('B') == 0);
    Assert.assertTrue(suchU.getLinks().getInhalt().compareTo('M') == 0);
    Assert.assertNull(suchU.getRechts());
    
    Assert.assertTrue(suchA.getOben().getInhalt().compareTo('B') == 0);
    Assert.assertNull(suchA.getLinks());
    Assert.assertTrue(suchA.getRechts().getInhalt().compareTo('B') == 0);
    //Tear Down
    
  }
  /**
   * Was ist, wenn ein Knoten gesucht wird, welche garnicht im
   * Baum vorhanden ist.
   * ->null muss zurueckgegeben werden.
   */
  @Test
  public final void testSuche() {
    
    // Set up SUT
    BinaerSuchBaum<Character> buchstabenBaum = 
                       new BinaerSuchBaum<Character>();
    buchstabenBaum.einfuegen('B', 'A', 'U', 'M', 'B', 'E', 'I', 'S', 
                             'P', 'I', 'E', 'L');
    Knoten<Character> k;
    
    // Exercise SUT
    k = buchstabenBaum.suche('X');
    
    // Verify Outcome
    Assert.assertNull(k);
    
    // Tear Down
    
  }
  
} // end testclass

