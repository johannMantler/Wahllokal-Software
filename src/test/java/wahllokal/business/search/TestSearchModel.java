
package wahllokal.business.search;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


/**
 * @author Johann
 * <p>zuletzt bearbeitet: 23.06.2011</p>
 */
public class TestSearchModel {

  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#getInstance()}.
   * Hier wird die Singelton-Methode getInstance() getestet.
   * Der erste Aufruf von diser Methode muss ein neues
   * SearchModel Objekt zurueck liefern. Jeder weitere Aufruf
   * dieser Methode muss immer wieder dasselbe Objekt zurueckgeben.
   */
  @Test
  public final void testGetInstance() {
    
    // Set up SUT
    SearchModel model1;
    SearchModel model2;
    SearchModel model3;
    
    // Exercise SUT
    model1 = SearchModel.getInstance();
    model2 = SearchModel.getInstance();
    model3 = SearchModel.getInstance();
    
    // Verify Outcome
    Assert.assertNotNull(model1);
    Assert.assertNotNull(model2);
    Assert.assertNotNull(model3);
    Assert.assertEquals(model1, model2);
    Assert.assertEquals(model3, model2);
    
    // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.
   * search.SearchModel#searchWaehler(
   * java.lang.String)}.
   * Testet die Methode einmal mit einer gueltigen
   * Personalnummer und einmal mit einer ungueltigen.
   */
  @Test
  public final void testSearchWaehler() {
   
   // Set up SUT
   SearchModel model = SearchModel.getInstance();
   Waehler w1;
   Waehler w2;
   
   // Exercise SUT
   w1 = model.searchWaehler("1");
   w2 = model.searchWaehler("DieseNummerGibtEsNicht");
   
   // Verify Outcome
   Assert.assertTrue(w1.getNachName().equals("Müller"));
   Assert.assertNull(w2);
   // Tear Down
    
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#insertActiveWaehler
   * (wahllokal.business.search.Waehler)}.
   */
  @Test
  public final void testInsertActiveWaehler() {
    
    // Set Up SUT
    SearchModel model = SearchModel.getInstance();
    Waehler w1 = new Waehler("1");
    Waehler w2 = new Waehler("2");
    Waehler w3 = model.searchWaehler("77"); //von DummyDaten
    Waehler w4 = null;
    
    // Exercise SUT
    model.insertActiveWaehler(w1);
    model.insertActiveWaehler(w2);
    model.insertActiveWaehler(w3);
    model.insertActiveWaehler(w4);
    
    // Verify Outcome
    Assert.assertNotNull(w1);
    Assert.assertNotNull(w2);
    Assert.assertNotNull(w3);
    Assert.assertNull(w4);
    
    Assert.assertFalse(w1.getWaehlerKey().equals(w2.getWaehlerKey()));
    Assert.assertFalse(w2.getWaehlerKey().equals(w3.getWaehlerKey()));
    
    // Tear Down
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#insertActiveWaehler
   * (wahllokal.business.search.Waehler)}.
   * 
   * Testet die Methode mit mehreren tausend Waehlern.
   * Alle Aktiven Waehler müssen einen eindeutigen Key
   * bekommen.
   */
  @Test
  public final void testInsertActiveWaehler2() {
    
    // Set Up SUT
    ArrayList<Waehler> list = new ArrayList<Waehler>();
    SearchModel model = SearchModel.getInstance();
  
    Waehler w;
    final int waehlerAnzahl = 5500; //ab 9999 gibts endlosschleife
                                    // weil das Limit der RandomZahlen dann
                                    // erreicht ist. -> Es werden keine
                                    // neuen randomzahlen mehr gefunden.
    
    // Exercise SUT
    for (int i = 0; i < waehlerAnzahl; i++) {
      
      w = new Waehler(new Integer(i).toString());
      model.insertActiveWaehler(w);
      list.add(w);
    }

    // Verify Outcome
    for (int i = 0; i < list.size() - 1; i++) {
      
      for (int j = 0; j < list.size() - 1; j++) {
        
        if (j != i) {
          
          Assert.assertFalse(list.get(i).getWaehlerKey().
              equals(list.get(j).getWaehlerKey()));
        
        } else {
          
          Assert.assertTrue(list.get(i).getWaehlerKey().
              equals(list.get(j).getWaehlerKey()));
        }
        
      }
    }
    
    // Tear Down
  }
  
  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#searchKey(java.lang.String)}.
   */
  @Test
  public final void testSearchKey() {
    
    // Set Up SUT
    SearchModel model = SearchModel.getInstance();
  
    Waehler w = new Waehler("232232232");
    boolean find = false;
    String wKey = "58";
    w.setWaehlerKey(wKey);
    
    // Exercise SUT
    model.insertActiveWaehler(w);
    find = model.searchKey(wKey);
    
    // Verify Outcome
    Assert.assertTrue(find);
    
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#getAktWaehler()}.
   * 
   * Der aktuelle Waehler muss immer der sein, der
   * zuletzt mit searchWaehler gesucht wurde.
   */
  @Test
  public final void testGetAktWaehler() {
    
    // Set up SUT
    SearchModel model = SearchModel.getInstance();
    Waehler w1;
    Waehler w2;
    
    // Exercise SUT
    w1 = model.searchWaehler("1");
    w2 = model.searchWaehler("2");
    
    // Verify Outcome
    Assert.assertNotSame(w1, model.getAktWaehler());
    Assert.assertSame(w2, model.getAktWaehler());
    
  }

  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#deleteActiveWaehler(wahllokal.business.search.Waehler)}.
   */
  @Test
  public final void testDeleteActiveWaehler() {
   
    // Set up SUT
    SearchModel model = SearchModel.getInstance();
    Waehler w1 = new Waehler("1");
    Waehler w2 = new Waehler("2");
    
    model.insertActiveWaehler(w1);
    model.insertActiveWaehler(w2);
    
    // Exercise SUT
    model.deleteActiveWaehler(w1);
    model.deleteActiveWaehler(w2);
    
    model.deleteActiveWaehler(new Waehler("5")); //Dieser Aufruf hat
                                     //keine Auswirkungen, da es diesen
                                     //Waehler nicht gibt.
    model.deleteActiveWaehler(null);
    // Verify Outcome
    Assert.assertTrue(w1.isHatGewahelt());
    Assert.assertTrue(w2.isHatGewahelt());
  }

  
  /**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#searchActiveWaehler(java.lang.String)}.
   */
  @Test
  public final void testSearchActiveWaehler() {
   
    // Set up SUT
    SearchModel model = SearchModel.getInstance();
    Waehler w1 = new Waehler("1");
    Waehler w2 = new Waehler("2");
    Waehler w3 = new Waehler("3");
    Waehler w4 = new Waehler("4");
    Waehler w5 = new Waehler("5");
    
    w5.setWaehlerKey("5"); // Die Keys aendern sich nicht mehr
    w4.setWaehlerKey("4");
    
    model.insertActiveWaehler(w1);
    model.insertActiveWaehler(w2);
    model.insertActiveWaehler(w3);
    model.insertActiveWaehler(w4);
    model.insertActiveWaehler(w5);
    
    Waehler result1;
    Waehler result2;
    
    // Exercise SUT
    result1 = model.searchActiveWaehler("5");
    result2 = model.searchActiveWaehler("4");
    
    // Verify Outcome
    Assert.assertTrue(result1.equals(w5));
    Assert.assertTrue(result2.equals(w4));
    
  }
  
  /**
   * Testet die Methode getGesamtWaehlerAnzahl().
   * Basiert auf den WaehlerDummyDaten!
   */
  @Test
  public final void testGetGesamtWaehlerAnzahl() {
    
    // Set up SUT
    SearchModel model = SearchModel.getInstance();
    int result;
    final int anzahl = 5;  //es gibt 5 Dummywaehler
    
    // Exercise SUT
    result = model.getGesamtWaehlerAnzahl();
    
    // Verify Outcome
    Assert.assertTrue(result == anzahl);
    
  }
  
  /**
   * Testet die Methode getAktiveWaehlerAnzahl().
   */
  @Test
  public final void testgetAktiveWaehlerAnzahl() {
    
    // Set Up SUT
    SearchModel model = SearchModel.getInstance();
    int result;
      
    model.insertActiveWaehler(new Waehler("nummer"));
    
    // Exercise SUT
    result = model.getAktiveWaehlerAnzahl();
      
    // Verify Outcome
    Assert.assertTrue(result > 0); //durch die vorangegangenen Tests
                                   // ist die Anzahl nicht genau
                                   // bestimmbar
    
    // Tear Down
  }

  
/*  *//**
   * Test method for {@link wahllokal.business.search.
   * SearchModel#searchActiveWaehler(java.lang.String)}.
   *//*
  @Test
  public final void testSearchActiveWaehler() {
   
    // Set up SUT
    SearchModel model = SearchModel.getInstance();
    Waehler w1 = new Waehler("1");
    Waehler w2 = new Waehler("2");
    Waehler w3 = new Waehler("3");
    Waehler w4 = new Waehler("4");
    Waehler w5 = new Waehler("5");
    
    w5.setWaehlerKey("5"); // Die Keys aendern sich nicht mehr
    w4.setWaehlerKey("4");
    
    model.insertActiveWaehler(w1);
    model.insertActiveWaehler(w2);
    model.insertActiveWaehler(w3);
    model.insertActiveWaehler(w4);
    model.insertActiveWaehler(w5);
    
    Waehler result1;
    Waehler result2;
    
    // Exercise SUT
    result1 = model.searchActiveWaehler("5");
    result2 = model.searchActiveWaehler("4");
    
    // Verify Outcome
    Assert.assertTrue(result1.equals(w5));
    Assert.assertTrue(result2.equals(w4));
    
  }*/



}
