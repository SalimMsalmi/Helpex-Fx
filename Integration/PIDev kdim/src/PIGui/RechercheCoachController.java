/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.coach;
import PIServices.servicecoach;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RechercheCoachController implements Initializable {

    @FXML
    private TextField lbUsernameSC;
    @FXML
    private ListView<coach> lvC;
    @FXML
    private Button btnCancelR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lbUsernameSP_Changed(InputMethodEvent event) {
    }

    @FXML
    private void lbUsernameSP_Released(KeyEvent event) 
    {
        servicecoach sp= new servicecoach();
        lvC.getItems().setAll(sp.recherche(lbUsernameSC.getText()));
    }

    @FXML
    private void cancel_recherche(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Recherche.fxml"));
        Parent root= loader.load();
        btnCancelR.getScene().setRoot(root);
    }
    
}
