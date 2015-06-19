//package coffeemaker;
//
//import junit.framework.TestCase;
//
///**
// *
// */
//public class CoffeeMakerTest extends TestCase {
//  private CoffeeMaker cm;
//  private Inventory i;
//  private Recipe r1;
//  private Recipe r2;
//
//  public void setUp() {
//    cm = new CoffeeMaker();
//    i = cm.checkInventory();
//
//    r1 = new Recipe();
//    r1.setName("Coffee");
//    r1.setPrice(50);
//    r1.setAmtCoffee(6);
//    r1.setAmtMilk(1);
//    r1.setAmtSugar(1);
//    r1.setAmtChocolate(0);
//
//    r2 = new Recipe();
//    r2.setName("Mocha");
//    r2.setPrice(50);
//    r2.setAmtCoffee(100);
//    r2.setAmtMilk(100);
//    r2.setAmtSugar(100);
//    r2.setAmtChocolate(100);
//
//  }
//
//  public void testAddRecipe1() {
//    assertTrue(cm.addRecipe(r1));
//    assertFalse(cm.addRecipe(r1));
//  }
//
//  public void testDeleteRecipe1() {
//    cm.addRecipe(r1);
//    assertTrue(cm.deleteRecipe(r1));
//  }
//
//  public void testEditRecipe1() {
//    cm.addRecipe(r1);
//    Recipe newRecipe = new Recipe();
//    newRecipe = r1;
//    newRecipe.setAmtSugar(2);
//    assertTrue(cm.editRecipe(r1, newRecipe));
//    assertFalse(cm.editRecipe(r2, r2));
//  }
//
//  public void testAddInventoryCoffee() {
//    assertTrue(cm.addInventory(10,0,0,0));  
//    i = cm.checkInventory();
//    assertEquals(25, i.getCoffee());
//  }
//
//  public void testAddInventoryMilk() {
//    assertTrue(cm.addInventory(0,10,0,0));
//    i = cm.checkInventory();
//    assertEquals(25, i.getMilk());
//  }
//
//  public void testAddInventoryChocolate() {
//    assertTrue(cm.addInventory(0,0,0,10));  
//    i = cm.checkInventory();
//    assertEquals(25, i.getChocolate());
//  }
//
//  public void testAddInventorySugar() {
//    assertTrue(cm.addInventory(0,0,10,0));
//    i = cm.checkInventory();
//    assertEquals(25, i.getSugar());
//  }
//
//  public void testPurchaseBeverage() {
//    assertEquals(0, cm.makeCoffee(r1, 50));
//    assertEquals(9, i.getCoffee());
//    assertEquals(14, i.getMilk());
//    assertEquals(14, i.getSugar());
//    assertEquals(15, i.getChocolate());
//    assertEquals(25, cm.makeCoffee(r1, 25));
//    cm.addRecipe(r2);
//    assertEquals(50, cm.makeCoffee(r2, 50));
//  }
//
//
//
//}