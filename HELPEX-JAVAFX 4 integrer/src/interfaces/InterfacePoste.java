/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Poste;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfacePoste {
        public void ajouterPoste(Poste c);
        public Poste findbyid(int id);
        public void ajouterCentre2(Poste c);
        public void modifierPoste(Poste c,String nomCentre,String adresseCentre);
        public void supprimerPoste(Poste c);
        public List<Poste> afficherPoste();

    
}
