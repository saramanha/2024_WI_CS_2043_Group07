import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class driver extends Application{
    private Stage stage;

    private Scene scene1;
    private TilePane tPane1;
    private Label startLabel;
    private Button bUser;
    private Button bEmployee;

    private Scene scene2;
    private TilePane tPane2;
    private Label userLabel;
    private Button bLogIn;
    private Button bRegister;
    private Button bS2Back;

    private Scene scene3;
    private TilePane tPane3;
    private Button bRegisterEnd;
    private Button bCancel;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        stage.setTitle("GYM Events");

        scene1 = createSceneOne();
        scene2 = createSceneTwo();
        scene3 = createSceneThree();

        stage.setScene(scene1);

        stage.show();
    }

    private Scene createSceneOne(){
        startLabel = new Label("Welcome to UNB gym events.\n Are you an employee or user?");
        bUser = new Button("User");
        bUser.setOnAction(e -> switchScenes(scene2));
        bEmployee = new Button("Employee");
        // bEmployee.setOnAction(e-> switchScenes());
        tPane1 = new TilePane();
        tPane1.getChildren().addAll(startLabel, bUser, bEmployee);
        scene1 = new Scene(tPane1, 300, 300);

        return scene1;
    }

    private Scene createSceneTwo(){
        userLabel = new Label("Are you a returning member?");
        bLogIn = new Button("Log in");
        // bLogIn.setOnAction(e -> switchScenes());
        bRegister = new Button("Register");
        bRegister.setOnAction(e-> switchScenes(scene3));
        bS2Back = new Button("Back");
        bS2Back.setOnAction(e -> switchScenes(scene1));
        tPane2 = new TilePane();
        tPane2.getChildren().addAll(userLabel, bLogIn, bRegister, bS2Back);
        scene2 = new Scene(tPane2, 300, 300);

        return scene2;
    }

    private Scene createSceneThree(){
        bRegisterEnd = new Button("Register");
        // bLogIn.setOnAction(e -> switchScenes());
        bCancel = new Button("Cancel");
        bCancel.setOnAction(e-> switchScenes(scene2));
        tPane3 = new TilePane();
        tPane3.getChildren().addAll(bRegisterEnd, bCancel);
        scene3 = new Scene(tPane3, 300, 300);

        return scene3;
    }

    private void switchScenes(Scene scene){
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}