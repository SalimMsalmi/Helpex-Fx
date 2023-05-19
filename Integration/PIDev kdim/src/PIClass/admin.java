/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIClass;

/**
 *
 * @author HP
 */
public class admin extends user {
    
    
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

    public admin(int id_user, String username, String password, String mail) {
        super(id_user, username, password, mail);
    }

    public admin(String username, String password, String mail) {
        super(username, password, mail);
    }
 
    public admin() 
    {}

    public admin(int id_user, String username, String password) {
        super(id_user, username, password);
    }

    public admin(String username, String password) {
        super(username, password);
    }
    
    
    
}
