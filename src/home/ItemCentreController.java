/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import apex.helpex.utils.JavaMail;
import entities.Centre;
import entities.Formation;
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
import services.CRUDCentre;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemCentreController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label nom_centre;
    @FXML
    private Label adresse_centre;
    @FXML
    private Label email_centre;
    @FXML
    private Label telephone_centre;
    @FXML
    private Label site_web_centre;
    @FXML
    private Button button_modifier_centre;
    @FXML
    private Label id_centre;
    @FXML
    private Button supprimer_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierCentreBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCentre.fxml"));
            AnchorPane newInterface = loader.load();
            FormCentreController newInterfaceController = loader.getController();
            Centre P=new Centre();
            Stage stage = new Stage();
            String nomcentre=nom_centre.getText();
            String adressecentre=adresse_centre.getText();
            String emailcentre=email_centre.getText();
           // String var=telephone_centre.getText();
      //  int telephonecentre=Integer.parseInt(var);
          //  int telephocentre=telephone_centre.getText();
            String siteweb=site_web_centre.getText();
            
            newInterfaceController.txtNom.setText(nomcentre);
            newInterfaceController.txtAdresse.setText(adressecentre);
            newInterfaceController.txtEmail.setText(emailcentre);
            newInterfaceController.txtSiteweb.setText(siteweb);
                     //   P.setTelephoneCentre(Integer.parseInt(telephone_centre.getText()));
                       // P.setId(Integer.parseInt(id_centre.getText()));

            P.setId(Integer.parseInt(id_centre.getText()));
            System.out.println(P.getId());
            newInterfaceController.setC(P);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
    }

    private void refresh() {
    }

    @FXML
    private void supprimerBtn(ActionEvent event) {
            Centre c=new Centre();
            c.setId(Integer.parseInt(id_centre.getText()));
            CRUDCentre crudcentre=new CRUDCentre();
            crudcentre.supprimerCentre(c);
    }

    @FXML
    private void detail(ActionEvent event) throws IOException, SQLException {
        PreparedStatement pst;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterF.fxml"));
            AnchorPane newInterface = loader.load();
         
            AjouterFormationController newInterfaceController = loader.getController();
            Centre P=new Centre();
            P.setId(Integer.parseInt(id_centre.getText()));
            newInterfaceController.setF(P);
             Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
        try
       {
           Connection conn= MyConnection.getInstance().getConn();
           ObservableList<Formation> formations = FXCollections.observableArrayList();
            
           pst = conn.prepareStatement("SELECT `id`, `nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree` FROM `formation`WHERE id_centre_id='"+P.getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Formation st = new Formation();
            st.setId(rs.getInt("id"));
          
            st.setNomFormation(rs.getString("nom_formation"));
            st.setDescriptionFormation(rs.getString("description_formation"));
            st.setCoutFormation(rs.getFloat("cout_formation"));
                        st.setNombreDePlace(rs.getInt("nombre_de_place"));
                       // st.setDuree(rs.getString("duree"));
           
            formations.add(st);
       }
      
    
           
                newInterfaceController.tableF.setItems(formations);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
       newInterfaceController.IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

newInterfaceController.NOMColumnF.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomFormation()));
                newInterfaceController.DESCRIPTIONColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescriptionFormation()));
               // newInterfaceController.COUTColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCoutFormation()));
                                  newInterfaceController.COUTColumn.setCellValueFactory(new PropertyValueFactory<>("cout_formation"));

                //newInterfaceController.DUREEColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDuree()));
          //    TELEPHONEColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                  newInterfaceController.PLACEColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));
               
       }
      
     
      
       catch (SQLException ex)
       {
           Logger.getLogger(CentreController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
