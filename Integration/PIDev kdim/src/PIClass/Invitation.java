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
public class Invitation {
    private int id_invitation;
    private String nom_user;
    private String nom_event;
    public Invitation(int id_invitation) {
    this.id_invitation= id_invitation;
     
    }   

  /*  public Invitation( int id_user, int id_event) {
        this.id_user = id_user;
        this.id_event = id_event;
    }*/
       public Invitation( String nom_user, String nom_event) {
        this.nom_user = nom_user;
        this.nom_event = nom_event;
    }

    public Invitation(int id_invitation, String nom_user , String nom_event) {
        this.id_invitation = id_invitation;
        this.nom_user = nom_user;
        this.nom_event = nom_event;
    }
    

    public int getId() {
        return id_invitation;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id_invitation=" + id_invitation + ", nom_user=" + nom_user + ", nom_event=" + nom_event + '}';
    }

  
    
    

}
