/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Centre;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.CRUDCentre;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CentreFrontController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Label title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CRUDCentre CRUDCentre=new CRUDCentre();
        List<Centre> centreList;
        centreList=CRUDCentre.affichercentre();
        for (Centre centre : centreList)
            LoadItem(centre);
       // search();
    }    

    
   


 public void LoadItem(Centre centre){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCentreFront.fxml"));
        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

     Label nomcentre = (Label) itemPane.lookup("#nom_centre");
        Label adressecentre = (Label) itemPane.lookup("#adresse_centre");
        Label emailcentre = (Label) itemPane.lookup("#email_centre");
        Label telephonecentre = (Label) itemPane.lookup("#telephone_centre");
        Label sitewebcentre = (Label) itemPane.lookup("#site_web_centre");
                Label id_centre = (Label) itemPane.lookup("#id_centre");



        nomcentre.setText(centre.getNomCentre());
       emailcentre.setText(centre.getEmailCentre());
        telephonecentre.setText(String.valueOf(centre.getTelephoneCentre()));
        sitewebcentre.setText(centre.getSiteWebCentre());
        adressecentre.setText(centre.getAdresseCentre());
        id_centre.setText(String.valueOf(centre.getId()));
        pnItems.getChildren().add(itemPane);
    }

    @FXML
    private void formationNavBar(ActionEvent event) {
        
    }

    @FXML
    private void profileNavBar(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void signout(ActionEvent event) {
           Help.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void accompagnement(ActionEvent event) {
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
    private void blog(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetworkfront.fxml"));
        try {
            Parent root = loader.load();
            this.title.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
       @FXML
    private void parashop(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitFront.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Socialnetworkfront.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("Hello");
    }

   
  
    
}
