package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.CaisseOrganisation;
import entities.Organisation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrganisationDetails implements Initializable {
    public Label nomOrg;
    public Label emailOrg;
    public Label DescOrg;
    public Label numeTel;
    public Label DocOrg;
    public Label LogoOrg;
    public Label PayOrg;
    public TableView<CaisseOrganisation> Caisses;
    public TableColumn<CaisseOrganisation,Object> Montant;
    public TableColumn<CaisseOrganisation,Object> Goal;
    public TableColumn<CaisseOrganisation,Object> DescriptionCaisse;
    public TableColumn<CaisseOrganisation, String> ActionCaisse;
    int organisationId;
    Organisation organisation=null;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    CaisseOrganisation caisseOrganisation = null ;

    ObservableList<CaisseOrganisation> caisseOrganisationObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = MyConnection.getInstance().getConn();
        loadData();
    }

    @FXML
    private void AddCaisseView(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("CaisseForm.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }

            CaisseForm caisseForm = loader.getController();
            caisseForm.setOrganisationId(organisationId);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.setOnHidden((event1) -> refreshTable());
            stage.show();
    }

    @FXML
    private void refreshTable() {
        caisseOrganisationObservableList.clear();
        try {
            query = "SELECT * FROM `caisse_organisation` WHERE organisation_id="+ organisationId;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                caisseOrganisationObservableList.add(new CaisseOrganisation(
                                resultSet.getInt("id"),
                                resultSet.getInt("organisation_id"),
                                resultSet.getFloat("montant_caisse_org"),
                                resultSet.getFloat("goal"),
                                resultSet.getString("description")
                        )
                );
                Caisses.setItems(caisseOrganisationObservableList);
                System.out.println("Refresh caisse");
            }



        } catch (SQLException ex) {
            Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {

        refreshTable();

        Montant.setCellValueFactory(new PropertyValueFactory<>("montant_caisse_org"));
        Goal.setCellValueFactory(new PropertyValueFactory<>("goal"));
        DescriptionCaisse.setCellValueFactory(new PropertyValueFactory<>("description"));

        Callback<TableColumn<CaisseOrganisation, String>, TableCell<CaisseOrganisation, String>> cellFoctory = (TableColumn<CaisseOrganisation, String> param) -> {
            final TableCell<CaisseOrganisation, String> cell = new TableCell<CaisseOrganisation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
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

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                caisseOrganisation = Caisses.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `caisse_organisation` WHERE id  ="+caisseOrganisation.getId();
                                connection = MyConnection.getInstance().getConn();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            caisseOrganisation = Caisses.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("CaisseForm.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            CaisseForm caisseForm = loader.getController();
                            caisseForm.setUpdate(true);
                            System.out.println(caisseOrganisation);
                            caisseForm.setTextField(caisseOrganisation, organisationId,caisseOrganisation.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setOnHidden((event1) -> refreshTable());
                            stage.show();

                        });



                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ActionCaisse.setCellFactory(cellFoctory);
        Caisses.setItems(caisseOrganisationObservableList);


    }
    void setTextField() {
        organisationId = organisation.getId();
        nomOrg.setText(organisation.getNom());
        emailOrg.setText(organisation.getEmail());
        DescOrg.setText(organisation.getDescription());
        numeTel.setText(organisation.getNumero_tel());
        DocOrg.setText(organisation.getDocument());
        PayOrg.setText(organisation.getPayment_info());
        LogoOrg.setText(organisation.getLogo());
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
}
