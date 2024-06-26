
import java.util.*;
import java.io.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;


//all the changes are directly in the file, however in order to print all the values 

public class Inventory{

	static File file = new File("Inventory_DB.csv");
	
	private String type;
	private int amount;
	static ArrayList<Inventory> list;

	public Inventory(String type, int amount){
		this.type = type;
		this.amount = amount;
	}
	
//getters and setters:

	public String getType(){
		return type;
	}

	public int getAmount(){
		return amount;
	}


	public void setAmount(int amountIn){
		amount = amountIn;
	}


	//update File with a new array of Inventory items
	public static void listToFile(ArrayList<Inventory> newList) {

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Type,Amount");
            for (Inventory inv : newList) {
                writer.println(inv.getType() + "," + inv.getAmount());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

	//add inventory (to an array) and then update the file
	public static void addInventory(Inventory invIn){
		
		list = fileToList();
		boolean added = false;

		for(Inventory i : list){
			
			if(i.getType().equals(invIn.getType())){
				System.out.println("The " + invIn.getType() + " is already in the list\n");
				added = true;
				break;
			}
		}
			if(!added){
				list.add(invIn);
				listToFile(list);
			}
	}

	public static void removeAllInventory(){

		list.clear();
		listToFile(list);
	}
 
//removes an inventory from an array and then updates a file
	public static void removeInventory(String typeIn){

		list = fileToList();


		for(Inventory i : list){
			if(i.getType().equals(typeIn)){
				list.remove(i);
				listToFile(list);
				break;
			}
		}
					
	}


	public static ArrayList<Inventory> fileToList(){

		ArrayList <Inventory> inventoryList = new ArrayList<Inventory>();

		try (Scanner scan = new Scanner(file)){
		
			if(scan.hasNextLine()){
				scan.nextLine();
		}

		while(scan.hasNextLine()){

			String lineIn = scan.nextLine();
			try(Scanner rowScan = new Scanner(lineIn)) {
				rowScan.useDelimiter(",");

				String typeIn = rowScan.next();

				int amountIn = Integer.parseInt(rowScan.next());
				inventoryList.add(new Inventory(typeIn, amountIn));
				
			}
		}

	}catch(FileNotFoundException e){
		e.printStackTrace();
	}
	return inventoryList;
}

//prints out all the Inventory
public static void print(ArrayList<Inventory> inventoryList) {
    System.out.printf("%-20s %-10s%n", "Type of Inventory", "Quantity");
    for (Inventory i : inventoryList) {
        System.out.printf("%-20s %-10d%n", i.getType(), i.getAmount());
    }
}

//main class for testing 
public static void main(String[] args){

	list = fileToList();
	System.out.println("Before any changes");
	print(list);

	System.out.println("After adding the Yoga Mat: \n" );
	Inventory inv1 = new Inventory("Yoga Mat",30);
	addInventory(inv1);
	print(list);

	Inventory inv3 = new Inventory ("Yoga Mat", 40);
	System.out.println("Trying to add the same inventory twice: ");
	addInventory(inv3);
	print(list);


	System.out.println("After removing the yoga mat: ");
	removeInventory("Yoga Mat");
	print(list);


	Inventory inv2 = new Inventory("Ball", 100);
	System.out.println("After adding ball Inventory: \n");
	addInventory(inv2);
	print(list);
	

}

}





