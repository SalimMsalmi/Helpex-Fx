package interfaces;

import entities.Organisation;

import java.util.List;

public interface OrganisationInterface {

    public void ajouterOrg(Organisation organisation);
    public void modifierOrg(int organisationID, String description, String email, String numero_tel, String document, String payment_info, String nom, String logo);
    public void supprimerOrg(Organisation organisation);
    public List<Organisation> afficherOrg();
}
