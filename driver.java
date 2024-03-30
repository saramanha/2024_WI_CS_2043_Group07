import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class driver extends Application{
    private Stage stage;
    private File memberFile;
    private ArrayList<Member> memberList = new ArrayList<>(); 

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

    //Scene 7: After registering new member in scene3
    private Scene scene7;

    private VBox vBox7;

    @Override
    public void start(Stage primaryStage) throws IOException{
        stage = primaryStage;
        stage.setTitle("GYM Events");

        memberFile = new File("members.csv");



        scene1 = createSceneOne();
        scene2 = createSceneTwo();
        scene3 = createSceneThree();
        scene4 = createSceneFour();
        scene5 = createSceneFive();
        scene6 = createSceneSix();
        scene7 = createSceneSeven();

        stage.setScene(scene1);

        stage.show();
    }

    private Scene createSceneOne(){
        startLabel = new Label("Welcome to UNB gym events.\n Are you an employee or user?");
        bUser = new Button("User");
        bUser.setOnAction(e -> switchScenes(scene2));
        bEmployee = new Button("Employee");
        bEmployee.setOnAction(e-> switchScenes(scene4));

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
        bLogIn.setOnAction(e -> switchScenes(scene5));
        bRegister = new Button("Register");
        bRegister.setOnAction(e-> switchScenes(scene3));
        bS2Back = new Button("Back");
        bS2Back.setOnAction(e -> switchScenes(scene1));

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
            message.setText("");
            if(phoneText.getText().length() == 10) {
                Member newUser = new Member(nameText.getText(), emailText.getText(), phoneText.getText());
                memberList.add(newUser);
                System.out.println(memberList.get(0).getId());
                switchScenes(scene7);
            } else {
                message.setText("Please make sure you input correctly.");
            }
        });

        bCancel = new Button("Cancel");
        bCancel.setOnAction(e-> {switchScenes(scene2); message.setText("");});

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
        empPassText = new TextField();
        bEmpLogIn = new Button("Log in");
        bEmpLogIn.setOnAction(e -> switchScenes(scene6));
        bEmpCancel = new Button("Cancel");
        bEmpCancel.setOnAction(e -> switchScenes(scene1));

        vBox4 = new VBox();
        vBox4.setAlignment(Pos.TOP_CENTER);
        vBox4.setSpacing(30);

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
        vBox4.getChildren().addAll(hBox4_name, hBox4_pw, hBox4_buttons);
        scene4 = new Scene(vBox4, 300, 200);

        return scene4;
    }

    private Scene createSceneFive(){
        userIdLabel = new Label("Generated Id: ");
        userIdText = new TextField();
        // userIdText.setMaxSize(150, 5);
        userNameLabel = new Label("Name: ");
        userNameText = new TextField();
        bUserLogIn = new Button("Log in");
        //bUserLogIn.setOnAction(e -> switchScenes());
        bUserCancel = new Button("Cancel");
        bUserCancel.setOnAction(e -> switchScenes(scene2));

        vBox5 = new VBox();
        vBox5.setAlignment(Pos.TOP_CENTER);
        vBox5.setSpacing(30);

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
        vBox5.getChildren().addAll(hBox5_id, hBox5_name, hBox5_buttons);
        scene5 = new Scene(vBox5, 300, 200);

        return scene5;
    }

    private Scene createSceneSix(){
        bCreateEvent = new Button("Create new event");
        //bCreateEvent.setOnAction(e -> switchScenes());

        bEditEvent = new Button("Edit an event");
        //bEditEvent.setOnAction(e -> switchScenes());

        bEmpBack = new Button("Back to main page");
        bEmpBack.setOnAction(e -> switchScenes(scene1));

        vBox6 = new VBox();
        vBox6.setAlignment(Pos.TOP_CENTER);
        vBox6.setSpacing(10);

        vBox6.getChildren().addAll(bCreateEvent, bEditEvent, bEmpBack);
        scene6 = new Scene(vBox6, 300, 200);

        return scene6;
    }
   
    private Scene createSceneSeven(){
        vBox7 = new VBox();
        scene7 = new Scene(vBox7, 300, 200);
        return scene7;
    }

    private void switchScenes(Scene scene){
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}