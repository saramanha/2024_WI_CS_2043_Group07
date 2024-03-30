import java.util.ArrayList;
import java.util.List;

public class Survey {
    private Event event;
    private List<String> questions;
    private List<Feedback> feedbackList;

    public Survey(Event event, List<String> questions) {
        this.event = event;
        this.questions = questions;
        this.feedbackList = new ArrayList<>();
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public Event getEvent() {
        return event;
    }

    public List<String> getQuestions() {
        return questions;
    }
}