/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Filiere;
import entities.User;

import utils.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Interfaces.FiliereInterface;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus-PC
 */
public class FiliereService implements FiliereInterface {

    Statement ste;
    Connection conn=    DB.getInstance().getCon();

    @Override
    public void ajouterF(Filiere c) {
 try {
                    String req="INSERT INTO `filiere`(`nom_filiere`, `description_filiere`) VALUES ('"+c.getNomF()+"','"+c.getDescF()+"')";
            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println(" succes");
        } catch (SQLException ex) {
            System.out.println("fail");        }    }

    @Override
    public void supprimerf(Filiere c) {
  try {
        String req = "DELETE FROM filiere WHERE id='"+c.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println(" supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression");
    }    }

    @Override
    public List<Filiere> afficherf() {
  List<Filiere> list=new ArrayList<>();


        String req="SELECT * FROM `filiere` ";
        Statement ste;
        try {

            ste = conn.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                Filiere c =new Filiere();
                c.setId(RS.getInt(1));
                //c.setId(RS.getInt(1));
                    c.setNomF(RS.getString("nom_filiere"));
                                        c.setDescF(RS.getString("description_filiere"));

                list.add(c);

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return list;    }

    @Override
    public Filiere findbyid(int id) {
 String req = "SELECT * FROM filiere WHERE id=" + id; 
    Statement ste;
    Filiere c = new Filiere();

    try {
        ste = conn.createStatement();
        ResultSet RS = ste.executeQuery(req);
        while (RS.next()) {
            c.setId(RS.getInt(1));
            c.setNomF(RS.getString("nom_filiere"));
            c.setDescF(RS.getString("description_filiere"));
          
        }
    } catch (SQLException ex) {
        System.out.println("erreur get by id ");        
    }
    
    return c;    }
   
    
}
