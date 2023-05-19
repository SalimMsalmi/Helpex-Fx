/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemUserController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label email;
    @FXML
    private Button delete;
    @FXML
    private Label sexe;
    @FXML
    private Label adresse;
    @FXML
    private Label num_tel;
    @FXML
    private Label enabled;
    @FXML
    private Button disable;
    @FXML
    private Button enable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) {
      User P=new User();
        P.setEmail(email.getText());
         UserService t= new UserService();
        try {
            t.deleteByEmail(P.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(ItemUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void disable(ActionEvent event) {
            System.out.println("Disable button clicked!");

         User P=new User();
          P.setEmail(email.getText());
    UserService t = new UserService();
    t.DisableEnable(P);
    }

    @FXML
    private void enable(ActionEvent event) {
         System.out.println("enable button clicked!");

         User P=new User();
          P.setEmail(email.getText());
    UserService t = new UserService();
    t.Enable(P); 
    }

  

   
    
}
