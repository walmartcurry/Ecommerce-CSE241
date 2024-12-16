package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class FXMLDocumentController {

    // FXML Fields for Login
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label lbl_feedback_login;

    // FXML Fields for SignUp
    @FXML
    private TextField txt_username_up;
    @FXML
    private PasswordField txt_password_up;
    @FXML
    private ComboBox<String> type_up;
    @FXML
    private Label lbl_feedback_signup;

    // Panels
    @FXML
    private AnchorPane pane_login;
    @FXML
    private AnchorPane pane_signup;

    // Registered users list for SignUp
    private final List<User> registeredUsers = new ArrayList<>();

    // Initialize method to set up ComboBox options and clear feedback labels
    @FXML
    public void initialize() {
        // Populate ComboBoxes with user types
        type.getItems().addAll("Admin", "User");
        type_up.getItems().addAll("Admin", "User");

        // Clear feedback labels
        lbl_feedback_login.setText("");
        lbl_feedback_signup.setText("");
    }

    // Switch to Login Panel
    @FXML
    private void LoginpaneShow(ActionEvent event) {
        pane_signup.setVisible(false); // Hide SignUp panel
        pane_login.setVisible(true);  // Show Login panel

        // Reset feedback and input fields
        lbl_feedback_login.setText("");
        txt_username.clear();
        txt_password.clear();
        type.setValue(null); // Reset ComboBox
    }

    // Switch to SignUp Panel
    @FXML
    private void SignuppaneShow(ActionEvent event) {
        pane_login.setVisible(false); // Hide Login panel
        pane_signup.setVisible(true); // Show SignUp panel

        // Reset feedback and input fields
        lbl_feedback_signup.setText("");
        txt_username_up.clear();
        txt_password_up.clear();
        type_up.setValue(null); // Reset ComboBox
    }

    // Handle Login
    @FXML
    private void Login(ActionEvent event) {
        // Get values from input fields
        String username = txt_username.getText();
        String password = txt_password.getText();
        String userType = type.getValue();

        // Validate that all fields are filled
        if (username.isEmpty() || password.isEmpty() || userType == null) {
            lbl_feedback_login.setText("Please fill in all fields to login.");
            lbl_feedback_login.setStyle("-fx-text-fill: red;");
            return;
        }

        // Check credentials against registered users
        boolean found = registeredUsers.stream().anyMatch(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password) && user.getType().equals(userType));

        if (found) {
            lbl_feedback_login.setText("Login successful!");
            lbl_feedback_login.setStyle("-fx-text-fill: green;");

            // Clear input fields after successful login
            txt_username.clear();
            txt_password.clear();
            type.setValue(null);
        } else {
            lbl_feedback_login.setText("Invalid credentials. Please try again.");
            lbl_feedback_login.setStyle("-fx-text-fill: red;");
        }
    }

    // Handle SignUp
    @FXML
    private void SignUp(ActionEvent event) {
        // Get values from input fields
        String username = txt_username_up.getText();
        String password = txt_password_up.getText();
        String userType = type_up.getValue();

        // Validate that all fields are filled
        if (username.isEmpty() || password.isEmpty() || userType == null) {
            lbl_feedback_signup.setText("Please fill in all fields to register.");
            lbl_feedback_signup.setStyle("-fx-text-fill: red;");
            return;
        }

        // Check if username already exists
        boolean exists = registeredUsers.stream()
                .anyMatch(user -> user.getUsername().equals(username));

        if (exists) {
            lbl_feedback_signup.setText("Username already exists! Choose a different username.");
            lbl_feedback_signup.setStyle("-fx-text-fill: red;");
            return;
        }

        // Add new user to the registered users list
        registeredUsers.add(new User(username, password, userType));
        lbl_feedback_signup.setText("SignUp successful! You can now login.");
        lbl_feedback_signup.setStyle("-fx-text-fill: green;");

        // Clear input fields after successful registration
        txt_username_up.clear();
        txt_password_up.clear();
        type_up.setValue(null);
    }

    // Internal User class for storing user information
    private static class User {
        private final String username;
        private final String password;
        private final String type;

        public User(String username, String password, String type) {
            this.username = username;
            this.password = password;
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getType() {
            return type;
        }
    }
}