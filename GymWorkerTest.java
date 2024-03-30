
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GymWorkerTest {

    private GymWorker worker;

    @BeforeEach
    void setUp() {
        worker = new GymWorker("John Doe", "Trainer", "doe@gmail.com", "5063346521");
    }

    @Test
    void getId_ShouldReturnIncrementedId() {
        GymWorker secondWorker = new GymWorker("Jane Smith", "Receptionist", "smithj@yahoo.com", "5061256744");
        assertTrue(secondWorker.getId() > worker.getId());
    }

    @Test
    void getName_ShouldReturnCorrectName() {
        assertEquals("John Doe", worker.getName());
    }

    @Test
    void getRole_ShouldReturnCorrectRole() {
        assertEquals("Trainer", worker.getRole());
    }

    @Test
    void getEmail_ShouldReturnCorrectEmail() {
        assertEquals("doe@gmail.com", worker.getEmail());
    }

    @Test
    void getPhoneNumber_ShouldReturnCorrectPhoneNumber() {
        assertEquals("5063346521", worker.getPhoneNumber());
    }

    @Test
    void setEmail_ShouldUpdateEmail() {
        worker.setEmail("dunphy16@gmail.com");
        assertEquals("dunphy16@gmail.com", worker.getEmail());
    }

    @Test
    void setPhoneNumber_ShouldUpdatePhoneNumber() {
        worker.setPhoneNumber("5069988776");
        assertEquals("5069988776", worker.getPhoneNumber());
    }

    @Test
    void setName_ShouldUpdateName() {
        worker.setName("Claire Dunphy");
        assertEquals("Claire Dunphy", worker.getName());
    }

    @Test
    void setRole_ShouldUpdateRole() {
        worker.setRole("Manager");
        assertEquals("Manager", worker.getRole());
    }

    // This test captures the standard output and compares it with the expected
    // output.
    @Test
    void displayInfo_ShouldPrintCorrectInfo() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        worker.displayInfo();
        String expectedOutput = "Gym Worker ID: " + String.format("%04d", worker.getId()) + ", Name: "
                + worker.getName() +
                ", Role: " + worker.getRole() + ", Email: " + worker.getEmail() + ", Phone: " + worker.getPhoneNumber()
                + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
