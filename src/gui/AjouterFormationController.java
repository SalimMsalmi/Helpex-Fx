/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Centre;
import entities.Formation;
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
import services.CRUDFormation;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterFormationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtNomF;
    @FXML
    private TextField txtcouF;
    @FXML
    private TextField txtnombredeplaceF;
    @FXML
    private TextField txtdureeF;
    @FXML
    private TextField txtdescriptionF;
    @FXML
    private Button btnAddF;
    @FXML
    private Button btnDeleteF;
    @FXML
    private Button btnUpdateF;
    @FXML
    private TableView<Formation> tableF;
 
    @FXML
    private TableColumn<Formation,String> IDColumn;
    @FXML
    private TableColumn<Formation,String> NOMColumnF;
    @FXML
    private TableColumn<Formation,String> DESCRIPTIONColumn;
    @FXML
    private TableColumn<Formation,String> COUTColumn;
    @FXML
    private TableColumn<Formation,String> PLACEColumn;
    @FXML
    private TableColumn<Formation,String> DUREEColumn;
    @FXML
    private TextField txt_id_centre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
table();    }    

    @FXML
    
    
    private void Add(ActionEvent event) {
        String nomFormation; String descriptionFormation; float coutFormation; int NombreDePlace; String duree;int id_centre;
            nomFormation = txtNomF.getText();
            descriptionFormation=txtdescriptionF.getText();
            duree=txtdureeF.getText();
                        NombreDePlace=Integer.parseInt(txtnombredeplaceF.getText()) ;
                                              //  coutFormation=Integer.parseInt(txtcouF.getText()) ;
                                              id_centre=Integer.parseInt(txt_id_centre.getText());
                                              Centre centre_jointure=new Centre();
                                              CRUDCentre  crudcentre=new CRUDCentre();
                                              System.out.println(id_centre);
                                              centre_jointure= crudcentre.findbyid(id_centre);
                                             System.out.println(centre_jointure); 
                                              


           // siteWebCentre=txtSiteweb.getText();
            
            Formation c = new Formation( nomFormation, descriptionFormation,5, NombreDePlace, duree,centre_jointure);
            CRUDFormation cu = new CRUDFormation();
            cu.ajouterFormation(c);
             table();
    }

  

    @FXML
    //    public Formation(String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {
//    public void modifierFormation(Formation f, String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {

    private void Update(ActionEvent event) {
    CRUDFormation rc = new CRUDFormation();
        String var1=txtNomF.getText();
        String var2=txtdescriptionF.getText();
        String var3=txtcouF.getText();
//        float var3float=Integer.parseInt(var3);
        String var4=txtnombredeplaceF.getText();
//        int var5=Integer.parseInt(var4);
        String var6=txtdureeF.getText();
       Formation r =new Formation();
        r.setNomFormation(var1);
      r.setDescriptionFormation(var2);
      //r.getCoutFormation(var3);
    //  r.getNombreDePlace(var5);
      r.setDuree(var6);
        r=tableF.getSelectionModel().getSelectedItem();
        rc.modifierFormation(r,var1,var2,5,2,var6);
       table();
    }
    
    int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      public void table()
      {
          
    Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Formation> formations = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("SELECT `id`, `nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree` FROM `formation`");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Formation st = new Formation();
            st.setId(rs.getInt("id"));
          
            st.setNomFormation(rs.getString("nom_formation"));
            st.setDescriptionFormation(rs.getString("description_formation"));
            st.setCoutFormation(rs.getFloat("cout_formation"));
                        st.setNombreDePlace(rs.getInt("nombre_de_place"));
                        st.setDuree(rs.getString("duree"));

            formations.add(st);
       }
    }
                tableF.setItems(formations);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

NOMColumnF.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomFormation()));
                DESCRIPTIONColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescriptionFormation()));
               // COUTColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCoutFormation()));
                                  COUTColumn.setCellValueFactory(new PropertyValueFactory<>("cout_formation"));

                DUREEColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDuree()));
          //    TELEPHONEColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                  PLACEColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));

 
       }
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                tableF.setRowFactory(tv -> {
     TableRow<Formation> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  tableF.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(tableF.getItems().get(myIndex).getId()));
           txtNomF.setText(tableF.getItems().get(myIndex).getNomFormation());
        //   txtTelephone.setText(table.getItems().get(myIndex));
           txtdescriptionF.setText(tableF.getItems().get(myIndex).getDescriptionFormation());
           txtdureeF.setText(tableF.getItems().get(myIndex).getDuree());
                          //   txtnombredeplaceF.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));

           //txtEmail.setText(tableF.getItems().get(myIndex).getEmailCentre());

                          
                        
                          
        }
     });
        return myRow;
                   });
    
    
      }
      
        @FXML
    private void Delete(ActionEvent event) {
     CRUDFormation  rcd = new  CRUDFormation();
           Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
              System.out.println(c);
              rcd.supprimerFormation(c);
              table();
    }
    
}
