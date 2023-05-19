/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import entities.Commentaire;
import entities.Poste;
import entities.User;
import javafx.application.Application;
import javafx.stage.Stage;
import services.CRUDCommentaire;
import services.CRUDPoste;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class Help extends Application {

    
            public static User loggedUser = null;

    
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
