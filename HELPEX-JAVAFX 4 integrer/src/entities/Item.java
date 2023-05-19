package entities;


import java.sql.Time;

public class Item {
    private int id ;
    private String titre ;
    private Time time ;
    private boolean id_complete ;
    private Tasks tasks ;
    private String photo ;

    public Item() {
    }

    public Item(int id, String titre, Time time, boolean id_complete, Tasks tasks,String photo) {
        this.id = id;
        this.titre = titre;
        this.time = time;
        this.id_complete = id_complete;
        this.tasks = tasks;
        photo=photo ;
    }

    public Item(String titre, Time time, boolean id_complete, Tasks tasks, String photo) {
        this.titre = titre;
        this.time = time;
        this.id_complete = id_complete;
        this.tasks = tasks;
        photo=photo ;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", time=" + time +
                ", id_complete=" + id_complete +
                ", tasks=" + tasks +
                "photo"+photo+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isId_complete() {
        return id_complete;
    }

    public void setId_complete(boolean id_complete) {
        this.id_complete = id_complete;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
