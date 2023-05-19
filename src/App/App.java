/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import entities.Organisation;
import services.OrganisationCRUD;
import utils.MyConnection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                MyConnection cc =  MyConnection.getInstance()  ;
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        List<Organisation> organisationList;
        organisationList=organisationCRUD.afficherOrg();
        System.out.println(organisationList);
    }

}
