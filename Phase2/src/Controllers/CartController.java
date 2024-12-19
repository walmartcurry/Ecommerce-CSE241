package Controllers;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import models.Customer;
import models.CustomerProduct;
import models.Database;
import models.Supplier;

public class CartController {
    private static Customer c1 = (Customer)UserSession.getUser();

    @FXML
    private FlowPane cartItemsContainer;
    @FXML
    private Label cartTitle;
    @FXML
    private Button checkoutbtn;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {
        // Set spacing between items in FlowPane
        cartItemsContainer.setHgap(20);
        cartItemsContainer.setVgap(20);
        cartItemsContainer.setPadding(new Insets(20));
        fillCartHolder();
        cartTitle.setText("Cart Items (" + String.valueOf(c1.getOrder().getMyCart().cartItems.size()) + ")");

    }

    @FXML
    void fillCartHolder() {
        cartItemsContainer.getChildren().clear();
        
        for(CustomerProduct product : c1.getOrder().getMyCart().cartItems) {
            VBox productContainer = new VBox(15);
            productContainer.setStyle("-fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-padding: 15; -fx-background-color: white;");
            
            // Product details label
            Label details = new Label(
                "Product name: " + product.getName() + '\n' +
                "Price: " + product.getPrice()
            );
            details.setStyle("-fx-font-size: 14;");
    
            // Quantity controls
            HBox quantityBox = new HBox(10);
            Label quantityLabel = new Label("Quantity: ");
            
            // Create Spinner starting with current product quantity
            Spinner<Integer> quantitySpinner = new Spinner<>(1, 99, product.getQuantity());
            quantitySpinner.setEditable(true);
            quantitySpinner.setPrefWidth(100);
            
            // Update product quantity when spinner value changes
            quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                product.setQuantity(newVal);  // Update product quantity
                updateTotal();  // Update total price
                // Update details label to show new quantity if needed
                details.setText(
                    "Product name: " + product.getName() + '\n' +
                    "Price: " + product.getPrice() + '\n' +
                    "Total: " + (product.getPrice() * newVal)
                );
            });
    
            quantityBox.getChildren().addAll(quantityLabel, quantitySpinner);
    
            Button remove = new Button("Remove");
            remove.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white; -fx-padding: 8 16;");
            
            remove.setOnAction(e -> {
                c1.getOrder().getMyCart().removeFromCart(product);
                cartItemsContainer.getChildren().remove(productContainer);
                cartTitle.setText("Cart Items (" + String.valueOf(c1.getOrder().getMyCart().cartItems.size()) + ")");
                updateTotal();
            });
    
            productContainer.getChildren().addAll(details, quantityBox, remove);
            cartItemsContainer.getChildren().add(productContainer);
        }
        
        updateTotal();
    }

    private void updateTotal() {
        double total = c1.getOrder().getMyCart().calcTotalPrice();
        totalLabel.setText(String.format("%.2f", total));
    }

    @FXML
    void checkoutAction(ActionEvent event) {
        // Implement checkout logic here
    }
}