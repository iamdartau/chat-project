package sample.model;

import java.sql.Timestamp;

public class Messege {

    @Override
    public String toString() {
        return "Messege{" +
                "id=" + id +
                ", user_chat=" + user_chat +
                ", messege_text='" + messege_text + '\'' +
                ", time_of_messege=" + time_of_messege +
                '}';
    }

    int id, user_chat;
    String messege_text;
    Timestamp time_of_messege;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_chat() {
        return user_chat;
    }

    public void setUser_chat(int user_chat) {
        this.user_chat = user_chat;
    }

    public String getMessege_text() {
        return messege_text;
    }

    public void setMessege_text(String messege_text) {
        this.messege_text = messege_text;
    }

    public Timestamp getTime_of_messege() {
        return time_of_messege;
    }

    public void setTime_of_messege(Timestamp time_of_messege) {
        this.time_of_messege = time_of_messege;
    }

    public Messege(int id, int user_chat, String messege_text, Timestamp time_of_messege) {
        this.id = id;
        this.user_chat = user_chat;
        this.messege_text = messege_text;
        this.time_of_messege = time_of_messege;
    }


}
