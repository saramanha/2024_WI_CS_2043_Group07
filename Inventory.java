import java.io.*;  
import java.util.Scanner;  


public class Inventory{
	private String type;
	private int amount;

	private List<Inventory> readInventoryFromFile() {

        List<Inventory> inventories = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("Inventory_DB.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0];
                    int amount = Integer.parseInt(parts[1]);
                    Inventory inventory = new Inventory(type, amount);
                    inventories.add(inventory);
                }
            }
        } catch (FileNotFoundException e) {
			System.out.println("File Not Found!");

	     }
        return inventories;
    }
	
	Scanner sc = new Scanner(new File("/Users/mariiakamenshchykova/Desktop/java/javafx-sdk-22/lib/Inventory_DB.csv"));  
	sc.useDelimiter(","); 

	public Inventory(String type, int amount){
		this.type = type;
		this.amount = amount;
	}
	

	public String getType(){
		return type;
	}

	public int getAmount(){
		return amount;
	}


	public void setAmount(int amountIn){
		amount = amountIn;
	}

	public void addInventory(Inventory inventory) {

        try (PrintWriter writer = new PrintWriter(new FileWriter("Inventory_DB.csv", true))) {

            writer.println(inventory.getType() + "," + inventory.getAmount());
            System.out.println("Inventory added: " + inventory);

        } catch (IOException e) {
            System.out.println("This item cannot be added!");
        }
    }

	public void removeInventory(String inventoryType) {
        List<Inventory> inventories = readInventoryFromFile();
        List<Inventory> updatedInventories = new ArrayList<>();

        for (Inventory inventory : inventories) {
            if (!inventory.getType().equals(inventoryType)) {
                updatedInventories.add(inventory);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Inventory_DB.csv"))) {
            for (Inventory inventory : updatedInventories) {
                writer.println(inventory.getType() + "," + inventory.getAmount());
            }
            System.out.println("Inventory with type " + inventoryType + " removed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void displayAllInventory() {
        	
        List<Inventory> inventories = readInventoryFromFile();
        
        for (Inventory inventory : inventories) {
            System.out.println("Type of the equipment: " + inventory.getType() + " Quantity in storage: " + inventory.getAmount());
        }
    }

}



