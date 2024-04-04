
import java.util.*; // import the ArrayList class
import java.time.LocalDate;
import java.io.*;
import java.util.stream.Stream;



public class Event {

	private String name;
	private int capacity;
	private Inventory inventory;
	private LocalDate date;
	private double price;
	private String gymWorker;

	static ArrayList<Event> eventList;
	static File eventFile = new File("Event_DB.csv");
	

	public Event(String name, int capacity, Inventory inventory, double price, LocalDate date, String gymWorker){

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

	public String getGymWorkerName(){
		return gymWorker;
	}

	public String getInventoryName(){
		return inventory.getType();
	}
	public int getInventoryAmount(){
		return inventory.getAmount();
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

	public void setInventory(Inventory inventoryIn){
		
		inventory = new Inventory(inventoryIn.getType(), inventoryIn.getAmount());
	}

	public void setDate(LocalDate newDate){
		//check if available
		date = newDate;
	}

	public void setGymWorker(String newGymWorker){
		gymWorker = newGymWorker;
	}

	public static void print(ArrayList<Event> listIn){

		for(Event e : listIn){

			System.out.println(e.toString());
			//System.out.println("Name: " + e.getName() + " Capacity: " + e.getCapacity() + " Inventory: " + e.getInventory().getType() + " Price: " + e.getPrice() + " Date: " +
							//	e.getDate() + "GymWorker: " + e.getGymWorkerName() + "\n");
		}
	}

	public static ArrayList<Event> fileToList(){

		eventList = new ArrayList<Event>();

		try(Scanner scan = new Scanner(eventFile)){

			if(scan.hasNextLine()){
				scan.nextLine();
			}

			while(scan.hasNextLine()){

				String lineIn = scan.nextLine();

				try(Scanner rowScan = new Scanner(lineIn)){
					rowScan.useDelimiter(",");

 					String nameIn = rowScan.next();
                	int capacityIn  = Integer.parseInt(rowScan.next());
                	String invType = rowScan.next();	
                	int invAmount = Integer.parseInt(rowScan.next());
                	Inventory invIn = new Inventory(invType, invAmount);
                	double priceIn = Double.parseDouble(rowScan.next());
                	LocalDate dateIn = LocalDate.parse(rowScan.next());
                	String gymWorkerIn = rowScan.next();

                	Event eventAdd = new Event(nameIn, capacityIn, invIn, priceIn, dateIn, gymWorkerIn);
                	eventList.add(eventAdd);
                	listToFile(eventList);

                }
            }
                
		}catch(FileNotFoundException e){
            e.printStackTrace();
		
		}return eventList;
	}


	public static void listToFile(ArrayList<Event> listIn){

		try(PrintWriter pw = new PrintWriter(eventFile)){
			pw.println("Name,Capacity,Inventory,Price,Date,GymWorker");
			for(Event e : listIn){
                pw.println(e.getName() + "," + e.getCapacity() + "," + e.getInventoryName() + "," + e.getInventoryAmount() + "," + e.getPrice() + "," + e.getDate() + "," + e.getGymWorkerName());
			}
		}catch(IOException e){
			 System.out.println("Error updating this file: " + e.getMessage());

		}

	}

	public static void addEvent(Event eventIn){

		eventList = fileToList();
		boolean added = false;

		for(Event e : eventList){

			if(e.getDate().equals(eventIn.getDate()) && eventIn.getDate().isAfter(LocalDate.now())){
				added = true;
				System.out.println("That date is already booked for a different event!");
				break;
			}
		}
		
		if(!added){
			eventList.add(eventIn);
			listToFile(eventList);
		}
	}

	public static void removeEvent(Event eventIn){

		eventList = fileToList();

		for(Event e : eventList){
			if(eventIn.getDate().isEqual(e.getDate())){
				eventList.remove(e);
				break;
			}
		}
		listToFile(eventList);
	}

	public static void removeAllEvents(ArrayList<Event> listIn){

		eventList.clear();
		listToFile(eventList);
	}


	public String toString(){

		return "Event: " + name + " Max. capacity: " + capacity + " Inventory: " + inventory.getType() +  "The price: $" + price + " Date: " + date + " Instructor name: " + gymWorker+ ".\n" ;
	
	}

	public static void main(String[] args){

		eventList = fileToList();

		System.out.println("The document before changes: ");
		print(eventList);

		Inventory inv1 = new Inventory("Yoga Mat", 30);
		Event e1 = new Event("Yoga Class", 25, inv1, 40.0, LocalDate.of(2024, 4, 5), "Matt Black");
		addEvent(e1);
		Event e2 = new Event("Pilates", 30, inv1, 45.0, LocalDate.of(2024, 8, 8), "Matt Black");
		addEvent(e2);

		System.out.println("The list of events after adding two events:  " );
		print(eventList);

		removeEvent(e1);

		System.out.println("After removing the yoga event");
		print(eventList);

		addEvent(e1);

		Inventory inv2 = new Inventory("Dumbbells", 50);
		Event e3 = new Event("HIT", 30, inv2,  35.0, LocalDate.of(2024, 9, 1), "Matt Black");
		addEvent(e3);

		System.out.println("After adding it back and adding the third event:");
		print(eventList);
		removeAllEvents(eventList);


	}
}











