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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConnectionUController implements Initializable {

    @FXML
    private TextField TextUsername;
    @FXML
    private Hyperlink CreateNew;
    @FXML
    private PasswordField TextPassword;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginU(ActionEvent event) throws IOException 
    {
        servicesimple sU = servicesimple.getInstance();
		simple u = sU.login(TextUsername.getText(), TextPassword.getText());
		if (u.getId_user() > -1) {
                        sU.userInfos = u;
			FXMLLoader loader1= new FXMLLoader(getClass().getResource("Profil.fxml"));              
			Parent root1= loader1.load();
			btnLogin.getScene().setRoot(root1);

           ProfilController controller = (ProfilController) loader1.getController();
            controller.setSimple(u);
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

    @FXML
    private void CreateNewUser(ActionEvent event) throws IOException 
    {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("captcha.fxml"));
        Parent root= loader.load();
        btnLogin.getScene().setRoot(root);
    }
    
}
