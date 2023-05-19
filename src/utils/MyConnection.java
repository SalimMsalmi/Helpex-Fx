/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MyConnection {
  static  MyConnection instance;
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    String url="jdbc:mysql://localhost:3306/apexhelpex";
    String user="root";
    String pwd="";
    private MyConnection(){
        try {
            conn=DriverManager.getConnection(url, user, pwd);
            System.out.println("connecté");
        } catch (SQLException ex) {
            System.out.println("erreur de connexion");
        }
        
}
    public static MyConnection getInstance(){
        if (instance==null){
        instance = new MyConnection();
        }
        return instance;
        
    }
    
}
