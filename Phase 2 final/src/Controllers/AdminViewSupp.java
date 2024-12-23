package Controllers;

import java.io.IOException;
import java.util.function.Supplier;

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
import models.*;

public class AdminViewSupp {

    @FXML
    private TableView<models.Supplier> DataTable;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<models.Supplier, String> compName;

    @FXML
    private TableColumn<models.Supplier, String> password;

    @FXML
    private TableColumn<models.Supplier, String> username;

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void initialize(){
        ObservableList<models.Supplier> observableSuppList = FXCollections.observableArrayList(Database.suppliers);
        DataTable.setItems(observableSuppList);
        username.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("username"));
        password.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("password"));
        compName.setCellValueFactory(cellData -> {
            String comp =  cellData.getValue().getcompName();
            return new javafx.beans.property.SimpleStringProperty(comp);
        });
    }

}
