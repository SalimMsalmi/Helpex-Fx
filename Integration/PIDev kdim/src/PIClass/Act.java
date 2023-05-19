/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

/**
 *
 * @author Islem
 */
public class Act {


    private int id_act;
    private String nom_act;
    private String type_act;
      public Act( String nom_act) {
       
        this.nom_act= nom_act;}

      public Act(){
          
      }
      
    public Act( int id_act,String nom_act, String type_act) {
       this.id_act= id_act;
        this.nom_act= nom_act;
        this.type_act= type_act;
         
    }

    public Act(String nom_act, String type_act) {
        this.nom_act = nom_act;
        this.type_act = type_act;
    }

    public int getId_act() {
        return id_act;
    }
 public void setId_act(int id_act) {
        this.id_act= id_act;
    }
    public String getNom_act() {
        return nom_act;
    }

    public void setNom_act(String nom_act) {
        this.nom_act= nom_act;
    }

    public String getType_act() {
        return type_act;
    }

    /**
     *
     * @param type_act
     */
    public void setType_act(String type_act) {
        this.type_act = type_act;
    }

    @Override
    public String toString() {
        return "activite{" + "id=" + id_act+ ", nom_act=" + nom_act+ ", type_act=" + type_act + '}';
    }
}


