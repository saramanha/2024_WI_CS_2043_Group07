/** CS2043 template for a A JavaFX GUI Payment application
     @author Yohanne Husserlle Noume Happi
*/
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import java.time.*;

public class PaymentFX extends Application 
{
    private GymWorker instructor1;
    private Inventory fitnessMat;
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
	LocalDate date1 = LocalDate.of(2024, 01, 24);
        instructor1 = new GymWorker(1010, "John Black", "Fitness instructor", "johnBlack@gmail.com", "+15068990825");
        fitnessMat = new Inventory("Mat", 100);
        Event event = new Event("Fitness", 30, fitnessMat, 45.0, date1, instructor1); 

        primaryStage.setTitle("Check out");
        eventNameLabel = new Label("Event Name: " + event.getName());
        eventNameLabel.setFont(Font.font("times new roman", 14));
        equipmentLabel = new Label("Equipment Used: " + event.getInventory());
        equipmentLabel.setFont(Font.font("times new roman", 14));
        instructorLabel = new Label("Instructor: " + event.getGymWorkerName());
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
        mainPane.setPadding(new Insets(10));
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
            try{
                URI uri = new URI("https://www.paypal.com");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
            }catch(Exception ex){}
        }
        else if (event.getSource() == googlepayButton) 
        {
            try{
                URI uri = new URI("https://pay.google.com");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
            }catch(Exception ex){}
           
        } 
        else if (event.getSource() == applepayButton) 
        {
            try{
                URI uri = new URI("https://www.apple.com/apple-pay/");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
            }catch(Exception ex){}
           
        }
    }  
}
