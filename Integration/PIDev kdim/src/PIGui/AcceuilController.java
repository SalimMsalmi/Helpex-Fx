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
public class AcceuilController implements Initializable {

    @FXML
    private Button btnlogin;
    @FXML
    private Button btncreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotologin(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("LoginWho.fxml"));
        Parent root= loader.load();
        btnlogin.getScene().setRoot(root);
    }

    @FXML
    private void gotocreate(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("captcha.fxml"));
        Parent root= loader.load();
        btnlogin.getScene().setRoot(root);
    }
    
}
