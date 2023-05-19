/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Helpex;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ProUsersFrontController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField search;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlMenus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          UserService us=new UserService();
        List<User> usersList;
        usersList=us.afficherProUsers();
        
        for (User user : usersList)
            LoadItem(user);
    }    

    public void LoadItem(User user){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemUserFront.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label emailLabel = (Label) itemPane.lookup("#nom_pro");
        Label prenomLabel = (Label) itemPane.lookup("#prenom_pro");
                Label adresseLabel = (Label) itemPane.lookup("#adresse_pro");
                        Label numLabel = (Label) itemPane.lookup("#num_pro");
        Label tarifLabel = (Label) itemPane.lookup("#tarif_pro");


        
        emailLabel.setText(user.getNom());
      prenomLabel.setText(user.getPrenom());
        adresseLabel.setText(user.getAdresse());
         numLabel.setText(user.getNum_tel());
                 tarifLabel.setText(String.valueOf(user.getNum_tel()));
        pnItems.getChildren().add(itemPane);
    }
    
    @FXML
    private void signout(ActionEvent event) {
         Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.pnItems.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
}
