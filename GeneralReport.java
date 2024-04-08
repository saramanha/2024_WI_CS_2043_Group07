import java.util.*;
import java.time.LocalDate;
import java.io.*;


public class GeneralReport{

	static ArrayList<Event> pastEvents;
	//ArrayList<Event> currentEvents = new ArrayList<Event>();
	static ArrayList<Member> membersList = new ArrayList<Member>();

	static File memberFile = new File("Member_DB.csv");
	static File eventFile = new File("Event_DB.csv");
	static File reportFile = new File("GeneralReport_DB.csv");


	public static ArrayList<Event> getList(){
		pastEvents = new ArrayList<>();

		try(Scanner scan = new Scanner(reportFile)){
			if(scan.hasNext()){
				scan.nextLine();
			}

			while(scan.hasNextLine()){
				String line = scan.nextLine();
				String[] parts = line.split(",");
				System.out.println(line);

				if(parts[0].equals("Total Revenue")){
					break;
				} 
				for(String st: parts){
					System.out.println(st);
				}
				/*
				Event evIn = new Event(parts[0], Integer.parseInt(parts[1]), new Inventory(parts[2], Integer.parseInt(parts[3])), Double.parseDouble(parts[4]), LocalDate.parse(parts[5]), parts[6], Integer.parseInt(parts[7]));
				pastEvents.add(evIn);*/
			}


		}catch(IOException ioe){
			System.out.println("Unable to read the file!");
		}

		return pastEvents;
		
	}
	
	public static double totalRevenue(){
		double totalRevenue = 0;
		pastEvents = getList();

		for(Event e : pastEvents){
			totalRevenue += getRevenue(e.getDate());
		}
		return totalRevenue;

	}

//a method that returns the amount of Revenue per one events (a request from staff)
	public static double getRevenue(LocalDate dateIn){
		double revenue = 0;
		pastEvents = getList();

        for (Event e : pastEvents) {
        	System.out.println("The date1 " + e.getDate()  + " The date1 " + dateIn);
        	/*
            if (e.getDate().isEqual(dateIn)) {
                revenue = e.getPrice() * e.getAttendees();
                System.out.println("The price: " + e.getPrice()  + " The capacity: " + e.getAttendees() + " The revenue: " + revenue);
                break;
            }*/
        }

        return revenue;
    }

//adds id's to th file
	public static ArrayList<Member> atendees(LocalDate dateIn){

		List<Integer> ids = new ArrayList<>();
		membersList = new ArrayList<>();

//store the ids in array
		try(Scanner scan = new Scanner(reportFile)){

			if(scan.hasNextLine()){
				scan.nextLine();
			}

			while(scan.hasNext()){

				String line = scan.nextLine();
				String[] parts = line.split(",");
 				
 				LocalDate date = LocalDate.parse(parts[5]);

           		if (date.equals(dateIn)) {

					for (int i = parts.length - 4; i < parts.length; i++) {
                    	int id = Integer.parseInt(parts[i]); // Trim any leading/trailing whitespace
                    	ids.add(id);
					}
				}
			}
		}catch(FileNotFoundException fnfe){

			System.out.println("The file was not found!");

		}

//find the coresponding id members

			try(Scanner scan1 = new Scanner(memberFile)){

				scan1.nextLine();

				while(scan1.hasNextLine()){
					String line = scan1.nextLine();
					String[] parts = line.split(",");

					int memberId = Integer.parseInt(parts[3]);



						if(ids.contains(memberId)){

								membersList.add(new Member(parts[0],parts[1], parts[2], Integer.parseInt(parts[3])));
						}
				}
			}catch(IOException ioe){
				System.out.println("IOException");
		}


		return membersList;
	}


	public static void main(String[]args){

		System.out.println("Here's the revenue for the third event: " + getRevenue(LocalDate.of(2018,4,9)));
		System.out.println("Here's the revenue for the second event: " + getRevenue(LocalDate.of(2019,4,9)));

			/*+ "\nThe revenue for the second one: " + getRevenue(LocalDate.of(2019,4,9)));
		System.out.println("The total Revenue for two events: " + totalRevenue());
*/
	}
	
}

