/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class SignUpChooseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void client(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("SignUpCli.fxml")) ;
              try {
                  Parent root= loader.load();
                   Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
    }

    @FXML
    private void pro(ActionEvent event) {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("SignUpPro.fxml")) ;
              try {
                  Parent root= loader.load();
                    Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
    }

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml")) ;
              try {
                  Parent root= loader.load();
                    Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                } 
    }
    
}
