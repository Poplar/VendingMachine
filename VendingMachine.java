import java.io.*;
import java.util.*;

class VendingMachine implements Serializable{
  private Dispenser[] dispensers;
  private int[] sales;
  //private double balance; 
  private Random randy = new Random();
  private int dispenserItems;
  
  private String[] foodNames = {
    "Lays", 
    "Pringles", 
    "Doritos", 
    "Cheetos",
    "Chex Mix", 
    "Skittles", 
    "Sun Chips", 
    "Fritos", 
    "Combos", 
    "Mentos", 
    "Milkduds", 
    "Hersheys"};

  private String[] drinkNames = {
    "Sprite", 
    "Coke", 
    "Fanta", 
    "Root Beer", 
    "Ginger Ale", 
    "Grape Soda", 
    "Iced Tea", 
    "Cherry Coke", 
    "Vanilla Coke",
    "Lemonade"};

  private double[] foodPrices = {
    0.75, 
    1.00, 
    1.25, 
    1.50, 
    1.75, 
    2.00, 
    2.25, 
    2.50, 
    2.75};

  private String[] foodNutritionalInformation = {
    "80", 
    "130", 
    "150", 
    "200", 
    "250", 
    "375", 
    "425", 
    "500"}; 


  public VendingMachine(int dispenserNumber){
    dispensers = new Dispenser[dispenserNumber];
    dispenserItems = randy.nextInt( 80 ) + 20;
    boolean randType =randy.nextBoolean();
    sales = new int[dispenserNumber];
    for(int i = 0; i < dispenserNumber; i++){
      double randPrice =foodPrices[ randy.nextInt(foodPrices.length) ];
      String randNutritionalInformation = foodNutritionalInformation[ randy.nextInt(foodNutritionalInformation.length) ];
      // If randType = true, stock food items, if false stock drinks
      int randName = ( randType ) ? 
        randy.nextInt(foodNames.length) :
        randy.nextInt(drinkNames.length);
      String itemName = ( randType ) ? 
        foodNames[ randName ] : 
        drinkNames[ randName ];
      FoodInformation item = new FoodInformation(
        itemName,
        randPrice,
        randNutritionalInformation);
      dispensers[i] = new Dispenser( item, dispenserItems, dispenserItems );
      sales[i] = 0;
    }
      
  }


/*  public VendingMachine( Dispenser[] inDispensers  ) { 
    dispensers = new Dispenser[inDispensers.length];
    for(int i = 0; i < inDispensers.length; i++){
      dispensers[i] = inDispensers[i];
      sales[i] = 0;
    }
  }

*/
/* public void insertMoney(double money){
    balance += money;
  }
  public int returnChange(){
    ;
  }
*/
  public double sale(int itemNumber, double money )throws SelectionException, PriceException, StockException {
    
    if(itemNumber >= 0 && itemNumber < dispensers.length){ 
      if(money >= dispensers[itemNumber].getCost()){
        dispensers[itemNumber].buy();
        sales[itemNumber]++;

        String itemName = dispensers[itemNumber].getName();
        double itemCost = dispensers[itemNumber].getCost();
        double change = money - itemCost;
        System.out.println(
          itemName + 
          " cost: $" + itemCost + 
          " money entered: $" + money + 
          " change: $" + change
        );

        return (money - dispensers[itemNumber].getCost());
      } else { 
        throw new PriceException("Insufficient Funds");
      }
    } else { 
      throw new SelectionException("Non-Existent Selction");
    }

    //return change
  } 


  public String toString(){
    String temp = "";
    for(int i = 0; i < dispensers.length; i++){
      
      String itemName = dispensers[i].getName();
      String itemNutr = dispensers[i].getNutritionalInformation();
      double itemCost = dispensers[i].getCost();

      temp += i + ": " + itemName + " - " + itemNutr + " Calories $" + itemCost + "\n";
    }
    return temp;
  }
  public int itemCount(){
    return dispensers.length;
  }
  public String salesTotal(){
    String temp = "";
    int runningTotal = 0;
    for(int i = 0; i < dispensers.length; i++){
      temp += dispensers[i].getName() + " sold " + sales[i] + " units for: $" + (sales[i] * dispensers[i].getCost()) + "\n";
      runningTotal+= (sales[i] * dispensers[i].getCost());
    }
    temp += "Total sales: " + runningTotal;
    return temp;
  }
  public void clearSales(){
    for(int i = 0; i < sales.length; i++)
      sales[i] = 0;
  }
 
}

