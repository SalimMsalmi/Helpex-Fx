package services;



import Interfaces.InterfaceItem;
import entities.Item;
import entities.Tasks;


import java.sql.*;
import java.util.ArrayList;

import javafx.scene.chart.XYChart;
import utils.ConnexionJDBC;
import utils.MyConnection;

public class ItemService implements InterfaceItem {

    private int complete=0 , noncomplete =0, expired  =0;

    @Override
    public void AjouterItem(Item item) {
    String requete = "INSERT INTO item ( tasks_id, titre, time, is_complete, photo) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
           // pst.setInt(1,667);
            pst.setInt(1,item.getTasks().getId());
            pst.setString(2, item.getTitre());
            pst.setTime(3,item.getTime());
            pst.setBoolean(4, item.isId_complete());
            pst.setString(5,item.getPhoto());
            pst.executeUpdate();
            System.out.println("Done!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public  void completeItem(int id){
        String req ="UPDATE `item` SET is_complete=1 WHERE id = ? ";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1,id);

            pst.executeUpdate();
            System.out.println("Done Modification!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void EditerItem( Item item) {
        String req = "UPDATE item SET titre=? , time= ?,is_complete = ?,`photo`= ? WHERE id = ?";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx().prepareStatement(req);
            pst.setString(1,item.getTitre());
            pst.setTime(2,item.getTime());
            pst.setBoolean(3, item.isId_complete());
            pst.setString(4,item.getPhoto());
            pst.setInt(5,item.getId());
            pst.executeUpdate();
            System.out.println("Done Modification!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void SupprimerItem(int id) {
        String sql = "DELETE FROM item WHERE id= '"+id+"'";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx().prepareStatement(sql);
            Statement st = ConnexionJDBC.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            System.out.println("l'item :  " +"  "+id+" " +"est supprimer avec succés...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Item> listerItems() {
        ArrayList<Item> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM `item` join tasks ON item.tasks_id=tasks.id;";
            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Item i = new Item();
                i.setId(rs.getInt(1));
                i.setTitre(rs.getString("titre"));
                i.setTime(rs.getTime("time"));
                i.setId_complete(rs.getBoolean("is_complete"));
                if (rs.getBoolean("is_complete")==false){
                    complete ++;
                }
                else {
                    noncomplete ++ ;
                }
                //maybe an error
                i.setTasks(new Tasks(rs.getInt("tasks_id"),rs.getString("tasks.titre"),rs.getDate("tasks.start_date"),rs.getDate("tasks.end_date"),rs.getBoolean("tasks.is_valid")) );
                i.setPhoto(rs.getString("photo"));
                myList.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ArrayList<Item> listerItemsforUser(int id) {
        ArrayList<Item> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM `item` join tasks ON item.tasks_id=tasks.id join accompagnement on task_id=tasks.id WHERE accompagnement.user_id= "+id;
            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Item i = new Item();
                i.setId(rs.getInt(1));
                i.setTitre(rs.getString("titre"));
                i.setTime(rs.getTime("time"));
                i.setId_complete(rs.getBoolean("is_complete"));
                if (rs.getBoolean("is_complete")==false){
                    complete ++;
                }
                else {
                    noncomplete ++ ;
                }
                //maybe an error
                i.setTasks(new Tasks(rs.getInt("tasks_id"),rs.getString("tasks.titre"),rs.getDate("tasks.start_date"),rs.getDate("tasks.end_date"),rs.getBoolean("tasks.is_valid")) );
                i.setPhoto(rs.getString("photo"));
                myList.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ArrayList<Item>listerItemById(int id_task){
        ArrayList<Item> myList = new ArrayList<>();
        try {
            String requete ="SELECT * FROM `item` join tasks ON item.tasks_id=tasks.id WHERE item.is_complete=0 and  item.tasks_id = "+id_task;

            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Item i = new Item();
                i.setId(rs.getInt(1));
                i.setTitre(rs.getString("titre"));
                i.setTime(rs.getTime("time"));
                i.setId_complete(rs.getBoolean("is_complete"));
                if (rs.getBoolean("is_complete")==false){
                    complete ++;
                }
                else {
                    noncomplete ++ ;
                }
                //maybe an error
                i.setTasks(new Tasks(rs.getInt("tasks_id"),rs.getString("tasks.titre"),rs.getDate("tasks.start_date"),rs.getDate("tasks.end_date"),rs.getBoolean("tasks.is_valid")) );
                i.setPhoto(rs.getString("photo"));
                myList.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;


    }

    public int getComplete() {
        return complete;
    }

    public int getNoncomplete() {
        return noncomplete;
    }

    public int getExpired() {
        return expired;
    }




}
