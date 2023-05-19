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
public class ModifProfilCoachController implements Initializable {

    @FXML
    private DatePicker tfDateU;
    @FXML
    private TextField tfMailU;
    @FXML
    private TextField tfUsernameU;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancelU;
    @FXML
    private PasswordField tfPasswordU;
    @FXML
    private TextField tfIdU;
    @FXML
    private TextField tfCodeU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) throws IOException 
    {
        if ( tfUsernameU.getText().isEmpty() | tfPasswordU.getText().isEmpty() | tfMailU.getText().isEmpty() )
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!tfMailU.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else if (tfUsernameU.getText().equals(tfPasswordU.getText()))
        {
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez ne pas mettre votre username en tant que mot de passe ! ");
            a3.showAndWait();
        }
        else
        {
            servicecoach sc= new servicecoach();
            sc.Modifier(new coach (tfUsernameU.getText(), tfPasswordU.getText(), tfMailU.getText(), java.sql.Date.valueOf((tfDateU.getValue())) ));

            JOptionPane.showMessageDialog(null,"Coach modified");

            FXMLLoader loader2= new FXMLLoader(getClass().getResource("ProfilCoach.fxml"));
            Parent root0= loader2.load();
            btnUpdate.getScene().setRoot(root0);

            ProfilController pc= loader2.getController();
            pc.setLbUsername(tfUsernameU.getText());
            pc.setLbMail(tfMailU.getText());
            pc.setLbDate(java.sql.Date.valueOf(tfDateU.getValue()));
        }
        
    }

    @FXML
    private void cancelupdate(ActionEvent event) {
    }
    
}
