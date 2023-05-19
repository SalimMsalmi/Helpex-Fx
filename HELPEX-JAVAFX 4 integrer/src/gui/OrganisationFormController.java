package gui;

import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import services.OrganisationCRUD;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrganisationFormController implements Initializable {

    public TextField nom;
    public TextField email;
    public TextField desc;
    public TextField numero;
    public TextField document;
    public TextField logo;
    public TextField payment;
    public Button Formbtn;
    Organisation organisation = null;
    private boolean update;
    int OrganisationId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
    public boolean testPattern(String regex, String input){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);

        return matcher.find();
    }
    @FXML
    private void AddOrganisation(ActionEvent event) {
        String description;
        String emailOrg;
        String numero_tel;
        String documentOrg;
        String payment_info;
        String nomOrg;
        String logoOrg;
        nomOrg = nom.getText();
        emailOrg = email.getText();
        description = desc.getText();
        numero_tel = numero.getText();
        documentOrg = document.getText();
        payment_info = payment.getText();
        logoOrg = logo.getText();
        if(testPattern("[a-z]+",nomOrg) &&testPattern("[a-z]+.pdf$",documentOrg)&&testPattern("[a-z]+@gmail.com$",emailOrg)&&testPattern("[a-z]+",description)&&testPattern("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",numero_tel)&&testPattern("[a-z]+",payment_info)&&testPattern("[a-z]+.png",logoOrg)){
            if(update){
                OrganisationCRUD organisationCRUD=new OrganisationCRUD();
                organisationCRUD.modifierOrg(OrganisationId,description,emailOrg,numero_tel,documentOrg,payment_info,nomOrg,logoOrg);
            }else {
                Organisation organisation = new Organisation(description, emailOrg, numero_tel, documentOrg, payment_info, nomOrg, logoOrg);
                OrganisationCRUD organisationCRUD=new OrganisationCRUD();
                organisationCRUD.ajouterOrg(organisation);
            }
        }else {
            System.out.println("erreur");
            Alert alert=new Alert(Alert.AlertType.ERROR,"saisie ghalet", ButtonType.OK);
            alert.setTitle("mauvais input");
            alert.setHeaderText("echec d'envoie");
            alert.setContentText("S'il vous plait verifier vos input");
            alert.showAndWait();
        }



    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField(Organisation organisation) {
        OrganisationId = organisation.getId();
        nom.setText(organisation.getNom());
        email.setText(organisation.getEmail());
        desc.setText(organisation.getDescription());
        numero.setText(organisation.getNumero_tel());
        document.setText(organisation.getDocument());
        payment.setText(organisation.getPayment_info());
        logo.setText(organisation.getLogo());

        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
    }

}
