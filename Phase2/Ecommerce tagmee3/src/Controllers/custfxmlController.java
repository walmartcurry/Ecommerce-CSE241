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

public class custfxmlController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public void viewProfile (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/myProfile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
        
    

