
import java.util.ArrayList; // import the ArrayList class
import java.time.LocalDate;

public class Event {

	private String name;
	private String description;
	private int capacity;
	private Inventory inventory;
	private LocalDate date;
	private double price;
	private GymWorker gymWorker;
	

	private Event(String name, String description, int capacity, Inventory inventory, double price, LocalDate date, GymWorker gymWorker){
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.inventory = inventory;
		this.price = price;
		this.date = date;
		this.gymWorker = gymWorker;
	}

	public double getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getCapacity(){
		return capacity;
	}

	public LocalDate getDate(){
		return date;
	}

	public String getGymWorkerName(){
		return gymWorker.getName();
	}

	public String getInventory(){
		return inventory.getType();
	}

// setters

	public void setName(String newName){
		name = newName;
	}

	public void setDescription(String newDescription){
		description = newDescription;
	}

	public void setPrice(double newPrice){
		price = newPrice;
	}

	public void setCapacity(int newCapacity){
		capacity = newCapacity;
	}

	public void setInventory(Inventory inventoryIn){
		inventory = inventoryIn;
	}

	public void setDate(LocalDate newDate){
		//check if available
		date = newDate;
	}

	public void setGymWorker(GymWorker newGymWorker){
		gymWorker = newGymWorker;
	}

	// overriding toString()
	public String toString(){
		return "Event: " + name + " Max. capacity: " + capacity + " The price: $" + price + " Date: " + date + " Instructor name: " + gymWorker.getName() + ".\n" ;	
	}
}