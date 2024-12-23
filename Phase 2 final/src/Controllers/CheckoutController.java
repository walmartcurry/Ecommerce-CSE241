package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import models.Order;
import models.PromoCode;

public class CheckoutController {
    private Customer user = (Customer)UserSession.getUser();

    @FXML
    private Button applyGiftCodeBtn;

    @FXML
    private RadioButton codOption;

    @FXML
    private RadioButton codOption1;

    @FXML
    private TextField giftCodeField;

    @FXML
    private Label headerLabel;

    @FXML
    private Label itemsLabel;

    @FXML
    private Label orderTotalLabel;

    @FXML Label balanceLabel;

    @FXML
    private Label paymentMethodLabel;
    private PromoCode promo;

    @FXML
    private Label error;

    @FXML
    private Button PlaceOrder;
    @FXML
    private Button backBtn;

    public void backAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myCart.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void initialize(){
        orderTotalLabel.setText(String.valueOf(user.getOrder().getMyCart().calcTotalPrice()));
        itemsLabel.setText(String.valueOf(user.getOrder().getMyCart().cartItems.size()));
        balanceLabel.setText("Your available balance : " + user.getBalance());
    }
    public void applyDiscount(ActionEvent event){
        error.setText("");
        String promoCode = giftCodeField.getText();
        promo = Order.promoCodeRepo.get(promoCode);
        if(promo == null){
            error.setText("Promo Code doesnt exist");
            return;
        }
        if(!promo.isValid(user.getOrder().getMyCart().calcTotalPrice())){
            error.setText("Promo Code in not applicable for you");
            return;
        }
        user.getOrder().applyPromoCode(promoCode);
        user.getOrder().getMyCart().calcTotalPrice();
        orderTotalLabel.setText(String.valueOf(user.getOrder().getMyCart().getafterDiscount(promo.getDiscount())));
        applyGiftCodeBtn.setDisable(true);
       
    }
    public void placeOrder(ActionEvent event) throws IOException{
        error.setText("");
        if(!codOption.isSelected() && !codOption1.isSelected()){
            error.setText("Select a payment method");
            return;
        }
        if(codOption.isSelected() && codOption1.isSelected()){
            error.setText("Select a payment method");
            return;
        }
        if(codOption.isSelected()){
          
            user.finaliseOrder("cash");
        }
        else{
            if(giftCodeField.getText().equals("")){
                if(user.getBalance() < user.getOrder().getMyCart().calcTotalPrice()){
                    error.setText("Insufficient funds");
                    return;
                }
            }
            if(!giftCodeField.getText().equals("")){
                if(user.getBalance() < user.getOrder().getMyCart().getafterDiscount(promo.getDiscount())){
                    error.setText("Insufficient funds");
                    return;
                }
            }
          
            user.finaliseOrder("balance");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        Parent root = FXMLLoader.load(getClass().getResource("/views/placed.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
