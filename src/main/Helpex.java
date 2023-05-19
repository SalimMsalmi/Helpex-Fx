/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import services.UserService;
import utils.DB;
import utils.JavaMail;

import entities.User;
import java.io.IOException;
import java.sql.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/**
 *
 * @author Asus-PC
 */
public class Helpex extends Application {
    // public static void main(String[] args) {
        //DB db= new DB();
       // UserService U= new UserService();
       // Date date2 = Date.valueOf("2000-10-11");
      //  User pro = new User("pro@example.com", "[\"ROLE_PRO\"]","17777", "MARRRRRR", "Mo", "Femme", "Gob", "55655853", "fjfj", "fhhf", date2 , "hhhhhhh", 88888, 1);

        //User client = new User("client@example.com", "","777777777", "mim", "Mo", "Femme", "Gob", "55655853", "fjfj", "fhhf", date2 , 1);
        //System.out.println(U.afficherClients());
       // U.delete(client);
      //  U.registerClient(client);
           //  U.updateProfile(pro);

             
        
   // }
    
    /**
     * @param args the command line arguments
     */
    
        public static User loggedUser = null;

    
     @Override
    public void start(Stage primaryStage)  {
       try{
        Parent root = FXMLLoader.load(getClass().getResource("/apex/helpex/GUI/SignUpPro.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("SignUp Pro");
        primaryStage.setScene(scene);
        primaryStage.show();
       } catch(IOException ex) {
              System.out.println(ex.getMessage());
       }
    }
    
     public static void main(String[] args) {
        launch(args);
    }
}
