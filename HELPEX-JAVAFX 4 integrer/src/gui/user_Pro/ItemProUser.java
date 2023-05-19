package gui.user_Pro;

import entities.DataSingleton;
import entities.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ItemService;
import services.TasksService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ItemProUser implements Initializable {
    @FXML
    private Label accomswitch;

    @FXML
    private VBox vbox;
    private StringProperty firstNameString = new SimpleStringProperty();
    DataSingleton dataSingleton = DataSingleton.instance ;

    public ItemProUser(){}
    public ItemProUser (String s) {
        firstNameString.set(s);
    }

    public void setStringValue(String value) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listerItems();


    }
    public  void listerItems (){

        ItemService itemService = new ItemService();
        ArrayList<Item>items=itemService.listerItemById(dataSingleton.getValue());
        for (int i = 0; i <items.size(); i++) {
            System.out.println("thheeeeeeeee"+items.size());
            AtomicBoolean areAllVaild = new AtomicBoolean(true);
            if (items.size()==0){
                Label label = new Label(" pas de items attribués :) ") ;
                vbox.getChildren().add(label);
            }else {
            CheckBox checkBox = new CheckBox(items.get(i).getTitre()+"                               "+items.get(i).getTime()); // create checkbox
            int finalI = i;
            int finalI1 = i;
            checkBox.setOnMouseClicked(event -> {checkBox.setSelected(checkBox.isSelected());
                itemService.completeItem(items.get(finalI1).getId());
                //dialog
                createDialog("statuts item ","Item est bien completé","bien fait vous avez terminez un item !");

                ArrayList<Item>items1=itemService.listerItemById(dataSingleton.getValue());
                System.out.println(items1.size());
                for (int j = 0; j <items1.size(); j++){
                    if (items1.get(j).isId_complete()==false){
                        areAllVaild.set(false);
                        System.out.println("not complete");

                    }


                }
                if(areAllVaild.get()){
                    TasksService tasksService = new TasksService();
                    tasksService.MakeTaskValid(dataSingleton.getValue());
                    createDialog("Task est maintenant valid","tout les items sont completés","un email va étre envozer pour notifier le user");

                    System.out.println("all the tasks are done now you need to send email and task becomes valid");
                   // put a notification here
                    sendEmail();
                }

            vbox.getChildren().remove(checkBox);

            });

            vbox.getChildren().add(checkBox); // add checkbox to VBox

        }}
}
public void sendEmail() {
    Properties props = new Properties();

    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication("oussema.ayari2001@gmail.com", "osdysqsfjqbwveyn");
                }
            });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("oussema.ayari2001@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("naimihedia1@gmail.com"));
        message.setSubject("Task information");


        // Construction du contenu de l'email
        String emailContent = "task : " + dataSingleton.getValue() + "\n" +
                " est validé et tout les items son't bien terminés  " ;

        message.setText(emailContent);

        Transport.send(message);

        System.out.println("Email envoyé avec succès");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}



public void createDialog(String title,String header,String label1){
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle(title);
    dialog.setHeaderText(header);

    // Set the dialog's content
    Label label = new Label(label1);
    StackPane pane = new StackPane(label);
    dialog.getDialogPane().setContent(pane);

    // Add a button to close the dialog
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

    // Show the dialog and wait for a response
    dialog.showAndWait();



}



    @FXML
    public void switchingAccompagnemment()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accompagnement_UserPro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();


            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }}




}
