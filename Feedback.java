import java.util.Map;

public class Feedback {
    private String participantName;
    private Map<String, String> answers; // Maps question to answer

    public Feedback(String participantName, Map<String, String> answers) {
        this.participantName = participantName;
        this.answers = answers;
    }

    public String getParticipantName() {
        return participantName;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }
}
