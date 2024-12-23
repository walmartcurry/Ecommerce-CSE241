package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import models.Category;
import models.Customer;

public class CustomerInterestsController {
    private Customer cust;

    @FXML
    private CheckBox electronicsCheckBox;

    @FXML
    private CheckBox fashionCheckBox;

    @FXML
    private CheckBox groceryCheckBox;

    @FXML
    private CheckBox homeCheckBox;


    @FXML
    private CheckBox sportsCheckBox;


    @FXML
    private Button saveBtn;
    @FXML
    private Button backBtn;


    private CheckBox[] categoryCheckboxes = {
        electronicsCheckBox,
        fashionCheckBox,
        sportsCheckBox,
        homeCheckBox,
        groceryCheckBox,
    };

    @FXML
    void handleReset(ActionEvent event) {

    }
    public void initialize() {
        cust = (Customer)UserSession.getUser();
        for (Category cat : cust.getIntrests() ){

            if (electronicsCheckBox.getText().equals(cat.gettype()))
                electronicsCheckBox.setSelected(true);
    
            if (fashionCheckBox.getText().equals(cat.gettype()))
                fashionCheckBox.setSelected(true);
    
            if (groceryCheckBox.getText().equals(cat.gettype()))
                groceryCheckBox.setSelected(true);
    
            if (homeCheckBox.getText().equals(cat.gettype()))
                homeCheckBox.setSelected(true);
    
            if (sportsCheckBox.getText().equals(cat.gettype()))
                sportsCheckBox.setSelected(true);

        }
    }

    @FXML
    private void handleSave() {
        cust.getIntrests().clear();
    
        if (electronicsCheckBox.isSelected()) {
            cust.addIntrests(electronicsCheckBox.getText());
        }
        if (fashionCheckBox.isSelected()) {
            cust.addIntrests(fashionCheckBox.getText());
        }
        if (groceryCheckBox.isSelected()) {
            cust.addIntrests(groceryCheckBox.getText());
        }
        if (homeCheckBox.isSelected()) {
            cust.addIntrests(homeCheckBox.getText());
        }
        if (sportsCheckBox.isSelected()) {
            cust.addIntrests(sportsCheckBox.getText());
        }
        
    }
    @FXML
    void BackAct(ActionEvent event)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/custfxml.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    



}


