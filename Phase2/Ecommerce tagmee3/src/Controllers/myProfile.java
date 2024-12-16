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
    private Customer currentUser;

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
    private TextField usernameTxt;

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
        usernameTxt.setDisable(false);
        passTxt.setDisable(false);
        addressText.setDisable(false);
        balanceTxt.setDisable(false);
    }

    @FXML
    void viewOrders(ActionEvent event) {

    }
    public void populateFields(){
        passTxt.setText(currentUser.getUsername());
        usernameTxt.setText(currentUser.getUsername());
        balanceTxt.setText(String.valueOf(currentUser.getBalance()));
        addressText.setText(currentUser.getAddress());
    }
    public void setUser(Customer user){
        this.currentUser = user;

    }

}
