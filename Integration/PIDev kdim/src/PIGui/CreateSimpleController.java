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
public class CreateSimpleController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnConfirm;
    @FXML
    private TextField tfMail;
    @FXML
    private TextField tfUsername;
    @FXML
    private DatePicker tfDate;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutersimple(ActionEvent event) throws IOException 
    {
        if ( tfUsername.getText().isEmpty() | tfPassword.getText().isEmpty() | tfMail.getText().isEmpty() )
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
            servicesimple ss= new servicesimple();
            ss.Ajouter( new simple ( tfUsername.getText(), tfPassword.getText(), tfMail.getText(), java.sql.Date.valueOf((tfDate.getValue()))) );

            JOptionPane.showMessageDialog(null,"Welcome ♥");

            FXMLLoader loader= new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent root= loader.load();
            tfUsername.getScene().setRoot(root);

            ProfilController pc= loader.getController();
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
