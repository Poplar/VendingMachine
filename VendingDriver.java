import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
class VendingDriver {
  public static void main(String[] args){
    ArrayList<VendingMachine> machines = new ArrayList<VendingMachine>();
    Random randy = new Random();
    int clients = 0;
    double money = 0.0;
    int machineCount = 0;
    boolean finished = false;
    int patronage; //which machine the client is purchasing from
    int selection = 0;
    int attempts = 0;
    File vendingMachines = new File("Vending0.ser");
    String encoding = "UTF-8";

    if(vendingMachines.exists()){

      for(int i = 0; i < 5; i++){
        try{
          FileInputStream fileIn = new FileInputStream("Vending" + i + ".ser");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          machines.add((VendingMachine) in.readObject());
          in.close();
          fileIn.close();
        } 
      
        catch (IOException e){
          break;
        }
        catch (ClassNotFoundException c){
          c.printStackTrace();
          return;
        }
      
      }
    }
    else{
      machineCount = 2 + randy.nextInt(3);
      for(int i = 0; i < machineCount; i++){
        int dispensers = randy.nextInt(30) + 20;
        machines.add(new VendingMachine(dispensers));
      }
    }

    machineCount = machines.size();
    clients = 5 + randy.nextInt(15);//sets between 5 and 20 clients
    while(clients > 0){
      attempts = 0;
      money = 1.00 + (.25 * randy.nextInt(16)); 
      patronage = randy.nextInt(machineCount);
      while(attempts < 5){
        try{
          selection = randy.nextInt(machines.get(patronage).itemCount());
          machines.get(patronage).sale(selection, money);

        }
        catch (PriceException e){
          System.out.println(e.getMessage());
          attempts++;
          continue;
        }
        catch (StockException e){
          System.out.println(e.getMessage());
          attempts++;
          continue;
        }
        catch (SelectionException e){
          System.out.println(e.getMessage());
          attempts++; 
          continue;
        }

        finally {
          break;
        }

      }
    clients--;
    }
  
  for(int i = 0; i < machineCount; i++){
    Calendar date = Calendar.getInstance();
    //String temp = date
    date.add(Calendar.DATE, 1);
    try{
    SimpleDateFormat format1 = new SimpleDateFormat("MM.dd.yy.HH.mm.ss");
    File file = new File("Vending" + i + format1.format(date.getTime()));
    PrintWriter writer = new PrintWriter(file, encoding);
    writer.print(machines.get(i).salesTotal());
    writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  for(int i = 0; i < machineCount; i++){
    System.out.println(machines.get(i).toString());
  }
  for(int i = 0; i < machineCount; i++){
    machines.get(i).clearSales();
  }
  for(int i = 0; i < machineCount; i++){
    try {
      FileOutputStream fileOut = new FileOutputStream("Vending" + i + ".ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(machines.get(i));
      out.close();
      fileOut.close();
    }
    catch(IOException e){
      e.printStackTrace();
      }
  }
  


  }
  
}

