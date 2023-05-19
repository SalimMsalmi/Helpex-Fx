/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Centre;
import entities.Formation;
import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDFormation;
import utils.JavaMail;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutFFrontController implements Initializable {

    @FXML
    public TableView<Formation> tableF;
 
    @FXML
    public TableColumn<Formation,String> IDColumn;
    @FXML
    public TableColumn<Formation,String> NOMColumnF;
    @FXML
    public TableColumn<Formation,String> DESCRIPTIONColumn;
    @FXML
    public TableColumn<Formation,String> COUTColumn;
    @FXML
    public TableColumn<Formation,String> PLACEColumn;
    @FXML
    public TableColumn<Formation,String> DUREEColumn;
    @FXML
    private Button btnimprimer;
    @FXML
    private Button inscribtn;
    @FXML
    private Button btnlocalisation;
     private Centre F;
    @FXML
public Centre getF() {
        return F;
    }

    public void setF(Centre F) {
        this.F = F;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
tableF.setStyle("-fx-background-color: #f4f4f4;");
    }    

    @FXML
    private void imprimerF(ActionEvent event) {
           CRUDFormation  rcd = new  CRUDFormation();
        Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
              rcd.generatePDF(c);
    }

    @FXML
    private void inscriptionF(ActionEvent event) {
         Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
                try {
                //send email to emailField.getText()
                    sendSMS(c.getNomFormation(),"test");
                    JavaMail.sendMail("ahmedbelhajhassen22@gmail.com",c);
            } catch (Exception ex) {
                Logger.getLogger(ItemCentreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      ObservableList<Formation> formations = FXCollections.observableArrayList();
    public void table()
      {
             try
       {
           formations.clear();
           Connection conn= MyConnection.getInstance().getConn();
          //  formations = FXCollections.observableArrayList();

           pst = conn.prepareStatement("SELECT `id`, `nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree` FROM `formation`WHERE id_centre_id='"+F.getId()+"'");  
           ResultSet rs = pst.executeQuery();
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
      
    
           
                tableF.setItems(formations);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
       IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

NOMColumnF.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomFormation()));
           DESCRIPTIONColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescriptionFormation()));
               // newInterfaceController.COUTColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCoutFormation()));
                                 COUTColumn.setCellValueFactory(new PropertyValueFactory<>("cout_formation"));

               DUREEColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDuree()));
          //    TELEPHONEColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                  PLACEColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));
               
       }
       catch (SQLException ex)
       {
           //Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                tableF.setRowFactory(tv -> {
     TableRow<Formation> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  tableF.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(tableF.getItems().get(myIndex).getId()));
          // txtNomF.setText(tableF.getItems().get(myIndex).getNomFormation());
        //   txtTelephone.setText(table.getItems().get(myIndex));
          // txtdescriptionF.setText(tableF.getItems().get(myIndex).getDescriptionFormation());
          // txtdureeF.setText(tableF.getItems().get(myIndex).getDuree());
                          //   txtnombredeplaceF.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));

           //txtEmail.setText(tableF.getItems().get(myIndex).getEmailCentre());

                          
                        
                          
        }
     });
     
        return myRow;
                   });
    
    
      }
      public void sendSMS(String nom,String numeroTel) throws IOException {
    String endpointUrl = "https://api.twilio.com/2010-04-01/Accounts/AC35f3e6bdc59a86fd5d3763e8d3e093a6/Messages.json";
    String numeroTelephone="+216"+numeroTel;
    Map<String, String> parameters = new HashMap<>();
    parameters.put("To", "+21623398991");
    parameters.put("From", "+19103354023");
    parameters.put("Body", "inscription a la formation "+nom+" €");

    URL url = new URL(endpointUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    String username = "AC35f3e6bdc59a86fd5d3763e8d3e093a6";
    String password = "b98f6255b040afaa8779af5c687286dd";
    String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

    connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
    connection.setDoOutput(true);
    connection.setDoOutput(true);

    String requestBody = encodeFormData(parameters);
    OutputStream outputStream = connection.getOutputStream();
    byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
    outputStream.write(requestBodyBytes);
    outputStream.close();

    int responseCode = connection.getResponseCode();
    System.out.println("Response Code : " + responseCode);
}
       private static String encodeFormData(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "utf-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));
        }

        return sb.toString();
    }

    @FXML
    private void localisationF(ActionEvent event) {
         Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
         String url = "https://www.google.com/maps/place/"+c.getDescriptionFormation()+"/";
        try {
            Desktop.getDesktop().browse(URI.create(url).create(url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
