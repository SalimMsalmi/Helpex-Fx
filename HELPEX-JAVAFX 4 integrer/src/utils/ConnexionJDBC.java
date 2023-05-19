package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionJDBC {
    String url="jdbc:mysql://localhost:3306/apexhelpex";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static ConnexionJDBC instance;

    public ConnexionJDBC() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static ConnexionJDBC getInstance(){
        if(instance == null){
            instance = new ConnexionJDBC();
        }
        return instance;
    }



}
