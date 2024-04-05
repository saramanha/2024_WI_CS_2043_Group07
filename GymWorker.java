/** CS2043 template for GymWorker 
     @author Yohanne Husserlle Noume Happi
*/

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class GymWorker 
{

    private String password;
    private String name;
    private String role;
    private String email;
    private String phoneNumber;

    static ArrayList<GymWorker> gymWorkers = new ArrayList<GymWorker>();
    static File gymWorkersFile = new File("GymWorker_DB.csv");

    public GymWorker(String name, String role, String email, String phoneNumber, String password) 
    {
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
//setters and getters
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Role: " + role
                + ", Email: " + email + ", Phone: " + phoneNumber);
    }

    public static void print(ArrayList<GymWorker> listIn){

        for(GymWorker gw : listIn){
            gw.displayInfo();
        }
    }
//
    public static ArrayList<GymWorker> fileToList(){

        gymWorkers = new ArrayList<GymWorker>();


        try(Scanner scan = new Scanner(gymWorkersFile)){

            if (scan.hasNext()){
                scan.nextLine();
            }

                while(scan.hasNextLine()){

                    String lineIn = scan.nextLine();

                    try(Scanner rowScan = new Scanner(lineIn)){
                        rowScan.useDelimiter(",");

                        String nameIn = rowScan.next();
                        String roleIn = rowScan.next();
                        String emailIn = rowScan.next();
                        String phoneIn = rowScan.next();
                        String passwordIn = rowScan.next();

                        GymWorker gw1 = new GymWorker(nameIn, roleIn, emailIn, phoneIn, passwordIn);

                        gymWorkers.add(gw1);
                        listToFile(gymWorkers);
                    }
                }
        }catch(FileNotFoundException fnf){
            System.out.println("The file was not found!");
        }

        return gymWorkers;
    }

    public static void listToFile(ArrayList<GymWorker> listIn){

        try(PrintWriter pw = new PrintWriter(gymWorkersFile)){
            pw.println("Name, Role, Email, Phone Number, Password");

            for(GymWorker gw : listIn){
                pw.println(gw.getName() + "," + gw.getRole() + "," + gw.getEmail() + ","  + gw.getPhoneNumber() + "," + gw.getPassword());
            }
        }catch(IOException e) {
             System.out.println("Error updating this file: " + e.getMessage());
        }
    }

    public static void addWorker(GymWorker workerIn){

        gymWorkers = fileToList();
        boolean added = false;

        for(GymWorker gw : gymWorkers){

            if(gw.getName().equals(workerIn.getName())){
                added = true;
                System.out.println("The worker is already in the list!");
                break;
            }
        }

        if(!added){
            gymWorkers.add(workerIn);
            listToFile(gymWorkers);
        }
    }

    public static void removeWorker(GymWorker workerIn){

        gymWorkers = fileToList();

        for(GymWorker gw : gymWorkers){
            if(workerIn.getName().equals(gw.getName())){
                gymWorkers.remove(gw);
                break;
            }
        }
        listToFile(gymWorkers);
    }


    public static void removeAll(){

        gymWorkers.clear();
        listToFile(gymWorkers);

    }
/*main method to test
    public static void main(String[] args) 
    {

        gymWorkers = fileToList();

        GymWorker worker1 = new GymWorker("John Doe", "Trainer", "doe@gmail.com", "5063346521", "DoesApples");
        GymWorker worker2 = new GymWorker("Jane Smith", "Receptionist", "smithj@yahoo.com", "5061256744", "41261");
        GymWorker worker3 = new GymWorker("Yohanne Noume", "Yoga Instructor", "noume@yahoo.com", "5078956744", "password123");

        System.out.println("The list of the staff before changes: ");
        print(gymWorkers);

        addWorker(worker1);        
        addWorker(worker2);
        addWorker(worker3);

        System.out.println("The list of the staff after adding 3 workers: ");
        print(gymWorkers);

        System.out.println("After an attempt of adding one worker twice:");
        addWorker(worker1);
        print(gymWorkers);

        System.out.println("After removing the first worker: ");
        removeWorker(worker1);
        print(gymWorkers);

        System.out.println("After removing all  workers: ");
        removeAll();
    }
    */
}










