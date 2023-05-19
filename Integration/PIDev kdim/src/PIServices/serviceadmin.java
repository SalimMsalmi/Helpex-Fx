/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.admin;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author HP
 */
public class serviceadmin implements Iservice <admin>
{
    
    Connection cnx = MyConnection.getInstance().getCnx();
	private static serviceadmin instance;
        private Statement st;
        private ResultSet rs;
        public admin userInfos;

    MyConnection cnn= new MyConnection();
    private final Connection cn;   
    
    public serviceadmin() {
        cn = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void Ajouter(admin t) 
    {
        String requete="INSERT INTO admin(username, password, mail)"
               + "VALUES (?,?,?)";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, t.getUsername());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(2, hashedPassword);
            pst.setString(3, t.getMail());
            pst.executeUpdate();
            System.out.println("Admin ajouté !");

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(admin t) 
    {
        String requete = "DELETE FROM admin WHERE id_user=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getId_user());
            pst.executeUpdate();
            System.out.println("Admin supprimé !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(admin t) 
    {
        String requete = "UPDATE admin SET password=?, mail=? WHERE username=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(1, hashedPassword);
            pst.setString(2, t.getMail());
            pst.setString(3, t.getUsername());

            
            pst.executeUpdate();
            System.out.println("Admin modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<admin> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<admin> userListe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<admin> TrieParUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<admin> recherche(String Xusername) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int check_user(admin t) 
    {   
        
      MyConnection mc = cnn.getInstance();
        try{    
        PreparedStatement posted = mc.prepareStatement("SELECT * FROM admin where username= ? and password=? ;");
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
    
    public admin login(String inputUsername, String inputPassword) {
		admin account = new admin();
		account.setId_user(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM admin where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM admin where username=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, inputUsername);
				rs = pst.executeQuery();
				while (rs.next()) {
					account.setId_user(rs.getInt("id_user"));
					account.setMail(rs.getString("mail"));
					account.setUsername(rs.getString("username"));
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
     
     public static serviceadmin getInstance(){
            if(instance==null) 
                instance=new serviceadmin();
            return instance;
        }
}


