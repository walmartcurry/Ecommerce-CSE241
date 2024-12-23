package Controllers;
import java.io.IO;
import java.io.IOException;
import models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class custfxmlController {
    private  Customer user ;
    private Stage stage;
    private Scene scene;
    private Parent root;
// public static void setUser(Customer inUser) {
//     user = (Customer)UserSession.getUser();
// }
    @FXML
    private Button accid;
    

    @FXML
    private Button addiid;

    @FXML
    private ImageView custid;

    @FXML
    private Label dashid;

    @FXML
    private Button logout;

    @FXML
    private Button mycartid;

    @FXML
    private Button pastordersid;

    @FXML
    private ImageView stackshopid;

    @FXML
    private Button viewprodid;

    @FXML
    private Button viewsuppid;
    public void initialize(){
       user = (Customer)UserSession.getUser();
    }
    public void viewProfile (ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myProfile.fxml"));
       root = loader.load();
       myProfile Controller =  loader.getController();
       Controller.populateFields();
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void myCartaction (ActionEvent event) throws IOException{
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/myCart.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void viewProd(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void viewOrders(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewOrders.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void addIntrest(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Addintrests.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void logout(ActionEvent event) throws IOException{
        UserSession.setUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginPage.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
        
    

