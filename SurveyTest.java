package D4.nhyh;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;

class SurveyTest {
    private Survey survey;
    private Event event;
    private List<String> questions;
    private Feedback feedback1;
    private Feedback feedback2;

    @BeforeEach
    public void setUp() {

        GymWorker gymWorker = new GymWorker("Alice", "Instructor", "alice@yahoo.com", "123456789");
        Inventory inventory = new Inventory("Balls", 10);
        event = new Event("Fitness Workshop", 30, inventory, 20.0, LocalDate.now(), gymWorker);
        questions = Arrays.asList("How was the event?", "Would you recommend it to others?");
        survey = new Survey(event, questions);
        Map<String, String> answers1 = new HashMap<>();
        answers1.put("How was the event?", "Great");
        answers1.put("Would you recommend it to others?", "Yes");
        feedback1 = new Feedback("Luke Danis", answers1);
        Map<String, String> answers2 = new HashMap<>();
        answers2.put("How was the event?", "Good");
        answers2.put("Would you recommend it to others?", "Maybe");
        feedback2 = new Feedback("Cameron Lee", answers2);
    }

    @Test
    public void testSurveyCreation() {

        assertEquals(event, survey.getEvent());
        assertTrue(survey.getQuestions().containsAll(questions));
        assertTrue(survey.getFeedbackList().isEmpty());
    }

    @Test
    public void testAddAndGetFeedback() {

        survey.addFeedback(feedback1);
        survey.addFeedback(feedback2);
        List<Feedback> feedbackList = survey.getFeedbackList();
        assertEquals(2, feedbackList.size());
        assertTrue(feedbackList.contains(feedback1));
        assertTrue(feedbackList.contains(feedback2));
    }
}
