/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Commentaire {
    private int id;
    private String description;
    private Poste p;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Poste getP() {
        return p;
    }

    public void setP(Poste p) {
        this.p = p;
    }
    
    public Commentaire() {
    }

    public Commentaire(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Commentaire(String description, Poste p) {
        this.description = description;
        this.p = p;
    }

    public Commentaire(String description) {
        this.description = description;
    }
    
    
}
