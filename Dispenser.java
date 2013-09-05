import java.util.*;
import java.io.*;

class Dispenser implements Serializable{
  FoodInformation item;
  private int quantity = 0;
  private int capacity = 0;
  private boolean isEmpty = true;

  public Dispenser(FoodInformation itemIn, int quantityIn, int capacityIn){ 
    item = itemIn;
    if(quantityIn > 0)
      quantity = quantityIn;
    if(capacityIn > 0)
      capacity = capacityIn;
  }

  public String getName() { 
    return item.getName();
  }
  
  public double getCost() {
    return item.getCost();
  }

  public String getNutritionalInformation(){
    return item.getNutritionalInformation();
  }

  public void buy() throws StockException{
    if(quantity <= 0)
      throw new StockException("Out of Stock");
    else
      quantity--;
  }
  
  public boolean isEmpty(){
    return isEmpty;
  }

  



}

