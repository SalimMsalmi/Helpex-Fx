/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author belkn
 */
public class poste {
    
    private int id;
    private String description,titre,multimedia;

    public poste() {
    }

    public poste(String description, String titre) {
        this.description = description;
        this.titre = titre;
    }

    public poste(int id, String description, String titre, String multimedia) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.multimedia = multimedia;
    }

    public poste(String description, String titre, String multimedia) {
        this.description = description;
        this.titre = titre;
        this.multimedia = multimedia;
    }

    @Override
    public String toString() {
        return "poste{" + "id=" + id + ", description=" + description + ", titre=" + titre + ", multimedia=" + multimedia + '}';
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String nom) {
        this.description = nom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String prenom) {
        this.titre = prenom;
    }


    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    
    
    
}
