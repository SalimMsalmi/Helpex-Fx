package services;


import Interfaces.InterfaceTasks;
import entities.Accompagnement;
import entities.Item;
import entities.Tasks;

import entities.User;

import help.Helpex;
import utils.ConnexionJDBC;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TasksService implements InterfaceTasks {



    /////////////enregister les tasks dans accompagnements AddTasks(version2) /////////////////
    @Override
    public void AjouterTask(Tasks tasks ) {

        //here id user
        String requete = "INSERT INTO `tasks`( `titre`, `start_date`, `end_date`, `is_valid`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, tasks.getTitre());
            pst.setDate(2,tasks.getStart_date());
            pst.setDate(3, tasks.getEnd_date());
            pst.setBoolean(4, tasks.isIs_valid());
            int rowsInserted =  pst.executeUpdate();
            System.out.println("Done!");
            if (rowsInserted > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("Inserted row with ID: " + id);
                        tasks.setId(id);
                        AjouterAccompagnement(tasks);
                    }
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void AjouterAccompagnement(Tasks task){
        //here id user & id user_pro
        Accompagnement accompagnement = new Accompagnement();
        accompagnement.setId_task(task);
        String requette = "INSERT INTO `accompagnement`(`task_id`, `user_id`,  `is_accepted`) VALUES (?,?,?)";
        try {
            PreparedStatement pst =ConnexionJDBC.getInstance().getCnx().prepareStatement(requette);
            pst.setInt(1,task.getId());
            pst.setInt(2, Helpex.loggedUser.getId());

            pst.setBoolean(3, false);
            pst.executeUpdate();
            System.out.println("task created and added to the accompagnement");


        } catch (SQLException e) {
            throw new RuntimeException() ;

        }
    }

    @Override
    public void EditerTask(Tasks tasks ,int id ) {
        String req ="UPDATE `tasks` SET `titre`= ?,`start_date`= ?,`end_date`= ?,`is_valid`= ? WHERE id= ?" ;
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setString(1, tasks.getTitre());
            pst.setDate(2,tasks.getStart_date());
            pst.setDate(3, tasks.getEnd_date());
            pst.setBoolean(4, tasks.isIs_valid());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Done MODIFICATION TASK !");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void MakeTaskValid(int id ) {
        String req ="UPDATE `tasks` SET `is_valid`= ? WHERE id= ?" ;
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);

            pst.setBoolean(1, true);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Done  TASK becomes Valid !");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void SupprimerTask(int id) throws SQLException {
        String sql = "DELETE FROM Tasks WHERE id= '"+id+"'";

            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx().prepareStatement(sql);
            Statement st = ConnexionJDBC.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            System.out.println("le task :  " +"  "+id+" " +"est supprimer avec succés...");

    }

    @Override
    public ArrayList<Tasks> listerTasks() {
        ArrayList<Tasks> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM tasks";
            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Tasks tasks = new Tasks();
                tasks.setId(rs.getInt(1));
                tasks.setTitre(rs.getString("titre"));
                tasks.setStart_date(rs.getDate("start_date"));
                tasks.setEnd_date(rs.getDate("end_date"));
                tasks.setIs_valid(rs.getBoolean("is_valid"));
                myList.add(tasks);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ArrayList<Accompagnement> listerTasksofUser(int id_user) {
        ArrayList<Accompagnement> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM `accompagnement` join tasks on accompagnement.task_id = tasks.id JOIN user on accompagnement.user_id=user.id WHERE accompagnement.user_id = "+ id_user;
            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Accompagnement accompagnement = new Accompagnement();
                accompagnement.setId(rs.getInt("id"));
                User user = new User(rs.getInt("user_id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                accompagnement.setUser(user);
                User userPro = new User(rs.getInt("user_pro_id"));
                accompagnement.setUser_pro_id(userPro);
                Tasks tasks = new Tasks(rs.getInt("task_id"));
                tasks.setTitre("titre");
                tasks.setTitre(rs.getString("titre"));
                tasks.setStart_date(rs.getDate("start_date"));
                tasks.setEnd_date(rs.getDate("end_date"));
                tasks.setIs_valid(rs.getBoolean("is_valid"));
                accompagnement.setId_task(tasks);


                myList.add(accompagnement);

            }
            System.out.println("herrrrrre"+id_user +myList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ArrayList<Accompagnement> listerTasksofUser_pro(int id_user_pro) {
        ArrayList<Accompagnement> myList = new ArrayList<>();
        try {
            String requete="SELECT * FROM `accompagnement` join tasks on accompagnement.task_id = tasks.id JOIN user on accompagnement.user_id=user.id WHERE accompagnement.user_pro_id ="+id_user_pro+" and is_accepted=1 and tasks.start_date>= CURRENT_TIMESTAMP;";

            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Accompagnement accompagnement = new Accompagnement();
                accompagnement.setId(rs.getInt("id"));
                User user = new User(rs.getInt("user_id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                accompagnement.setUser(user);
                User userPro = new User(rs.getInt("user_pro_id"));
                accompagnement.setUser_pro_id(userPro);
                Tasks tasks = new Tasks(rs.getInt("task_id"));
                tasks.setTitre("titre");
                tasks.setTitre(rs.getString("titre"));
                tasks.setStart_date(rs.getDate("start_date"));
                tasks.setEnd_date(rs.getDate("end_date"));
                tasks.setIs_valid(rs.getBoolean("is_valid"));
                accompagnement.setId_task(tasks);

                myList.add(accompagnement);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ArrayList<Item> listeIemsTasks(int id_tasks) {
        return null;
    }
}
