package gui;

import com.sun.org.apache.xpath.internal.operations.Or;
import entities.Centre;
import entities.User;
import help.Help;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CRUDCentre;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class CentreController implements Initializable {

    @FXML
    private VBox pnItems = null;
   


    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlOverview;

    private Pane pnlMenus;
    @FXML
    private TextField search;
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
    private Label title;
    @FXML
    private Label currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CRUDCentre CRUDCentre=new CRUDCentre();
        List<Centre> centreList;
        centreList=CRUDCentre.affichercentre();
        for (Centre centre : centreList)
            LoadItem(centre);
        search();

    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        
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
                  if(actionEvent.getSource()==btnSocial)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetwork.fxml"));
                Parent root = loader.load();
                               this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(CentreController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           if(actionEvent.getSource()==btnShop)
        {         
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
    public void LoadItem(Centre centre){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCentre.fxml"));
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
    private void AjouterInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCentre.fxml"));
        AnchorPane newInterface = loader.load();
        FormCentreController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    private void refresh() {

        pnItems.getChildren().clear();
       CRUDCentre CRUDCentre=new CRUDCentre();
        List<Centre> centreList;
        centreList=CRUDCentre.affichercentre();
        for (Centre centre : centreList)
            LoadItem(centre);
    }
    private void search() {
        CRUDCentre CRUDcentre=new CRUDCentre();
        List<Centre> posteList;

        posteList=CRUDcentre.affichercentre();

            search.textProperty().addListener((observable, oldValue, newValue)-> {
                        pnItems.getChildren().clear();

 for (Centre poste : posteList)
        {
                if (poste.getNomCentre().contains(search.getText()))
            LoadItem(poste);
             }});



    }

}
