package sample;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import sample.model.User;

import java.util.ArrayList;


public class Style extends ListCell<String> {
    private ArrayList<User> users;

    public  Style(ArrayList<User> users)
    {
        this.users = users;
    }
    @Override
    protected void updateItem(String item, boolean empty)
    {
        super.updateItem(item, empty);
        for (User u : users
             ) {
            if (u.getLogin().equals(item) && u.getStatus())
            {
                setText(item);
                setTextFill(Color.GREEN);
            }
            else if (u.getLogin().equals(item) && !(u.getStatus()))
            {
                setText(item);
                setTextFill(Color.RED);
            }
        }
    }
}
