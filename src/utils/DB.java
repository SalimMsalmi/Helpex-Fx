/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Asus-PC
 */
public class DB {
    final String url ="jdbc:mysql://localhost:3306/apexhelpex";
    final String user="root";
    final String password="";
    
    static DB instance ;
    private Connection con;
    
    public DB(){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connection established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DB getInstance(){
    if (instance == null)
        instance = new DB();
    return instance;
    }

    public Connection getCon() {
        return con;
    }
}
