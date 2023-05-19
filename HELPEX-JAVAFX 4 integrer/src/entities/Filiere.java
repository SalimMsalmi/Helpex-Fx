/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus-PC
 */
public class Filiere {
      private int id;
    private String nomF;
    private String descF;
  
    @Override
    public String toString() {
        return "Filiere{" + "id=" + id + ", nomF=" + nomF + ", desc=" + descF + '}';
    }

     public Filiere() {
        
       
    }
    
    public Filiere(int id, String nomF, String descF) {
        this.id = id;
        this.nomF = nomF;
        this.descF = descF;
       
    }

    public Filiere(String nomF, String descF) {
        this.nomF = nomF;
        this.descF = descF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomF() {
        return nomF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public String getDescF() {
        return descF;
    }

    public void setDescF(String descF) {
        this.descF = descF;
    }
    
    
    
    
}
