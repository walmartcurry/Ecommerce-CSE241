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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Supplier;

public class suppfxmlController {
    private Supplier supp;

    @FXML
    private Button accid;

    @FXML
    private Button addprodid;

    @FXML
    private Label commnameid;

    @FXML
    private Label dashboardid;

    @FXML
    private ImageView logoid;

    @FXML
    private Button logoutid;

    @FXML
    private ImageView ssid;

    @FXML
    private Button viewprodid;
    public void initialize(){
        supp = SuppSession.getUser();
    }
    public void viewProd(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/suppViewProducts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addProd(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/addproduct.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void back(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/views/welcomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
