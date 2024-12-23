package Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.collections.FXCollections;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import models.Database;

public class SignUpController {

    @FXML
    private TextField addressField;


    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField dayField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private TextField monthField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField yearField;
    
    @FXML private Label usernameError, passwordError, dateError, addressError;

    public void initialize(){
        genderComboBox.setItems(FXCollections.observableArrayList("Male","Female"));
    }

    @FXML
    void loginCLick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUpbtn(ActionEvent event) throws IOException {
        clearErrors();
        if(!isValidUsername(usernameField.getText())){
            usernameError.setText("Username might be taken");
            return;
        }
        if(!isValidPassword(passwordField.getText())){
            passwordError.setText("Password must be 8+ chars , contains uppercase ,lowercase , and a number . Try again");
            return;
        }
        try{
            Integer.parseInt(dayField.getText());
            Integer.parseInt(monthField.getText());
            Integer.parseInt(yearField.getText());
            if(!isValidDateOfBirth(Integer.parseInt(dayField.getText()) , Integer.parseInt(monthField.getText()) , Integer.parseInt(yearField.getText()))){
                dateError.setText("Invalid DOB , try again");
                return;
            }
        }
        catch(NumberFormatException e){
            dateError.setText("Enter a valid DOB");
            return;
        }
        if(!isValidAddress(addressField.getText())){
            addressError.setText("Invalid address , try again");
            return;
        }
        if(genderComboBox.getValue() == null){
            addressError.setText("Select your gender");
            return;
        }

        Customer user  = new Customer(usernameField.getText(), passwordField.getText(), Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()), genderComboBox.getValue(), addressField.getText());
        Database.users.add(user);
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void clearErrors() {
        usernameError.setText("");
        passwordError.setText("");
        addressError.setText("");
        dateError.setText("");
    }


     private  boolean isValidUsername(String username) {
        for(Customer user : Database.users){
            if(username.equals(user.getUsername()))
                return false;
        }
        return username.matches("^[a-zA-Z0-9]{3,20}$");
    }
    private  boolean isValidPassword(String password) {
        // Example rules: 8+ characters, at least one uppercase, one lowercase, one digit
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    }
    private  boolean isValidAddress(String address) {
        return address != null && address.trim().length() >= 3;
    }

private  boolean isValidDateOfBirth(int day, int month, int year) {
    if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) return false;
    if (month < 1 || month > 12) return false;
    
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
        daysInMonth[1] = 29; // Leap year
    }
    
    return day >= 1 && day <= daysInMonth[month - 1];
}

}
