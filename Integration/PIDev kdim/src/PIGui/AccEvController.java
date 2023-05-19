/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Islem
 */
public class AccEvController implements Initializable {

    @FXML
    private Button Add;
    @FXML
    private Button edit;
    @FXML
    private Button acivités;
    @FXML
    private Button invitations;
        @FXML
    private Button option;
    @FXML
    private ImageView imgv1;
    @FXML
    private ImageView imgv4;
    @FXML
    private ImageView imgv5;
    @FXML
    private ImageView imgv2;
    @FXML
    private ImageView imgv3;
    @FXML
    private AnchorPane holderPane;   
    @FXML
    private Button Statistic;
    @FXML
    private ImageView back;
    @FXML
    private Button retour;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add_event(ActionEvent event) throws IOException {
         FXMLLoader loader =new FXMLLoader (getClass().getResource("AjouterEvent.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);
    }

    @FXML
    private void edit_event(ActionEvent event) throws IOException {
           FXMLLoader loader =new FXMLLoader (getClass().getResource("EditEvent.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);
    }

    @FXML
    private void activités(ActionEvent event) throws IOException {
           FXMLLoader loader =new FXMLLoader (getClass().getResource("Activités.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);
    }

    @FXML
    private void invitations(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader (getClass().getResource("Invitation.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);
    }

    @FXML
    void options(ActionEvent event) throws IOException {
   FXMLLoader loader =new FXMLLoader (getClass().getResource("EventMetier.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);
    }
     @FXML
    void Statistic(ActionEvent event) throws IOException {
          FXMLLoader loader =new FXMLLoader (getClass().getResource("Statistic.fxml"));
        Parent root= loader.load();
      Add.getScene().setRoot(root);

    }
           @FXML
    private void back(MouseEvent event) {
    
    
}

    @FXML
    private void retour(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ProfilCoach.fxml"));
        Parent root1= loader1.load();
        Add.getScene().setRoot(root1);

    }
}
