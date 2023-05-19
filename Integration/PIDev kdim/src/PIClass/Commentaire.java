/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

import java.sql.Timestamp;

/**
 *
 * @author ASuS
 */
public class Commentaire {
    private int id_com;
    private int id_pub;
    private int id_user;
    private String username;
    private String suj_com;
    private Timestamp date_com;
    private int nb_reaction;

    public Commentaire (int id_com, int id_user, String username, int id_pub, int nb_reaction, String suj_com, Timestamp date_com) {
        this.id_com = id_com;
        this.id_user = id_user;
        this.username = username;
        this.id_pub = id_pub;
        this.suj_com = suj_com;
        this.date_com = date_com;
        this.nb_reaction = nb_reaction;
    }

    public Commentaire (int id_user, String username, int id_pub, String suj_com, Timestamp date_com) {
        this.id_user = id_user;
        this.username = username;
        this.id_pub = id_pub;
        this.suj_com = suj_com;
        this.date_com = date_com;
    }

    public Commentaire(int id_com) {
        this.id_com = id_com;
    }
    
    public Commentaire(int id_user, String username, int id_pub, String suj_com) {
        this.id_user = id_user;
        this.username = username;
        this.id_pub = id_pub;
        this.suj_com = suj_com;
    }

    public Commentaire(int id_com, String suj_com) {
        this.id_com = id_com;
        this.suj_com = suj_com;
    }
    
    public int getId_com() {
        return id_com;
    }
    
    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public int getId_user() {
        return id_user;
    }
    

    public void setId_user(int id_user) {
        this.id_user=id_user;
    }

    public String getSuj_com() {
        return suj_com;
    }

    public void setSuj_com(String suj_com) {
        this.suj_com = suj_com;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDate_com() {
        return date_com;
    }

    public void setDate_com(Timestamp date_com) {
        this.date_com = date_com;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public int getNb_reaction() {
        return nb_reaction;
    }

    public void setNb_reaction(int nb_reaction) {
        this.nb_reaction = nb_reaction;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_com=" + id_com + ", id_pub=" + id_pub + ", id_user=" + id_user + ", username=" + username + ", suj_com=" + suj_com + ", date_com=" + date_com + ", nb_reaction=" + nb_reaction + '}';
    }

    

    

    
    
}
