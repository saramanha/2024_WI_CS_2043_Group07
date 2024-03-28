/**
 * This class allows the gym staff to add and remove a fitness events, set a price, capacity, inventory needed etc.
 * @author Mariia Kamenshchykova
*/

//using if statement in setter and call it from constructor

import java.time.LocalDate;
import java.time.format.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.*;


public class EventFX extends Application{


	
	private Label label1;
	private TextField nameTextField;
	private TextField capacityTextField;
	private TextField inventoryTextField;
	private TextField dateTextField;
	private TextField instructorTextField;
	private TextField priceTextField;
	private Button backButton;
	private Button submitButton;
	private String eventName;
	private int capacity;
	private Inventory inventory;
	private GymWorker gymWorker;
	private LocalDate date;


	 public static void main(String[] args) 
    {
        launch(args);
        
    }

	@Override
	public void start(Stage primaryStage){

		primaryStage.setTitle("Add a New Event");

		label1 = new Label("Please enter the details of the upcoming event: ");
		label1.setFont(Font.font("times new roman", 20));

		//accept the user's input

		//name of the event
		nameTextField = new TextField("Enter the name of the event here.");
		nameTextField.setPrefWidth(100);
		eventName = nameTextField.getText();

		//the capacity of the event
		capacityTextField = new TextField("Enter the capacity of the event here.");
		capacityTextField.setPrefWidth(100);
		capacity = Integer.parseInt(capacityTextField.getText());


		// the type of the inventory used
		inventoryTextField = new TextField("Enter the inventory needed for the event.");
		inventoryTextField.setPrefWidth(100);
		inventory = new Inventory(inventoryTextField.getText(), 100);

		//the desired date of the event
		dateTextField = new TextField("Enter the date of the event here (yyyy-mm-dd).");
		dateTextField.setPrefWidth(100);

  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
  		String date = dateTextField.getText();
  		//convert String to LocalDate
  		LocalDate localDate = LocalDate.parse(date, formatter);


		instructorTextField = new TextField("Please enter a 4-digit ID of an instructor");
		instructorTextField.setPrefWidth(100);
		int instructorID = Integer.parseInt(instructorTextField.getText());

		priceTextField = new TextField("Please enter the price for the event (taxes included):");
		priceTextField.setPrefWidth(100);
		double price = Double.parseDouble(priceTextField.getText());


	 gymWorker = new GymWorker(instructorID, null, null, null, null);


		// create an object
		Event event = new Event(eventName, capacity, inventory, price, localDate, gymWorker);


		//buttons to return back and proceed with creating the events

		backButton = new Button("Cancel");
		backButton.setOnAction(this::eventHandler);

	 	submitButton = new Button("Continue");
	 	submitButton.setOnAction(this::eventHandler);

	 	//create a pane and add the elements on the pane
	 	GridPane inputGridPane = new GridPane();
	 	GridPane buttonGridPane = new GridPane();
	 	VBox mainPane = new VBox(10);
	 	mainPane.setMinSize(1000, 800);
	 	mainPane.setPadding(new Insets(20));
	 	mainPane.setAlignment(Pos.CENTER);

	 	inputGridPane.setVgap(10);
	 	buttonGridPane.setVgap(10);

	 	inputGridPane.add(nameTextField, 5, 0);
	 	inputGridPane.add(capacityTextField, 5, 1);
	 	inputGridPane.add(inventoryTextField, 5, 2);
	 	inputGridPane.add(dateTextField, 5, 3);
	 	inputGridPane.add(instructorTextField, 5, 4);
	 	inputGridPane.add(priceTextField, 5, 5);

	 	buttonGridPane.add(backButton, 2, 0, 1, 1);
	 	buttonGridPane.add(submitButton, 4, 0, 1, 1);

	 	mainPane.getChildren().addAll(inputGridPane, buttonGridPane);
	 	Scene scene = new Scene(mainPane);
	 	primaryStage.setScene(scene);
	 	primaryStage.show();
	}

	public void eventHandler(ActionEvent event){

			if (event.getSource() == submitButton){
				System.out.println("The event will be created an added to our database!");
			} else if(event.getSource() == backButton){
				System.out.println("You will be redirected to the previous page!");
			}
	}

}
















