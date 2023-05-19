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
public class ConnectionPController implements Initializable {

    @FXML
    private TextField TextUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink CreateNew;
    @FXML
    private PasswordField TextPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginP(ActionEvent event) throws IOException 
    {
        servicepsycho sU = servicepsycho.getInstance();
		psycho u = sU.login(TextUsername.getText(), TextPassword.getText());
		if (u.getId_user() > -1) {
                        sU.userInfos = u;
			FXMLLoader loader1= new FXMLLoader(getClass().getResource("ProfilPsy.fxml"));              
			Parent root1= loader1.load();
			btnLogin.getScene().setRoot(root1);

           ProfilPsyController controller = (ProfilPsyController) loader1.getController();
            controller.setPsy(u);
			controller.refresh();
		}
		else {
			Notifications n;
            n = Notifications.create()
                    .title("SUCCESS")
                    .text("  Incorrect Email or Password! ")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		}
    }

    @FXML
    private void CreateNewPsy(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("captcha.fxml"));
        Parent root= loader.load();
        btnLogin.getScene().setRoot(root);
    }
    
}
