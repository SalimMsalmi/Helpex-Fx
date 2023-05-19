/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Filiere;
import services.FiliereService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
public class AddFController implements Initializable {

    @FXML
    private TextField nf;
    @FXML
    private TextField df;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         
        FiliereService us = new FiliereService();
        String nom= nf.getText() ;
     String desc= df.getText() ;
 
            Filiere u = new Filiere(nom,desc);
            
           us.ajouterF(u);
          
    
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Fil.fxml")) ;
              try {
                  Parent root= loader.load();
                  
                  nf.getScene().setRoot(root);
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
        }
        
    
    
}
