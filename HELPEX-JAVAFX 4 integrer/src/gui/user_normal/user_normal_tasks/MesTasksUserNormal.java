package gui.user_normal.user_normal_tasks;

import entities.Accompagnement;
import help.Helpex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import services.TasksService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MesTasksUserNormal implements Initializable {
    private int current_user_pro_connectes= Helpex.loggedUser.getId() ;
    @FXML
    private GridPane MyUserGrid;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listerTasks();

    }
    public void listerTasks(){
        TasksService tasksService = new TasksService() ;
        ArrayList<Accompagnement> accompagnements = tasksService.listerTasksofUser(current_user_pro_connectes);
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
            MyUserGrid.add(title, i % 3, i / 3);
        }



// set the properties of the grid pane
        MyUserGrid.setHgap(10);
        MyUserGrid.setVgap(10);

    }
}
