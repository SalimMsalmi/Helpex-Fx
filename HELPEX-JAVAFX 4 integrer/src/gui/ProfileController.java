/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.User;

import help.Help;
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
import javafx.scene.image.Image;


/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ProfileController implements Initializable {

    @FXML
    private Label email;
    @FXML
    private Label bio;
    @FXML
    private Button updateBtn;
    @FXML
    private Label num;
    @FXML
    private Label adresse;

    /**
     * Initializes the controller class.
     */
    
     public void setInfos() {
        this.email.setText(Help.loggedUser.getEmail());
        this.bio.setText(Help.loggedUser.getBio());
          this.num.setText(Help.loggedUser.getNum_tel());
       // this.adresse.setText(Help.loggedUser.getAdresse());
        this.adresse.setText(String.valueOf(Help.loggedUser.getId()));

      /*  this.prenom.setText(Helpex.loggedUser.getPrenom());
        this.nom.setText(Helpex.loggedUser.getNom());
        this.bio.setText(Helpex.loggedUser.getBio());*/
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          this.setInfos();    }    

    @FXML
    private void updateprofile(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUpdate.fxml"));
        try {
            Parent root = loader.load();
            email.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void signout(ActionEvent event) {
         Help.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.email.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
