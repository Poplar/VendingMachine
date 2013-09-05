import java.util.*;
import java.io.*;
class FoodInformation implements Serializable{
  private String name;
  private double cost;
  private String nutritionalInformation;

  public FoodInformation(String nameIn, double costIn, String nutritionalInformationIn) {
    name = nameIn;
    cost = costIn;
    nutritionalInformation = nutritionalInformationIn;
  }
  
  public String getName(){
    return name;
  }
  public double getCost(){
    return cost;
  }

  public String getNutritionalInformation(){
    return nutritionalInformation;
  }
}
