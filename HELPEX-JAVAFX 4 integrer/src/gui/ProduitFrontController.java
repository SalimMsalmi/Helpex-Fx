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
import javafx.scene.layout.VBox;
import services.CrudProduits;
import entities.Produit;
import entities.User;
import help.Help;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
        @FXML
    private Label title;

    
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
    private void AjouterInterface(ActionEvent event) throws IOException {
        CrudCategorieProduit C = new CrudCategorieProduit();
        if (C.getAllCategories().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("You can't Add Products ");
        alert.setContentText("ParaShop doesn't Containt any Categorie Produit Please Contact Support apex.helpex@gmail.com.");
        alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormProduit.fxml"));
        AnchorPane newInterface = loader.load();
        FormProduitController newInterfaceController = loader.getController();
        

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
        }
         
    }
    
    @FXML
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
    
        @FXML
    private void donations(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("frontdonations.fxml"));
        try {
            Parent root = loader.load();
            this.title.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void shifts(ActionEvent event) {
         
        FXMLLoader loader= null ;

        try {
            if (Help.loggedUser.getRoles().equals("[\"ROLE_PRO\"]"))
            {  loader = new FXMLLoader(getClass().getResource("user_Pro/mes_tasks_pro.fxml"));

            }
            else if (Help.loggedUser.getRoles().equals("[\"ROLE_USER\"]")){
                loader = new FXMLLoader(getClass().getResource("gui_Tasks/GUI_Tasks.fxml"));
            }

            Parent root = loader.load();
            this.title.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void formation(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CentreFront.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
        @FXML
    private void profile(ActionEvent event) {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUpdate.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void signout(ActionEvent event) {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @FXML
    private void blog(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetworkfront.fxml"));
        try {
            Parent root = loader.load();
            this.title.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
