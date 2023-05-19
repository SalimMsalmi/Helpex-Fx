/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Centre;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDCentre;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtSiteweb;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TableView<Centre> table;
    @FXML
    private TableColumn<Centre,String> IDColumn;
    @FXML
    private TableColumn<Centre,String> NOMColumn;
    @FXML
    private TableColumn<Centre,String> ADRESSEColumn;
    @FXML
    private TableColumn<Centre,String> EMAILColumn;
    @FXML
    private TableColumn<Centre,String> TELEPHONEColumn;
    @FXML
    private TableColumn<Centre,String> SITEWEBColumn;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
table();    }    
        int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;


    @FXML
    private void Add(ActionEvent event) {
        String nomCentre,adresseCentre,emailCentre,siteWebCentre;
        int telephoneCentre;
            nomCentre = txtNom.getText();
            adresseCentre=txtAdresse.getText();
            emailCentre=txtEmail.getText();
                        telephoneCentre=Integer.parseInt(txtTelephone.getText()) ;

            siteWebCentre=txtSiteweb.getText();
            
            Centre c = new Centre( nomCentre, adresseCentre,emailCentre, telephoneCentre, siteWebCentre);
            CRUDCentre cu = new CRUDCentre();
            cu.ajouterCentre(c);
             table();
    }
     public void table()
      {
          
    Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Centre> centres = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("select id,nom_centre,adresse_centre,email_centre,telephone_centre,site_web_centre FROM `centre`");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Centre st = new Centre();
            st.setId(rs.getInt("id"));
          
            st.setNomCentre(rs.getString("nom_centre"));
            st.setAdresseCentre(rs.getString("adresse_centre"));
            st.setEmailCentre(rs.getString("email_centre"));
                        st.setTelephoneCentre(rs.getInt("telephone_centre"));
                        st.setSiteWebCentre(rs.getString("site_web_centre"));

            centres.add(st);
       }
    }
                table.setItems(centres);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

NOMColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomCentre()));
                ADRESSEColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getAdresseCentre()));
                EMAILColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getEmailCentre()));
                SITEWEBColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getSiteWebCentre()));
          //    TELEPHONEColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                  TELEPHONEColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneCentre"));

 
       }
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                table.setRowFactory(tv -> {
     TableRow<Centre> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtNom.setText(table.getItems().get(myIndex).getNomCentre());
        //   txtTelephone.setText(table.getItems().get(myIndex));
           txtSiteweb.setText(table.getItems().get(myIndex).getSiteWebCentre());
           txtAdresse.setText(table.getItems().get(myIndex).getAdresseCentre());
           txtEmail.setText(table.getItems().get(myIndex).getEmailCentre());

                          
                        
                          
        }
     });
        return myRow;
                   });
    
    
      }
    

    @FXML
    private void Delete(ActionEvent event) {
CRUDCentre  rcd = new  CRUDCentre ();
           Centre c= new Centre();
              c= table.getSelectionModel().getSelectedItem();
              rcd.supprimerCentre(c);
              table();
    }

    @FXML
    
    private void Update(ActionEvent event) {
       CRUDCentre rc = new CRUDCentre();
        String var1=txtNom.getText();
        String var2=txtEmail.getText();
        String var3=txtSiteweb.getText();
        String var4=txtTelephone.getText();
        int var5=Integer.parseInt(var4);
        String var6=txtAdresse.getText();
       Centre r =new Centre();
        r.setNomCentre(var1);
      r.setEmailCentre(var2);
      r.setSiteWebCentre(var3);
      r.setTelephoneCentre(var5);
      r.setAdresseCentre(var6);
        r=table.getSelectionModel().getSelectedItem();
        rc.modifierCentre(r,var1,var6,var2,var5,var3);
       table();
        
    }
    
}
