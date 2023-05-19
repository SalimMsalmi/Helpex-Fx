package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Organisation;
import entities.User;
import help.Help;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.OrganisationCRUD;
import utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrganisationController implements Initializable {

    @FXML
    public TableView<Organisation> Organisations;
    public TableColumn<Organisation, Object> Nomorg;
    public TableColumn<Organisation, Object> EmailOrg;

    public TableColumn<Organisation, Object> PayOrg;

    public TableColumn<Organisation, Object> LogoOrg;
    public TableColumn<Organisation, String> ActionOrg;
    public TableColumn<Organisation, Object> NumeroTelOrg;
    public VBox pnItems;
    public Label Total_Organisations;
    public TextField searchfieldOrg;


    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Organisation organisation = null ;

    ObservableList<Organisation>  organisationObservableList = FXCollections.observableArrayList();
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
    private Button btnSignout;
    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        advanced_search();
        loadData();
    }

    @FXML
    private void AddOrgView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrganisationForm.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setOnHidden((event1) -> refreshTable());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        if(actionEvent.getSource()==btnSocial)
        {
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Socialnetwork.fxml"));
                Parent root = loader.load();
                                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(SocialnetworkController.class.getName()).log(Level.SEVERE, null, ex);
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
              
                if(actionEvent.getSource()==btnSignout)
        {         
                   Help.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(GUI.LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   private void refreshTable() {
       organisationObservableList.clear();
       try {
           query = "SELECT * FROM `organisation`";
           preparedStatement = connection.prepareStatement(query);
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               organisationObservableList.add(new Organisation(
                       resultSet.getInt("id"),
                       resultSet.getString("description_organisation"),
                       resultSet.getString("email_organisation"),
                       resultSet.getString("num_tel_organisation"),
                       resultSet.getString("document_organisation"),
                       resultSet.getString("payment_info"),
                       resultSet.getString("nom_org"),
                       resultSet.getString("logo_org")
                       )
               );
               Organisations.setItems(organisationObservableList);

           }
           OrganisationCRUD organisationCRUD=new OrganisationCRUD();
           Total_Organisations.setText(String.valueOf(organisationCRUD.afficherOrg().size()));
            

       } catch (SQLException ex) {
           Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
       }
       advanced_search();
   }

    private void loadData() {

        connection = MyConnection.getInstance().getConn();
        refreshTable();

        Nomorg.setCellValueFactory(new PropertyValueFactory<>("nom"));
        EmailOrg.setCellValueFactory(new PropertyValueFactory<>("email"));
        LogoOrg.setCellValueFactory(new PropertyValueFactory<>("logo"));
        NumeroTelOrg.setCellValueFactory(new PropertyValueFactory<>("numero_tel"));
        PayOrg.setCellValueFactory(new PropertyValueFactory<>("payment_info"));

        Callback<TableColumn<Organisation, String>, TableCell<Organisation, String>> cellFoctory = (TableColumn<Organisation, String> param) -> {
            final TableCell<Organisation, String> cell = new TableCell<Organisation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView detailsIcon=new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        detailsIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#0869ff;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                                organisation = Organisations.getSelectionModel().getSelectedItem();
                                OrganisationCRUD organisationCRUD=new OrganisationCRUD();
                                organisationCRUD.supprimerOrg(organisation);
                                refreshTable();


                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            organisation = Organisations.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("OrganisationForm.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            OrganisationFormController addOrganisationController = loader.getController();
                            addOrganisationController.setUpdate(true);
                            addOrganisationController.setOrganisation(organisation);
                            addOrganisationController.setTextField(organisation);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setOnHidden((event1) -> refreshTable());
                            stage.show();

                        });

                        detailsIcon.setOnMouseClicked((MouseEvent event) -> {

                            organisation = Organisations.getSelectionModel().getSelectedItem();
                            FXMLLoader loader1 = new FXMLLoader ();
                            loader1.setLocation(getClass().getResource("OrganisationDetails.fxml"));
                            try {
                                loader1.load();
                            } catch (IOException ex) {
                                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            OrganisationDetails organisationDetails = loader1.getController();
                            organisationDetails.setOrganisation(organisation);
                            organisationDetails.setTextField();

                            Parent parent = loader1.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setOnHidden((event1) -> refreshTable());
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon,detailsIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(detailsIcon,new Insets(2,3,0,4));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ActionOrg.setCellFactory(cellFoctory);
        Organisations.setItems(organisationObservableList);


    }


    public void advanced_search() {
        FilteredList<Organisation> filteredData = new FilteredList<>(organisationObservableList, b -> true);
        searchfieldOrg.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(organisation -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (organisation.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return organisation.getNumero_tel().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Organisation> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(Organisations.comparatorProperty());

        Organisations.setItems(sortedData);
    }

    @FXML
    public void piechartOrg(MouseEvent mouseEvent) {
        FXMLLoader loader1 = new FXMLLoader ();
        loader1.setLocation(getClass().getResource("statOrg.fxml"));
        try {
            loader1.load();
        } catch (IOException ex) {
            Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }




        Parent parent = loader1.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnHidden((event1) -> refreshTable());
        stage.show();
    }
}
