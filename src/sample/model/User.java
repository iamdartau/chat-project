package sample.model;

import java.sql.Time;
import java.sql.Timestamp;

public class User implements Comparable<User>{
    int id;

    String name, surname, login, password;
    Timestamp time_reg;
    Timestamp last_activity;
    boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User(String name, String surname, String login, String password, String st) {
        this.status = (st.equals("Y")? true:false);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;

    }
    public User(int id, String name, String surname, String login, String password, String st) {
        this.id = id;
        this.status = (st.equals("Y")? true:false);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;

    }
    public User(int id, String name, String surname, String login, String password, Timestamp time_reg, Timestamp last_activity, String st) {
        this.status = (st.equals("Y")? true:false);
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.time_reg = time_reg;
        this.last_activity = last_activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTime_reg() {
        return time_reg;
    }

    public void setTime_reg(Timestamp time_reg) {
        this.time_reg = time_reg;
    }

    public Timestamp getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(Timestamp last_activity) {
        this.last_activity = last_activity;
    }

    @Override
    public String toString() {
        return "ModelUsers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", time_reg=" + time_reg +
                ", last_activity=" + last_activity +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int b1 = getStatus()? 1:0;
        int b2 = o.getStatus()? 1:0;
        return b2 - b1;
    }
}



