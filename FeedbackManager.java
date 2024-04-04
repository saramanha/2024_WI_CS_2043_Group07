import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class FeedbackManager {
    private List<Survey> surveys;

    public FeedbackManager(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    public void analyzeFeedback() {
        for (Survey survey : surveys) {
            System.out.println("Event: " + survey.getEvent().getName());
            for (String question : survey.getQuestions()) {
                List<String> answers = survey.getFeedbackList().stream()
                        .map(feedback -> feedback.getAnswers().get(question))
                        .collect(Collectors.toList());
                System.out.println("Question: " + question);
                answers.forEach(answer -> System.out.println(" - " + answer));
            }
        }
    }

    public void calculateAverageRatings() {
        for (Survey survey : surveys) {
            System.out.println("Event: " + survey.getEvent().getName());
            Map<String, List<String>> questionAnswers = survey.getQuestions().stream()
                    .collect(Collectors.toMap(question -> question, question -> new ArrayList<>()));
            for (Feedback feedback : survey.getFeedbackList()) {
                feedback.getAnswers().forEach((question, answer) -> {
                    if (questionAnswers.containsKey(question)) {
                        questionAnswers.get(question).add(answer);
                    }
                });
            }
            questionAnswers.forEach((question, answers) -> {
                double average = answers.stream()
                        .mapToDouble(answer -> {
                            try {
                                return Double.parseDouble(answer);
                            } catch (NumberFormatException e) {
                                return 0.0;
                            }
                        })
                        .average()
                        .orElse(0.0);

                if (average > 0.0) {
                    System.out.println("Question: " + question + ", Average Rating: " + String.format("%.2f", average));
                }
            });
        }
    }
}