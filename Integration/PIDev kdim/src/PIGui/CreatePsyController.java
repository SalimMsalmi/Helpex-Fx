/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.psycho;
import PIServices.servicepsycho;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreatePsyController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfMail;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfCode;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_psy(ActionEvent event) throws IOException 
    {
        if ( tfUsername.getText().isEmpty() | tfPassword.getText().isEmpty() | tfMail.getText().isEmpty() | tfCode.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (tfDate.getValue().getYear() < 2006)
        {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Vous devez avoir plus de 15 ans !");
            al2.showAndWait();
        }
        else if (!tfMail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfUsername.getText().equals(tfPassword.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {  
           servicepsycho sp= new servicepsycho();
           sp.Ajouter(new psycho (tfCode.getText(), tfUsername.getText(), tfPassword.getText(), tfMail.getText(), java.sql.Date.valueOf((tfDate.getValue())) )) ;

           JOptionPane.showMessageDialog(null,"Welcome sir ♥");

           FXMLLoader loader= new FXMLLoader(getClass().getResource("profilPsy.fxml"));
           Parent root= loader.load();
           tfUsername.getScene().setRoot(root);

           ProfilPsyController pc= loader.getController();
           pc.setLbUsername(tfUsername.getText());
           pc.setLbMail(tfMail.getText());
           pc.setLbDate(java.sql.Date.valueOf(tfDate.getValue()));
        }
        
    }

    @FXML
    private void cancel_create(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Create.fxml"));
        Parent root= loader.load();
        btnCancel.getScene().setRoot(root);
    }
    
}
