package Controllers;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Admin;
import models.Database;
import models.Supplier;

import java.io.IOException;

public class SupplierRequestController {
 private Admin currentUser;
 private ObservableList<Supplier> observableRequestList = FXCollections.observableArrayList(Database.supplierRequests);

    @FXML
    private TableView<Supplier> requestTable;

    @FXML
    private TableColumn<Supplier, String> supplierColumn;

    @FXML
    private TableColumn<Supplier, String> companyColumn;
    @FXML
    private TableColumn<Supplier, Void> Actions;

    @FXML
    private Button backBtn;

    private void addActionButtonsToTable() {
    Actions.setCellFactory(param -> new TableCell<>() {
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
               
                Button acceptButton = new Button("Accept");
                Button declineButton = new Button("Decline");

               
                acceptButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                declineButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");

                
                acceptButton.setOnAction(event -> {
                    Supplier selectedSupplier = getTableView().getItems().get(getIndex());
                    if (selectedSupplier != null) {
                      Database.suppliers.add(selectedSupplier);
                      Database.supplierRequests.remove(selectedSupplier);
                      observableRequestList.remove(selectedSupplier);
                      requestTable.setItems(observableRequestList);
                      
                    }
                });

                declineButton.setOnAction(event -> {
                    Supplier selectedSupplier = getTableView().getItems().get(getIndex());
                    if (selectedSupplier != null) {
                        Database.supplierRequests.remove(selectedSupplier);
                        observableRequestList.remove(selectedSupplier);
                        requestTable.setItems(observableRequestList);
                    }
                });

                
                HBox buttonContainer = new HBox(5, acceptButton, declineButton);
                setGraphic(buttonContainer);
            }
        }
    });
}


    public void initialize() {
        currentUser= (Admin)UserSession.getUser();
        supplierColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUsername()));

        companyColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getcompName()));

        requestTable.setItems( observableRequestList);
        addActionButtonsToTable();
    }
    
    

    @FXML
    public void backBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}