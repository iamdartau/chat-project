package sample.model;

public class User_Chat {

    int  id, chat_id, user_id;

    @Override
    public String toString() {
        return "User_Chat{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", user_id=" + user_id +
                '}';
    }

    public User_Chat(int id, int chat_id, int user_id) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
