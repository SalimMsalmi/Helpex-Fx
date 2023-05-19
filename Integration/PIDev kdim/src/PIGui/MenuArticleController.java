package PIGui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import PIGui.AjoutArticleController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuArticleController implements Initializable {
int id_user;
    @FXML
    private Button btn_affArticles;
    @FXML
    private Button btn_AjoutArticle;
    
    private Stage primaryStage;
    @FXML
    private Button btn_EditArticles;
    @FXML
    private FontAwesomeIconView btnmodifier;
    @FXML
    private Button btn_AjoutArticle1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GotoArticles(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AjoutArticle.fxml"));
            Stage window = primaryStage;
        Parent rootRec2 = loader.load();
        Scene rec2 = new Scene(rootRec2);
        AjoutArticleController ac  = loader.getController();
        
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        ac.setid(1);
        app.show();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
    }
    
    @FXML
    private void ConsulterArticles(ActionEvent event) throws IOException {
       
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ListeArticles.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
            
        
    }
//    
//    @FXML
//    private void EditerArticles(ActionEvent event) throws IOException {
//        Stage window = primaryStage;
//        Parent rootRec2 = FXMLLoader.load(getClass().getResource("EditerArticle.fxml"));
//        Scene rec2 = new Scene(rootRec2);
//        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app.setScene(rec2);
//        app.show();
//    }

    @FXML
    private void GotoCat(ActionEvent event) throws IOException {
        try {
            Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
//     @FXML
//    private void teste(ActionEvent event) throws IOException {
//         try {
//             Stage window = primaryStage;
//        Parent rootRec2 = FXMLLoader.load(getClass().getResource("test.fxml"));
//        Scene rec2 = new Scene(rootRec2);
//        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app.setScene(rec2);
//        app.show();
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//        
//            
//        
//    }
//    
    
}
