package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SupplierProduct;
import models.Database;
import models.Supplier;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class ProductEditController {

    @FXML
    private TableView<SupplierProduct> productTable;

    @FXML
    private TableColumn<SupplierProduct, String> nameColumn;

    @FXML
    private TableColumn<SupplierProduct, Double> priceColumn;

    @FXML
    private TableColumn<SupplierProduct, Integer> stockColumn;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    @FXML
    private Button editPriceButton;

    @FXML
    private Button editStockButton;

    @FXML
    private Label errLabel;

    @FXML
    private Button backBtn;

    private SupplierProduct selectedProduct;

    private  Supplier user ;
    private ObservableList<SupplierProduct> observableProductsList ;
    public void initialize() {
        user = SuppSession.getUser();
        if (user != null) {
           observableProductsList = FXCollections.observableArrayList(user.getProducts());
            productTable.setItems(observableProductsList);
        }
        nameColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("stock"));
       

        priceField.setVisible(false);
        stockField.setVisible(false);
        editPriceButton.setVisible(false);
        editStockButton.setVisible(false);
    }

    @FXML
    private void onProductSelected() {
        selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            priceField.setText(String.valueOf(selectedProduct.getPrice()));
            stockField.setText(String.valueOf(selectedProduct.getStock()));
            priceField.setVisible(true);
            stockField.setVisible(true);
            editPriceButton.setVisible(true);
            editStockButton.setVisible(true);
        }
    }

    @FXML
    private void editPrice() {
        try {
            double newPrice = Double.parseDouble(priceField.getText());

            if (newPrice > 0) {
                selectedProduct.setPrice(newPrice);
                productTable.refresh();
            } else {
                errLabel.setText("Enter valid price");
            }
        } catch (NumberFormatException e) {
            errLabel.setText("Enter valid price");
        }
    }

    @FXML
    private void editStock() {
        try {
            int newStock = Integer.parseInt(stockField.getText());
            if (newStock >= 0) {
                selectedProduct.setStock(newStock);
                productTable.refresh();
            } else {
               errLabel.setText("Enter valid stock");
            }
        } catch (NumberFormatException e) {
            errLabel.setText("Enter valid stock");
        }
    }

    @FXML
    public void backBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/suppfxml.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}