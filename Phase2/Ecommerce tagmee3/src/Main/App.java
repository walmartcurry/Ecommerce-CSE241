package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Views.*; // Adjust the import to match your project structure
import models.*; // Adjust the import to match your project structure
import Controllers.*; // Adjust the import to match your project structure

import java.io.IOException;

public class App extends Application {
    public Customer c1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        c1 = new Customer("Amir", "AmirtheGoat", 2, 7, 2005, "male", "Gardenia city");
        c1.setBalance(1000);
        Login_Sign();
    }

    private void showProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myProfile.fxml"));
            Pane root = loader.load();
            myProfile profileController = loader.getController();
            profileController.setUser(c1); 
            profileController.populateFields();
            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root));
            profileStage.setTitle("My Profile");
            profileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void CustomerDash(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/custfxml.fxml"));
            Pane root = loader.load();
            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root));
            profileStage.setTitle("Dashboard");
            profileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void viewProducts() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScene.fxml"));
        Pane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Products");
        profileStage.show();

    }
    private void Login_Sign() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FXMLDocument.fxml"));
        Pane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Products");
        profileStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
