package sample.controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import sample.Controller;
import sample.Style;
import sample.model.Chat;
import sample.model.User;
import sample.repository.ChatRep;
import sample.repository.MeesegeRep;
import sample.repository.UserChatRep;
import sample.repository.UsersRep;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MainController
{
    @FXML
    private Button sendButton;
    @FXML
    private ListView<String> usersList;
    @FXML
    private TextArea messageBox;
    @FXML
    private TabPane tabs;

//------------------------------------------------------

    private UserChatRep userChatRep = new UserChatRep();

    private UsersRep usersRep = new UsersRep();

    private MeesegeRep meesegeRep = new MeesegeRep();

    private ChatRep chatRep = new ChatRep();


    private ArrayList<User> usersArrayList;

    private ArrayList<Chat> chats;


    private TextArea ta = null;

    private int receiveChatId = 0;

    private int sentChatId = 0;

    private int countMessage = 0;

//-------------------------------------------------------------------------------------------

    public TextArea getTa() {
        return ta;
    }

    public void setTa(TextArea ta) {
        this.ta = ta;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public MeesegeRep getMeesegeRep() {return meesegeRep;}

    public void setMeesegeRep(MeesegeRep meesegeRep) {
        this.meesegeRep = meesegeRep;
    }

    public UserChatRep getUserChatRep() {
        return userChatRep;
    }

    public void setUserChatRep(UserChatRep userChatRep) {
        this.userChatRep = userChatRep;
    }

    public UsersRep getUsersRep() {
        return usersRep;
    }

    public void setUsersRep(UsersRep usersRep) {
        this.usersRep = usersRep;
    }

    public ChatRep getChatRep() {
        return chatRep;
    }

    public void setChatRep(ChatRep chatRep) {
        this.chatRep = chatRep;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public int getSentChatId() {return sentChatId;}

    public void setSentChatId(int sentChatId) {
        this.sentChatId = sentChatId;
    }

    public int getReceiveChatId() {
        return receiveChatId;
    }

    public void setReceiveChatId(int receiveChatId) {
        this.receiveChatId = receiveChatId;
    }

    public TabPane getTabs() {
        return tabs;
    }

    public void setTabs(TabPane tabs) {
        this.tabs = tabs;
    }

//--------------------------------------------------



    @FXML
    public void initialize() throws SQLException {

        chats = chatRep.getAllChats(); // помещаем все все Логины

        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB); // кнопка закрытия Таба

        ArrayList<String> logins = new ArrayList<>(); // создаём список Логинов

        usersArrayList = getUsers();

        Collections.sort(usersArrayList);

//        isOnline();

        for (User u: usersArrayList
             ) {
            logins.add(u.getLogin()); //для каждого юзера из списка Юзеров вносим в список по логину

        }

// Сделать поток для нового

        ObservableList<String> items = FXCollections.observableArrayList ( // создаём объект items для нового списка для прослушивания
                logins
        );


        usersList.setItems(items);
        usersList.setCellFactory((param -> new Style(usersArrayList)));



        usersList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldLogin, String newLogin) -> {
//
//                    ThreadMainChat threadMainChat = new ThreadMainChat(this, oldLogin, newLogin);
////                    Thread thread = new Thread(threadMainChat);
//
//                    Platform.runLater(threadMainChat);

//                    thread.start();
//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(getReceiveChatId());
//
//                    System.out.println(getSentChatId());
//
//                    setSentChatId(0);
//                    setReceiveChatId(0);


// System.out.println("ListView selection changed from oldValue = "
//                            + oldLogin + " to newValue = " + newLogin);


                                Tab tab = null;

                                String tabColor = null;
                                String name = newLogin + Controller.login;

                                try {


                                    Optional<Tab> matchingObject = tabs.getTabs()
                                            .stream()
                                            .filter(p -> p.getText().equals(newLogin) ||
                                                    p.getText().equals(Controller.login))
                                            .findFirst();
                                    tab = matchingObject.get();

                                    System.out.println(tab.getText());
                                }
                                catch (NoSuchElementException e) {

                                        tab = new Tab(newLogin);

                                        ta = new TextArea("Chat me");
                                        ta.setEditable(false);
                                        tab.setContent(ta);
                                        tabColor = getColorLogin(newLogin);
                                        tab.setStyle(tabColor);
                                        tabs.getTabs().add(tab);

                                }
                                tabs.getSelectionModel().select(tab);

//                                ListenerMessages listenerMessages = new ListenerMessages(this, tabs.getSelectionModel().getSelectedItem());
//                                Platform.runLater(listenerMessages);
                                int i = 0;
//                    System.out.println(chats.toString());
//                    System.out.println("size " + chats.size());
                                do {
                                    if ( chats.size() != 0 &&
                                            (chats.get(i).getName().equals(newLogin + Controller.login)
                                                    || chats.get(i).getName().equals(Controller.login + newLogin)))
                                    {
                                        try {
                                            String chatName = (chats.get(i).getName().
                                                    equals(newLogin + Controller.login))? newLogin + Controller.login
                                                    :Controller.login + newLogin;
                                            sentChatId = userChatRep.getUserChatId(
                                                    chatRep.getChatId(chatName),
                                                    usersRep.getByLogin(Controller.login));

                                            receiveChatId = userChatRep.getUserChatId(
                                                    chatRep.getChatId(chatName),
                                                    usersRep.getByLogin(newLogin));

                                String text = meesegeRep.getAllMessage(sentChatId);
                                ta.setText(ta.getText() + "\n" + text);
                               break;

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        else if ( chats.size() == 0 || i == chats.size()-1){
                            try {

                                int chat_id =  chatRep.insertChat(name);
//                                System.out.println(usersRep.getByLogin(Controller.login));
                                sentChatId = userChatRep.insertIntoUsersChat(chat_id,
                                        usersRep.getByLogin(Controller.login));
                                receiveChatId = userChatRep.insertIntoUsersChat(chat_id,
                                        usersRep.getByLogin(newLogin));


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        i++;
//                        System.out.println("i" + i);
//
//                        System.out.println("size " + chats.size());
                    }
                    while (i < chats.size());
                    try {
                        chats = chatRep.getAllChats();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


//                    ListenerMessages listenerMessages = null;
//                    try {
//                        listenerMessages = new ListenerMessages(this);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    Thread thread = new Thread(listenerMessages);
//                    thread.start();
//                    Platform.runLater(listenerMessages);

                });

//
//        Controller.mainScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER)
//                {
//                    System.out.println("A key was pressed");
//                }
//            }
//        });
//        Controller.mainScene.setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.A) {
//
//            }
//        });
    }

    public void clickOnSend() throws SQLException {
        sendButton.setText("_send");
        sendButton.setMnemonicParsing(true);
        String mess = messageBox.getText();
        messageBox.setText("");
        int i = tabs.getSelectionModel().getSelectedIndex();
        TextArea textArea = (TextArea) tabs.getSelectionModel().getSelectedItem().getContent();
        textArea.setText(textArea.getText() + "\n"+mess);
        meesegeRep.insertMessege(sentChatId, mess);
        meesegeRep.insertMessege(receiveChatId, mess);
        countMessage = meesegeRep.getCount(sentChatId);



    }
//
//    public void isOnline (){
//
//        for (User u:usersArrayList){
//            if (u.getStatus() == true){
//               System.out.println(u.getLogin() + " is online");
//            }
//            else {
//                System.out.println(u.getLogin() + " is offline");
//            }
//        }
//    }


    public ArrayList<User> getUsers() throws SQLException {
        return usersRep.getAll();
    }

    @FXML
    public void shutdown() throws SQLException {

        for (User u: usersArrayList
        )

        {
            u.getLogin();

            if (u.getLogin().equals(Controller.login)){

            usersRep.updateStatusOnNo(u.getId());
        }

        }
        usersArrayList = usersRep.getAll();
//        isOnline();
//        System.out.println("Stop");
    }


    public String getColorLogin(String login)
    {
        Optional<User> matchingObject = usersArrayList.stream().
                filter(p -> p.getLogin().equals(login)).
                findFirst();
        User u = matchingObject.get();

        if (u.getStatus())
            return "-fx-text-base-color: green;";
        return "-fx-text-base-color: red;";


//        usersArrayList.get()
    }

    public void onkeyPressed(KeyEvent keyEvent) throws SQLException {
        if (keyEvent.getCode() == KeyCode.S)
        {
            clickOnSend();
            System.out.println("S");
        }
    }
}
