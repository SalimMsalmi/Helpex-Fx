package gui;

import entities.Poste;
import entities.User;
import help.Helpex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
//import jdk.nashorn.internal.parser.JSONParser;
import services.CRUDPoste;

public class SocialnetworkController implements Initializable {

    @FXML
    private VBox pnItems = null;
    
    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    @FXML
    private Label NumPostes;
    @FXML
    private TextField searchfield;
    @FXML
    private Label title;
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

    public VBox getPnItems() {
        return pnItems;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search();
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        for (Poste poste : posteList)
            LoadItem(poste);
        NumPostes.setText(String.valueOf(posteList.size()));
                 pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();

    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
         pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
            
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
        if(actionEvent.getSource()==btnShop)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CatProduit.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(CatProdController.class.getName()).log(Level.SEVERE, null, ex);
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
                  
                    if(actionEvent.getSource()==btnSignout)
        {         
                   Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void LoadItem(Poste poste){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Itemposte.fxml"));

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
    private void AjouterInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPoste.fxml"));
        AnchorPane newInterface = loader.load();
        AjouterPosteController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
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
    private void Refresh(ActionEvent event) {
        refresh();
    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Api.fxml"));
        AnchorPane newInterface = loader.load();
        ApiController newInterfaceController = loader.getController();

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

   

}
