package home;

import entities.Centre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDCentre;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class FormCentreController implements Initializable {

  
    @FXML
    public TextField txtNom;
    @FXML
    public TextField txtAdresse;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtTelephone;
    @FXML
    public TextField txtSiteweb;
    
    private Centre c;

    public Centre getC() {
        return c;
    }

    public void setC(Centre c) {
        this.c = c;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void AddOrganisation(ActionEvent event) {
       String nomCentre, adresseCentre, emailCentre, siteWebCentre;
int telephoneCentre;
nomCentre = txtNom.getText();
adresseCentre = txtAdresse.getText();
emailCentre = txtEmail.getText();
siteWebCentre = txtSiteweb.getText();

// Vérification du champ nom
if (nomCentre.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire");
    return;
}

// Vérification du champ adresse
if (adresseCentre.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Le champ adresse est obligatoire");
    return;
} else if (adresseCentre.length() > 100) {
    JOptionPane.showMessageDialog(null, "Le champ adresse doit contenir au maximum 100 caractères");
    return;
}

// Vérification du champ email
if (emailCentre.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Le champ email est obligatoire");
    return;
} else {
    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailPattern);
    Matcher matcher = pattern.matcher(emailCentre);
    if (!matcher.matches()) {
        JOptionPane.showMessageDialog(null, "Le champ email est invalide");
        return;
    }
}

// Vérification du champ téléphone
if (txtTelephone.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Le champ téléphone est obligatoire");
    return;
} else if (!txtTelephone.getText().matches("[0-9]+")) {
    JOptionPane.showMessageDialog(null, "Le champ téléphone doit contenir uniquement des chiffres");
    return;
} else {
    telephoneCentre = Integer.parseInt(txtTelephone.getText());
}

// Vérification du champ site web
if (siteWebCentre.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Le champ site web est obligatoire");
    return;
}

Centre c = new Centre(nomCentre, adresseCentre, emailCentre, telephoneCentre, siteWebCentre);
CRUDCentre cu = new CRUDCentre();
cu.ajouterCentre(c);

        

    }

    @FXML
    private void modifierCentre(ActionEvent event) {
        String nomCentre,adresseCentre,emailCentre,siteWebCentre;
        int telephoneCentre;
            nomCentre = txtNom.getText();
            adresseCentre=txtAdresse.getText();
            emailCentre=txtEmail.getText();
//                      telephoneCentre=Integer.parseInt(txtTelephone.getText()) ;

            siteWebCentre=txtSiteweb.getText();
            
            CRUDCentre cu = new CRUDCentre();
            System.out.println("testttform");
        cu.modifierCentre(c,nomCentre, adresseCentre,emailCentre, 4, siteWebCentre);
    }

}
