/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.poste;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.CRUDPoste;

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class AjouterPController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxPrenom;
    @FXML
    private Button fxAjouter;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        //int id= Integer.parseInt(fxid.getText());
        String nom = fxNom.getText();
        String prenom = fxPrenom.getText();
        poste p = new poste(nom, prenom);
        
        CRUDPoste crud = new CRUDPoste();
        
        crud.ajouterPoste(p);
    }
    
}
