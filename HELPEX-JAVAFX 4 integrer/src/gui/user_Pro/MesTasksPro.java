package gui.user_Pro;

import entities.Accompagnement;
import entities.DataSingleton;
import entities.Tasks;
import help.Help;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.TasksService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MesTasksPro  implements Initializable {
    @FXML
    private GridPane mesTasksGrid;
    @FXML
    private Label tasklabel;

    @FXML
    private Label accomswitch;
    private int current_user_pro_connectes= Help.loggedUser.getId() ;

    DataSingleton dataSingleton=DataSingleton.instance ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listerTasks();

    }
    public void listerTasks(){
        TasksService tasksService = new TasksService() ;
        ArrayList<Accompagnement> accompagnements = tasksService.listerTasksofUser_pro(current_user_pro_connectes);
        for (int i = 0; i < accompagnements.size(); i++){
            Image image ;
            Label title = null;
            if (accompagnements.get(i).getId_task().isIs_valid()){
                image  = new Image("images/folderyes.png");


            }
            else {
                image  = new Image("images/folderno.png");
            }

            title = new Label(accompagnements.get(i).getId_task().getTitre());

            int finalI = i;
            title.setOnMouseClicked(event -> {
                dataSingleton.setValue(accompagnements.get(finalI).getId_task().getId());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item_proUser.fxml"));
                Parent targetSceneParent = null;
                try {
                    targetSceneParent = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Create a controller instance
                ItemProUser controller = new ItemProUser();
                // Set it in the FXMLLoader
                loader.setController(controller);
                Scene targetScene = new Scene(targetSceneParent);

                // targetScene.setUserData(val());
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

               // window.setWidth(1350);
               // window.setHeight(890);
                window.setScene(targetScene);

                window.show();
            });

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            title.setGraphic(imageView);
            title.setMinWidth(500);
            title.setMinHeight(500);

            //////////////event////////////
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Editer");
            MenuItem deleteItem = new MenuItem("supprimer");

            int finalI1 = i;


            Label finalTitle = title;

            ///////////tootltip////////////
            Tooltip tooltip = new Tooltip(accompagnements.get(i).getId_task().getStart_date()+"/"+accompagnements.get(i).getId_task().getEnd_date());
            Tooltip.install(finalTitle, tooltip);

            ////////////tooltip///////////
            editItem.setOnAction(event -> {
            });



            deleteItem.setOnAction(event -> {






            });



            contextMenu.getItems().addAll(editItem,deleteItem);

// Attach the context menu to the label
            title.setContextMenu(contextMenu);


            /////////////event////////////
            mesTasksGrid.add(title, i % 3, i / 3);
        }



// set the properties of the grid pane
        mesTasksGrid.setHgap(10);
        mesTasksGrid.setVgap(10);

    }

    @FXML
    public void switchingAccompagnemment()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accompagnement_UserPro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
           // currentStage.setWidth(1350);
          //  currentStage.setHeight(890);
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    @FXML
    public void switchingProfil()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Profil.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
}}

