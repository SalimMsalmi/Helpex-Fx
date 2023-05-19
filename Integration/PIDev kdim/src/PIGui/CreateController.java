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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateController implements Initializable {

    @FXML
    private Button btnNewSimple;
    @FXML
    private Button btnNewPsy;
    @FXML
    private Button btnNewNutri;
    @FXML
    private Button btnNewCoach;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNewAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void new_simp(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("CreateSimple.fxml"));
        Parent root= loader.load();
        btnNewSimple.getScene().setRoot(root);
    }

    @FXML
    private void new_psy(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("CreatePsy.fxml"));
        Parent root= loader.load();
        btnNewPsy.getScene().setRoot(root);
    }

    @FXML
    private void new_nutri(ActionEvent event) throws IOException
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("CreateNutri.fxml"));
        Parent rootX= loader1.load();
        btnNewNutri.getScene().setRoot(rootX);
    }

    @FXML
    private void new_coach(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("CreateCoach.fxml"));
        Parent root1= loader1.load();
        btnNewCoach.getScene().setRoot(root1);
    }
    
    @FXML
    private void new_admin(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("CreateA.fxml"));
        Parent root= loader.load();
        btnNewAdmin.getScene().setRoot(root);
    }
    
    @FXML
    private void cancel(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root= loader.load();
        btnCancel.getScene().setRoot(root);
    }

    
}
