/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.captcha.Captcha;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author yassi
 */
public class CaptchaController implements Initializable {

    @FXML
    private ImageView cap;
    @FXML
    private JFXButton submit;
    @FXML
    private TextField code;
    @FXML
    private JFXButton reset;

    /**
     * Initializes the controller class.
     * @return 
     */
    
    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }
    Captcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        captcha =  setCaptcha();
    
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        if (captcha.isCorrect(code.getText())) {

            String tilte = "Captcha";
            String message = "Correct form";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            
    //     try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Create.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(Agent_mainController.class.getName()).log(Level.SEVERE, null, ex);
        }

         else {

            String tilte = "Captcha";
            String message = "Incorrect form";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            
            captcha =  setCaptcha();
            code.setText("");
        }
    }

    @FXML
    private void reseting(ActionEvent event) {
        captcha =  setCaptcha();
             code.setText("");
    }

}
