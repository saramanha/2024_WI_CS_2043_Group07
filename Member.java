public class Member{

    private String name;
    private String email;
    private String phone;
    private final int id;
    private static int nextId = 1000;

    public Member(String nameIn, String emailIn, String phoneIn){
        name = nameIn;
        email = emailIn;
        phone = phoneIn;
        id = nextId++;
    }

    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPhone(){ return phone; }
    public int getId(){ return id; }

    public void setName(String newName){ name = newName; }
    public void setEmail(String newEmail){ email = newEmail; }
    public void setPhone(String newPhone){ phone = newPhone; }

    public void displayInfo(){
        System.out.println("Member Id: " + String.format("%0.4d", id) + ", Name: " + name +
                           ", Email: " + email + ", Phone: " + phone);
    }
}