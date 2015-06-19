package coffeemaker;

/**
 * 
 * @author Sarah E. Smith
 *
 * Recipe object for the coffee maker
 */
public class Recipe {
  private String name;
  private int price;
  private int amtCoffee;
  private int amtMilk;
  private int amtSugar;
  private int amtChocolate;

  public int getAmtChocolate() {
    return amtChocolate;
  }
  public void setAmtChocolate(int amtChocolate) {
    if(amtChocolate >= 0) {
      this.amtChocolate = amtChocolate;
    }
    else {
      this.amtChocolate = 0;
    }
  }
  public int getAmtCoffee() {
    return amtCoffee;
  }
  public void setAmtCoffee(int amtCoffee) {
    if(amtCoffee >= 0) {
      this.amtCoffee = amtCoffee;
    }
    else {
      this.amtCoffee = 0;
    }
  }
  public int getAmtMilk() {
    return amtMilk;
  }
  public void setAmtMilk(int amtMilk) {
    if(amtMilk >= 0) {
      this.amtMilk = amtMilk;
    }
    else {
      this.amtMilk = 0;
    }
  }
  public int getAmtSugar() {
    return amtSugar;
  }
  public void setAmtSugar(int amtSugar) {
    if(amtSugar >= 0) {
      this.amtSugar = amtSugar;
    }
    else {
      this.amtSugar = 0;
    }
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    if(price >= 0) {
      this.price = price;
    }
    else {
      this.price = 0;
    }
  } 
  public boolean equals(Recipe r) {
    if(r.getName() == null) {
      return false;
    }       
    if(this.name == null) {
      return false;
    }
    if((this.name).equals(r.getName())) {
      return true;
    }
    return false;
  }
  public String toString() {
    return name;
  }
}