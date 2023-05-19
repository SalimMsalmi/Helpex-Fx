package gui.admin;

import entities.Accompagnement;
import entities.User;
import gui.LoginController;
import help.Helpex;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AccompagnementService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccompagnmemntsAdmin implements Initializable {

    @FXML
    private TableView<Accompagnement> AdminAccompagnementTable;
    @FXML
    private TableColumn<Accompagnement, Integer> colId;

    @FXML
    private TableColumn<Accompagnement,Boolean> statuts;

    @FXML
    private TableColumn<Accompagnement, Number> taskId;

    @FXML
    private TableColumn<Accompagnement, Number> userIdCol;

    @FXML
    private TableColumn<Accompagnement, Number> userProId;
    @FXML
    private Label dashClick;
    @FXML
    private Label AccompagnementLabel;



    @FXML
    private Button btnAccomp;
    @FXML
    private VBox pnItems = null;

    @FXML
    private Button btnSignout;



    @FXML
    private Label title;
    @FXML
    private Label currentUser;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AccompagnementService accompagnementService = new AccompagnementService();
        System.out.println("eya looka"+accompagnementService.lister_accompagnement_for_admin());
        ObservableList<Accompagnement> accompagnementObservableList = FXCollections.observableArrayList(accompagnementService.lister_accompagnement_for_admin());
        colId.setCellValueFactory(new PropertyValueFactory<Accompagnement, Integer>("id"));
        statuts.setCellValueFactory(new  PropertyValueFactory<Accompagnement,Boolean>("is_accepted"));
        taskId.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getId_task().getId()));
        userIdCol.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getUser().getId()));
        userProId.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getUser_pro().getId()));


        AdminAccompagnementTable.setItems(accompagnementObservableList);
    }

    @FXML
    public void switching()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui_tasks/TasksGui_info.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) dashClick.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
}





    @FXML
    public void switchingDashboard() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../gui/gui_Tasks/Gui_taskAdmin.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) dashClick.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchingStat() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui_Tasks/chart.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) dashClick.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleClicks(ActionEvent actionEvent) {




        if(actionEvent.getSource()==btnUser)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Dashboard.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(actionEvent.getSource()==btnSignout){

            Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if(actionEvent.getSource()==btnSocial)
        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Socialnetwork.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CentreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(actionEvent.getSource()==btnShop)
        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../CatProduit.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(actionEvent.getSource()==btnCaisse)
        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Organisation.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        if(actionEvent.getSource()==btnAccomp)
        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui_taskAdmin.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        if(actionEvent.getSource()==btnCentre)
        {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Centre.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(gui.CentreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
