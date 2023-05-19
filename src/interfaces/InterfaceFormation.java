/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Formation;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceFormation {
    /*
      private int id;
    private String nomFormation;
    private String descriptionFormation;
    private float coutFormation;
    private int NombreDePlace;
    private String duree;
    private Centre idCentre;
    */
    public void ajouterFormation(Formation f);
    public void modifierFormation(Formation f,String nomFormation,String descriptionFormation,float coutFormation ,int NombreDePlace,String duree);
    public void supprimerFormation(Formation f);
     public List<Formation> afficherFormation();
    
}
