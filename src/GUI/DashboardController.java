/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.User;
import main.Helpex;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;

public class DashboardController implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    @FXML
    private Label title;
    @FXML
    private Label currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                currentUser.setText(Helpex.loggedUser.getEmail());

        UserService us=new UserService();
        List<User> usersList;
        usersList=us.afficherProUsers();
        
        for (User user : usersList)
            LoadItem(user);

    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnSignout)
        {
           Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void LoadItem(User user){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemUser.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label emailLabel = (Label) itemPane.lookup("#email");
        Label sexeLabel = (Label) itemPane.lookup("#sexe");
                Label adresseLabel = (Label) itemPane.lookup("#adresse");
                        Label numLabel = (Label) itemPane.lookup("#num_tel");
        Label enabledLabel = (Label) itemPane.lookup("#enabled");


        

        
        emailLabel.setText(user.getEmail());
      sexeLabel.setText(user.getSexe());
        adresseLabel.setText(user.getAdresse());
         numLabel.setText(user.getNum_tel());
                 enabledLabel.setText(user.getNum_tel());
        pnItems.getChildren().add(itemPane);
    }
    
    private void refresh() {

        pnItems.getChildren().clear();
        UserService us=new UserService();
                List<User> usersList;

           usersList=us.afficherProUsers();
 for (User user : usersList)
            LoadItem(user);
    }

    @FXML
    private void filieres(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Fil.fxml"));
        try {
            Parent root = loader.load();
            title.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}