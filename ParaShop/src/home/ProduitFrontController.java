/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import services.CrudProduits;
import entities.Produit;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CrudCategorieProduit;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ProduitFrontController implements Initializable {

    @FXML
    private VBox pnItems;
    @FXML
    private TextField searchfield;
    
    //public static int refresh =0; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudProduits productCrud = new CrudProduits();
        List<Produit> produitList;
        produitList = productCrud.getAllProduit();
        System.out.println(produitList);
        //LoadItem(produitList);
        for (Produit oneProduct : produitList)
            LoadItem(oneProduct);
        search();
    }    
    
        private void LoadItem(Produit Produit) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemFrontController.fxml"));
        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
 Label id_produit = (Label) itemPane.lookup("#id_produit");
     Label nom = (Label) itemPane.lookup("#nom_produit");
     
                Label categorie = (Label) itemPane.lookup("#categorie_produit");
                
                Label etat_produit = (Label) itemPane.lookup("#etat_produit");
                Label PrixProduit = (Label) itemPane.lookup("#PrixProduit");
                Label Created_At = (Label) itemPane.lookup("#Created_At");
                Label Updated_At = (Label) itemPane.lookup("#Updated_At");
                Label Authorisation = (Label) itemPane.lookup("#Authorisation");


            
id_produit.setText(String.valueOf(Produit.getId()));
        nom.setText(Produit.getNomProduit());
        categorie.setText(Produit.getCategoryProduit().getNom_cat_produit());
        etat_produit.setText(Produit.getEtatproduit());
        PrixProduit.setText(Produit.getPrixProduit());
        Created_At.setText(Produit.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Updated_At.setText(Produit.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Authorisation.setText(String.valueOf(Produit.isAuthorization()));
        pnItems.getChildren().add(itemPane);
    }

    @FXML
    private void signout(ActionEvent event) {
    }

    @FXML
    private void AjouterInterface(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FormProduit.fxml"));
        AnchorPane newInterface = loader.load();
        FormProduitController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    
        public void refresh() {

        pnItems.getChildren().clear();
       CrudProduits CRUDCentre=new CrudProduits();
        List<Produit> ProduitsList;
        ProduitsList=CRUDCentre.getAllProduit();
        for (Produit P : ProduitsList)
            LoadItem(P);
    }
     private void Refresh(ActionEvent event) {
        refresh();
    }
    
    
    private void search() {
        CrudProduits CRUDcategoriePeoduit=new CrudProduits();
        List<Produit> ProduitList;

        ProduitList=CRUDcategoriePeoduit.getAllProduit();

            searchfield.textProperty().addListener((observable, oldValue, newValue)-> {
                        pnItems.getChildren().clear();

 for (Produit Cat : ProduitList)
        {
                if (Cat.getNomProduit().contains(searchfield.getText()))
            LoadItem(Cat);
             }});



    }
}
