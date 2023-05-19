package GUI.admin;

import entities.Accompagnement;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AccompagnementService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Label tasksSideNav;
    @FXML
    private Label AccompagnementLabel;

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
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
}





    @FXML
    public void switchingDashboard() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui_Tasks/Gui_taskAdmin.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
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
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
