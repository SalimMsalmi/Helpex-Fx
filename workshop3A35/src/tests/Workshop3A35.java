/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entites.poste;
import services.CRUDPoste;
import utils.MyConnection;

/**
 *
 * @author belkn
 */
public class Workshop3A35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyConnection cc = MyConnection.getInstance();
         
        
        poste p = new poste("test", "test");
        CRUDPoste per = new CRUDPoste();
        per.ajouterPoste(p);
        System.out.println(per.afficherPoste());
    }
    
}
