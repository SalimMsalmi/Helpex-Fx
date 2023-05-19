/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.Reclamation;
import PIServices.ServiceBadWords;
import PIServices.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class AddRecController implements Initializable {
   
    @FXML
    private Button email;
    @FXML
    private Button xbtn;
    @FXML
    private Button home;
    @FXML
    private Button sub;
    @FXML
    private ComboBox<String> area_recBox;
    @FXML
    private TextField textobj;
    @FXML
    private TextArea textsuj;
    
    private final ObservableList <String> area_recList = FXCollections.observableArrayList("Articles","Chat","Events","Feed","Pubs","Tasks");
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Combobox
        area_recBox.setValue("Select the area of the problem");
        area_recBox.setItems(area_recList);
        //Bad Words
        ServiceBadWords.loadConfigs(); 
    }
               
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeRec.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Welcome HOOOME !!");  
    }
    
    @FXML
    private void AddRec(ActionEvent event) throws IOException {
         if(textobj.getText().isEmpty() | textsuj.getText().isEmpty() | area_recBox.getValue()==null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty fields");
            a.showAndWait();
        }
         else {
              ArrayList<String> badWordsList = ServiceBadWords.badWordsFound(textsuj.getText());
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
              else{
        ServiceReclamation srec = new ServiceReclamation();
        srec.envoyer(new Reclamation(2,"JM",textobj.getText(), (String) area_recBox.getValue(), textsuj.getText()));
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your report has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
    }}
    
    @FXML
    private void MailingRec(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MailRec.fxml"));
        Parent root = loader.load(); 
        email.getScene().setRoot(root);
    }
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");  
    }
   
}