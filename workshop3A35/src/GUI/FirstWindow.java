/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author belkn
 */
public class FirstWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

           // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/GUI_Tasks.fxml"));
            //Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/Gui_taskAdmin.fxml"));
         // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/accompagnement_button.fxml"));



            //  Parent  root = FXMLLoader.load(getClass().getResource("../GUI/user_Pro/Accompagnement_UserPro.fxml"));
         // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/user_normal/user_normal_tasks/mes_tasks_user_normal.fxml"));
          // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/admin/accompagnmemnts_admin.fxml"));



            ///////////////////admin////////////////////
             Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/Gui_taskAdmin.fxml"));

            ///////////////////admin////////////////////

            ////////////////////user 3adi//////////
           // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/GUI_Tasks.fxml"));

            ///////////////////user 3adi /////////////


            //////////////////user pro ///////////////
       // Parent  root = FXMLLoader.load(getClass().getResource("../GUI/user_Pro/mes_tasks_pro.fxml"));
            /////////////////user pro //////////////

           // Scene scene = new Scene(root,1350,890);
            Scene scene = new Scene(root);
            Image icon = new Image("images/helpexpro.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Helpex");
            primaryStage.setScene(scene);

            primaryStage.setResizable(true);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
