/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.JavaMail;
//import com.google.zxing.qrcode.encoder.QRCode;
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
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.javafx.iio.ImageStorage.ImageType;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javafx.application.HostServices;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

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
    private TextField txt_id_centre;
    
    private Centre F;

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
   }    

    @FXML
    
    
    private void Add(ActionEvent event) {
       String nomFormation; String descriptionFormation; float coutFormation; int NombreDePlace; String duree;int id_centre;
            nomFormation = txtNomF.getText();
            descriptionFormation=txtdescriptionF.getText();
            duree=txtdureeF.getText();
                        NombreDePlace=Integer.parseInt(txtnombredeplaceF.getText()) ;
                                              //  coutFormation=Integer.parseInt(txtcouF.getText()) ;
                                            //  id_centre=Integer.parseInt(txt_id_centre.getText());
                                              Centre centre_jointure=new Centre();
                                              CRUDCentre  crudcentre=new CRUDCentre();
                                            //  System.out.println(id_centre);
                                              centre_jointure= crudcentre.findbyid(F.getId());
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
      // rc.generatePDF(r);
    
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

    private void imprimerF(ActionEvent event) {
             CRUDFormation  rcd = new  CRUDFormation();
        Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
              rcd.generatePDF(c);
         
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
