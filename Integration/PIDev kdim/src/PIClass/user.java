/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


/**
 *
 * @author HP
 */
public class user implements Comparator<user> {
    protected int id_user;
    static protected String username;
    protected String password;
    protected String mail;
    protected Date date_n;

    public user(int id_user, String username, String password, String mail, Date date_n) 
    {
        this.id_user= id_user;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.date_n = date_n;
    }    

    public user(int id_user, String username, String password) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public user(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    
    
    public user(String username, String password, String mail, Date date_n) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.date_n = date_n;
    }

    public user(int id_user, String username, String password, String mail) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    
    
    public user() 
    {}

    
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDate_n() {
        return date_n;
    }

    public void setDate_n(Date date_n) {
        this.date_n = date_n;
    }
    
    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", username=" + username + ", password=" + password + ", mail=" + mail + ", date_n=" + date_n + '}';
    }

    @Override
    public int compare(user o1, user o2)
    {
        return o1.getUsername().compareTo(o2.getUsername());
    }
    
        
    
}
