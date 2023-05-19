/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.simple;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author HP
 */
public class servicesimple implements Iservice <simple>{
    
    
    Connection cnx = MyConnection.getInstance().getCnx();
	private static servicesimple instance;
        private Statement st;
        private ResultSet rs;
        public simple userInfos;

    MyConnection cnn= new MyConnection();
    private final Connection cn;    
    public servicesimple() {
        cn = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void Ajouter(simple t)
    {
        String requete="INSERT INTO simple(username, password, mail, date_n)"
               + "VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, t.getUsername());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(2, hashedPassword);
            pst.setString(3, t.getMail());
            pst.setDate(4, (Date) t.getDate_n());
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutee !");

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(simple t)
    {
        String requete = "DELETE FROM simple WHERE id_user=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getId_user());
            pst.executeUpdate();
            System.out.println("Utilisateur Supprimé !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(simple t) 
    {
        String requete = "UPDATE simple SET password=?, mail=?, date_n=? WHERE username=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(4, t.getUsername());
            pst.setString(2, t.getMail());
            pst.setDate(3, (Date) t.getDate_n());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(1, hashedPassword);
            pst.executeUpdate();
            System.out.println("utilisateur Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<simple> afficher() 
    {
        List<simple> list = new ArrayList<>();
        
        String requete = "SELECT * FROM simple";
        try {            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new simple(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<simple> userListe() 
    {
        List<simple> list = new ArrayList<>();
        
        String requete = "SELECT * FROM simple";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                simple u =new simple();
                u.setId_user(rs.getInt("id_user"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setMail(rs.getString("mail"));
                u.setDate_n(rs.getDate("date_n"));
                
                list.add(u);
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<simple> TrieParUsername() 
    {
        List<simple> list = this.userListe();
        Collections.sort(list, new simple());
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<simple> recherche(String Xusername)
    {
        ArrayList<simple> ListSimpleUsername = new ArrayList<>();
        try {
            String req = "Select username, mail, date_n from simple where username=?";
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(req);
            pst.setString(1, Xusername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
            {
                simple c = new simple();
                c.setUsername(rs.getString(1));
                c.setMail(rs.getString(2));
                c.setDate_n(rs.getDate(3));
                if (c.getUsername().equals(Xusername))
                {
                    ListSimpleUsername.add(c);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (ListSimpleUsername.isEmpty()) {
            System.out.println("Cet utilisateur n'éxiste pas.");
        }
        return ListSimpleUsername;
    }
    
     public List<simple> getSimpleById(){
        
        List<simple> list = new ArrayList<simple>();
        int count =0;
        
        String requete="select * from simple ";
         try{
            Statement st =cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                simple u = new simple();
                u.setId_user(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setMail(rs.getString(4));
                u.setDate_n(rs.getDate(5));



                list.add(u);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(servicesimple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }

     public int check_user(simple t) 
    {   
        
      MyConnection mc = cnn.getInstance();
        try{    
        PreparedStatement posted = mc.prepareStatement("SELECT * FROM simple where username= ? and password=? ;");
        posted.setString(1,t.getUsername());
        posted.setString(2,t.getPassword());
        ResultSet result = posted.executeQuery();
        if(result.first())
        {
            return result.getInt("id_user");
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return -1;
    }
     
     public simple login(String inputUsername, String inputPassword) {
		simple account = new simple();
		account.setId_user(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM simple where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM simple where username=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, inputUsername);
				rs = pst.executeQuery();
				while (rs.next()) {
					account.setId_user(rs.getInt("id_user"));
					account.setMail(rs.getString("mail"));
					account.setUsername(rs.getString("username"));
					account.setDate_n(rs.getDate("date_n"));
					account.setPassword(rs.getString("password"));
					System.out.println("  test "+account);
				}
			}
			else {
				System.out.println(" test2 "+account.getId_user());
			}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	
		
		return account;
	}
     
     public static servicesimple getInstance(){
            if(instance==null) 
                instance=new servicesimple();
            return instance;
        }
}
   
    
    
    
    
    
    
    


