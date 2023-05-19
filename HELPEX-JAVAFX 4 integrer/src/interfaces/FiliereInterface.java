/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.Filiere;
import java.util.List;

/**
 *
 * @author Asus-PC
 */
public interface FiliereInterface {
     public void ajouterF(Filiere c);
       // public void modifierCentre(Centre c,String nomCentre,String adresseCentre,String emailCentre,int telephoneCentre,String siteWebCentre);
        public void supprimerf(Filiere c);
        public List<Filiere> afficherf();
                public Filiere findbyid(int id);
}
