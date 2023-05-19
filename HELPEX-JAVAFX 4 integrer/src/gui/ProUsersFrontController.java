/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Accompagnement;
import entities.Tasks;
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
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import help.Help;
import services.AccompagnementService;
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
    @FXML
    private Label label;
    @FXML
    private Button signoutBtn;
    @FXML
    MenuButton accompagnment_button  ;
    private User myUser= Help.loggedUser;
    private User thisUser =new User(8);



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
            MenuButton button =(MenuButton)itemPane.lookup("#accompagnment_button");


        
        emailLabel.setText(user.getNom());
      prenomLabel.setText(user.getPrenom());
        adresseLabel.setText(user.getAdresse());
         numLabel.setText(user.getNum_tel());
                 tarifLabel.setText(String.valueOf(user.getTarif()));

        pnItems.getChildren().add(itemPane);


    }


    public void setAccompagnment_button(){
        AccompagnementService accompagnementService = new AccompagnementService();
        List<Tasks> myTasks = accompagnementService.lister_tasks_for_user(myUser.getId()) ;
        //    accompagnment_button.
        for(Tasks task :myTasks){
            int idTask = task.getId();

            MenuItem menuItem = new MenuItem(task.getTitre());
            menuItem.setOnAction(e->{
                //sendSms();

                accompagnementService.EnvoierAccompagnementPartie3(new Accompagnement(new Tasks(idTask),false,myUser,thisUser)) ;
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "ACCOMPAGNEMENT ENVOYER !!", ButtonType.OK);
                a.showAndWait();
                accompagnment_button.getItems().remove(menuItem);
                System.out.println("the idddddddddds"+idTask);

            });

            accompagnment_button.getItems().add(menuItem);
        }

    }
    @FXML
    private void pro(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProUsersFront.fxml"));
                Parent root = loader.load();
                this.pnItems.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   

    @FXML
    private void signout(ActionEvent event) {
        Help.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.search.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    @FXML
    private void accompButton(ActionEvent event) {

        FXMLLoader loader= null ;

        try {

            if (Help.loggedUser.getRoles().equals("[\"ROLE_PRO\"]"))
            {  loader = new FXMLLoader(getClass().getResource("user_Pro/mes_tasks_pro.fxml"));

            }
            else if (Help.loggedUser.getRoles().equals("[\"ROLE_USER\"]")){
                loader = new FXMLLoader(getClass().getResource("gui_Tasks/GUI_Tasks.fxml"));
            }
            Parent root = loader.load();
            this.search.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void profileNavBar(ActionEvent event) {
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent root = loader.load();
                this.search.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void shop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            this.search.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
}
