package Interfaces;


import entites.Accompagnement;
import entites.Item;
import entites.Tasks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceTasks {

    public void AjouterTask(Tasks tasks) ;
    public  void EditerTask( Tasks tasks,int id) ;
    public  void SupprimerTask(int id) throws SQLException;
  // for admin
    public ArrayList<Tasks> listerTasks ();
    // for user
    public ArrayList<Accompagnement> listerTasksofUser (int id_user);
    // for pro user
    public ArrayList<Accompagnement> listerTasksofUser_pro ( int id_user_pro);


    public  ArrayList <Item> listeIemsTasks (int id_tasks);




}
