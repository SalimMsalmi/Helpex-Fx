/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.psycho;
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
public class servicepsycho implements Iservice <psycho>
{
    Connection cnx = MyConnection.getInstance().getCnx();
	private static servicepsycho instance;
        private Statement st;
        private ResultSet rs;
        public psycho userInfos; 
        
    MyConnection cnn= new MyConnection();
    private final Connection cn;    
    public servicepsycho() {
        cn = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void Ajouter(psycho t) 
    {
        String requete="INSERT INTO psycho(username, password, mail, date_n, code)"
               + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, t.getUsername());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(2, hashedPassword);
            pst.setString(3, t.getMail());
            pst.setDate(4, (Date) t.getDate_n());
            pst.setString(5, t.getCode());
            pst.executeUpdate();
            System.out.println("Psychologue ajouté !");

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(psycho t) 
    {
        String requete = "DELETE FROM psycho WHERE id_user=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getId_user());
            pst.executeUpdate();
            System.out.println("Psychologue supprimé !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(psycho t) 
    {
        String requete = "UPDATE psycho SET password=?, mail=?, date_n=? WHERE username=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(4, t.getUsername());
            pst.setString(2, t.getMail());
            pst.setDate(3, (Date) t.getDate_n());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(1, hashedPassword);
            pst.executeUpdate();
            System.out.println("Psycho modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<psycho> afficher() 
    {
        List<psycho> list = new ArrayList<>();
        
        String requete = "SELECT * FROM psycho";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new psycho(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }    

    @Override
    public List<psycho> userListe() 
    {
        List<psycho> list = new ArrayList<>();
        
        String requete = "SELECT * FROM psycho";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                psycho c =new psycho();
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
    public List<psycho> TrieParUsername() 
    {
        List<psycho> list = this.userListe();
        Collections.sort(list, new psycho());
        Collections.reverse(list);
        return list;
    }
    
    @Override
    public List<psycho> recherche(String Xusername) 
    {
        ArrayList<psycho> ListPsychoUsername = new ArrayList<>();
        try {
            String req = "Select username, mail, date_n from psycho where username=?";
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(req);
            pst.setString(1, Xusername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
            {
                psycho c = new psycho();
                c.setUsername(rs.getString(1));
                c.setMail(rs.getString(2));
                c.setDate_n(rs.getDate(3));
                if (c.getUsername().equals(Xusername))
                {
                    ListPsychoUsername.add(c);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (ListPsychoUsername.isEmpty()) {
            System.out.println("Cet utilisateur n'éxiste pas.");
        }
        return ListPsychoUsername;
    }
    
    public List<psycho> getPsyById(){
        
        List<psycho> list = new ArrayList<psycho>();
        int count =0;
        
        String requete="select * from psycho ";
         try{
            Statement st =cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                psycho p = new psycho();
                p.setId_user(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setMail(rs.getString(4));
                p.setDate_n(rs.getDate(5));
                p.setCode(rs.getString(6));



                list.add(p);
                
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
    
    public int check_user(psycho t) 
    {   
        
      MyConnection mc = cnn.getInstance();
        try{    
        PreparedStatement posted = mc.prepareStatement("SELECT * FROM psycho where username= ? and password=? ;");
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
    
    public psycho login(String inputUsername, String inputPassword) {
		psycho account = new psycho();
		account.setId_user(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM psycho where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM psycho where username=?";
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
     
     public static servicepsycho getInstance(){
            if(instance==null) 
                instance=new servicepsycho();
            return instance;
        }
}
