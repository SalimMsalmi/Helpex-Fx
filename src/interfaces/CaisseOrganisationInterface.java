package interfaces;

import entities.CaisseOrganisation;

public interface CaisseOrganisationInterface {
    public void ajouterCaisse(CaisseOrganisation caisseOrganisation);
    public void modifierCaisse(int id, float goal, String description);
}
