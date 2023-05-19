package Interfaces;

import entites.Accompagnement;

import java.util.List;


public interface InterfaceAccompagnement {


    //creation
    public void EnvoierAccompagnement(Accompagnement accompagnement);
    //is_valid==true

    public void accepterAccompagnement(Accompagnement accompagnement);
   //supprimer
    public  void retirer_accompagnement (Accompagnement accompagnement);

    public List<Accompagnement> lister_accompagnement_for_admin ();

    public List<Accompagnement> lister_accompagnment_for_user();
    public List<Accompagnement> lister_accompagnment_for_pro(int id);
}
