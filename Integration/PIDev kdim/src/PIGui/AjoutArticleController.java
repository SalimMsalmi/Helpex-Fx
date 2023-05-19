/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import PIClass.Article;
import PIClass.userclient;
import PIServices.ServiceArticle;
import PIServices.ServiceCategorie;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutArticleController implements Initializable {
 int id_user;
 public void setid(int i)
 {
     id_user =i ;
 }
    @FXML
    private JFXTextField tauteur;
    @FXML
    private JFXTextField ttitre;
    @FXML
    private JFXComboBox<String> tcategorie;
    @FXML
    private TextArea tdescription;
    @FXML
    private Button btn_ph;
    @FXML
    private JFXButton tbtn;
    @FXML
    private Label image;

    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    LocalDate d = java.time.LocalDate.now();
    @FXML
    private ImageView imview;
    @FXML
    private JFXButton tbtn1;
    @FXML
    private FontAwesomeIconView btnactualiser;
    @FXML
    private JFXButton Menu_Article;
    @FXML
    private FontAwesomeIconView btnback;
    @FXML
    private JFXTextField linkk;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(id_user);
        ServiceCategorie cat = new ServiceCategorie();
        cat.afficher().forEach(e -> {
            tcategorie.getItems().add(e.getTitre_cat());
        });

    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException {
//        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
//        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
//        if (selectedFiles != null) {
//            for (int i = 0; i < selectedFiles.size(); i++) {
//                listView.getItems().add(selectedFiles.get(i).getAbsolutePath());
//            }
//        } else {
//            System.out.println("Image non valide");
//        }
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            //this.image.setText(selectedFile.getName());
            this.image.setText(selectedFile.toURI().toURL().toString());
            imview.setImage(image);
        }

//        if (selectedFile != null) {
//            Upload u = new Upload();
//            u.upload(selectedFile);
//            image.setText(selectedFile.getName());
//           
//            BufferedImage bufferedImage = ImageIO.read(selectedFile);
//           WritableImage image1 = SwingFXUtils.toFXImage(bufferedImage, null);
//            imview.setImage(image1);
//            selectedFile.toURI().toURL().toString() ;
        // JOptionPane.showMessageDialog(null, "PHoto Ajouté! ☺ ");
    }

    @FXML
    private void AjoutArticle(ActionEvent event) throws IOException {
        if (ttitre.getText().isEmpty() || tdescription.getText().isEmpty() || tcategorie.getValue() == null) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();

        } else if (ttitre.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[0-9].*") || tdescription.getText().matches(".*[%-@-.-/-!-;-,-_].*") || ttitre.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {
            Article art = new Article();
            if(linkk.getText().isEmpty())
            {
                art = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(),image.getText(), (String) tcategorie.getValue());
            }
            else if(!linkk.getText().isEmpty())
            {
                art = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(),linkk.getText(), (String) tcategorie.getValue());
            }
            ServiceArticle sa = new ServiceArticle();
            //Article test = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(), (String) tcategorie.getValue());
           // Article test = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(d), tdescription.getText(),image.getText(), (String) tcategorie.getValue());
//           if(art.getAuteur_art().compareTo(userclient.getUsername()) == 0){
//               sa.ajouter(art);
//           }else{
//               
//           }
           
            
            //AjouterPhoto(event);
            sa.ajouter(art);
            Notifications notificationBuilder = Notifications.create()
                    .title("Succes").text("An article has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
            notificationBuilder.show();
            JOptionPane.showMessageDialog(null, "Article Ajouté! ☺ ");
            ttitre.setText("");
            tauteur.setText("");
            tdescription.setText("");
            image.setText("");
            Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
        }

        //Autre Methode :
//        ServiceArticle sa = new ServiceArticle();
//
//        //java.util.Date date = Date.from(tdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Article test = new Article(ttitre.getText(), tauteur.getText(), Date.valueOf(tdate.getValue()), tdescription.getText(), (String) tcategorie.getValue());
//        sa.ajouter(test);
//        JOptionPane.showMessageDialog(null, "Article Ajouté! ☺ ");
System.out.println(id_user);
    }

    @FXML
    private void Annuler(Event event) {
        ttitre.setText("");
        tauteur.setText("");
        tdescription.setText("");
        image.setText("");
    }

}
