package entites;
import java.sql.Date;
public class User {
    private int id,  is_enabled, tarif;
    private String email, password, roles, nom,prenom, sexe,adresse,num_tel,pdp,bio,diplome;
    private Date date_naissance;


    public User(int id) {
        this.id = id;
    }

    /*const PRO*/
    public User( String email, String roles ,String password, String nom, String prenom, String sexe, String adresse, String num_tel, String pdp, String bio, Date date_naissance, String diplome, int tarif, int is_enabled) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.pdp = pdp;
        this.bio = bio;
        this.date_naissance = date_naissance;
        this.diplome = diplome;
        this.tarif = tarif;
        this.is_enabled = is_enabled ;
    }

    /*const Cli*/
    public User( String email, String roles ,String password, String nom, String prenom, String sexe, String adresse, String num_tel, String pdp, String bio, Date date_naissance, int is_enabled) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.pdp = pdp;
        this.bio = bio;
        this.date_naissance = date_naissance;

        this.is_enabled = is_enabled ;
    }


    /* const affich PRO */

    public User(int id, String email, String roles , String nom, String prenom, String sexe, String adresse, String num_tel, String pdp, String bio, Date date_naissance, String diplome, int tarif, int is_enabled) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.pdp = pdp;
        this.bio = bio;
        this.date_naissance = date_naissance;
        this.diplome = diplome;
        this.tarif = tarif;
        this.is_enabled = is_enabled ;
    }

    /*const CLIENT*/
    public User(int id, String email, String roles ,String password, String nom, String prenom, String sexe, String adresse, String num_tel, String pdp, String bio, Date date_naissance,  int is_enabled) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.pdp = pdp;
        this.bio = bio;
        this.date_naissance = date_naissance;

        this.is_enabled = is_enabled ;
    }
    /* const affich CLIENT */

    public User(int id, String email, String roles , String nom, String prenom, String sexe, String adresse, String num_tel, String pdp, String bio, Date date_naissance, int is_enabled) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.pdp = pdp;
        this.bio = bio;
        this.date_naissance = date_naissance;

        this.is_enabled = is_enabled ;
    }

    public User() {
    }

    public User(int aInt, String string, String string0, String string1, int aInt0) {
        this.id = aInt;
        this.email = string;
        this.roles = string0;
        this.password = string1;
        this.is_enabled = aInt0 ;    }

    public int getId() {
        return id;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public int getTarif() {
        return tarif;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getPdp() {
        return pdp;
    }

    public String getBio() {
        return bio;
    }

    public String getDiplome() {
        return diplome;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", is_enabled=" + is_enabled + ", tarif=" + tarif + ", email=" + email + ", password=" + password + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", adresse=" + adresse + ", num_tel=" + num_tel + ", pdp=" + pdp + ", bio=" + bio + ", diplome=" + diplome + ", date_naissance=" + date_naissance + '}';
    }

    /////OTHER CONSTRUCTORSSSSSSS
}