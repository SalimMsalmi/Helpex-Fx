/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.nutri;
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
public class servicenutri implements Iservice <nutri>
{
    Connection cnx = MyConnection.getInstance().getCnx();
	private static servicenutri instance;
        private Statement st;
        private ResultSet rs;
        public nutri userInfos;
    
    MyConnection cnn= new MyConnection();
    
    private final Connection cn;    
    public servicenutri() {
        cn = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void Ajouter(nutri t) 
    {
        String requete="INSERT INTO nutri(username, password, mail, date_n, code)"
               + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
//            pst.setInt(1, t.getId_user());
            pst.setString(1, t.getUsername());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(2, hashedPassword);
            pst.setString(3, t.getMail());
            pst.setDate(4, (Date) t.getDate_n());
            pst.setString(5, t.getCode());
            pst.executeUpdate();
            System.out.println("Nutritionniste ajouté !");

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(nutri t) 
    {
        String requete = "DELETE FROM nutri WHERE id_user=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getId_user());
            pst.executeUpdate();
            System.out.println("Nutritionniste supprimé !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(nutri t) 
    {
        String requete = "UPDATE nutri SET mail=?, password=?, date_n=? WHERE username=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(4, t.getUsername());
            pst.setString(1, t.getMail());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(2, hashedPassword);
            pst.setDate(3, (Date) t.getDate_n());
            pst.executeUpdate();
            System.out.println("Nutritionniste modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<nutri> afficher() 
    {
        List<nutri> list = new ArrayList<>();
        
        String requete = "SELECT * FROM nutri";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new nutri(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }    

    @Override
    public List<nutri> userListe() 
    {
        List<nutri> list = new ArrayList<>();
        
        String requete = "SELECT * FROM nutri";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                nutri c =new nutri();
                c.setId_user(rs.getInt("id_user"));
                c.setUsername(rs.getString("username"));
//                c.setPassword(rs.getString("password"));
                c.setMail(rs.getString("mail"));
                c.setDate_n(rs.getDate("date_n"));
//                c.setCode(rs.getString("code"));
                list.add(c);
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<nutri> TrieParUsername() 
    {
        List<nutri> list = this.userListe();
        Collections.sort(list, new nutri());
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<nutri> recherche(String Xusername) 
    {
        ArrayList<nutri> ListNutriUsername = new ArrayList<>();
        try {
            String req = "Select username, mail, date_n from nutri where username=?";
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(req);
            pst.setString(1, Xusername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
            {
                nutri n = new nutri();
                n.setUsername(rs.getString(1));
                n.setMail(rs.getString(2));
                n.setDate_n(rs.getDate(3));
                if (n.getUsername().equals(Xusername))
                {
                    ListNutriUsername.add(n);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (ListNutriUsername.isEmpty()) {
            System.out.println("Cet utilisateur n'éxiste pas.");
        }
        return ListNutriUsername;
    }
    
    public List<nutri> getNutriById(){
        
        List<nutri> list = new ArrayList<nutri>();
        int count =0;
        
        String requete="select * from nutri ";
         try{
            Statement st =cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                nutri n = new nutri();
                n.setId_user(rs.getInt(1));
                n.setUsername(rs.getString(2));
                n.setPassword(rs.getString(3));
                n.setMail(rs.getString(4));
                n.setDate_n(rs.getDate(5));
                n.setCode(rs.getString(6));

                list.add(n);
                
                count++;
            }
            if(count == 0){
                return null;
           }else
            {
               return list;
            }
         }
        catch (SQLException ex) {
            Logger.getLogger(servicesimple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
    
    public int check_user(nutri t) 
    {   
        
      MyConnection mc = cnn.getInstance();
        try{    
        PreparedStatement posted = mc.prepareStatement("SELECT * FROM nutri where username= ? and password=? ;");
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
    
    public nutri login(String inputUsername, String inputPassword) {
		nutri account = new nutri();
		account.setId_user(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM nutri where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM nutri where username=?";
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
     
     public static servicenutri getInstance(){
            if(instance==null) 
                instance=new servicenutri();
            return instance;
        }
}
