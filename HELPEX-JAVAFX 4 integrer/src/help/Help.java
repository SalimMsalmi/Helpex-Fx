/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import entities.Commentaire;
import entities.Poste;
import services.CRUDCommentaire;
import services.CRUDPoste;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class Help {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                MyConnection cc =  MyConnection.getInstance()  ;
             CRUDPoste pr= new CRUDPoste();
           Poste C=  pr.findbyid(3);
            System.out.println(C.toString());
        //Poste p = new Poste("centre1", "mourouj");
      //  Commentaire c = new Commentaire("centre1");

      //  CRUDCommentaire CR= new CRUDCommentaire();
        //CRUDPoste PR=new  CRUDPoste() ;
       // PR.ajouterPoste(p);
        //System.out.println(PR.afficherPoste());
     //   CR.ajouterCommentaire(c);
    }
    
}
