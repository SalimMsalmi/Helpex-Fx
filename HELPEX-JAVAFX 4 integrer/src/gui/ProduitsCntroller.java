/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import entities.Produit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.CrudProduits;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.JavaMail;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ProduitsCntroller implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TextArea txtNomProduit;
    @FXML
    private TextArea txtEtatProduit;
    @FXML
    private TextArea txtPrixProduit;
    @FXML
    private TextArea txtAuthorisationProduit;
    @FXML
    public TableView<Produit> table;
     @FXML
    public TableColumn<Produit, String> IdProduitColumn;
    @FXML
    public TableColumn<Produit, String> NomProduitColumn;
    @FXML
    public TableColumn<Produit, String> EtatProduitColumn;
    @FXML
    public TableColumn<Produit, String> PrixProduitColumn;
    @FXML
    private TableColumn<Produit, String> CreatedAtColumn;
    @FXML
    private TableColumn<Produit, String> UpdatedAtColumn;
    @FXML
    private TableColumn<Produit, Boolean> AuthorisationColumn;
    @FXML
    private Label NumPostes;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    
    private Produit C;
    
    int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      
      ObservableList<Produit> produits = FXCollections.observableArrayList();
    @FXML
    private TextField searchfieldProd;
    @FXML
    private Button StatProd;
    @FXML
    private ChoiceBox<String> TriProduits  ;
    
    private ObservableList<String> TriChoices = FXCollections.observableArrayList("All Products" , "Authorized", "Not Authorized");
    @FXML
    private Label TriLabel;
    
    
    private static String choice = "Authorized" ; 
    
    
       public Produit getC() {
        return C;
    }

    public void setC(Produit c) {
        this.C = c;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //advanced_search();
        TriProduits.getItems().addAll(TriChoices);
        TriProduits.setValue("Authorized");
        TriProduits.setOnAction(this::getTriProduits);
        table();
       // advanced_search();
        
       
            //System.out.println(TriProduits.getValue());
        
/*         if (choice.equals("All Products"))
            System.out.println("id = "+ItemController.P.getCategoryProduit().getId());
        else if (choice.equals("Authorized"))
            System.out.println("hello");*/


  /* try
                     {
                         Connection conn= MyConnection.getInstance().getConn();
                         ObservableList<Produit> produits = FXCollections.observableArrayList();
                          PreparedStatement pst;
                           
                         pst = conn.prepareStatement("select nom_produit,etat_produit,prix_produit FROM produits WHERE categorie_produit_id='"+ItemController.P.getCategoryProduit().getId()+"'");  
                         ResultSet rs = pst.executeQuery();
                      while (rs.next())
                      {
                          Produit st = new Produit();
                          st.setNomProduit(rs.getString("nom_produit"));
                          st.setEtatproduit(rs.getString("etat_produit"));
                          st.setPrixProduit(rs.getString("prix_produit"));

                          produits.add(st);
                     }



                              table.setItems(produits);
                              //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
                            //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());

                            NomProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomProduit()));
                              EtatProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getEtatproduit()));
                                PrixProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getPrixProduit()));


                     }



                     catch (SQLException ex)
                     {
                         Logger.getLogger(ProduitsCntroller.class.getName()).log(Level.SEVERE, null, ex);
                     }


                        table.setRowFactory(tv -> {
                   TableRow<Produit> myRow = new TableRow<>();
                   myRow.setOnMouseClicked (event ->
                   {
                      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                      {
                         myIndex =  table.getSelectionModel().getSelectedIndex();
                         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                         txtNomProduit.setText(table.getItems().get(myIndex).getNomProduit());
                      //   txtTelephone.setText(table.getItems().get(myIndex));
                         txtEtatProduit.setText(table.getItems().get(myIndex).getEtatproduit());
                         txtPrixProduit.setText(table.getItems().get(myIndex).getPrixProduit());
                         txtAuthorisationProduit.setText(Boolean.toString(table.getItems().get(myIndex).isAuthorization()));




                      }
                   });
                      return myRow;
                                 });*/
        
        
        
    }   
    
    public void getTriProduits (ActionEvent event){
        ProduitsCntroller.choice = TriProduits.getValue();
        //TriLabel = new Label();
        TriLabel.setText( ProduitsCntroller.choice);
        table();
        //advanced_search();

        
    }
    
        public void table() 
      {
          
          System.out.println(TriProduits.getValue());
         // MyConnection conn= MyConnection.getInstance();
          
         //CrudProduits ProduitList = new CrudProduits( conn);
         
          //advanced_search();
           
          
          //for (Produit p : ProduitList.getProduitByCatProduit(C.getCategoryProduit())) {
          /*for (Produit p : ProduitList.getAllProduit()) {
              produits.add(p);
          }*/
          
          
          

      
             
          
          
        
                        
                        
                        
                        
                        try
                     {
                         Connection conn= MyConnection.getInstance().getConn();
                         ObservableList<Produit> produits = FXCollections.observableArrayList();
                          PreparedStatement pst;
                           if (TriProduits.getValue().equals("All Products"))
                           {
                               

                                 pst = conn.prepareStatement("select nom_produit,etat_produit,prix_produit,authorisation FROM produits ");
                           }
                           else {
                               boolean  auth =true ; 
                               if (TriProduits.getValue().equals("Authorized")){
                                    auth = true ; 
                               }
                                   
                               else if  (TriProduits.getValue().equals("Not Authorized")){
                                    auth = false  ; 
                               }
                               
                                   /*pst = conn.prepareStatement("select nom_produit,etat_produit,prix_produit,authorisation FROM produits WHERE categorie_produit_id='"+
                                         ItemController.P.getCategoryProduit().getId()+" AND  authorisation='" + auth);*/

                                pst = conn.prepareStatement("SELECT id,nom_produit, etat_produit, prix_produit , authorisation FROM produits WHERE categorie_produit_id = ? AND authorisation= ?");
                                pst.setInt(1, ItemCatProdController.P.getCategoryProduit().getId());
                                pst.setInt(2, auth ? 1 : 0);                                   
                               
                           }
                         
                         ResultSet rs = pst.executeQuery();
                      while (rs.next())
                      {
                          Produit st = new Produit();
                          st.setId(Integer.parseInt(rs.getString("id")));
                          st.setNomProduit(rs.getString("nom_produit"));
                          st.setEtatproduit(rs.getString("etat_produit"));
                          st.setPrixProduit(rs.getString("prix_produit"));
                          st.setAuthorization((rs.getInt("authorisation")!=0));

                          produits.add(st);
                     }



                              table.setItems(produits);
                              //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
                            //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());

                            NomProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomProduit()));
                              EtatProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getEtatproduit()));
                                PrixProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getPrixProduit()));
                                   IdProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getId() +""));

                     }



                     catch (SQLException ex)
                     {
                         Logger.getLogger(ProduitsCntroller.class.getName()).log(Level.SEVERE, null, ex);
                     }
//advanced_search();
//advanced_search();

                        table.setRowFactory(tv -> {
                   TableRow<Produit> myRow = new TableRow<>();
                   myRow.setOnMouseClicked (event ->
                   {
                      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                      {
                         myIndex =  table.getSelectionModel().getSelectedIndex();
                         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                         txtNomProduit.setText(table.getItems().get(myIndex).getNomProduit());
                      //   txtTelephone.setText(table.getItems().get(myIndex));
                         txtEtatProduit.setText(table.getItems().get(myIndex).getEtatproduit());
                         txtPrixProduit.setText(table.getItems().get(myIndex).getPrixProduit());
                         txtAuthorisationProduit.setText(Boolean.toString(table.getItems().get(myIndex).isAuthorization()));




                      }
                   });
                      return myRow;
                                 });
          
          
      }
        
        

    @FXML
    private void Add(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        Connection con = MyConnection.getInstance().getConn();
        CrudProduits  rcd = new  CrudProduits((MyConnection)con);
           Produit c= new Produit();
              c= table.getSelectionModel().getSelectedItem();
              rcd.deleteProduite(c.getId());
              table();
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        
        CrudProduits  rcd = new  CrudProduits();
        String var1=txtNomProduit.getText();
        String var2=txtEtatProduit.getText();
        String var3=txtPrixProduit.getText();
        String var4=txtAuthorisationProduit.getText();
        Boolean var5=Boolean.parseBoolean(var4);
        
       Produit r =new Produit();
       r= table.getSelectionModel().getSelectedItem();
       System.out.println(id);
        System.out.println(r);
       //Produit rtest =new Produit();
       //rtest = r ;
        //System.out.println("Before update = "+rtest);
        r.setNomProduit(var1);
      r.setEtatproduit(var2);
      r.setPrixProduit(var3);
      r.setAuthorization(var5);
      if (r.isAuthorization() == true )
      {
          try {
            //send email to emailField.getText()
            JavaMail.sendMail("farouk.chalghoumi@esprit.tn");
        } catch (Exception ex) {
            Logger.getLogger(ItemCatProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
        //r=table.getSelectionModel().getSelectedItem();
        rcd.updateProduit(r);
        String title = "Product  Authorized";
        String message = "The Product "+txtNomProduit.getText()+" has been Authorized To Parashop.";
            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
        trayNotification.showAndDismiss(Duration.seconds(5));
       table();
    }
    
    public void advanced_search() {
        FilteredList<Produit> filteredData = new FilteredList<>(produits, b -> true);
        searchfieldProd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Produit.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else return Produit.getNomProduit().toLowerCase().contains(lowerCaseFilter); // Filter matches last name.
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    @FXML
    public void Stat (ActionEvent event){
        FXMLLoader loader1 = new FXMLLoader ();
        loader1.setLocation(getClass().getResource("statProd.fxml"));
        try {
            loader1.load();
        } catch (IOException ex) {
            Logger.getLogger(CatProdController.class.getName()).log(Level.SEVERE, null, ex);
        }




        Parent parent = loader1.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    
    
    
    
}
