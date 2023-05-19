/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ItemUserFrontController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label nom_pro;
    @FXML
    private Label prenom_pro;
    @FXML
    private Label adresse_pro;
    @FXML
    private Label num_pro;
    @FXML
    private Label tarif_pro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Consult(ActionEvent event) {
    }
    
}
