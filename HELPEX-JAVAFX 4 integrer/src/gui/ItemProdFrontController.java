/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import entities.Produit;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CrudProduits;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ItemProdFrontController implements Initializable {

    @FXML
    private HBox itemP;
    @FXML
    private Label nom_produit;
    @FXML
    private Label id_produit;
    @FXML
    private Label etat_produit;
    @FXML
    private Label PrixProduit;
    @FXML
    private Label Created_At;
    @FXML
    private Label Updated_At;
    @FXML
    private Label Authorisation;
    @FXML
    private Button supprimer_btn;
    @FXML
    private Button button_modifier_centre;
    @FXML
    private Label categorie_produit;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerBtn(ActionEvent event) {
        
        Produit p =new Produit();
        System.out.println(Integer.parseInt(id_produit.getText()));
            p.setId(Integer.parseInt(id_produit.getText()));
                CrudProduits crudproduit=new CrudProduits();
            crudproduit.deleteProduite(p.getId());
           // refresh();
           //ProduitFrontController.refresh = 1 ; 
        
    }

    @FXML
    private void modifierProduitBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormProduit.fxml"));
        AnchorPane newInterface = loader.load();
        FormProduitController newInterfaceController = loader.getController();
        
        Produit Produit = new Produit();
        
        Stage stage = new Stage();
        
        
        
        String nom_prod =nom_produit.getText();
        String PrixProd =PrixProduit.getText();
        String etat_prod =etat_produit.getText();
        String categorie_prod =categorie_produit.getText();
        
        newInterfaceController.nom_produit.setText(nom_prod);
        newInterfaceController.prix_produit.setText(PrixProd);
        newInterfaceController.CatProduitChoice.setValue(categorie_prod);
        newInterfaceController.EtatProdChoices.setValue(etat_prod);
        
        newInterfaceController.id_prod.setText(id_produit.getText());
        
        Produit.setId(Integer.parseInt(id_produit.getText()));
        System.out.println(Produit.getId());
            //newInterfaceController.set(Produit);
            stage.setScene(new Scene(newInterface));
            //stage.setOnHidden((event1) -> refresh());
            stage.show();
        
    }

    @FXML
    private void detail(ActionEvent event) {
    }
    
}
