/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Filiere;
import main.Helpex;
import services.FiliereService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class FilController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label title;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Button addBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 FiliereService us=new FiliereService();
        List<Filiere> fList;
      fList=us.afficherf();
      pnlOverview.toFront();
        for (Filiere fil : fList)
            LoadItemf(fil);  
    }    
 public void LoadItemf(Filiere f){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemF.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

       Label idLabel = (Label) itemPane.lookup("#id");
        Label nfLabel = (Label) itemPane.lookup("#nom_filiere");
                Label descLabel = (Label) itemPane.lookup("#desc");
                    

      idLabel.setText(String.valueOf(f.getId()));
        nfLabel.setText(f.getNomF());
        descLabel.setText(f.getDescF());
        pnItems.getChildren().add(itemPane);
    }

    
    @FXML
    private void filieres(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("addF.fxml")) ;
              try {
                  Parent root= loader.load();
                   Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
    }
    
}
