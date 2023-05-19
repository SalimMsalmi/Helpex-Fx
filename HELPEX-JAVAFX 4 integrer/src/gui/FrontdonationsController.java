/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import entities.CaisseOrganisation;
import entities.Organisation;
import entities.Poste;
import entities.User;
import help.Help;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import services.CaisseOrganisationCRUD;
import services.OrganisationCRUD;
/**
 * FXML Controller class
 *
 * @author MSI
 */



public class FrontdonationsController implements Initializable {

    @FXML
    private VBox caisseContainerFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CaisseOrganisationCRUD caisseOrganisationCRUD= new CaisseOrganisationCRUD();
        List<CaisseOrganisation> caisseOrganisationList= caisseOrganisationCRUD.afficher();
        for(CaisseOrganisation caisse: caisseOrganisationList){
            LoadItem(caisse);
            
        }
    }
    
public void LoadItem(CaisseOrganisation caisseOrganisation){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("caisseOrgFront.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label goalCaisse = (Label) itemPane.lookup("#GoalCaisse");
        Label descriptionCaisse = (Label) itemPane.lookup("#descriptionCaisse");
        Label montantCaisse = (Label) itemPane.lookup("#MontantCaisse");
        Label nomOrg=(Label) itemPane.lookup("#nomOrg");
        Label IdC=(Label) itemPane.lookup("#IdC");
        goalCaisse.setText(String.valueOf(caisseOrganisation.getGoal()));
        descriptionCaisse.setText(caisseOrganisation.getDescription());
        montantCaisse.setText(String.valueOf(caisseOrganisation.getMontant_caisse_org()));
        IdC.setText(String.valueOf(caisseOrganisation.getId()));
        Organisation organisation= new Organisation();
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        organisation=organisationCRUD.OrgByID(caisseOrganisation.getOrganisation_id());
        nomOrg.setText(organisation.getNom());
        System.out.println(organisation);
        
        caisseContainerFront.getChildren().add(itemPane);
    }    
    
}
