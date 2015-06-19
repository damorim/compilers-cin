package coffeemaker;

/**
 * 
 * @author Sarah E. Smith
 *
 * Inventory for the coffee maker
 */
public class Inventory {

  private static int coffee;
  private static int milk;
  private static int sugar;
  private static int chocolate;

  public Inventory() {
    setCoffee(15);
    setMilk(15);
    setSugar(15);
    setChocolate(15);
  }

  public int getChocolate() {
    return chocolate;
  }
  public void setChocolate(int chocolate) {
    if(chocolate >= 0) {
      Inventory.chocolate = chocolate;
    }
    else {
      Inventory.chocolate = 0;
    }

  }
  public int getCoffee() {
    return coffee;
  }
  public void setCoffee(int coffee) {
    if(coffee >= 0) {
      Inventory.coffee = coffee;
    }
    else {
      Inventory.coffee = 0;
    }
  }
  public int getMilk() {
    return milk;
  }
  public void setMilk(int milk) {
    if(milk >= 0) {
      Inventory.milk = milk;
    }
    else {
      Inventory.milk = 0;
    }
  }
  public int getSugar() {
    return sugar;
  }
  public void setSugar(int sugar) {
    if(sugar >= 0) {
      Inventory.sugar = sugar;
    }
    else {
      Inventory.sugar = 0;
    }
  }

  /**
   * Returns true if there are enough ingredients to make
   * the beverage.
   * @param r
   * @return boolean
   */
   public boolean enoughIngredients(Recipe r) {
     boolean isEnough = true;
     if(Inventory.coffee < r.getAmtCoffee()) {
       isEnough = false;
     }
     if(Inventory.milk < r.getAmtMilk()) {
       isEnough = false;
     }
     if(Inventory.sugar < r.getAmtSugar()) {
       isEnough = false;
     }
     if(Inventory.chocolate < r.getAmtChocolate()) {
       isEnough = false;
     }
     return isEnough;
   }

   public String toString() {
     return "Coffee: " + getCoffee() + "\n" +
         "Milk: " + getMilk() + "\n" +
         "Sugar: " + getSugar() + "\n" +
         "Chocolate: " + getChocolate() + "\n";
   }
}