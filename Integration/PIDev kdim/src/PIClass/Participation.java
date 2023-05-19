/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

/**
 *
 * @author ASuS
 */
public class Participation {
    private int id_par;
    private int id_user;
    private int id_event;
    private int nbr_par;
    private String username;
    private String date_par;

    public Participation(int id_par, int id_user, int id_event, int nbr_par, String username, String date_par) {
        this.id_par = id_par;
        this.id_user = id_user;
        this.id_event = id_event;
        this.nbr_par = nbr_par;
        this.username = username;
        this.date_par = date_par;
    }

    public Participation(int id_user, int id_event, String username, String date_par) {
        this.id_user = id_user;
        this.id_event = id_event;
        this.username = username;
        this.date_par = date_par;
    }

    public Participation(int id_user, String date_par) {
        this.id_user = id_user;
        this.date_par = date_par;
    }
    
    public Participation(int nbr_par, String username, String date_par) {
        this.username = username;
        this.nbr_par = nbr_par;
        this.date_par = date_par;
    }
    
    public Participation(int id_par, int id_event) {
        this.id_par = id_par;
        this.id_par = id_event;
    }

    public Participation(int id_par) {
        this.id_par = id_par;
    }

    public int getId_par() {
        return id_par;
    }

    public void setId_par(int id_par) {
        this.id_par = id_par;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNbr_par() {
        return nbr_par;
    }

    public void setNbr_par(int nbr_par) {
        this.nbr_par = nbr_par;
    }

    public String getDate_par() {
        return date_par;
    }

    public void setDate_par(String date_par) {
        this.date_par = date_par;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_par=" + id_par + ", id_user=" + id_user + ", id_event=" + id_event + ", nbr_par=" + nbr_par + ", username=" + username + ", date_par=" + date_par + '}';
    }
}
