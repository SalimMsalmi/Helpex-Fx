/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import entities.Commentaire;
import entities.Poste;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.CRUDPoste;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItempostefrontController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label id_poste;
    @FXML
    private Label titre_poste;
    @FXML
    private Label description_poste;
    @FXML
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private Button detail;
    @FXML
    private Button share;
 public Label getId_poste() {
        return id_poste;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 PreparedStatement pst;

    @FXML
    private void Modify(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FormPoste.fxml"));
            AnchorPane newInterface = loader.load();
            FormPosteController newInterfaceController = loader.getController();
            Poste P=new Poste();
            Stage stage = new Stage();
            String t=titre_poste.getText();
            String c=description_poste.getText();
            newInterfaceController.Description.setText(c);
            newInterfaceController.Titre.setText(t);
            P.setId(Integer.parseInt(id_poste.getText()));
            newInterfaceController.setP(P);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
    }
    private void refresh() {   
          
    }
    @FXML
    private void Delete(ActionEvent event) {
          Poste P=new Poste();
        P.setId(Integer.parseInt(id_poste.getText()));
         CRUDPoste t= new CRUDPoste();
        t.supprimerPoste(P);
         String title = "Poste Deleted";
         String message = "The Poste has been deleted successfully.";
    NotificationType notificationType = NotificationType.SUCCESS;
    TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
    trayNotification.showAndDismiss(Duration.seconds(5));

    }

    @FXML
    private void Detail(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommentaire.fxml"));
            AnchorPane newInterface = loader.load();
            AjouterCommentaireController newInterfaceController = loader.getController();
           
            newInterfaceController.pnlOverview.setStyle("-fx-background-color :  white");
        newInterfaceController.Comments.setStyle("-fx-text-fill :  Black");
          newInterfaceController.Descriptioncommentaire.setStyle("-fx-text-fill :  Black");
            Poste P=new Poste();
            P.setId(Integer.parseInt(id_poste.getText()));
              newInterfaceController.setP(P);
             Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
            
                try
       {
           Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
            
           pst = conn.prepareStatement("select id,description FROM `commentaire`WHERE poste_id='"+P.getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Commentaire st = new Commentaire();
            st.setId(rs.getInt("id"));
            st.setDescription(rs.getString("description"));
           
            commentaires.add(st);
       }
      
    
           
                newInterfaceController.table.setItems(commentaires);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        newInterfaceController.IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                newInterfaceController.DESCRIPTIONcolumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescription()));
               
       }
      
     
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
       }
            
            
                
          
    }

    @FXML
    private void Share(ActionEvent event) {
             String accessToken = "EAACRyMBXvYEBAEMEMvzDKQld4yCswTkbv50ZCZBtMEXCkaIyZApuiVY725xcEQZCoFIGaVhGxZAjk97DKaIXZCiM4b6ZBIxTwGKRUFPz1DitAYLJ5RCE0mOZA5A7zLY1mwloF2qsLTZBka4gZAYvaaOneHV1Bi8dELJKsscIxTtuAGmQNQbZC1wng7H";

                        FacebookClient client = new DefaultFacebookClient(accessToken);

                        try {
                            FacebookType response = client.publish("116954624708499" + "/photos", FacebookType.class,
                                    BinaryAttachment.with(titre_poste.getText(),new FileInputStream(new File("C:\\test.png"))),
                                    Parameter.with("message", description_poste.getText()));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ItemposteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
}
