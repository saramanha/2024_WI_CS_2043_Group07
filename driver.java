import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.Desktop;

public class driver extends Application{
    private Stage stage;
    private static ArrayList<Member> memberList = new ArrayList<>();
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static ArrayList<GymWorker> workerList = new ArrayList<>(); //This is not used yet
    private static ArrayList<Inventory> inventoryList = new ArrayList<>();
    private static ArrayList<Button> eventButtons = new ArrayList<>();
    private String[] emails = {"@gmail.com", "@outlook.com"};
    private Member newUser;
    private Event newEvent;
    static File eventFile = new File("Event_DB.csv");

    //Scene 1: Deciding if it's a member or worker using the application.
    private Scene scene1;
    private Label startLabel;
    private Button bUser;
    private Button bEmployee;

    private VBox vBox1;
    private HBox hBox1;

    //Scene 2: After clicking User in scene1, it gives 2 options; Log in, and register.
    private Scene scene2;
    private Label userLabel;
    private Button bLogIn;
    private Button bRegister;
    private Button bS2Back;

    private VBox vBox2;
    private HBox hBox2;

    //Scene 3: After clicking Register in scene2, input necessary information to create new member (and save it to somewhere?).
    private Scene scene3;
    private TextField nameText;
    private TextField emailText;
    private TextField phoneText;
    private Label nameLabel;
    private Label emailLabel;
    private Label phoneLabel;
    private Button bRegisterEnd;
    private Button bCancel;
    private Label message;

    private VBox vBox3;
    private HBox hBox3_name;
    private HBox hBox3_email;
    private HBox hBox3_phone;
    private HBox hBox3_buttons;


    //Scene 4: After clicking Employee in scene1, 
    private Scene scene4;
    private Label empNameLabel;
    private Label empPassLabel;
    private TextField empNameText;
    private TextField empPassText;
    private Button bEmpLogIn;
    private Button bEmpCancel;
    private Label empMessage;

    private VBox vBox4;
    private HBox hBox4_name;
    private HBox hBox4_pw;
    private HBox hBox4_buttons;

    //Scene 5: After clicking Log in in scene2, 
    private Scene scene5;
    private Label userIdLabel;
    private Label userNameLabel;
    private TextField userIdText;
    private TextField userNameText;
    private Button bUserLogIn;
    private Button bUserCancel;
    private Label messageLogIn;

    private VBox vBox5;
    private HBox hBox5_id;
    private HBox hBox5_name;
    private HBox hBox5_buttons;

    //Scene 6: After clicking log in in scene 4, 
    private Scene scene6;
    private Button bCreateEvent;
    private Button bEditEvent;
    private Button bEmpBack;

    private VBox vBox6;

    //Scene 7: After registering new member in scene3 or Log in as old member in scene5
    private Scene scene7;
    private Button bEventCancel;
    private Button bEvent;
    private Label userIdMessage;
    private Label eventListMessage;

    private ScrollPane scene7ScrollP;
    private VBox vBox7;
    private HBox hBox7_registerMessage;
    private VBox vBox7_buttons;

    //Scene 8:
    private Scene scene8;
    private Button bBackEventList;
    private Button bPaymentPage;
    private Label eventName;
    private Label eventDesc;
    private Label eventCap;
    private Label eventPrice;
    private Label eventDate;

    private VBox vBox8;
    private HBox hBox8_buttons;
    private HBox hBox8_CapPrice;

    //Scene 9:
    private Scene scene9;
    private Button bPaypal;
    private Button bGooglePay;
    private Button bApplePay;
    private Button bCancelPay;

    private VBox vBox9;

    //Scene 10:
    private Scene scene10;
    private Button bCreate;
    private Button bCancelCreate;
    private TextField createName;
    private TextField createCap;
    private TextField createInvName;
    private TextField createPrice;
    private TextField createWorker;
    private TextField createDate;
    private Label createMessage;
    private Label createNameLabel;
    private Label createCapLabel;
    private Label createInvLabel;
    private Label createWorkerLabel;
    private Label createDateLabel;
    private Label createPriceLabel;

    private VBox vBox10;
    private HBox hBox10_name;
    private HBox hBox10_cap;
    private HBox hBox10_inv;
    private HBox hBox10_worker;
    private HBox hBox10_date;
    private HBox hBox10_price;
    private HBox hBox10_buttons;

    //Scene 11;
    private Scene scene11;
    private Button bEditCancel;
    private Button bEditE;
    private Label workerMessage;

    private ScrollPane scene11ScrollP;
    private VBox vBox11;
    private VBox vBox11_buttons;
    private HBox hBox11_registerMessage;

    //Scene 12;
    private Scene scene12;
    private TextField editEventName;
    private TextField editEventCap;
    private TextField editEventDate;
    private TextField editEventPrice;
    private TextField editEventWorker;
    private TextField editEventInv;
    private Label editEventNameLabel;
    private Label editEventCapLabel;
    private Label editEventInvLabel;
    private Label editEventPriceLabel;
    private Label editEventDateLabel;
    private Label editEventWorkerLabel;
    private Button bEditEventBack;
    private Button bEditEventFinish;
    private Label editMessage;

    private VBox vBox12;
    private HBox hBox12_buttons;
    private HBox hBox12_name;
    private HBox hBox12_cap;
    private HBox hBox12_price;
    private HBox hBox12_worker;
    private HBox hBox12_inv;
    private HBox hBox12_date;

    //Scene 13:
    private Scene scene13;
    private Button bPaid;
    private Label thankYouLabel;

    private VBox vBox13;

    //Scene 14:
    private Scene scene14;
    private Button bCreateFinish;
    private Label createFinishMessage;

    private VBox vBox14;

    //Scene 15:
    private Scene scene15;
    private Button bEditFinish;
    private Label editFinishMessage;

    private VBox vBox15;
    @Override
    public void start(Stage primaryStage) throws IOException{
        stage = primaryStage;
        stage.setTitle("GYM Events");

        //Testing.
        memberList.add(new Member("Teoman Gur", "tmngr@gmail.com", "5069218321", 1021));
        RFileEvent();

        //Testing end.

        scene1 = createSceneOne();
        stage.setScene(scene1);
        stage.show();
    }

    private Scene createSceneOne(){
        startLabel = new Label("Welcome to UNB gym events.\n Are you an employee or user?");
        bUser = new Button("User");
        bUser.setOnAction(e -> {scene2 = createSceneTwo(); switchScenes(scene2);});
        bEmployee = new Button("Employee");
        bEmployee.setOnAction(e-> {scene4 = createSceneFour(); switchScenes(scene4);});

        vBox1 = new VBox();
        vBox1.setSpacing(30);
        vBox1.setAlignment(Pos.TOP_CENTER);

        hBox1 = new HBox();
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.TOP_CENTER);

        hBox1.getChildren().addAll(bUser, bEmployee);
        vBox1.getChildren().addAll(startLabel, hBox1);
        scene1 = new Scene(vBox1, 300, 200);

        return scene1;
    }

    private Scene createSceneTwo(){
        userLabel = new Label("Are you a returning member?");
        bLogIn = new Button("Log in");
        bLogIn.setOnAction(e -> {scene5 = createSceneFive(); switchScenes(scene5);});
        bRegister = new Button("Register");
        bRegister.setOnAction(e-> {scene3 = createSceneThree(); switchScenes(scene3);});
        bS2Back = new Button("Back");
        bS2Back.setOnAction(e -> {scene1 = createSceneOne(); switchScenes(scene1);});

        vBox2 = new VBox();
        vBox2.setAlignment(Pos.TOP_CENTER);
        vBox2.setSpacing(50);

        hBox2 = new HBox();
        hBox2.setAlignment(Pos.TOP_CENTER);
        hBox2.setSpacing(20);

        hBox2.getChildren().addAll(bLogIn, bRegister, bS2Back);
        vBox2.getChildren().addAll(userLabel, hBox2);
        scene2 = new Scene(vBox2, 300, 200);

        return scene2;
    }

    private Scene createSceneThree(){
        nameText = new TextField();
        nameLabel= new Label("Name: ");
        emailText = new TextField();
        emailLabel = new Label("Email: ");
        phoneText = new TextField();
        phoneLabel = new Label("Phone: ");
        message = new Label("");

        bRegisterEnd = new Button("Register");
        bRegisterEnd.setOnAction(e -> {
            if(nameText.getText().compareTo("")!=0 && emailText.getText().compareTo("")!=0 && phoneText.getText().compareTo("")!=0){
                message.setText("");
                boolean contains = false;
                for(String str : emails){
                    if(emailText.getText().contains(str)){
                        contains = true;
                        break;
                    }
                }
                if(phoneText.getText().length() == 10 && contains) { //emailText.getText().contains(
                    if(memberList.size() == 0){
                        newUser = new Member(nameText.getText(), emailText.getText(), phoneText.getText(), 1000);
                        memberList.add(newUser);
                    }else {
                        int nextId = memberList.get(memberList.size() - 1).getId() + 1;
                        newUser = new Member(nameText.getText(), emailText.getText(), phoneText.getText(), nextId);
                        memberList.add(newUser);
                    }
                    userIdMessage = new Label("Thank you for registering. " + String.valueOf(newUser.getId()) + " is your id. You will need this to log in later.");
                    scene7 = createSceneSeven();

                    switchScenes(scene7);
                } else {
                    message.setText("Please make sure you input correctly.");
                }
            }else {
                message.setText("Please make sure to fill everything.");
            }
        });

        bCancel = new Button("Cancel");
        bCancel.setOnAction(e-> {scene2 = createSceneTwo(); switchScenes(scene2); message.setText("");});

        vBox3 = new VBox();
        vBox3.setAlignment(Pos.TOP_CENTER);
        vBox3.setSpacing(10);

        hBox3_name = new HBox();
        hBox3_name.setAlignment(Pos.TOP_CENTER);
        hBox3_name.setSpacing(10);

        hBox3_email = new HBox();
        hBox3_email.setAlignment(Pos.TOP_CENTER);
        hBox3_email.setSpacing(10);

        hBox3_phone = new HBox();
        hBox3_phone.setAlignment(Pos.TOP_CENTER);
        hBox3_phone.setSpacing(10);

        hBox3_buttons = new HBox();
        hBox3_buttons.setAlignment(Pos.TOP_CENTER);
        hBox3_buttons.setSpacing(10);

        hBox3_name.getChildren().addAll(nameLabel, nameText);
        hBox3_email.getChildren().addAll(emailLabel, emailText);
        hBox3_phone.getChildren().addAll(phoneLabel, phoneText);
        hBox3_buttons.getChildren().addAll(bRegisterEnd, bCancel);
        vBox3.getChildren().addAll(hBox3_name, hBox3_email, hBox3_phone, hBox3_buttons, message);
        scene3 = new Scene(vBox3, 300, 200);

        return scene3;
    }

    private Scene createSceneFour(){
        empNameLabel = new Label("Name: ");
        empNameText = new TextField();
        empPassLabel = new Label("Password: ");
        empPassText = new PasswordField();
        empMessage = new Label("");

        bEmpLogIn = new Button("Log in");
        bEmpLogIn.setOnAction(e -> {
            boolean empExist = false;
            if(empNameText.getText().compareTo("")!=0 && empPassText.getText().compareTo("")!=0){
                for(int i = 0; i < workerList.size(); i++){
                    if(workerList.get(i).getName().compareTo(empNameText.getText())==0 && workerList.get(i).getPassword().compareTo(empPassText.getText())==0){ 
                        empExist = true;
                    }
                }
                if(empExist){
                    scene6 = createSceneSix(); 
                    switchScenes(scene6);
                }else {
                    empMessage.setText("Please make sure you input correctly.");
                }
            }else{
                empMessage.setText("Please make sure to fill everything.");
            }
        });

        bEmpCancel = new Button("Cancel");
        bEmpCancel.setOnAction(e -> {scene1 = createSceneOne(); switchScenes(scene1);});

        vBox4 = new VBox();
        vBox4.setAlignment(Pos.TOP_CENTER);
        vBox4.setSpacing(25);

        hBox4_name = new HBox();
        hBox4_name.setAlignment(Pos.TOP_CENTER);
        hBox4_name.setSpacing(10);

        hBox4_pw = new HBox();
        hBox4_pw.setAlignment(Pos.TOP_CENTER);
        hBox4_pw.setSpacing(5);

        hBox4_buttons = new HBox();
        hBox4_buttons.setAlignment(Pos.TOP_CENTER);
        hBox4_buttons.setSpacing(10);

        hBox4_name.getChildren().addAll(empNameLabel, empNameText);
        hBox4_pw.getChildren().addAll(empPassLabel, empPassText);
        hBox4_buttons.getChildren().addAll(bEmpLogIn, bEmpCancel);
        vBox4.getChildren().addAll(hBox4_name, hBox4_pw, hBox4_buttons, empMessage);
        scene4 = new Scene(vBox4, 300, 200);
        return scene4;
    }

    private Scene createSceneFive(){
        userIdLabel = new Label("Generated Id: ");
        userIdText = new TextField();
        userNameLabel = new Label("Name: ");
        userNameText = new TextField();

        messageLogIn = new Label("");

        bUserLogIn = new Button("Log in");
        bUserLogIn.setOnAction(e -> {
            if(userIdText.getText().compareTo("")!= 0 && userNameText.getText().compareTo("")!=0){
                messageLogIn.setText("");
                Boolean exist = false;
                for(int i = 0; i < memberList.size(); i++){
                    if(memberList.get(i).getId() == Integer.valueOf(userIdText.getText()) && memberList.get(i).getName().compareTo(userNameText.getText())==0){
                        exist = true;
                        break;
                    }
                }
                if(exist){
                    userIdMessage = new Label("Welcome back, " + userNameText.getText() + "!");
                    scene7 = createSceneSeven(); 
                    switchScenes(scene7);
                }
                else{
                    messageLogIn.setText("Please make sure you input correctly.");
                }
            }else {
                messageLogIn.setText("Please make sure to fill everything");
            }
        });

        bUserCancel = new Button("Cancel");
        bUserCancel.setOnAction(e -> {scene2 = createSceneTwo(); switchScenes(scene2);  messageLogIn.setText("");});

        vBox5 = new VBox();
        vBox5.setAlignment(Pos.TOP_CENTER);
        vBox5.setSpacing(25);

        hBox5_id = new HBox();
        hBox5_id.setAlignment(Pos.TOP_CENTER);
        hBox5_id.setSpacing(10);

        hBox5_name = new HBox();
        hBox5_name.setAlignment(Pos.TOP_CENTER);
        hBox5_name.setSpacing(30);

        hBox5_buttons = new HBox();
        hBox5_buttons.setAlignment(Pos.TOP_CENTER);
        hBox5_buttons.setSpacing(20);

        hBox5_id.getChildren().addAll(userIdLabel, userIdText);
        hBox5_name.getChildren().addAll(userNameLabel, userNameText);
        hBox5_buttons.getChildren().addAll(bUserLogIn, bUserCancel);
        vBox5.getChildren().addAll(hBox5_id, hBox5_name, hBox5_buttons, messageLogIn);
        scene5 = new Scene(vBox5, 300, 200);

        return scene5;
    }

    private Scene createSceneSix(){
        bCreateEvent = new Button("Create new event");
        bCreateEvent.setOnAction(e -> {scene10 = createSceneTen(); switchScenes(scene10);});

        bEditEvent = new Button("Edit an event");
        bEditEvent.setOnAction(e -> {
            workerMessage = new Label("Which event would you like to edit.");
            scene11 = createSceneEleven(); 
            switchScenes(scene11);
        });

        bEmpBack = new Button("Back to main page");
        bEmpBack.setOnAction(e -> {scene1 = createSceneOne(); switchScenes(scene1);});

        vBox6 = new VBox();
        vBox6.setAlignment(Pos.TOP_CENTER);
        vBox6.setSpacing(10);

        vBox6.getChildren().addAll(bCreateEvent, bEditEvent, bEmpBack);
        scene6 = new Scene(vBox6, 300, 200);

        return scene6;
    }
   
    private Scene createSceneSeven(){
        eventButtons.clear();
        eventListMessage = new Label("");

        bEventCancel = new Button("Back to main page");
        bEventCancel.setOnAction(e -> {scene1 = createSceneOne(); switchScenes(scene1);});

        vBox7 = new VBox();
        vBox7.setAlignment(Pos.TOP_CENTER);
        vBox7.setSpacing(20);

        hBox7_registerMessage = new HBox();
        hBox7_registerMessage.setAlignment(Pos.TOP_LEFT);

        vBox7_buttons = new VBox();
        vBox7_buttons.setAlignment(Pos.TOP_LEFT);
        vBox7_buttons.setSpacing(10);

        hBox7_registerMessage.getChildren().add(userIdMessage);
        vBox7.getChildren().addAll(hBox7_registerMessage);

        if(eventList.size() == 0){
            System.out.println(eventList.size());
            eventListMessage.setText("Currently there are no events\n please check later.");

            vBox7.getChildren().addAll(eventListMessage, bEventCancel);
        }
        else{
            for(int i = 0; i < eventList.size(); i++){
                bEvent = new Button("Event " + (i+1) + ": " + eventList.get(i).getName());
                eventButtons.add(bEvent);
                bEvent.setOnAction(this::handleEventButtons);

                vBox7_buttons.getChildren().add(bEvent);
            }

            scene7ScrollP = new ScrollPane(vBox7_buttons);
            vBox7.getChildren().addAll(scene7ScrollP, bEventCancel);
        }

        scene7 = new Scene(vBox7, 500, 300);
        return scene7;
    }

    private Scene createSceneEight(int pos){
        Event current = eventList.get(pos); 

        eventName = new Label("Event " + (pos+1) + ": " + current.getName());
        eventName.setFont(new Font(24));
        eventDesc = new Label("\tJoin us for " + current.getName() + " class in our gym. \nThis class is directed by our own instructor: \n\t\t" + current.getGymWorkerName());
        eventDesc.setFont(new Font(13));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        eventDate = new Label(current.getDate().format(formatter));

        eventCap = new Label("Current Capacity: " + (
            current.getCapacity()-current.getAttendees()));
        eventPrice = new Label("Total Price: " + current.getPrice());

        bBackEventList = new Button("Back");
        bBackEventList.setOnAction(e -> {scene7 = createSceneSeven(); switchScenes(scene7);});

        bPaymentPage = new Button("Continue with payment");
        bPaymentPage.setOnAction(e -> {scene9 = createSceneNine(pos); switchScenes(scene9);});

        if(current.getCapacity() == 0){
            bPaymentPage.setDisable(true);
        }

        vBox8 = new VBox();
        vBox8.setAlignment(Pos.TOP_CENTER);
        vBox8.setSpacing(10);

        hBox8_buttons = new HBox();
        hBox8_buttons.setAlignment(Pos.TOP_CENTER);
        hBox8_buttons.setSpacing(10);

        hBox8_CapPrice = new HBox();
        hBox8_CapPrice.setAlignment(Pos.TOP_CENTER);
        hBox8_CapPrice.setSpacing(20);

        hBox8_buttons.getChildren().addAll(bPaymentPage, bBackEventList);
        hBox8_CapPrice.getChildren().addAll(eventCap, eventPrice);
        vBox8.getChildren().addAll(eventName, eventDate, eventDesc, hBox8_CapPrice, hBox8_buttons);
        scene8 = new Scene(vBox8, 300, 200);
        return scene8;
    }

    private Scene createSceneNine(int pos){
        bPaypal = new Button("Paypal");
        bPaypal.setOnAction(e -> {
            try{
                URI uri = new URI("https://www.paypal.com");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
                eventList.get(pos).setAttendees(eventList.get(pos).getAttendees()+1);

                scene13 = createSceneThirteen();
                switchScenes(scene13);
            }catch(Exception ex){}
        });

        bGooglePay = new Button("Google Pay");
        bGooglePay.setOnAction(e -> {
            try{
                URI uri = new URI("https://pay.google.com");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
                eventList.get(pos).setAttendees(eventList.get(pos).getAttendees()+1);

                scene13 = createSceneThirteen();
                switchScenes(scene13);
            }catch(Exception ex){}
        });

        bApplePay = new Button("Apple Pay");
        bApplePay.setOnAction(e -> {
            try{
                URI uri = new URI("https://www.apple.com/apple-pay/");
                Desktop dt = Desktop.getDesktop();
                dt.browse(uri);
                eventList.get(pos).setAttendees(eventList.get(pos).getAttendees()+1);

                scene13 = createSceneThirteen();
                switchScenes(scene13);
            }catch(Exception ex){}
        });

        bCancelPay = new Button("Cancel");
        bCancelPay.setOnAction(e -> {scene8 = createSceneEight(pos); switchScenes(scene8);});

        vBox9 = new VBox();
        vBox9.setAlignment(Pos.TOP_CENTER);
        vBox9.setSpacing(10);

        vBox9.getChildren().addAll(bPaypal, bGooglePay, bApplePay, bCancelPay);
        scene9 = new Scene(vBox9, 300, 200);
        return scene9;
    }

    private Scene createSceneTen(){
        createNameLabel = new Label("Name of the event: ");
        createCapLabel = new Label("Capacity of this event: ");
        createInvLabel = new Label("Type of inventory: ");
        createWorkerLabel = new Label("Instructor name: ");
        createDateLabel = new Label("The date of the event: ");
        createPriceLabel = new Label("Price of this event: ");

        createName = new TextField();
        createCap = new TextField();
        createInvName = new TextField();
        createWorker = new TextField();
        createDate = new TextField();
        createPrice = new TextField();

        createMessage = new Label("");

        bCreate = new Button("Create new event");
        bCreate.setOnAction(e -> {
            if(!createName.getText().isEmpty() && !createCap.getText().isEmpty() && !createInvName.getText().isEmpty() 
                && !createWorker.getText().isEmpty() && !createDate.getText().isEmpty() && !createPrice.getText().isEmpty()){
                    boolean contains = false;
                    int i;
                    for(i = 0; i < inventoryList.size();i++){
                        if(inventoryList.get(i).getType().compareTo(createInvName.getText())==0){
                            contains = true;
                            break;
                        }
                    }
                    if(inventoryList.size() == 0 || !contains){
                        Inventory newInventory = new Inventory(createInvName.getText(), Integer.parseInt(createCap.getText()));
                        inventoryList.add(newInventory);
                        newEvent = new Event(createName.getText(), Integer.parseInt(createCap.getText()), newInventory, Double.parseDouble(createPrice.getText()), LocalDate.parse(createDate.getText()), createWorker.getText(), 0);
                        eventList.add(newEvent);

                        scene14 = createSceneFourteen();
                        switchScenes(scene14);
                    }else {
                        if(Integer.parseInt(createCap.getText()) <= inventoryList.get(i).getAmount()){
                            newEvent = new Event(createName.getText(), Integer.parseInt(createCap.getText()), inventoryList.get(i), Double.parseDouble(createPrice.getText()), LocalDate.parse(createDate.getText()), createWorker.getText(), 0);
                            eventList.add(newEvent);

                            scene14 = createSceneFourteen();
                            switchScenes(scene14);
                        }else{
                            createMessage.setText("Capacity can't be more than inventory amount.\n\tPlease input maximum of " + inventoryList.get(i).getAmount());
                        }
                    }
            }else {
                createMessage.setText("Please make sure to fill everything.");
            }
        });

        bCancelCreate = new Button("Back");
        bCancelCreate.setOnAction(e -> {scene6 = createSceneSix(); switchScenes(scene6);});

        vBox10 = new VBox();
        vBox10.setAlignment(Pos.TOP_CENTER);
        vBox10.setSpacing(20);

        hBox10_name = new HBox();
        hBox10_name.setAlignment(Pos.TOP_CENTER);
        hBox10_name.setSpacing(10);

        hBox10_cap = new HBox();
        hBox10_cap.setAlignment(Pos.TOP_CENTER);
        hBox10_cap.setSpacing(10);

        hBox10_inv = new HBox();
        hBox10_inv.setAlignment(Pos.TOP_CENTER);
        hBox10_inv.setSpacing(10);

        hBox10_date = new HBox();
        hBox10_date.setAlignment(Pos.TOP_CENTER);
        hBox10_date.setSpacing(10);

        hBox10_price = new HBox();
        hBox10_price.setAlignment(Pos.TOP_CENTER);
        hBox10_price.setSpacing(10);

        hBox10_worker = new HBox();
        hBox10_worker.setAlignment(Pos.TOP_CENTER);
        hBox10_worker.setSpacing(10);

        hBox10_buttons = new HBox();
        hBox10_buttons.setAlignment(Pos.TOP_CENTER);
        hBox10_buttons.setSpacing(20);

        hBox10_name.getChildren().addAll(createNameLabel, createName);
        hBox10_cap.getChildren().addAll(createCapLabel, createCap);
        hBox10_inv.getChildren().addAll(createInvLabel, createInvName);
        hBox10_date.getChildren().addAll(createDateLabel, createDate);
        hBox10_price.getChildren().addAll(createPriceLabel, createPrice);
        hBox10_worker.getChildren().addAll(createWorkerLabel, createWorker);
        hBox10_buttons.getChildren().addAll(bCreate, bCancelCreate);
        vBox10.getChildren().addAll(hBox10_name, hBox10_cap, hBox10_inv, hBox10_date, hBox10_price, hBox10_worker, hBox10_buttons, createMessage);
        scene10 = new Scene(vBox10, 350, 400);
        return scene10;
    }

    private Scene createSceneEleven(){
        eventButtons.clear();
        eventListMessage = new Label("");

        bEditCancel = new Button("Back");
        bEditCancel.setOnAction(e -> {scene6 = createSceneSix(); switchScenes(scene6);});

        vBox11 = new VBox();
        vBox11.setAlignment(Pos.TOP_CENTER);
        vBox11.setSpacing(20);

        hBox11_registerMessage = new HBox();
        hBox11_registerMessage.setAlignment(Pos.TOP_LEFT);

        vBox11_buttons = new VBox();
        vBox11_buttons.setAlignment(Pos.TOP_LEFT);
        vBox11_buttons.setSpacing(10);

        hBox11_registerMessage.getChildren().add(workerMessage);
        vBox11.getChildren().addAll(hBox11_registerMessage);
        if(eventList.size() == 0){
            System.out.println(eventList.size());
            eventListMessage.setText("Currently there are no events\n please check later.");

            vBox11.getChildren().addAll(eventListMessage, bEditCancel);
        }
        else{
            for(int i = 0; i < eventList.size(); i++){
                bEditE = new Button("Event " + (i+1) + ": " + eventList.get(i).getName());
                eventButtons.add(bEditE);
                bEditE.setOnAction(this::handleEditButtons);

                vBox11_buttons.getChildren().add(bEditE);
            }

            scene11ScrollP = new ScrollPane(vBox11_buttons);
            vBox11.getChildren().addAll(scene11ScrollP, bEditCancel);
        }

        scene11 = new Scene(vBox11, 500, 300);
        return scene11;
    }

    private Scene createSceneTwelve(int pos){
        Event editCurrent = eventList.get(pos);

        editEventNameLabel = new Label("Current Event Name: ");
        editEventCapLabel = new Label("Current Event Capacity: ");
        editEventDateLabel = new Label("Current Event Date: ");
        editEventPriceLabel = new Label("Current Event Price: ");
        editEventWorkerLabel = new Label("Current Event Instructor: ");
        editEventInvLabel = new Label("Current Inventory Name: ");

        editEventName = new TextField(editCurrent.getName());
        editEventCap = new TextField(Integer.toString(editCurrent.getCapacity()));
        editEventDate = new TextField(editCurrent.getDate().toString());
        editEventPrice = new TextField(Double.toString(editCurrent.getPrice()));
        editEventWorker = new TextField(editCurrent.getGymWorkerName());
        editEventInv = new TextField(editCurrent.getInventoryName());

        editMessage = new Label("");

        bEditEventBack = new Button("Back");
        bEditEventBack.setOnAction(e -> {scene11 = createSceneEleven(); switchScenes(scene11);});

        bEditEventFinish = new Button("Make the changes");
        bEditEventFinish.setOnAction(e -> {
            if(!editEventName.getText().isEmpty() && !editEventCap.getText().isEmpty() && !editEventDate.getText().isEmpty() 
                && !editEventPrice.getText().isEmpty() && !editEventWorker.getText().isEmpty() && !editEventInv.getText().isEmpty()){
                    boolean contains = false;
                    int i;
                    for(i = 0; i < inventoryList.size();i++){
                        if(inventoryList.get(i).getType().compareTo(editEventInv.getText())==0){
                            contains = true;
                            break;
                        }
                    }
                    if(inventoryList.size() == 0 || !contains){
                        Inventory newInventory = new Inventory(editEventInv.getText(), Integer.parseInt(editEventCap.getText()));
                        inventoryList.add(newInventory);
                        editCurrent.setAll(editEventName.getText(), Integer.valueOf(editEventCap.getText()), LocalDate.parse(editEventDate.getText()), Double.parseDouble(editEventPrice.getText()), editEventWorker.getText(), newInventory);

                        scene15 = createSceneFifteen();
                        switchScenes(scene15);
                    }else{
                        if(Integer.parseInt(editEventCap.getText()) <= inventoryList.get(i).getAmount()){
                            editCurrent.setAll(editEventName.getText(), Integer.valueOf(editEventCap.getText()), LocalDate.parse(editEventDate.getText()), Double.parseDouble(editEventPrice.getText()), editEventWorker.getText(), inventoryList.get(i));

                            scene15 = createSceneFifteen();
                            switchScenes(scene15);
                        }else{
                            editMessage.setText("Capacity can't be more than inventory amount.\n\tPlease input maximum of " + inventoryList.get(i).getAmount());
                        }
                    }
            }else{
                editMessage.setText("Please make sure to fill everything.");
            }
        });

        vBox12 = new VBox();
        vBox12.setAlignment(Pos.TOP_CENTER);
        vBox12.setSpacing(10);

        hBox12_buttons = new HBox();
        hBox12_buttons.setAlignment(Pos.TOP_CENTER);
        hBox12_buttons.setSpacing(10);

        hBox12_name = new HBox();
        hBox12_name.setAlignment(Pos.TOP_CENTER);
        hBox12_name.setSpacing(10);

        hBox12_cap = new HBox();
        hBox12_cap.setAlignment(Pos.TOP_CENTER);
        hBox12_cap.setSpacing(10);

        hBox12_price = new HBox();
        hBox12_price.setAlignment(Pos.TOP_CENTER);
        hBox12_price.setSpacing(10);

        hBox12_date = new HBox();
        hBox12_date.setAlignment(Pos.TOP_CENTER);
        hBox12_date.setSpacing(10);

        hBox12_inv = new HBox();
        hBox12_inv.setAlignment(Pos.TOP_CENTER);
        hBox12_inv.setSpacing(10);

        hBox12_worker = new HBox();
        hBox12_worker.setAlignment(Pos.TOP_CENTER);
        hBox12_worker.setSpacing(10);

        hBox12_name.getChildren().addAll(editEventNameLabel, editEventName);
        hBox12_cap.getChildren().addAll(editEventCapLabel, editEventCap);
        hBox12_price.getChildren().addAll(editEventPriceLabel, editEventPrice);
        hBox12_date.getChildren().addAll(editEventDateLabel, editEventDate);
        hBox12_worker.getChildren().addAll(editEventWorkerLabel, editEventWorker);
        hBox12_inv.getChildren().addAll(editEventInvLabel, editEventInv);
        hBox12_buttons.getChildren().addAll(bEditEventFinish, bEditEventBack);
        vBox12.getChildren().addAll(hBox12_name, hBox12_cap, hBox12_inv, hBox12_price, hBox12_date, hBox12_worker, hBox12_buttons, editMessage);
        scene12 = new Scene(vBox12, 375, 400);
        return scene12;
    }

    private Scene createSceneThirteen(){
        WFileEvent(eventList);
        thankYouLabel = new Label("Thank you for registering for this event.");

        bPaid = new Button("Back to events page");
        bPaid.setOnAction(e -> {scene7 = createSceneSeven(); switchScenes(scene7);});

        vBox13 = new VBox();
        vBox13.setAlignment(Pos.BASELINE_CENTER);
        vBox13.setSpacing(30);
        
        vBox13.getChildren().addAll(thankYouLabel, bPaid);
        scene13 = new Scene(vBox13, 300, 200);
        return scene13;
    }
  
    private Scene createSceneFourteen(){
        WFileEvent(eventList);
        createFinishMessage = new Label("New event has been added to the list");

        bCreateFinish = new Button("Back to options");
        bCreateFinish.setOnAction(e -> {scene6 = createSceneSix(); switchScenes(scene6);});

        vBox14 = new VBox();
        vBox14.setAlignment(Pos.TOP_CENTER);
        vBox14.setSpacing(20);

        vBox14.getChildren().addAll(createFinishMessage, bCreateFinish);
        scene14 = new Scene(vBox14, 300, 200);
        return scene14;
    }

    private Scene createSceneFifteen(){
        WFileEvent(eventList);
        editFinishMessage = new Label("Event has been edited.");

        bEditFinish = new Button("Back to events list");
        bEditFinish.setOnAction(e -> {scene11 = createSceneSix(); switchScenes(scene11);});

        vBox15 = new VBox();
        vBox15.setAlignment(Pos.TOP_CENTER);
        vBox15.setSpacing(20);

        vBox15.getChildren().addAll(editFinishMessage, bEditFinish);
        scene15 = new Scene(vBox15, 300, 200);
        return scene15;
    }

    private void switchScenes(Scene scene){
        stage.setScene(scene);
    }

    public void handleEventButtons(ActionEvent e){
        for(int i = 0; i < eventButtons.size(); i++){
            if(e.getSource() == eventButtons.get(i)){
                System.out.println("Button " + (i+1) + " pressed");
                scene8 = createSceneEight(i);
                switchScenes(scene8);
                break;
            }
        }
    }

    public void handleEditButtons(ActionEvent e){
        for(int i = 0; i < eventButtons.size(); i++){
            if(e.getSource() == eventButtons.get(i)){
                System.out.println("Button " + (i+1) + " pressed");
                scene12 = createSceneTwelve(i);
                switchScenes(scene12);
                break;
            }
        }
    }

    public static void RFileEvent(){
		try(Scanner scan = new Scanner(eventFile)){

			if(scan.hasNextLine()){
				scan.nextLine();
			}

			while(scan.hasNextLine()){

				String lineIn = scan.nextLine();

				try(Scanner rowScan = new Scanner(lineIn)){
					rowScan.useDelimiter(",");

 					String nameIn = rowScan.next();
                	int capacityIn  = Integer.parseInt(rowScan.next());
                	String invType = rowScan.next();	
                	int invAmount = Integer.parseInt(rowScan.next());
                	Inventory invIn = new Inventory(invType, invAmount);
                	double priceIn = Double.parseDouble(rowScan.next());
                	LocalDate dateIn = LocalDate.parse(rowScan.next());
                	String gymWorkerIn = rowScan.next();
                    int attendeesIn = Integer.parseInt(rowScan.next());

                    inventoryList.add(invIn);
                	Event eventAdd = new Event(nameIn, capacityIn, invIn, priceIn, dateIn, gymWorkerIn, attendeesIn);
                	eventList.add(eventAdd);
                }
            }
                
		}catch(FileNotFoundException e){
            e.printStackTrace();
		}
	}

    public static void WFileEvent(ArrayList<Event> listIn){
		try(PrintWriter pw = new PrintWriter(eventFile)){
			pw.println("Name,Capacity,Inventory,Price,Date,GymWorker,Attendees");
			for(Event e : listIn){
                pw.println(e.getName() + "," + e.getCapacity() + "," + e.getInventoryName() + "," + e.getInventoryAmount() + "," + e.getPrice() + "," + e.getDate() + "," + e.getGymWorkerName() + "," + e.getAttendees());
			}
		}catch(IOException e){
			 System.out.println("Error updating this file: " + e.getMessage());
		}
	}

    public static void main(String[] args) {
        launch(args);
    }
}



/*
 * Promotion????
 * add ArrayList<Event> passEvents
 */