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
public class LoginWhoController implements Initializable {

    @FXML
    private Button loginS;
    @FXML
    private Button loginA;
    @FXML
    private Button loginC;
    @FXML
    private Button loginP;
    @FXML
    private Button loginN;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotosimple(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ConnectionU.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }

    @FXML
    private void gotoadmin(ActionEvent event) throws IOException {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ConnectionA.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }

    @FXML
    private void gotocoach(ActionEvent event) throws IOException
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ConnectionC.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }

    @FXML
    private void gotopsy(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ConnectionP.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }

    @FXML
    private void gotonutri(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("ConnectionN.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root1= loader1.load();
        loginS.getScene().setRoot(root1);
    }
    
}
