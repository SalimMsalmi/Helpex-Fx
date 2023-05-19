/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parashop;

import entities.CategorieProduit;
import entities.Produit;
import services.CrudProduits;
import utils.MyConnection;

/**
 *
 * @author FaroukDev
 */
public class ParaShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                        MyConnection cc =  MyConnection.getInstance()  ;
                        CategorieProduit Cp1 = new CategorieProduit(6,"new");
                        Produit p1 = new Produit(Cp1, "helloWorld", "new", "556", true);
                        Produit p2 = new Produit("SansCat", "new1", "557", true);
                        
                        Produit p3 = new Produit(23,Cp1,"SansCat", "new1", "557", true);
                        
                        CrudProduits p = new CrudProduits(cc);
                        //System.out.println(p.addProduit(p1, Cp1));
                         //System.out.println( p.addProduit(p2, Cp1));
                       System.out.println(p.updateProduit(p3));
                        //p.addProduit(p1, Cp1);
                      //p.addProduit(p2, Cp1);
                      
                      
                        
                        
    }
    
}
