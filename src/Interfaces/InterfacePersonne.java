/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.poste;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface InterfacePersonne {
    
    public void ajouterPoste(poste p);
    public void ajouterPoste2(poste p);
    public void modifierPoste(poste p);
    public void supprimerPoste(poste p);
    public List<poste> afficherPoste();
    
}
