package Controllers;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Admin;
import models.Customer;
import models.Database;
import models.Order;
import models.Order.PaymentMethods;
import models.Person;

public class viewOrderController {
    private Person user = UserSession.getUser();
    
    @FXML
    private TableColumn<Order, String> AddressCol;
    
    @FXML
    private TableView<Order> OrdersTable;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private TableColumn<Order, Integer> orderCol;
    
    @FXML
    private TableColumn<Order, String> statusCol;
    
    @FXML
    private TableColumn<Order, Double> totalCol;
    
    @FXML
    private TableColumn<Order, String> paymentMethodCol;
    
    @FXML
    private TableColumn<Order, String> CustomerUsername;

    public void initialize() {
        AddressCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("shippingAddress"));
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("orderStatus"));
        
        paymentMethodCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPaymentMethod())
        );
        
        // Add CustomerUsername column initialization for admin view
        if(user instanceof Admin) {
            CustomerUsername.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCustomerName())
            );
        }
        else{
            CustomerUsername.setVisible(false);
        }
        
        // Load orders into the table
        loadOrders();
    }
    
    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/admindash.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void loadOrders(){
        if(user instanceof Admin){
            ObservableList<Order> observableAdminList = FXCollections.observableArrayList(Database.orders);
            OrdersTable.setItems(observableAdminList);
            
            orderCol.setCellValueFactory(cellData -> {
                int index = Database.orders.indexOf(cellData.getValue()) + 1; // +1 for 1-based index
                return new javafx.beans.property.SimpleIntegerProperty(index).asObject();
            });
            
            totalCol.setCellValueFactory(cellData -> {
                Order order = cellData.getValue();
                double totalPrice = order.getMyCart().calcTotalPrice();
                return new javafx.beans.property.SimpleDoubleProperty(totalPrice).asObject();
            });
        }
        else{
            ArrayList<Order> custOrders = new ArrayList<Order>();
            for (Order order : Database.orders){
                if(order.getCustomerName().equals(user.getUsername()))
                    custOrders.add(order);
            }
            
            ObservableList<Order> observableCustomerList = FXCollections.observableArrayList(custOrders);
            OrdersTable.setItems(observableCustomerList);
            
            orderCol.setCellValueFactory(cellData -> {
                int index = Database.orders.indexOf(cellData.getValue()) + 1; // +1 for 1-based index
                return new javafx.beans.property.SimpleIntegerProperty(index).asObject();
            });
            
            totalCol.setCellValueFactory(cellData -> {
                Order order = cellData.getValue();
                double totalPrice = order.getMyCart().calcTotalPrice();
                return new javafx.beans.property.SimpleDoubleProperty(totalPrice).asObject();
            });
        }
    }
}
