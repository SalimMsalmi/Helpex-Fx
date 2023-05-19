/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieProduit;
import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import services.CrudCategorieProduit;
import services.CrudProduits;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class FormProduitController implements Initializable {

    public TextField nom_cat_produit;
    @FXML
    public ChoiceBox<String> CatProduitChoice;
    @FXML
    public ChoiceBox<String> EtatProdChoices;
    @FXML
    public Button SaveProduit;

    ObservableList<String> CategorieListObservable = FXCollections.observableArrayList();
    
    ObservableList<String> EtatProduitListObservable = FXCollections.observableArrayList("New","Use-New","Good","Broken");
    /**
     * Initializes the controller class.
     */
    
    CategorieProduit Categorie = new CategorieProduit();
    String CategorieName ,Etat; 
    @FXML
    public TextField nom_produit;
    @FXML
    public TextField prix_produit;
    @FXML
    public Label id_prod;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudCategorieProduit CrudCategorie = new CrudCategorieProduit();
         //CrudProduits productCrud = new CrudProduits();
        List<CategorieProduit> CategoriesList;
        CategoriesList = CrudCategorie.getAllCategories();
         for (CategorieProduit Cat : CategoriesList)
             CategorieListObservable.add(Cat.getNom_cat_produit());
         
                 CatProduitChoice.getItems().addAll(CategorieListObservable);
                 EtatProdChoices.getItems().addAll(EtatProduitListObservable);
             
                 //Initializing liste deroulante with first List value 
        CatProduitChoice.setValue(CategorieListObservable.get(0));
        EtatProdChoices.setValue(EtatProduitListObservable.get(0));
       
//if the user dont touch the liste deroulant ( dont choose something , 
       //choose default so i dont need to wait for his action ) 
        CategorieName = CatProduitChoice.getValue();
        Etat = EtatProdChoices.getValue();
        
        //update selon user's action / choice 
        CatProduitChoice.setOnAction(this::getCatChoice);
        EtatProdChoices.setOnAction(this::getEtatChoice);
        
        
        prix_produit.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("\\d*(\\.\\d*)?")) {
        prix_produit.setText(oldValue);
    }
});
        
    }    
    
    private void getCatChoice (ActionEvent event) {
        //System.out.println(CatProduitChoice.getValue());
        /*CrudCategorieProduit CrudCategorie = new CrudCategorieProduit();
        
        Categorie = CrudCategorie.getByNomCategorie(CatProduitChoice.getValue());*/
        
        CategorieName = CatProduitChoice.getValue();

    }
    
       private void getEtatChoice (ActionEvent event) {
        
        Etat = EtatProdChoices.getValue();
    }
    

    @FXML
    private void AddProduit(ActionEvent event) throws SQLException {
        
        CrudProduits P = new CrudProduits();
        CrudCategorieProduit C = new CrudCategorieProduit();
        boolean ControlSaisie = true ; 
            // Validate inputs
            if (nom_produit.getText().isEmpty() && prix_produit.getText().isEmpty()) {
        // Show error message for empty nom_produit && prix_produit
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Product Name and Price ");
        alert.setContentText("Please Valid Inputs.");
        alert.showAndWait();
        ControlSaisie = false;
    }else {
                if (nom_produit.getText().isEmpty()) {
        // Show error message for empty nom_produit 
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Product Name");
        alert.setContentText("Please enter a name for the product.");
        alert.showAndWait();
       ControlSaisie = false;
    }else   if (nom_produit.getText().length() >= 50) {
        // Show error message for nom_produit too long
                Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Product Name Too Long");
        alert.setContentText("Please enter a name for the product that is less than 50 characters.");
        alert.showAndWait();

        ControlSaisie = false;
    }
                
    if (prix_produit.getText().isEmpty() ){
            Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Product Price");
        alert.setContentText("Please enter a Price for the product.");
        alert.showAndWait();
        ControlSaisie = false;
    }else 
  
    
    if ( Double.parseDouble(prix_produit.getText()) <= 0) {
        // Show error message for negative or empty prix_produit
                Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Product Price");
        alert.setContentText("Please enter a valid, non-negative price for the product.");
        alert.showAndWait();
        ControlSaisie = false;
    }
            }
    
        if (ControlSaisie == true ){
                    String NomProduit = nom_produit.getText();
        String PrixProduit = prix_produit.getText();
        if (Integer.parseInt(id_prod.getText())>0)
        {
            System.out.println(Integer.parseInt(id_prod.getText()));
            Produit Product = new Produit(Integer.parseInt(id_prod.getText()),C.getByNomCategorie(CategorieName), NomProduit, Etat, PrixProduit, true);
            if (P.updateProduitFront(Product))
            {
                //Notifier Modifier
        String title = "Product  Updated";
        String message = "The Product "+nom_produit.getText()+" has been Updated successfully.";
            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
        trayNotification.showAndDismiss(Duration.seconds(5));
            }
            
            
        
        }
        else {
             Produit Product = new Produit(C.getByNomCategorie(CategorieName), NomProduit, Etat, PrixProduit, true);
        System.out.println(C.getByNomCategorie(CategorieName));
        P.addProduit(Product, C.getByNomCategorie(CategorieName));
        
        
        //Notifier
        String title = "Product  added";
        String message = "The Product "+nom_produit.getText()+" has been added successfully.";
            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
        trayNotification.showAndDismiss(Duration.seconds(5));
        }
            
       
        }

        
    }
    
}
