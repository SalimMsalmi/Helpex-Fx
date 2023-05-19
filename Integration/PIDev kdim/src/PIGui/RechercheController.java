/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.simple;
import PIServices.servicesimple;
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
public class RechercheController implements Initializable {

    @FXML
    private TextField lbUsernameSS;
    @FXML
    private ListView<simple> lvS;
    @FXML
    private Button btnPsyS;
    @FXML
    private Button btnNutriS;
    @FXML
    private Button btnCoachS;
    @FXML
    private Button btn_cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lbUsernameSS_Changed(InputMethodEvent event) {
    }

    @FXML
    private void lbUsernameSS_Released(KeyEvent event) 
    {
        servicesimple ss= new servicesimple();
        lvS.getItems().setAll(ss.recherche(lbUsernameSS.getText()));
    }

    @FXML
    private void psy_search(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("RecherchePsy.fxml"));
        Parent root= loader.load();
        btnPsyS.getScene().setRoot(root);
    }

    @FXML
    private void nutri_search(ActionEvent event) {
    }

    @FXML
    private void coach_search(ActionEvent event) {
    }

    @FXML
    private void cancelSearch(ActionEvent event) {
    }
    
}
