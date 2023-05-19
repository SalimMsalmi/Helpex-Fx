/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.CRUDPoste;

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class DetailController implements Initializable {

    @FXML
    private Label fxper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CRUDPoste cr = new CRUDPoste();
        fxper.setText(cr.afficherPoste().toString());
    }    
    
}
