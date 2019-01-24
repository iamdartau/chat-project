package sample.model;

import java.util.ArrayList;

public class Chat {

    public ArrayList<User> getUsersChat() {
        return usersChat;
    }

    public void setUsersChat(ArrayList<User> usersChat) {
        this.usersChat = usersChat;
    }

    public ArrayList<User> usersChat;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    int id;

    String name;

    public Chat(int id, String name) {
        this.id = id;
        this.name = name;
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
}
