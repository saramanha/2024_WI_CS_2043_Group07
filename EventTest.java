package D4.nhyh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class EventTest {

    private Event event;
    private Inventory inventory;
    private GymWorker gymWorker;
    private LocalDate date;

    @BeforeEach
    void setUp() {
        inventory = new Inventory("Resistance Bands", 50);
        gymWorker = new GymWorker("Lize Monroe", "Instructor", "lmonroe@gmail.com", "5067653398");
        date = LocalDate.of(2023, 10, 5);
        event = new Event("Pilates Class", 20, inventory, 25.0, date, gymWorker);
    }

    @Test
    void testEventDetails() {
        assertEquals("Pilates Class", event.getName());
        assertEquals(20, event.getCapacity());
        assertEquals(25.0, event.getPrice());
        assertEquals(date, event.getDate());
        assertEquals(gymWorker, event.getGymWorker());
        Inventory newInventory = new Inventory("Weighted Balls", 10);
        GymWorker newGymWorker = new GymWorker("Dan Ennes", "Trainer", "dan15@gmail.com", "2269224587");
        LocalDate newDate = LocalDate.of(2023, 11, 5);
        event.setName("Cardio Blast");
        event.setCapacity(30);
        event.setPrice(30.0);
        event.setInventory(newInventory);
        event.setDate(newDate);
        event.setGymWorker(newGymWorker);
        assertEquals("Cardio Blast", event.getName());
        assertEquals(30, event.getCapacity());
        assertEquals(30.0, event.getPrice());
        assertEquals(newDate, event.getDate());
        assertEquals(newGymWorker, event.getGymWorker());
    }

    @Test
    void testToString() {
        String expected = "Event: Pilates Class Max. capacity: 20 The price: $25.0 Date: 2023-10-05 Instructor name: Lize Monroe.\n";
        assertEquals(expected, event.toString());
    }
}