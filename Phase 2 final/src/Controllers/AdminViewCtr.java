package Controllers;

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
import models.Database;
import models.Order;

public class AdminViewCtr {

    @FXML
    private TableView<Admin> DataTable;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Admin, String> password;

    @FXML
    private TableColumn<Admin, String> role;

    @FXML
    private TableColumn<Admin, String> username;

    @FXML
    private TableColumn<Admin, String> working;

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
    public void initialize(){
        ObservableList<Admin> observableAdminList = FXCollections.observableArrayList(Database.admins);
        DataTable.setItems(observableAdminList);
        username.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("username"));
        password.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("password"));
        working.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("working_hours"));
        role.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("Role"));
        
    }

}
