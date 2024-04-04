import java.util.*;
import java.io.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Optional;

public class Member{

    private String name;
    private String email;
    private String phone;
    private int id;

    static ArrayList<Member> members;
    static File memberFile = new File("Members_DB.csv");

    public Member(String nameIn, String emailIn, String phoneIn, int idIn){
        name = nameIn;
        email = emailIn;
        phone = phoneIn;
        id = idIn;
    }

    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPhone(){ return phone; }
    public int getId(){ return id; }
   // public void setStartId(int startId){ nextId = startId; }

    public void setName(String newName){ name = newName; }
    public void setEmail(String newEmail){ email = newEmail; }
    public void setPhone(String newPhone){ phone = newPhone; }
    public void setID(int idIn){
        id = idIn;
    }

    public static void removeAll(){

        members.clear();
        listToFile(members);
    }

    public static void displayInfo(ArrayList<Member> listIn){

        for(Member m : listIn){

            System.out.println("Member Id: " + String.format("%d", m.getId()) + ", Name: " + m.getName() +
                           ", Email: " + m.getEmail() + ", Phone: " + m.getPhone() + "\n");
        }
    }

    public static ArrayList<Member> fileToList(){
        
        members = new ArrayList<Member>();

        try (Scanner scan = new Scanner(memberFile)){
        
            if(scan.hasNextLine()){
                scan.nextLine();
            }

        while(scan.hasNextLine()){

            String lineIn = scan.nextLine();
            
            try(Scanner rowScan = new Scanner(lineIn)) {
                rowScan.useDelimiter(",");

                String nameIn = rowScan.next();
                String emailIn  = rowScan.next();
                String phoneIn = rowScan.next();
                int idIn = Integer.parseInt(rowScan.next());

                Member copyMember = new Member(nameIn, emailIn, phoneIn);
                copyMember.setID(idIn);
                members.add(copyMember);
            }
        }

    }catch(FileNotFoundException e){
        e.printStackTrace();
    }
    return members;
    }

    public static void listToFile(ArrayList<Member> listIn){

        try(PrintWriter writer = new PrintWriter(memberFile)){
            writer.println("Name,Email,Phone Number,ID");
            for(Member m : listIn){
                writer.println(m.getName() + "," + m.getEmail() + "," + m.getPhone() + "," + m.getId());
            }
        }catch(IOException e){
            System.out.println("Error updating this file: " + e.getMessage());
        }
    }

    public static void addMember(Member memberIn){
        members = fileToList();
        boolean added = false;

        for(Member m : members){

            if(m.getName().equals(memberIn.getName())){
                added = true;
                break;
            }
        }

        if(!added){
            members.add(memberIn);
            listToFile(members);
        }
    }

    public static void removeMember(Member memberIn){

        members = fileToList();

        for(Member m : members){
            if(m.getId() == memberIn.getId()){
                members.remove(m);
                break;
            }
        }

        listToFile(members);
    }

/* main class for testing
    public static void main(String []args){

        members = fileToList();

        System.out.println("The list of members before changing anything: \n");
        displayInfo(members);


        Member m1 = new Member("Kamenshchykova Mariia", "whatever@gmail.com", "5068990825");
        Member m2 = new Member("Teoman Gur", "whatever@unb.ca", "7083092111");
        Member m3 = new Member("John Smith", "johnSmith@ukr.net", "2433456798");



        addMember(m1);
        addMember(m2);
        addMember(m3);

        System.out.println("After adding 3 memebers: \n");
        displayInfo(members);

        removeMember(m1);

        System.out.println("After removing the first member: \n");
        displayInfo(members);

        addMember(m1);

        System.out.println("Before removing all members: \n");
        displayInfo(members);

        removeAll();
        System.out.println("After removing all members: \n");
        
    }
    */
}