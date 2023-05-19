/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.User;
import help.Help;
import services.UserService;
import utils.JavaMail;
import java.util.Random;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.util.Duration;



/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private VBox sendEmailLayer;
    @FXML
    private TextField emailField;
    @FXML
    private Label emailNotFoundErr;
    @FXML
    private VBox sendCodeLayer;
    @FXML
    private TextField code;
    @FXML
    private Label wrongCodeErr;
    @FXML
    private VBox resetPassLayer;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Label errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 sendEmailLayer.setVisible(true);
        sendCodeLayer.setVisible(false);
        resetPassLayer.setVisible(false);    }    

    @FXML
    private void sendEmail(ActionEvent event) {
         UserService us = new UserService();
        if (us.isEmailTaken(emailField.getText())) {
            emailNotFoundErr.setVisible(false);
            //generate code and put it in the database
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            String code = String.format("%06d", number);
            us.setCode(code, emailField.getText());
            try {
                //send email to emailField.getText()
                JavaMail.sendMail(emailField.getText(), code);
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
            String title = "Email sent";
            String message = "User "+emailField.getText()+" has received mail successfully.";
    NotificationType notificationType = NotificationType.SUCCESS;
    TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
    trayNotification.showAndDismiss(Duration.seconds(5));
           
    
//make the layer invisble and show the code layer
            sendEmailLayer.setVisible(false);
            sendCodeLayer.setVisible(true);
            resetPassLayer.setVisible(false);
        } else {
            emailNotFoundErr.setVisible(true);
        }
    }

    @FXML
    private void submitCode(ActionEvent event) {
         try {
            UserService us = new UserService();
            if (us.getCode(emailField.getText()).equals(code.getText())) {
                wrongCodeErr.setVisible(false);
                sendEmailLayer.setVisible(false);
                sendCodeLayer.setVisible(false);
                resetPassLayer.setVisible(true);
            } else {
                wrongCodeErr.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resendCode(ActionEvent event) {
         sendEmailLayer.setVisible(true);
        sendCodeLayer.setVisible(false);
        resetPassLayer.setVisible(false);
    }

    @FXML
    private void changePass(ActionEvent event) {
        
        UserService us = new UserService();
        if (passwordField.getText().length() < 6) {
            errorMessage.setText("Password is too weak!");
        } else if (!passwordField.getText().equals(confirmPassField.getText())) {
            errorMessage.setText("Passwords does not match!");
        } else {
            try {
                us.updatePassByEmail(emailField.getText(), passwordField.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                emailField.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void back(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            Parent root = loader.load();
            emailField.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
        
    
    
}
