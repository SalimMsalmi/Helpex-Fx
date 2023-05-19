/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Poste;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import services.CRUDPoste;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterPosteController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextArea Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
          String titre;
        String description;
        titre = Titre.getText();
        description = Description.getText();
        if (titre.isEmpty() || !titre.matches("^[a-zA-Z0-9\\s]+$")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Titre invalide");
        alert.setContentText("Le titre ne doit pas être vide et doit contenir uniquement des lettres, des chiffres et des espaces.");
        alert.showAndWait();
         return;
        }
        if (description.isEmpty() || description.length() > 100) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Description invalide");
        alert.setContentText("La description ne doit pas être vide et ne doit pas dépasser 500 caractères.");
        alert.showAndWait();
        return;
        }
        if (titre.isEmpty() || description.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champs manquants");
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        } else {
        Poste p = new Poste(titre, description);
        CRUDPoste t = new CRUDPoste();
        t.ajouterPoste(p);
       
         String title = "Poste added";
    String message = "The Poste"+Titre.getText()+" has been added successfully.";
    NotificationType notificationType = NotificationType.SUCCESS;
    TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
    trayNotification.showAndDismiss(Duration.seconds(5));
    
}
    }
    
}
