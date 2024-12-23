package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

public class myProfile {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private  Customer currentUser;

    @FXML
    private Button ViewOrderbtn;

    @FXML
    private TextField addressText;

    @FXML
    private Label adressLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Label balanceLabel;

    @FXML
    private TextField balanceTxt;

    @FXML
    private Button editProfilebtn;

    @FXML
    private Label passLabel;

    @FXML
    private TextField passTxt;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label errLabel;

    @FXML
    private TextField usernameTxt;
    public void initialize(){
        currentUser = (Customer)UserSession.getUser();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/custfxml.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editProfile(ActionEvent event) {
        errLabel.setText("");
        if(usernameTxt.isDisabled()){
        usernameTxt.setDisable(false);
        passTxt.setDisable(false);
        addressText.setDisable(false);
        balanceTxt.setDisable(false);
        editProfilebtn.setText("Save");
        }
        else{
            if(usernameTxt.getText().equals("") || passTxt.getText().equals("")||balanceTxt.getText().equals("") || addressText.getText().equals("")){
                errLabel.setText("Enter credentials");
                return;
            }
            try{
            Double.parseDouble(balanceTxt.getText());
            if( Double.parseDouble(balanceTxt.getText()) < 0){
                errLabel.setText("Enter Valid balance");
                return;
            }
            }
            catch(NumberFormatException ex){
                errLabel.setText("Enter Valid balance");
                return;

            }
            currentUser.setUsername(usernameTxt.getText());
            currentUser.setPassword(passTxt.getText());
            currentUser.setBalance(Double.parseDouble(balanceTxt.getText()));
            currentUser.setAddress(addressText.getText());
            populateFields();
            usernameTxt.setDisable(true);
            passTxt.setDisable(true);
            addressText.setDisable(true);
            balanceTxt.setDisable(true);
            editProfilebtn.setText("Edit profile");
        }
    }

    @FXML
    void viewOrders(ActionEvent event) {

    }
    public void populateFields(){
        passTxt.setText(currentUser.getPassword());
        usernameTxt.setText(currentUser.getUsername());
        balanceTxt.setText(String.valueOf(currentUser.getBalance()));
        addressText.setText(currentUser.getAddress());
    }


}
