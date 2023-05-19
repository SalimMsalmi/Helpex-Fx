/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Filiere;
import entities.User;
import help.Help;
import services.FiliereService;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class FilController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label title;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Button addBtn;
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
    private Button btnSignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 FiliereService us=new FiliereService();
        List<Filiere> fList;
      fList=us.afficherf();
      pnlOverview.toFront();
        for (Filiere fil : fList)
            LoadItemf(fil);  
    }    
 public void LoadItemf(Filiere f){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemF.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

       Label idLabel = (Label) itemPane.lookup("#id");
        Label nfLabel = (Label) itemPane.lookup("#nom_filiere");
                Label descLabel = (Label) itemPane.lookup("#desc");
                    

      idLabel.setText(String.valueOf(f.getId()));
        nfLabel.setText(f.getNomF());
        descLabel.setText(f.getDescF());
        pnItems.getChildren().add(itemPane);
    }

    
    @FXML
    private void filieres(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("addF.fxml")) ;
              try {
                  Parent root= loader.load();
                   Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
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
                Logger.getLogger(gui.CatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              
                if(actionEvent.getSource()==btnSocial)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetwork.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CentreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(actionEvent.getSource()==btnShop)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CatProduit.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(actionEvent.getSource()==btnCaisse)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Organisation.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                  if(actionEvent.getSource()==btnCentre)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Centre.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CentreController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
