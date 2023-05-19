/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import entities.Poste;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import services.CRUDCommentaire;
import services.CRUDPoste;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCommentaireController implements Initializable {

 
    @FXML
    public TableView<Commentaire> table;
    @FXML
    public TableColumn<Commentaire,String> IDColumn;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextArea txtDescriptioncommentaire;
    @FXML
    public TableColumn<Commentaire, String> DESCRIPTIONcolumn;
    private Poste p;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label NumPostes;

    public Poste getP() {
        return p;
    }

    public void setP(Poste p) {
        this.p = p;
    }

   

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
     
}    
        int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      
      
      
       public void table() throws IOException
      {
          

            try
       {
           Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
            
           pst = conn.prepareStatement("select id,description FROM `commentaire`WHERE poste_id='"+p.getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Commentaire st = new Commentaire();
            st.setId(rs.getInt("id"));
            st.setDescription(rs.getString("description"));
           
            commentaires.add(st);
       }
      
    
           
                table.setItems(commentaires);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                DESCRIPTIONcolumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescription()));
               
       }
      
     
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                table.setRowFactory(tv -> {
     TableRow<Commentaire> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtDescriptioncommentaire.setText(table.getItems().get(myIndex).getDescription());  
           
        }
     });
        return myRow;
                   });
    
    
      }

   /*  public void ListView() {
    Connection conn = MyConnection.getInstance().getConn();
    ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
    CRUDCommentaire cru=new CRUDCommentaire();
    try {
        PreparedStatement pst = conn.prepareStatement("select id,description FROM `commentaire`");
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Commentaire st = new Commentaire();
            st.setId(rs.getInt("id"));
            st.setDescription(rs.getString("description"));
           
            commentaires.add(st);
        }
    } catch (SQLException ex) {
        Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    listView.setItems(commentaires);
    listView.setCellFactory(param -> new ListCell<Commentaire>() {
        @Override
        protected void updateItem(Commentaire item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(String.valueOf(item.getId()));
                setText(item.getDescription());
            }
        }
    });
    
    listView.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && listView.getSelectionModel().getSelectedItem() != null) {
            Commentaire selectedCommentaire = listView.getSelectionModel().getSelectedItem();
            id = selectedCommentaire.getId();
            txtDescriptioncommentaire.setText(selectedCommentaire.getDescription());
        }
    });
}  */
       public String filterBadWords(String input) {
    // Define a list of bad words
    List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");

    // Loop through the list of bad words and replace them with an alternative
    for (String badWord : badWords) {
        input = input.replaceAll("(?i)" + badWord, "***"); // replace with asterisks
        // or input = input.replaceAll("(?i)" + badWord, "goodword"); // replace with a good word
    }

    return input;
}

    @FXML
    private void Add(ActionEvent event) throws IOException {
        
        String description;
        
        List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");
            description=txtDescriptioncommentaire.getText();
            AjouterCommentaireController AC = new AjouterCommentaireController();
           String d = AC.filterBadWords(description);
             if (d.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champs manquants");
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        } else {
            Commentaire c = new Commentaire(d);
            CRUDCommentaire cu = new CRUDCommentaire();
            cu.ajouterCommentaire(c,p);
             table();
 
             }
    }
    
     
    

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        CRUDCommentaire  rcd = new  CRUDCommentaire ();
           Commentaire c= new Commentaire();   
              c= table.getSelectionModel().getSelectedItem();
              rcd.supprimerCommentaire(c);
                 table();
      
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
         CRUDCommentaire rc = new CRUDCommentaire();
        String var2=txtDescriptioncommentaire.getText();
       Commentaire r =new Commentaire();
      r.setDescription(var2);
        r=table.getSelectionModel().getSelectedItem();
        rc.modifierCommentaire(r,var2);
       table();
    }

       
    
}
