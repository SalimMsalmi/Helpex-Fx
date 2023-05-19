/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.GesInv;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import PIClass.Invitation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class InvitationController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField nom2;
    @FXML
    private TextField nom1;
    @FXML
    private TextField nom21;
    @FXML
    private Button valider;
    @FXML
    private Button envoyer;
  @FXML
    private ImageView imgview2;

    @FXML
    private ImageView imgview;
      @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
//Valider la suppression d'une invitation
    @FXML
    private void valider(ActionEvent event) {
        GesInv inv =new GesInv();
       
                 if (nom1.getText().isEmpty() || nom21.getText().isEmpty() )
               
                 {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Veuillez remplir tous les champs");
                     alert.showAndWait();}
                 else if(controleTextFieldNonNumerique(nom1)||controleTextFieldNonNumerique(nom21));
                  else
                  {
                     Invitation test1 = new Invitation((nom1.getText()),(nom21.getText()));
                     String x = nom1.getText();
                     String v = nom21.getText();
                     inv.SupprimerAp(v,x);
                     JOptionPane.showMessageDialog(null, "invitation supprimée");
                 }
    }
//Envoyer une invitation
    @FXML
    private void envoyer(ActionEvent event) {
        GesInv e=new GesInv();
         if (nom.getText().isEmpty() || nom2.getText().isEmpty() )
               
                 {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Veuillez remplir tous les champs");
                     alert.showAndWait();}
                 else if(controleTextFieldNonNumerique(nom)||controleTextFieldNonNumerique(nom2));
    else {
    Invitation test = new Invitation((nom.getText()),(nom2.getText()));
    String Str1=nom.getText() ;
    String Str2= nom2.getText();
            
    e.ajouterAp(Str2,Str1);
    JOptionPane.showMessageDialog(null,"Invitation envoyée");
}
    }
//Le controle de saisie

    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des lettres");
    alert.showAndWait();
            return true;
        }
        return false;
    }
    

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 
//        back.getOnMouseMoved().getClass().getResource("../gui/AccEv.fxml");
        back.getScene().setRoot(root); 
    
   
}

    }

 





