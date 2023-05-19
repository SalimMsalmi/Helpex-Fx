/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ProfileController implements Initializable {

    @FXML
    private Label email;
    @FXML
    private Label num;
    @FXML
    private Label adresse;
    @FXML
    private Label bio;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signout(ActionEvent event) {
    }

    @FXML
    private void updateprofile(ActionEvent event) {
    }
    
}
