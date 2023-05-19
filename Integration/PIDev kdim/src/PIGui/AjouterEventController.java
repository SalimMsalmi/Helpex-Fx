/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;
import PIServices.Browser;
import PIServices.GererEv;
import PIServices.Mapping;
import static PIServices.Mapping.browser;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import PIClass.Ev;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class AjouterEventController implements Initializable {

    @FXML
    private AnchorPane zone_aj;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField age_min;
    @FXML
    private TextField emplacement;
    @FXML
    private TextField text_nom;
       @FXML
    private JFXTimePicker temps_deb;

    @FXML
    private JFXTimePicker temps_fin;
    @FXML
    private TextField age_max;
    @FXML
    private Button Ajouter;
    @FXML
    private Button back;
    @FXML
    private Label nom;
    @FXML
    private Label err_image;
    @FXML
    private DatePicker date_deb;
  
    @FXML
    private ComboBox<String> type;
    @FXML
    private ImageView imgview1;
    @FXML
    private ImageView imgview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> event = FXCollections.observableArrayList("sportif","educatif","loisir");
        type.setItems(event);
    }    

    @FXML
    private void Control(KeyEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
      GererEv e=new GererEv();      
    if (text_nom.getText().isEmpty() || emplacement.getText().isEmpty() || controleComboBox(type)|| age_min.getText().isEmpty() || age_max.getText().isEmpty())
   {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Veuillez remplir tous les champs");
                     alert.showAndWait();}
// else if(validateDatePickerdeb(date_deb)||validateDatePickerfin(date_fin)||validateTimePickerdeb(temps_deb) || validateTimePickerfin(temps_fin)||controleTextFieldNonNumerique(text_nom)||controleTextFieldNonNumerique(emplacement)|| 
//                         controleTextFieldNumerique(age_min)||controleTextFieldNumerique(age_max));
     else {
   Ev test = new Ev(text_nom.getText(),type.getValue(),emplacement.getText(),Date.valueOf(date_deb.getValue()),Date.valueOf(date_fin.getValue()),Time.valueOf(temps_deb.getValue()),Time.valueOf(temps_fin.getValue()),Integer.parseInt(age_min.getText()),Integer.parseInt(age_min.getText()));
       e.ajouter(test);
    JOptionPane.showMessageDialog(null,"event ajouté");}
    //API Notification lors de l'ajout d'un evenement
    Notifications notificationBuilder = Notifications.create()
    .title("new event")
    .text("Welcome to your event")
    .hideAfter(javafx.util.Duration.seconds(5))
    .position(Pos.TOP_CENTER);
    notificationBuilder.show(); }
    //Les fonctions de controle de saisie
     public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des lettres");
    alert.showAndWait();
            return true;
        }
        return false;
    }
      public boolean controleTextFieldNumerique(TextField textField) {
        if (textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des chiffres");
    alert.showAndWait();
            return true;
        }
             return false;
    }
        public boolean validateDatePickerfin(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("veuillez choisir la date de fin ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
    public boolean validateDatePickerdeb(DatePicker d1)
    {
          if(d1.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("veuillez choisir une date de  début");
            alert.showAndWait();
            return false;
         }
           return true;
        }
      public boolean validateTimePickerdeb( JFXTimePicker m)
    {
          if(m.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("veuillez choisir le temps  de  début");
            alert.showAndWait();
            return false;
         }
           return true;
        }
            public boolean validateTimePickerfin( JFXTimePicker f)
    {
          if(f.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("veuillez choisir le temps de fin");
            alert.showAndWait();
            return false;
         }
           return true;
        }
     public boolean controleComboBox(ComboBox<String> combo) {
        if (combo.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez chosir un Type");
        alert.showAndWait();
        return true;
        }
        return false;
    }
        @FXML
    void back(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load();
        back.getScene().setRoot(root);  
}
    
      @FXML
    void AfficherMap(ActionEvent event) {
          System.out.println("hallo did you work?");
        Stage stage = new Stage();
        Scene scene;
        Browser browser = new Browser();
        scene = new Scene(browser, 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }
  
}
