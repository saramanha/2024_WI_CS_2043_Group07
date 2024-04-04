package D4.nhyh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackTest {

    private Feedback feedback;
    private String expectedParticipantName;
    private Map<String, String> expectedAnswers;

    @BeforeEach
    public void setUp() {
        expectedParticipantName = "Manny Rickson";
        expectedAnswers = new HashMap<>();
        expectedAnswers.put("How was the event?", "It was great");
        expectedAnswers.put("Would you recommend it to others?", "Yes");
        feedback = new Feedback(expectedParticipantName, expectedAnswers);
    }

    @Test
    public void testGetParticipantName() {
        assertEquals(expectedParticipantName, feedback.getParticipantName(),
                "The participant name should match the expected value.");
    }

    @Test
    public void testGetAnswers() {
        assertEquals(expectedAnswers, feedback.getAnswers(), "The answers map should match the expected map.");
        assertTrue(feedback.getAnswers().containsKey("How was the event?"),
                "The answers should contain the key 'How was the event?'.");
        assertTrue(feedback.getAnswers().containsKey("Would you recommend it to others?"),
                "The answers should contain the key 'Would you recommend it to others?'.");
        assertEquals("It was great", feedback.getAnswers().get("How was the event?"),
                "The answer to 'How was the event?' should be 'It was great'.");
        assertEquals("Yes", feedback.getAnswers().get("Would you recommend it to others?"),
                "The answer to 'Would you attend again?' should be 'Yes'.");
    }
}