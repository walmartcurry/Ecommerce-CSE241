package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;

public class ECommerceController implements Initializable {
    @FXML
    private FlowPane productGrid;
    
    @FXML
    private Button butt1;

    @FXML
    private Button butt2;

    @FXML
    private Button butt3;

    @FXML
    private Button butt4;

    @FXML
    private Button butt5;

    @FXML
    private Button butt6;

    @FXML
    private Button butt7;

    @FXML
    private Button butt8;

    @FXML
    private Button butt9;

    @FXML
    private ComboBox<?> buttaddress;

    @FXML
    private Button buttcart;

    @FXML
    private Button buttlogin;

    @FXML
    private Button buttwishlist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProducts();
    }
    
    private void loadProducts() {
        // Product data: name, price, original price, discount, rating, reviews, image
        String[][] products = {
            {"Samsung Galaxy A15", "9,499", "10,999", "13", "4.5", "1.0K", "phone1.jpeg"},
            {"Samsung Galaxy A15", "6,999", "8,699", "19", "4.4", "5.2K", "phone2.jpeg"},
            {"Infinix Smart 8", "4,555", "4,999", "8", "4.3", "229", "phone1.jpeg"}
        };
        
        for (String[] product : products) {
            productGrid.getChildren().add(createProductCard(product));
        }
    }
    
    private VBox createProductCard(String[] productData) {
        VBox card = new VBox(10);
        card.getStyleClass().add("product-card");
        
        HBox topRow = new HBox(10);
        Label warranty = new Label("Official Warranty");
        warranty.getStyleClass().add("warranty-badge");
        Button wishlist = new Button("♡");
        wishlist.getStyleClass().add("wishlist-button");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topRow.getChildren().addAll(warranty, spacer, wishlist);
        
        ImageView productImage = new ImageView();
        try {
            String imagePath = "/application/images/" + productData[6]; // Get image path from data
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            productImage.setImage(image);
            productImage.setFitWidth(250);
            productImage.setFitHeight(250);
            productImage.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Error loading image: " + productData[6]);
            e.printStackTrace();
        }
        
        // Rating Row
        HBox ratingRow = new HBox(5);
        Label rating = new Label(productData[4]);
        rating.getStyleClass().add("rating");
        Label star = new Label("★");
        star.getStyleClass().add("rating-star");
        Label reviews = new Label("(" + productData[5] + ")");
        reviews.getStyleClass().add("rating-count");
        ratingRow.getChildren().addAll(rating, star, reviews);
        
        Label title = new Label(productData[0]);
        title.getStyleClass().add("product-title");
        title.setWrapText(true);
        
        HBox priceRow = new HBox(10);
        Label price = new Label("EGP " + productData[1]);
        price.getStyleClass().add("price");
        Label originalPrice = new Label("EGP " + productData[2]);
        originalPrice.getStyleClass().add("original-price");
        Label discount = new Label(productData[3] + "%");
        discount.getStyleClass().add("discount");
        priceRow.getChildren().addAll(price, originalPrice, discount);
        
        Label express = new Label("express");
        express.getStyleClass().add("express-badge");
        
        card.getChildren().addAll(topRow, productImage, ratingRow, title, priceRow, express);
        return card;
    }
}