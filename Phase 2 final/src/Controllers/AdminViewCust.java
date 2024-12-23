package Controllers;

import java.io.IOError;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Admin;
import models.Customer;
import models.Database;

public class AdminViewCust {

    @FXML
    private TableView<Customer> DataTable;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Customer, Double> balance;

    @FXML
    private TableColumn<Customer, String> password;

    @FXML
    private TableColumn<Customer, String> username;

    @FXML
    void backBtnAction(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void initialize(){
        ObservableList<Customer> observableCustomersList = FXCollections.observableArrayList(Database.users);
        DataTable.setItems(observableCustomersList);
        username.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("username"));
        password.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("password"));
        address.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("address"));
        balance.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("balance"));
    }

}
