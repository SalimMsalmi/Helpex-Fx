/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.CaisseOrganisation;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CaisseOrganisationCRUD;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CaisseOrgFrontController implements Initializable {

    CaisseOrganisation caisse;
    @FXML
    private Label nomOrg;
    @FXML
    private Label GoalCaisse;
    @FXML
    private Label MontantCaisse;
    @FXML
    private Label descriptionCaisse;
    @FXML
    private Label IdC;

    public CaisseOrganisation getCaisse() {
        return caisse;
    }

    public void setCaisse(CaisseOrganisation caisse) {
        this.caisse = caisse;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void payer(ActionEvent event) {
        
         FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("stripe.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(OrganisationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            StripeController stripeCont=loader.getController();
                            stripeCont.setCaisseId(Integer.valueOf(IdC.getText()));
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }
    
    
    
}
