package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Views.*; // Adjust the import to match your project structure
import models.*; // Adjust the import to match your project structure
import Controllers.*; // Adjust the import to match your project structure

import java.io.IOException;

import javax.print.attribute.standard.MediaSize.NA;

public class App extends Application {
    public Customer c1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        c1 = new Customer("Amir", "AmirtheGoat", 2, 7, 2005, "male", "Gardenia city");
        c1.setBalance(1000);
        Supplier Nada = new Supplier("NADA", "NADA", "Nada dairy and more");
        Supplier almara3y = new Supplier("almara3y", "almara3y","AlMara3y");
        c1.addIntrests("Electronics & Devices");
        c1.addIntrests("Clothing & Fashion");
        Database.users.add(c1);
        SupplierProduct product1 = new SupplierProduct("Protien shake", 120, "Electronics & Devices", 10, Nada);
        SupplierProduct product2 = new SupplierProduct("greek yogurt", 170, "Clothing & Fashion", 10, Nada);
        CustomerProduct product = new CustomerProduct(product1);
        CustomerProduct product3 = new CustomerProduct(product2);
        c1.getOrder().getMyCart().cartItems.add(product3);
        c1.getOrder().getMyCart().cartItems.add(product);
        Database.orders.add(c1.getOrder());
        Nada.add_product(product2);
        Nada.add_product(product1);
        Database.suppliers.add(Nada);
        Database.supplierRequests.add(almara3y);
        Admin Ceo = new Admin("beso","beso",10,10,2002,"Ceo","9-12");
        Database.admins.add(Ceo); 

        Welcome();

    }
    //Login... (Choice of admin or customer )
    //SignUp (Page either for supplier or customer)

    // private void showProfile() {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myProfile.fxml"));
    //         Pane root = loader.load();
    //         myProfile profileController = loader.getController();
    //         profileController.setUser(c1); 
    //         profileController.populateFields();
    //         Stage profileStage = new Stage();
    //         profileStage.setScene(new Scene(root));
    //         profileStage.setTitle("My Profile");
    //         profileStage.show();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    // private void CustomerDash(){
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/custfxml.fxml"));
    //         Pane root = loader.load();
    //         Stage profileStage = new Stage();
    //         profileStage.setScene(new Scene(root));
    //         profileStage.setTitle("Dashboard");
    //         profileStage.show();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }


    // }
    private void viewProducts() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScene.fxml"));
        Pane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Products");
        profileStage.show();

    }
    private void Login() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginPage.fxml"));
        AnchorPane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Sign Up");
        profileStage.show();

    }
    private void Welcome() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/welcomePage.fxml"));
        AnchorPane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Welcome");
        profileStage.show();

    }
    private void myCart() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myCart.fxml"));
        Pane root = loader.load();
        Stage profileStage = new Stage();
        profileStage.setScene(new Scene(root));
        profileStage.setTitle("Sign Up");
        profileStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
