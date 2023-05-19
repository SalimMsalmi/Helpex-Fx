/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Filiere;
import services.FiliereService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ItemFController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label id;
    @FXML
    private Label desc;
    @FXML
    private Button delete;
    @FXML
    private Button modif;
    @FXML
    private Label nom_filiere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) {
         Filiere P=new Filiere();
        P.setId(Integer.parseInt((id.getText())));
         FiliereService t= new FiliereService();
         t.supprimerf(P);
    }

    @FXML
    private void modif(ActionEvent event) {
    }
    
}
