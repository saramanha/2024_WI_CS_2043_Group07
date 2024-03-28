/** CS2043 template for GymWorker 
     @author Yohanne Husserlle Noume Happi
*/

import java.util.ArrayList;
import java.time.LocalDate;

public class GymWorker 
{

    private static int nextId = 1000; // Static variable for auto-incrementing IDs starting from 1000
    private int id;
    private String name;
    private String role;
    private String email;
    private String phoneNumber;
    private ArrayList<LocalDate> dateList;

    public GymWorker(String name, String role, String email, String phoneNumber) 
    {
        this.id = nextId++; // Increment nextId for each new GymWorker
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        dateList = new ArrayList<LocalDate>();

    }
    public int getId() 
    {
        return id;
    }
    public String getName() 
    {
        return name;
    }
    public String getRole() 
    {
        return role;
    }

    public String getEmail() 
    {
        return email;
    }
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    public void setRole(String role) 
    {
        this.role = role;
    }

    public boolean isAvailable(LocalDate dateIn){

        for(LocalDate d : dateList){
            if(d.isEqual(dateIn)){
                return false;
            }
        }

        return true;

    }

    public void displayInfo() 
    {
        System.out.println("Gym Worker ID: " + String.format("%04d", id) + ", Name: " + name + ", Role: " + role
                + ", Email: " + email + ", Phone: " + phoneNumber);
    }

    public static void main(String[] args) 
    {
        GymWorker worker1 = new GymWorker("John Doe", "Trainer", "doe@gmail.com", "5063346521");
        GymWorker worker2 = new GymWorker("Jane Smith", "Receptionist", "smithj@yahoo.com", "5061256744");
        GymWorker worker3 = new GymWorker("Yohanne Noume", "Yoga Instructor", "noume@yahoo.com", "5078956744");
        worker1.displayInfo();
        worker2.displayInfo();
        worker3.displayInfo();
    }
}
