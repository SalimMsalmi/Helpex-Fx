/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;



import entites.Accompagnement;
import entites.Item;
import entites.Tasks;
import entites.User;
import services.AccompagnementService;
import services.ItemService;
import services.TasksService;
import utils.ConnexionJDBC;
import utils.MyConnection;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

/**
 *
 * @author Eya
 */
public class JavaHelpex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        ConnexionJDBC mc = new ConnexionJDBC();
       /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse("05-10-2023");
        java.sql.Date sqlDate = java.sql.Date.valueOf("05-10-2023");*/
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

      //  Timestamp timestamp = Timestamp.valueOf("2023-04-06 00:01:05");
        Time timestamp = Time.valueOf("00:01:05");
        Time timestamp2 = Time.valueOf("00:01:06");



        //Ajout Item ok
        Item item = new Item("another day", timestamp,true,new Tasks(1),"yy.jpg");
        ItemService itemService = new ItemService();
        itemService.AjouterItem(item);
        System.out.printf(itemService.listerItems().toString());
        //end Ajout

        //editer Item Ok
        itemService.EditerItem(new Item(694,"ohMyGirlAnotherDia", timestamp,true,new Tasks(1),"yy.jpg"));
        //end editer item
        //supprimer item ok
        itemService.SupprimerItem(694);
        //end supprimer item

        /////////////accompagnment ok /////////////////

        Accompagnement accompagnement=new Accompagnement(new Tasks(2),false,new User(7),new User(6));
        AccompagnementService accompagnementService =new AccompagnementService();
        accompagnementService.EnvoierAccompagnement(accompagnement);
        accompagnementService.accepterAccompagnement(accompagnement);
        accompagnementService.retirer_accompagnement(accompagnement);
        /////////////accompagnment/////////////////


        /////////////tasks/////////////////
        Date datet = Date.valueOf("2000-10-10");
        Date date0 = Date.valueOf("2000-10-10");
        Date date2 = Date.valueOf("2000-10-11");
        Tasks tasks1 = new Tasks("task3",date0,date2,false);
        TasksService tasksService = new TasksService();
        tasksService.AjouterTask(tasks1);
        /////////////tasks/////////////////




    }

}
