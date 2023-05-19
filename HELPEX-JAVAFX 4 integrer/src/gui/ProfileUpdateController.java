/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import help.Helpex;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ProfileUpdateController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField num;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField bio;
    @FXML
    private TextField adresse;
    @FXML
    private TextField pdp;
    @FXML
    private TextField tarif;

    /**
     * Initializes the controller class.
     */
    
     public void setInfos() {
         
          this.nom.setText(Helpex.loggedUser.getNom());
        this.prenom.setText(Helpex.loggedUser.getPrenom());
        this.adresse.setText(Helpex.loggedUser.getAdresse());
                this.num.setText(Helpex.loggedUser.getNum_tel());
        this.pdp.setText(Helpex.loggedUser.getPdp());
        this.bio.setText(Helpex.loggedUser.getBio());
                this.bio.setText(Helpex.loggedUser.getBio());


      /*Helpex.loggedUser.setNom(nom.getText());
  Helpex.loggedUser.setPrenom(prenom.getText());
 Helpex.loggedUser.setAdresse(adresse.getText());
 Helpex.loggedUser.setNum_tel(num.getText());
 Helpex.loggedUser.setPdp(pdp.getText());
  Helpex.loggedUser.setPdp(bio.getText());
 Helpex.loggedUser.setTarif(Integer.parseInt(tarif.getText()));*/
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setInfos();
     
    }    

    @FXML
    private void updateprofile(ActionEvent event) {
        
        
          Helpex.loggedUser.setNom(nom.getText());
  Helpex.loggedUser.setPrenom(prenom.getText());
 Helpex.loggedUser.setAdresse(adresse.getText());
 Helpex.loggedUser.setNum_tel(num.getText());
 Helpex.loggedUser.setPdp(pdp.getText());
  Helpex.loggedUser.setBio(bio.getText());
 //Help.loggedUser.setTarif(Integer.parseInt(tarif.getText()));
        
        
        
                    UserService us = new UserService();
 

                    us.updateProfile(Helpex.loggedUser);
        
      
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Profile.fxml"));
      
        try {
            Parent homeNode = homeLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
           
      
    
    }    

    @FXML
    private void signout(ActionEvent event) {
          Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.bio.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void profile(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent root = loader.load();
                this.bio.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
