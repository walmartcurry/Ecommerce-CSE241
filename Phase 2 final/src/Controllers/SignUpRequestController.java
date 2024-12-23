package Controllers;

import java.io.IOException;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import models.Database;
import models.Supplier;

public class SignUpRequestController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField companyField;

    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    @FXML
    private Button sendRequestButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void sendRequest(ActionEvent event) throws IOException {
        if(!isValidUsername(usernameField.getText())){
            usernameError.setText("Username might be taken");
            return;
        }
        if(!isValidPassword(passwordField.getText())){
            passwordError.setText("Password must be 8+ characters , one lowercase , and oneupercase");
            return;
        }
        Supplier supp = new Supplier(usernameField.getText(), passwordField.getText(), companyField.getText());
        Database.supplierRequests.add(supp);
        Parent root = FXMLLoader.load(getClass().getResource("/views/suppLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void cancelAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/suppLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    private  boolean isValidUsername(String username) {
        for(Supplier user : Database.suppliers){
            if(username.equals(user.getUsername()))
                return false;
        }
        return username.matches("^[a-zA-Z0-9]{3,20}$");
    }
    private  boolean isValidPassword(String password) {
        // Example rules: 8+ characters, at least one uppercase, one lowercase, one digit
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

}}
