/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Poste;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CRUDPoste;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Socialnetworkfront implements Initializable {

    @FXML
    private TextField searchfield;
    @FXML
    private Label NumPostes;
    @FXML
    private VBox pnItems;
    @FXML
    private Label title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           search();
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        NumPostes.setText(String.valueOf(posteList.size()));
        for (Poste poste : posteList)
            LoadItem(poste);
    }    
 public void LoadItem(Poste poste){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Itempostefront.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label titreLabel = (Label) itemPane.lookup("#titre_poste");
        Label descriptionLabel = (Label) itemPane.lookup("#description_poste");
        Label idLabel = (Label) itemPane.lookup("#id_poste");
        titreLabel.setText(poste.getTitre());
        descriptionLabel.setText(poste.getDescription());
        idLabel.setText(String.valueOf(poste.getId()));
        pnItems.getChildren().add(itemPane);
    }
    @FXML
    private void profile(ActionEvent event) {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUpdate.fxml"));
                Parent root = loader.load();
                this.NumPostes.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void signout(ActionEvent event) {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.NumPostes.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void AjouterInterface(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPoste.fxml"));
        AnchorPane newInterface = loader.load();
        gui.AjouterPosteController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
            refresh();
    }
    public void refresh() {

        pnItems.getChildren().clear();
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        for (Poste poste : posteList)
            LoadItem(poste);
    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Api.fxml"));
        AnchorPane newInterface = loader.load();
        gui.ApiController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    private void search() {
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;

        posteList=CRUDposte.afficherPoste();
       
            searchfield.textProperty().addListener((observable, oldValue, newValue)-> {
                        pnItems.getChildren().clear();

 for (Poste poste : posteList)
        {
                if (poste.getTitre().contains(searchfield.getText()))
            LoadItem(poste);
             }});
            
       

    }

    @FXML
    private void donations(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("frontdonations.fxml"));
        try {
            Parent root = loader.load();
            this.NumPostes.getScene().setRoot(root);

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
            this.NumPostes.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void formation(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CentreFront.fxml"));
                Parent root = loader.load();
                this.NumPostes.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

           @FXML
    private void parashop(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitFront.fxml"));
                Parent root = loader.load();
                this.NumPostes.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Socialnetworkfront.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("Hello");
    }
}
