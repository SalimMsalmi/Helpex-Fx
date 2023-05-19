/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieProduit;
import entities.User;
import help.Help;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CrudCategorieProduit;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class CatProdController implements Initializable {

    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private TextField searchfield;
    @FXML
    private Label title;
    @FXML
    private Label Numbreproduit;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnSocial;
    @FXML
    private Button btnShop;
    @FXML
    private Button btnCentre;
    @FXML
    private Button btnCaisse;
    @FXML
    private Button btnShifts;
    @FXML
    private Label currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudCategorieProduit CRUDcategorie=new CrudCategorieProduit();
        List<CategorieProduit> categoriesList;
        categoriesList=CRUDcategorie.getAllCategories();
        for (CategorieProduit categorie : categoriesList)
            LoadItem(categorie);
                    pnlOverview.toFront();
Numbreproduit.setText(String.valueOf(categoriesList.size()));
        search();
    }    

    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        
          if(actionEvent.getSource()==btnUser)
        { 
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(CatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
                  if(actionEvent.getSource()==btnCentre)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Centre.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(CentreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(actionEvent.getSource()==btnSocial)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetwork.fxml"));
                Parent root = loader.load();
                                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(SocialnetworkController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(actionEvent.getSource()==btnCaisse)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Organisation.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         
           if(actionEvent.getSource()==btnSignout)
        {         
                   Help.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(GUI.LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void AjouterInterface(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCatProduit.fxml"));
        AnchorPane newInterface = loader.load();
        FormCatProdController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    private void refresh() {

        pnItems.getChildren().clear();
       CrudCategorieProduit CRUDCentre=new CrudCategorieProduit();
        List<CategorieProduit> CategoriePList;
        CategoriePList=CRUDCentre.getAllCategories();
        for (CategorieProduit CategorieP : CategoriePList)
            LoadItem(CategorieP);
    }
    
    private void Refresh(ActionEvent event) {
        refresh();
    }
        
    

    private void LoadItem(CategorieProduit categorie) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCatProduit.fxml"));
        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

     Label nom = (Label) itemPane.lookup("#nom_cat_produit");
     
                Label id = (Label) itemPane.lookup("#id_categorie_produit");



        nom.setText(categorie.getNom_cat_produit());
        id.setText(String.valueOf(categorie.getId()));
        pnItems.getChildren().add(itemPane);
    }
    
    
    
    private void search() {
        CrudCategorieProduit CRUDcategoriePeoduit=new CrudCategorieProduit();
        List<CategorieProduit> CategorieList;

        CategorieList=CRUDcategoriePeoduit.getAllCategories();

            searchfield.textProperty().addListener((observable, oldValue, newValue)-> {
                        pnItems.getChildren().clear();

 for (CategorieProduit Cat : CategorieList)
        {
                if (Cat.getNom_cat_produit().contains(searchfield.getText()))
            LoadItem(Cat);
             }});



    }
    
    
}
