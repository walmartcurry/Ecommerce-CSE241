package Controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;
import models.Database;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML private ComboBox<String> roleComboBox;

    @FXML private Label loginError;

    @FXML
    void cancelBtn(ActionEvent event) {
        // Implement if needed
    }
     public void initialize(){
        roleComboBox.setItems(FXCollections.observableArrayList("Admin","Customer"));
    }

    @FXML
    void signUpLinkClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginBtn(ActionEvent event) throws IOException {
        if(roleComboBox.getValue().equals("Customer")){
        loginError.setText("");
        String username = usernameField.getText();
        String password = passwordField.getText();
        Customer user = null;
        for(Customer c : Database.users)
            if(c.getUsername().equals(username) && c.getPassword().equals(password))
                user = c;
            

        if (user == null) {
            loginError.setText("Invalid username or password, try again.");
        } else {
            UserSession.setUser(user);
            // Successful login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/custfxml.fxml"));
            Parent root = loader.load();
            System.out.print(user.getUsername());
            // custfxmlController.setUser(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    else if (roleComboBox.getValue().equals("Admin")){
        loginError.setText("");
        String username = usernameField.getText();
        String password = passwordField.getText();
        Admin user = null;
        for(Admin c : Database.admins)
            if(c.getUsername().equals(username) && c.getPassword().equals(password))
                user = c;
            
        if (user == null) {
            loginError.setText("Invalid username or password, try again.");
        } else {
            // Successful login
            UserSession.setUser(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admindash.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
}
