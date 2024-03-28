/**
 * This class allows the gym staff to add and remove a fitness events, set a price, capacity, inventory needed etc.
 * @author Mariia Kamenshchykova
*/

//using if statement in setter and call it from constructor

import java.util.ArrayList; // import the ArrayList class
import java.time.LocalDate;

public class Event {

	private String name;
	private int capacity;
	private String inventory;
	private LocalDate date;
	private double price;
	private GymWorker gymWorker;
	

	private Event(String name, int capacity, Inventory inventory, double price, LocalDate date, GymWorker gymWorker){

			this.name = name;
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

	public int getCapacity(){
		return capacity;
	}

	public LocalDate getDate(){
		return date;
	}
	public GymWorker getGymWorker(){
		return gymWorker;
	}


// setters

	public void setName(String newName){
		name = newName;
	}

	public void setPrice(double newPrice){
		price = newPrice;
	}

	public void setCapacity(int newCapacity){
		capacity = newCapacity;
	}

	public void setInventory(Inventory newInventory){
		//again check if the inventory is available
		inventory = newInventory;
	}

	public void setDate(LocalDate newDate){
		//check if available
		date = newDate;
	}

	public void setInstructor(GymWorker newGymWorker){
		gymWorker = newGymWorker;
	}


// overriding toString()

public toString(){

	return "Event: " + name + " Max. capacity: " + capacity + " The price: $" + price + " Date: " + date + " Instructor name: " + gymWorker + ".\n" ;
	}

}
