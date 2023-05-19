/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Eya
 */
public class Tasks {
    private int id ;
    private String titre ;
    private Date start_date ;
    private java.sql.Date end_date ;
    private  boolean is_valid ;

    private ArrayList<Item> list_items ;

    public Tasks() {
    }

    public Tasks(int id) {
        this.id = id;
    }

    public Tasks(int id, String titre, java.sql.Date start_date, java.sql.Date end_date, boolean is_valid) {
        this.id = id;
        this.titre = titre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_valid = is_valid;
    }


    public Tasks(String titre, java.sql.Date start_date, Date end_date, boolean is_valid) {
        this.titre = titre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_valid = is_valid;
    }

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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isIs_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public ArrayList<Item> getList_items() {
        return list_items;
    }

    public void setList_items(ArrayList<Item> list_items) {
        this.list_items = list_items;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", is_valid=" + is_valid +
                ", list_items=" + list_items +
                '}';
    }
}

