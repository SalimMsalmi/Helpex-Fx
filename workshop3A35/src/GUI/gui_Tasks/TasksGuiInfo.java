package GUI.gui_Tasks;

import entites.Accompagnement;
import entites.Item;
import entites.Tasks;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ItemService;
import services.TasksService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TasksGuiInfo  implements Initializable {

    @FXML
    private Label dash ;
    @FXML
    private GridPane gridBox ;
    @FXML
    private Label accomswitch;
    private int currentUser=10 ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listerTasks();
    }
    public void switch1(){
        System.out.println("heeloo");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI_Tasks.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root,1000,600);

            Stage currentStage = (Stage) dash.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
    public void switch2(){


    }

    @FXML
    public void switchingAccompagnemment()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../admin/accompagnmemnts_admin.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void switch3(){

    }



    public void listerTasks(){
        TasksService tasksService = new TasksService() ;
        ArrayList<Accompagnement> tasks = tasksService.listerTasksofUser(currentUser);
        for (int i = 0; i < tasks.size(); i++){
            Image image ;
            Label title = null;
            if (tasks.get(i).getId_task().isIs_valid()){
                image  = new Image("images/folderyes.png");


            }
            else {
                image  = new Image("images/folderno.png");
            }

            title = new Label(tasks.get(i).getId_task().getTitre());
            title.setFont(new Font(20));

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            title.setGraphic(imageView);

            //////////////event////////////
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Editer");
            MenuItem deleteItem = new MenuItem("supprimer");

            int finalI1 = i;


            Label finalTitle = title;

            ///////////tootltip////////////
            Tooltip tooltip = new Tooltip(tasks.get(i).getId_task().getStart_date()+"/"+tasks.get(i).getId_task().getEnd_date());
            Tooltip.install(finalTitle, tooltip);

            ////////////tooltip///////////
            editItem.setOnAction(event -> {
                MakeDialog(tasks.get(finalI1).getId_task(), finalTitle)  ;          });
            int finalI = i;



            deleteItem.setOnAction(event -> {
                System.out.println(tasks.get(finalI).getId());
                try {
                    tasksService.SupprimerTask(tasks.get(finalI).getId());
                } catch (SQLException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "task contient des items , supprimer tous les items pour pouvoir le supprimer!!", ButtonType.OK);
                    a.showAndWait();
                    throw new RuntimeException(e);
                }


                gridBox.getChildren().clear();
                gridBox.layout();
                gridBox.requestLayout();
                listerTasks();

            });



            contextMenu.getItems().addAll(editItem,deleteItem);

// Attach the context menu to the label
            title.setContextMenu(contextMenu);


            /////////////event////////////
            gridBox.add(title, i % 3, i / 3);
        }



// set the properties of the grid pane
        gridBox.setHgap(10);
        gridBox.setVgap(10);

    }

    public void MakeDialog(Tasks task ,Label title){
        // Create a new stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        // dialog.initOwner(primaryStage);
        dialog.setTitle("Editer task");
        dialog.setHeight(300);
        dialog.setWidth(300);


        // Create the form to display the selected row's data
        Label Titrelabel = new Label("Titre task : ");
        TextField TitreField = new TextField(task.getTitre());

        Label Date_startLabel = new Label("date de début  : ");
        DatePicker Date_startField = new DatePicker(task.getStart_date().toLocalDate());

        Label Date_finLabel = new Label("date de fin  : ");
        DatePicker Date_ENDField = new DatePicker(task.getEnd_date().toLocalDate());

        Label validlabel = new Label("Status task : ");
        CheckBox checkBox = new CheckBox("valide");
        checkBox.setSelected(task.isIs_valid());



        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            //task.setTitre(TitreField.getText());
            title.setText(TitreField.getText());
            TasksService tasksService = new TasksService();
            tasksService.EditerTask(
                    new Tasks(TitreField.getText(),java.sql.Date.valueOf(Date_startField.getValue()),java.sql.Date.valueOf(Date_ENDField.getValue()),checkBox.isSelected()
                    ), task.getId() );

//refresh/////////////***-////////*-////


            dialog.close();
            gridBox.requestLayout();
        });

        VBox vbox = new VBox(Titrelabel,TitreField,Date_startLabel,Date_startField,Date_finLabel,Date_ENDField,validlabel,checkBox, saveButton);
        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }


}
