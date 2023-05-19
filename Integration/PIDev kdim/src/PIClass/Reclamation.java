/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author ASuS
 */
public class Reclamation {
    private int id_rec;
    private int id_user;
    private String username;
    private String obj_rec;
    private String area_rec;
    private String suj_rec;
    private String etat_rec;
    private Timestamp date_rec;
    
    public Reclamation (int id_rec, int id_user, String username, String obj_rec, String area_rec, String suj_rec, String etat_rec, Timestamp date_rec) {
        this.id_rec = id_rec;
        this.id_user = id_user;
        this.username = username;
        this.obj_rec = obj_rec;
        this.area_rec = area_rec;
        this.suj_rec = suj_rec;
        this.etat_rec = etat_rec;
        this.date_rec = date_rec;
    }

    public Reclamation (int id_user, String username, String obj_rec, String area_rec, String suj_rec, String etat_rec, Timestamp date_rec) {
        this.id_user=id_user;
        this.username = username;
        this.obj_rec = obj_rec;
        this.area_rec = area_rec;
        this.suj_rec = suj_rec;
        this.etat_rec = etat_rec;
        this.date_rec = date_rec;
    }
    
    public Reclamation (String obj_rec, String area_rec, String suj_rec, String etat_rec, Timestamp date_rec) {
        this.obj_rec = obj_rec;
        this.area_rec = area_rec;
        this.suj_rec = suj_rec;
        this.etat_rec = etat_rec;
        this.date_rec = date_rec;
    }

    public Reclamation(int id_user, String username, String obj_rec, String area_rec, String suj_rec) {
        this.id_user = id_user;
        this.username = username;
        this.obj_rec = obj_rec;
        this.area_rec = area_rec;
        this.suj_rec = suj_rec;
    }

    public Reclamation(int id_rec, String etat_rec) {
        this.id_rec = id_rec;
        this.etat_rec = etat_rec;
    }

    public Reclamation() {
    }

    public Reclamation(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public String getObj_rec() {
        return obj_rec;
    }

    public void setObj_rec(String obj_rec) {
        this.obj_rec = obj_rec;
    }

    public String getSuj_rec() {
        return suj_rec;
    }

    public void setSuj_rec(String suj_rec) {
        this.suj_rec = suj_rec;
    }

    public Timestamp getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Timestamp date_rec) {
        this.date_rec = date_rec;
    }

    public String getArea_rec() {
        return area_rec;
    }

    public void setArea_rec(String area_rec) {
        this.area_rec = area_rec;
    }

    public String getEtat_rec() {
        return etat_rec;
    }

    public void setEtat_rec(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", id_user=" + id_user + ", username=" + username + ", obj_rec=" + obj_rec + ", area_rec=" + area_rec + ", suj_rec=" + suj_rec + ", etat_rec=" + etat_rec + ", date_rec=" + date_rec + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id_rec;
        hash = 37 * hash + this.id_user;
        hash = 37 * hash + Objects.hashCode(this.obj_rec);
        hash = 37 * hash + Objects.hashCode(this.area_rec);
        hash = 37 * hash + Objects.hashCode(this.suj_rec);
        hash = 37 * hash + Objects.hashCode(this.date_rec);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id_rec != other.id_rec) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.obj_rec, other.obj_rec)) {
            return false;
        }
        if (!Objects.equals(this.area_rec, other.area_rec)) {
            return false;
        }
        if (!Objects.equals(this.suj_rec, other.suj_rec)) {
            return false;
        }
        if (!Objects.equals(this.date_rec, other.date_rec)) {
            return false;
        }
        return true;
    }

    
    
    
}
