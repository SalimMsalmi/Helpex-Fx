/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.CategorieProduit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
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
        
        search();
    }    

    @FXML
    private void handleClicks(ActionEvent actionEvent) {
        
         if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
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
