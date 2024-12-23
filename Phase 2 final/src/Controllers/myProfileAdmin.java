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

public class myProfileAdmin {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Admin currentUser;

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
    public void initialize(){
        currentUser= (Admin)UserSession.getUser();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
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
        passTxt.setText(currentUser.getPassword());
        usernameTxt.setText(currentUser.getUsername());
        balanceTxt.setText(String.valueOf(currentUser.getRole()));
        addressText.setText(currentUser.getWorking_hours());
    }


}
