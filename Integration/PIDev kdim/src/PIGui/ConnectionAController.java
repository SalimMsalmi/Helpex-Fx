/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.admin;
import PIServices.serviceadmin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConnectionAController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LostPass(ActionEvent event) {
    }

    @FXML
    private void GoToCreateA(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("captcha.fxml"));
        Parent root= loader.load();
        btnLogin.getScene().setRoot(root);
    }

    @FXML
    private void login(ActionEvent event) throws IOException 
    {
        serviceadmin sA = serviceadmin.getInstance();
		admin u = sA.login(tfUsername.getText(), tfPassword.getText());
		if (u.getId_user() > -1) {
                        sA.userInfos = u;
			FXMLLoader loader1= new FXMLLoader(getClass().getResource("GestionU.fxml"));              
			Parent root1= loader1.load();
			btnLogin.getScene().setRoot(root1);

           GestionUController controller = (GestionUController) loader1.getController();
            controller.setAdmin(u);
			controller.refresh();
		}
		else {
			Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Incorrect Email or Password! ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		}
    }
    
}
