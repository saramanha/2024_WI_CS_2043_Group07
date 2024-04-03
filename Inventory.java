
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;


//all the changes are directly in the file, however in order to print all the values 

public class Inventory{

	static File file = new File("Inventory_DB.csv");
	
	private String type;
	private int amount;
	private ArrayList<LocalDate> booked;
	static ArrayList<Inventory> list;

	public Inventory(String type, int amount){
		this.type = type;
		this.amount = amount;
		booked = new ArrayList<LocalDate>();
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


	public String datesToString() {

		String dates = "";
		for(LocalDate l : booked){
			
			dates += l + ",";
		}
    	return dates;
	}

	public void addDate(LocalDate dateIn){ 

	// Check if the date is not already in the list
		if (!booked.contains(dateIn)) {
        	booked.add(dateIn);
        	updateFile(list);
        }
	}
	//remove the date from the list
	public void removeDate(LocalDate dateIn){
		if (booked.contains(dateIn)){
			booked.remove(dateIn);
			updateFile(list);
		}
	}

	//update File with a new array of Inventory items
	public static void updateFile(ArrayList<Inventory> newList) {

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Type,Amount, Dates");
            for (Inventory inv : newList) {
                writer.println(inv.getType() + "," + inv.getAmount() + "," + inv.datesToString());
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

	//add inventory (to an array) and then update the file
	public static void addInventory(Inventory invIn){
		
		list = fileToArray();
		boolean added = false;

		for(Inventory i : list){
			
			System.out.println(" list.get(i).getType() is  " + i.getType() );
			if(i.getType().equals(invIn.getType())){
				System.out.println("The " + invIn.getType() + " is already in the list\n");
				added = true;
				break;
			}
		}
			if(!added){
				list.add(invIn);
				updateFile(list);
			}
	}

	public static void removeAllInventory(){

		list.clear();
		updateFile(list);
	}
 
//removes an inventory from an array and then updates a file
	public static void remove(Inventory invIn){

		list = fileToArray();

		for(int i = 0; i < list.size(); i++){

			if(list.get(i).getType().equals(invIn.getType())){

				list.remove(i);
				updateFile(list);
				i = list.size();
			}
		}
			
	}


	public static ArrayList<Inventory> fileToArray(){

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
    System.out.printf("%-20s %-10s %-20s%n", "Type of Inventory", "Quantity", "Dates");
    for (Inventory i : inventoryList) {
        System.out.printf("%-20s %-10d %-20s%n", i.getType(), i.getAmount(), i.datesToString());
    }
}

/* main class for testing 
public static void main(String[] args){

	list = fileToArray();
	System.out.println("Before any changes");
	print(list);

	System.out.println("After adding the Yoga Mat: \n" );
	Inventory inv1 = new Inventory("Yoga Mat",30);
	addInventory(inv1);
	print(list);

	System.out.println("After adding dates to yoga mats: \n");
	inv1.addDate(LocalDate.of(2023, 8, 3));
	inv1.addDate(LocalDate.of(2021, 6, 21));
	print(list);

	System.out.println("After removing one of the dates");
	inv1.removeDate(LocalDate.of(2023, 8, 3));

	print(list);
	Inventory inv3 = new Inventory ("Yoga Mat", 40);
	System.out.println("Trying to add the same inventory twice: ");
	addInventory(inv3);
	print(list);


	Inventory inv2 = new Inventory("Ball", 100);
	System.out.println("After adding ball Inventory: \n");
	list.add(inv2);
	inv2.addDate(LocalDate.of(2012, 8, 9));
	print(list);
}
*/

}





