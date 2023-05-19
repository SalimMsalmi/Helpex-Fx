package services;


import Interfaces.InterfaceAccompagnement;
import entities.Accompagnement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Tasks;
import entities.User;
import utils.ConnexionJDBC;
import utils.MyConnection;

public class AccompagnementService implements InterfaceAccompagnement {


    @Override
    public void EnvoierAccompagnement(Accompagnement accompagnement) {
        String requete = "INSERT INTO `accompagnement`( `task_id`, `user_id`, `user_pro_id`, `is_accepted`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1,667);
            pst.setInt(1,accompagnement.getId_task().getId());
            pst.setInt(2, accompagnement.getUser().getId());
            pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.setBoolean(4, false);
            pst.executeUpdate();
            System.out.println("Done envoie d' accompagnement!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void EnvoierAccompagnementPartie2(Accompagnement accompagnement) {
        String requete = "INSERT INTO `accompagnement`( `task_id`, `user_id`, `user_pro_id`, `is_accepted`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1,667);
            pst.setInt(1,accompagnement.getId_task().getId());
            pst.setInt(2, accompagnement.getUser().getId());
            pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.setBoolean(4, true);
            pst.executeUpdate();
            System.out.println("Done enregistrement  d' accompagnement!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void EnvoierAccompagnementPartie3(Accompagnement accompagnement) {
        String req = "UPDATE `accompagnement` SET accompagnement.user_pro_id= ? WHERE " +
                " `task_id`=? and `user_id`=? ";
        PreparedStatement pst = null;
        try {
            pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1,accompagnement.getUser_pro().getId());
            pst.setInt(2,accompagnement.getId_task().getId());
            pst.setInt(3, accompagnement.getUser().getId());

            pst.executeUpdate();
            System.out.println("accompagnement envoyer!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void accepterAccompagnement(Accompagnement accompagnement) {
        String req = "UPDATE `accompagnement` SET `is_accepted`=1 WHERE id = ? ";
        PreparedStatement pst = null;
        try {
            pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1,accompagnement.getId());
            //pst.setInt(2, accompagnement.getUser().getId());
          //  pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.executeUpdate();
            System.out.println("accompagnement accepté!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void retirer_accompagnement(Accompagnement accompagnement) {
        String sql = "DELETE FROM accompagnement WHERE  id = "+ accompagnement.getId();
        try {

            Statement st = ConnexionJDBC.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            System.out.println(" accompagnemment supprimer avec succés...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


     public List<Accompagnement> lister_accompagnement_for_admin (){
        List<Accompagnement> myList = new ArrayList<>();
        String sql="SELECT * FROM accompagnement";
         try {
             Statement st = ConnexionJDBC.getInstance().getCnx()
                     .createStatement();
             ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
                 Accompagnement accompagnement =new Accompagnement();
                 accompagnement.setId(rs.getInt("id"));
                 accompagnement.setId_task(new Tasks(rs.getInt("task_id")));
                 User user = new User(rs.getInt("user_id"));
                 accompagnement.setUser(user);
                 User userpro = new User(rs.getInt("user_pro_id"));
                 accompagnement.setUser_pro_id(userpro);
                 accompagnement.setIs_accepted(rs.getBoolean("is_accepted"));

                 myList.add(accompagnement);

             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return myList ;

     }
     public  ArrayList<Tasks> lister_tasks_for_user( int myUser){
        //user_id
        ArrayList<Tasks> tasks = new ArrayList<>();
         try {
             Statement stmt = ConnexionJDBC.getInstance().getCnx()
                     .createStatement();
             ResultSet rs = stmt.executeQuery("SELECT  tasks.id, tasks.titre FROM `accompagnement` JOIN tasks on accompagnement.task_id=tasks.id where accompagnement.user_id='"+myUser+"' AND accompagnement.is_accepted=0 AND accompagnement.user_pro_id is null");
             while(rs.next()) {
                 Tasks t = new Tasks();
                 t.setId(rs.getInt(1));
                 t.setTitre(rs.getString(2));
                 tasks.add(t) ;
             }

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
        return  tasks ;

     }

    public List<Accompagnement> lister_accompagnment_for_user(){return null;}
    public List<Accompagnement> lister_accompagnment_for_pro(int id ){

        //id user_pro
        ArrayList<Accompagnement> accompagnements = new ArrayList<>();
        try {
            Statement stmt = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = stmt.executeQuery("SELECT accompagnement.id, accompagnement.user_id, user.nom , USER.prenom,accompagnement.task_id,accompagnement.user_id, accompagnement.user_pro_id,accompagnement.is_accepted FROM `accompagnement` JOIN user on accompagnement.user_id=USER.id WHERE accompagnement.user_pro_id="+id+" and accompagnement.is_accepted=0;");
            while(rs.next()) {
                Accompagnement accompagnement = new Accompagnement();
                accompagnement.setId(rs.getInt(1));
                accompagnement.setId_task( new Tasks(rs.getInt(5)));
                User user = new User(rs.getInt(2));
                user.setNom(rs.getString(3));
                user.setPrenom(rs.getString(4));
                accompagnement.setUser(user);
                User userPro = new User(rs.getInt(6)) ;
                accompagnement.setUser_pro_id(userPro);
                accompagnement.setIs_accepted(rs.getBoolean(7));
                accompagnements.add(accompagnement);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  accompagnements ;





    }

}
