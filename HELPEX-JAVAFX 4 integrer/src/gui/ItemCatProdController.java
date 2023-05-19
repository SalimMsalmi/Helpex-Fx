/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieProduit;
import java.io.IOException;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CrudCategorieProduit;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JavaMail;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ItemCatProdController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label nom_cat_produit;
    @FXML
    private Label id_categorie_produit;
    @FXML
    private Button supprimer_btn;
    @FXML
    private Button button_modifier_centre;

    public static Produit P = new Produit ();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerBtn(ActionEvent event) {
        
        
        
        CategorieProduit c=new CategorieProduit();
            c.setId(Integer.parseInt(id_categorie_produit.getText()));
                CrudCategorieProduit crudcentre=new CrudCategorieProduit();
            crudcentre.deleteCategorie(c.getId());
            
    }

    @FXML
    private void modifierCentreBtn(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCatProdController.fxml"));
        AnchorPane newInterface = loader.load();
        FormCatProdController newInterfaceController = loader.getController();
        
        CategorieProduit CatProduit = new CategorieProduit();
        
        Stage stage = new Stage();
        
        
        
        String nom_cat_prod=nom_cat_produit.getText();
        
        newInterfaceController.nom_cat_produit.setText(nom_cat_prod);
        
        
        CatProduit.setId(Integer.parseInt(id_categorie_produit.getText()));
        System.out.println(CatProduit.getId());
            newInterfaceController.setC(CatProduit);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
        
    }

    @FXML
    private void detail(ActionEvent event) throws IOException, SQLException {
        CategorieProduit CP = new CategorieProduit();
            CP.setId(Integer.parseInt(id_categorie_produit.getText()));
            ItemCatProdController.P.setCategoryProduit(CP);
            //System.out.println(ItemController.P.getCategoryProduit());
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Produits.fxml"));
        AnchorPane newInterface = loader.load();
       /* try {
            //send email to emailField.getText()
            JavaMail.sendMail("farouk.chalghoumi@esprit.tn");
        } catch (Exception ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

            ProduitsCntroller newInterfaceController = loader.getController();
            //newInterfaceController.advanced_search();
            
            
            
            
            
            
            newInterfaceController.setC(P);
            Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
         //newInterfaceController.table();
        
        /*try
       {
           Connection conn= MyConnection.getInstance().getConn();
           ObservableList<Produit> produits = FXCollections.observableArrayList();
            PreparedStatement pst;
           pst = conn.prepareStatement("select nom_produit,etat_produit,prix_produit FROM produits WHERE categorie_produit_id='"+CP.getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Produit st = new Produit();
            st.setNomProduit(rs.getString("nom_produit"));
            st.setEtatproduit(rs.getString("etat_produit"));
            st.setPrixProduit(rs.getString("prix_produit"));
           
            produits.add(st);
       }
     
    
           
                newInterfaceController.table.setItems(produits);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());

                newInterfaceController.NomProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomProduit()));
                 newInterfaceController.EtatProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getEtatproduit()));
                  newInterfaceController.PrixProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getPrixProduit()));
               
       }
      
     
      
       catch (SQLException ex)
       {
           Logger.getLogger(ProduitsCntroller.class.getName()).log(Level.SEVERE, null, ex);
       }
*/
            
            
            
            //i was here ! 
    }
    
    
    
}
