/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.ServiceBadWords;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class MailRecController implements Initializable {

    @FXML
    private TextField destad;
    @FXML
    private TextArea msg;
    @FXML
    private TextField myad;
    @FXML
    private TextField obj;
    @FXML
    private Button xbtn;
    @FXML
    private Button ret;
    @FXML
    private Button send;
    @FXML
    private Label sent;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ServiceBadWords.loadConfigs();
    }    

    @FXML
    private void ReturnToRec(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRec.fxml"));
        Parent root = loader.load(); 
        ret.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Welcome back !!");  
    }

    @FXML
    private void SendEmail(ActionEvent event) {
        if(destad.getText().isEmpty() | myad.getText().isEmpty() | password.getText().isEmpty() | obj.getText().isEmpty() |  msg.getText().isEmpty()){
            Alert ops = new Alert(Alert.AlertType.ERROR);
            ops.setHeaderText(null);
            ops.setContentText("Please fill in the empty fields");
            ops.showAndWait();
        }
        else if (!myad.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$") && !destad.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$") )
        {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez une adresse mail valide ! ");
            a2.showAndWait();
        }
        else {
        
            ArrayList<String> badWordsList = ServiceBadWords.badWordsFound(msg.getText());
              if (badWordsList.size()>0)
              {
           Notifications notificationBuilder = Notifications.create()
            .title("No way !").text("Inappropriate word(s) has been detected, please rewrite it").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
            
            
            String destination = destad.getText();
            String myaddress = myad.getText();
            String host = "smtp.gmail.com";
            final String username = myad.getText();
            final String passwd = password.getText();
        
        
        //Mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, passwd);
            }
        });
        
        try{
            //Create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(myaddress));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(destination));
            m.setSubject(obj.getText());
            m.setText(msg.getText());

            //Send mail
            Transport.send(m);
            sent.setVisible(true);
            System.out.println("Done !");
            Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your mail has been sent !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();

        }   catch (MessagingException e){
            e.printStackTrace();
        }
        }
    }
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");  
    }
}
