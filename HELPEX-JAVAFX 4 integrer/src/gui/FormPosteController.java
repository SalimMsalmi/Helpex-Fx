package gui;

import entities.Poste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import services.CRUDPoste;

public class FormPosteController implements Initializable {
    @FXML
    public TextField Titre;
    @FXML
    public TextArea Description;
    private Poste p;

    public Poste getP() {
        return p;
    }

    public void setP(Poste p) {
        this.p = p;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
   
    @FXML
    private void ModifyPoste(ActionEvent event) {
        String titre;
        String description;
        titre = Titre.getText();
        description = Description.getText();
        CRUDPoste t= new CRUDPoste();
        t.modifierPoste(p,titre,description);
    }

}
