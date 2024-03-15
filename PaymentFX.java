/** CS2043 template for a A JavaFX GUI Payment application
     @author Yohanne Husserlle Noume Happi
*/
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;

public class PaymentFX extends Application 
{
    private Event event; 
  	private Button paypalButton;
    private Text paymentLabel;
    private Button googlepayButton;
    private Button applepayButton;
    private Label eventNameLabel;
    private Label equipmentLabel;
    private Label instructorLabel;
    private Label totalLabel;

    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Check out");
        // Create GUI components
        eventNameLabel = new Label("Event Name: " + event.getName());
        eventNameLabel.setFont(Font.font("times new roman", 14));
        equipmentLabel = new Label("Equipment Used: " + event.getEquipmentUsed());
        equipmentLabel.setFont(Font.font("times new roman", 14));
        instructorLabel = new Label("Instructor: " + event.getInstructorName());
        instructorLabel.setFont(Font.font("times new roman", 14));
        totalLabel = new Label("Total Price: $" + event.getPrice());
        totalLabel.setFont(Font.font("times new roman", 14));
        paymentLabel = new Label("Make payment");
        paymentLabel.setFont(Font.font("times new roman", 14));

        // Create buttons for payment methods
        Button paypalButton = new Button("Paypal");
        paypalButton.setFont(Font.font("arial",14));
        paypalButton.setOnAction(this::eventHandler);
        Button googlepayButton = new Button("Google Pay");
        googlepayButton.setFont(Font.font("arial",14));
        googlepayButton.setOnAction(this::eventHandler);
        Button applepayButton = new Button("Apple Pay");
        applepayButton.setFont(Font.font("arial",14));
        applepayButton.setOnAction(this::eventHandler);

       GridPane inputPane = new GridPane();
        GridPane buttonPane = new GridPane();
        VBox mainPane = new VBox(10);
        mainPane.setMinSize(600, 400);
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setAlignment(Pos.CENTER);

        inputPane.setVgap(10);
        buttonPane.setVgap(10);

        inputPane.add(eventNameLabel, 0, 0);
        inputPane.add(equipmentLabel, 0, 1);
        inputPane.add(instructorLabel, 0, 2);
        inputPane.add(totalLabel, 0, 3);

        buttonPane.add(paypalButton, 0, 0, 1, 1);
        buttonPane.add(googlepayButton, 1, 0, 1, 1);
        buttonPane.add(applepayButton, 2, 0, 1, 1);

        mainPane.getChildren().addAll(inputPane, buttonPane, paymentLabel);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    public void eventHandler(ActionEvent event)
	{
        if (event.getSource() == paypalButton) 
        {
            redirectToPayment("https://www.paypal.com");
        } 
        else if (event.getSource() == googlepayButton) 
        {
            redirectToPayment("https://pay.google.com");
        } 
        else if (event.getSource() == applepayButton) 
        {
            redirectToPayment("https://www.apple.com/apple-pay/");
        }
    }  
}
