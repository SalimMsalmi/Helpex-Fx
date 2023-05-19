/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Poste {
    private int id;
    private String titre;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Poste(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public Poste(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", titre=" + titre + ", description=" + description + '}';
    }

    public Poste() {
    }
   
    
  
    
}
