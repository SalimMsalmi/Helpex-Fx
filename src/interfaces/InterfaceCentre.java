/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Centre;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceCentre {
    public void ajouterCentre(Centre c);
        public void ajouterCentre2(Centre c);
        public void modifierCentre(Centre c,String nomCentre,String adresseCentre,String emailCentre,int telephoneCentre,String siteWebCentre);
        public void supprimerCentre(Centre c);
        public List<Centre> affichercentre();
        public Centre findbyid(int id);

    
}
