package gui;

import entities.CaisseOrganisation;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import services.CaisseOrganisationCRUD;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaisseForm implements Initializable {
    public TextArea CaisseDesc;
    public TextField CaisseGoal;
    public Button Formbtn;
    int organisationId;
    int caisseId;

    private boolean update;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    public boolean testPattern(String regex, String input){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);

        return matcher.find();
    }
    public void AddCaisseOrganisation(ActionEvent event){

        String description;
        float goal=0;
        if (!Objects.equals(CaisseGoal.getText(), "")){
            goal = Float.parseFloat(CaisseGoal.getText());
        }

        description = CaisseDesc.getText();
        if(testPattern("[a-z]+",CaisseDesc.getText())&&testPattern("[0-9]+",CaisseGoal.getText())){
            if(update){
                CaisseOrganisationCRUD caisseOrganisationCRUD=new CaisseOrganisationCRUD();
                caisseOrganisationCRUD.modifierCaisse(caisseId,goal,description);
            }else {
                CaisseOrganisation caisseOrganisation = new CaisseOrganisation(organisationId,0,goal,description);
                CaisseOrganisationCRUD caisseOrganisationCRUD=new CaisseOrganisationCRUD();
                caisseOrganisationCRUD.ajouterCaisse(caisseOrganisation);
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

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
    }

    void setTextField(CaisseOrganisation caisseOrganisation,int organisationId,int caisseId) {
        this.organisationId=organisationId;
        this.caisseId=caisseId;
        CaisseGoal.setText(String.valueOf(caisseOrganisation.getGoal()));
        CaisseDesc.setText(caisseOrganisation.getDescription());

        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
    }
}
