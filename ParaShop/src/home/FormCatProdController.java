/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.CategorieProduit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import services.CrudCategorieProduit;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class FormCatProdController implements Initializable {

    @FXML
    public TextField nom_cat_produit;

    /**
     * Initializes the controller class.
     */
    
     private CategorieProduit c;
     
     public CategorieProduit getC() {
        return c;
    }
    
     
     public void setC(CategorieProduit c) {
        this.c = c;
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddCategorieProduit(ActionEvent event) {
        
        CrudCategorieProduit CP = new CrudCategorieProduit();
        if (CP.isCategoryTaken(nom_cat_produit.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Categorie existe deja");
                alert.showAndWait();
        }
        else{
            String NouveauCategorieProduit = nom_cat_produit.getText();
        CategorieProduit c = new CategorieProduit(NouveauCategorieProduit);
         CP.addCategorie(c);
         String title = "Categorie  added";
        String message = "The Categorie "+nom_cat_produit.getText()+" has been added successfully.";
            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
        trayNotification.showAndDismiss(Duration.seconds(5));
        }
        
       
    }

    @FXML
    private void modifierCategorieProduit(ActionEvent event) {
        
        String NouveauCategorieProduit = nom_cat_produit.getText();
        CrudCategorieProduit CP = new CrudCategorieProduit();
        CP.updateCategorie(c);
        
        
    }
    
}
