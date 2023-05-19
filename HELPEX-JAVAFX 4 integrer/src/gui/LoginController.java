/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import help.Help;
import services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mdp;
    @FXML
    private TextField email;
    @FXML
    private Button btnlogin;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Login(ActionEvent event) {
UserService us = new UserService();
        if (email.getText().equals("") || mdp.getText().equals("")) {
            errorMsg.setText("All fields are required!");
        } else if (us.login(email.getText(), mdp.getText())) {
            if (Help.loggedUser.getIs_enabled() == 0) {
                errorMsg.setText("This account is banned!");
                Help.loggedUser = new User();
            } else {
                errorMsg.setText("");
                if (Help.loggedUser.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                        Parent root = loader.load();
                        email.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                        Parent root = loader.load();
                        email.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            errorMsg.setText("Invalid credentials.");
    }
    }

    void setemail(String email) {
        this.email.setText(email);
    }

    @FXML
    private void signupchoose(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpChoose.fxml"));
        try {
            Parent root = loader.load();
            email.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void resetpass(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        try {
            Parent root = loader.load();
            email.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}


