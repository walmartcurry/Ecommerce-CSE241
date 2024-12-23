package Controllers;

import java.io.IOException;
import java.util.Locale.Category;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

public class addProduct {
    private Supplier supp = SuppSession.getUser();

    @FXML
    private TextField NameField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button closeBtn;

    @FXML
    private TextField priceField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField stockField;
    @FXML
    private Label err;
    public void initialize(){
          categoryComboBox.setItems(FXCollections.observableArrayList(models.Category.categories));
    }
    @FXML
    void closeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/suppfxml.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void saveAction(ActionEvent event) {
        err.setText("");
        if(NameField.getText().trim().equals("")){
            err.setText("Name field cannot be empty");
            return;
        }
        if(categoryComboBox.getValue()== null){
            err.setText("Please select a category");
            return;

        }
        if(priceField.getText().trim().equals("")){
            err.setText("Price field cannot be empty");
            return;
        }
        try{
            Integer.parseInt(priceField.getText());
            if( Integer.parseInt(priceField.getText()) < 0){
                err.setText("Enter a valid price");
                return;
            }
        }
        catch(NumberFormatException e){
            err.setText("Enter a valid price");
            return;
        }
        if(stockField.getText().trim().equals("")){
            err.setText("Stock field cannot be empty");
            return;
        }
        try{
            Integer.parseInt(stockField.getText());
            if( Integer.parseInt(stockField.getText()) < 0){
                err.setText("Enter a valid stock amount");
                return;
            }
        }
        catch(NumberFormatException e){
            err.setText("Enter a valid stock amount");
            return;
        }
        if(categoryComboBox.getValue() == null){
            err.setText("Select a category");
        }
        SupplierProduct prod = new SupplierProduct(NameField.getText(), Integer.parseInt(priceField.getText()), categoryComboBox.getValue(),Integer.parseInt(stockField.getText()) , supp);
        supp.add_product(prod);
        err.setText("Product added");
    }

}
