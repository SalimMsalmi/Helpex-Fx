package entites;

public class Accompagnement {
    private int id ;
    private Tasks id_task ;
    private Boolean is_accepted ;
    private User   user ;
    private User     userPro ;

    public Accompagnement() {
    }

    public Accompagnement(int id, Tasks id_task, Boolean is_accepted, User   user, User     userPro ) {
        this.id = id;
        this.id_task = id_task;
        this.is_accepted = is_accepted;
        this.user = user;
        this.userPro = userPro;
    }

    public Accompagnement(Tasks id_task, Boolean is_accepted,  User   user, User     userPro ) {
        this.id_task = id_task;
        this.is_accepted = is_accepted;
        this.user = user;
        this.userPro = userPro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tasks getId_task() {
        return id_task;
    }

    public void setId_task(Tasks id_task) {
        this.id_task = id_task;
    }

    public Boolean getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(Boolean is_accepted) {
        this.is_accepted = is_accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        }

    public User getUser_pro() {
        return userPro;
    }

    public void setUser_pro_id(User user_pro) {
        this.userPro = user_pro;
    }

    @Override
    public String toString() {
        return "Accompagnement{" +
                "id=" + id +
                ", id_task=" + id_task +
                ", is_accepted=" + is_accepted +
                ", user_id=" + user +
                ", user_pro_id=" + userPro +
                '}';
    }
}
