package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import sample.Controller;
import sample.controller.MainController;

import java.sql.SQLException;

public class ThreadMainChat implements Runnable {

    private  Tab tab;

    MainController mainController;

    private String newLogin;

    private String oldLogin;

    public ThreadMainChat(MainController mainController, String oldLogin, String newLogin)
    {
        this.mainController = mainController;
        this.oldLogin = oldLogin;
        this.newLogin = newLogin;

        System.out.println("Thread start");
    }

    @Override
    public void run() {

        System.out.println("ThreadMain Работает до tabOpener");
        tabOpener();
        System.out.println("ThreadMain Работает после tabOpener");

/*   <<<=== Вот Здесь!!!


        Tab tab = new Tab(newLogin);
        String name = newLogin + Controller.login;
        TextArea ta = new TextArea("Chat me");
        ta.setEditable(false);
        tab.setContent(ta);
        mainController.getTabs().getTabs().add(tab);
        mainController.getTabs().getSelectionModel().select(tab);

        ListenerMessages listenerMessages = new ListenerMessages(mainController);
        Platform.runLater(listenerMessages);

        int i = 0;
//                    System.out.println(chats.toString());
//                    System.out.println("size " + chats.size());
        do {
            if ( mainController.getChats().size() != 0 &&
                    (mainController.getChats().get(i).getName().equals(newLogin + Controller.login)
                            || mainController.getChats().get(i).getName().equals(Controller.login + newLogin)))
            {
                try {
                    String chatName = (mainController.getChats().get(i).getName().
                            equals(newLogin + Controller.login))? newLogin + Controller.login
                            :Controller.login + newLogin;
                    mainController.setSentChatId(mainController.getUserChatRep().getUserChatId(
                            mainController.getChatRep().getChatId(chatName),
                            mainController.getUsersRep().getByLogin(Controller.login)));

                    mainController.setReceiveChatId( mainController.getUserChatRep().getUserChatId(
                            mainController.getChatRep().getChatId(chatName),
                            mainController.getUsersRep().getByLogin(newLogin)));

        String text = mainController.getMeesegeRep().getAllMessage(mainController.getSentChatId());
        ta.setText(ta.getText() + "\n" + text);
        break;

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

else if ( mainController.getChats().size() == 0 || i == mainController.getChats().size()-1){
    try {

        int chat_id =  mainController.getChatRep().insertChat(name);
//                                System.out.println(usersRep.getByLogin(Controller.login));
        mainController.setSentChatId( mainController.getUserChatRep()
                .insertIntoUsersChat(chat_id,
                mainController.getUsersRep()
                .getByLogin(Controller.login)));

        mainController.setReceiveChatId( mainController.getUserChatRep()
                .insertIntoUsersChat(chat_id,
                mainController.getUsersRep().getByLogin(newLogin)));
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
i++;
System.out.println("i" + i);

System.out.println("size " + mainController.getChats().size());
}
while (i < mainController.getChats().size());
try {
    mainController.setChats(mainController
            .getChatRep()
            .getAllChats());
} catch (SQLException e) {
e.printStackTrace();
}

                                                                                                 Вот Здесь!!! ===>>>  */

//        mainController.setReceiveChatId(1);
//        mainController.setSentChatId(2);
    }


    private void tabOpener (){

        tab = new Tab(newLogin);
        String name = newLogin + Controller.login;
        TextArea ta = new TextArea("Chat me");
        ta.setEditable(false);
        tab.setContent(ta);
        mainController.getTabs().getTabs().add(tab);
        mainController.getTabs().getSelectionModel().select(tab);

//        ListenerMessages listenerMessages = new ListenerMessages(mainController);
//        Platform.runLater(listenerMessages);

        int i = 0;
        do {
            if ( mainController.getChats().size() != 0 &&
                    (mainController.getChats().get(i).getName().equals(newLogin + Controller.login)
                            || mainController.getChats().get(i).getName().equals(Controller.login + newLogin)))
            {
                try {
                    String chatName = (mainController.getChats().get(i).getName().
                            equals(newLogin + Controller.login))? newLogin + Controller.login
                            :Controller.login + newLogin;
                    mainController.setSentChatId(mainController.getUserChatRep().getUserChatId(
                            mainController.getChatRep().getChatId(chatName),
                            mainController.getUsersRep().getByLogin(Controller.login)));

                    mainController.setReceiveChatId( mainController.getUserChatRep().getUserChatId(
                            mainController.getChatRep().getChatId(chatName),
                            mainController.getUsersRep().getByLogin(newLogin)));

                    String text = mainController.getMeesegeRep().getAllMessage(mainController.getSentChatId());

                    ta.setText(ta.getText() + "\n" + text);
                    ListenerMessages listenerMessages = new ListenerMessages(mainController, tab);
                    Platform.runLater(listenerMessages);
                    break;

                } catch (SQLException e) {
                    System.out.println("Error Thread");
                    e.printStackTrace();
                }
            }

            else if ( mainController.getChats().size() == 0 || i == mainController.getChats().size()-1){
                try {

                    int chat_id =  mainController.getChatRep().insertChat(name);
                    mainController.setSentChatId( mainController.getUserChatRep()
                            .insertIntoUsersChat(chat_id,
                                    mainController.getUsersRep()
                                            .getByLogin(Controller.login)));

                    mainController.setReceiveChatId( mainController.getUserChatRep()
                            .insertIntoUsersChat(chat_id,
                                    mainController.getUsersRep().getByLogin(newLogin)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("i" + i);

            System.out.println("size " + mainController.getChats().size());
        }
        while (i < mainController.getChats().size());
        try {
            mainController.setChats(mainController
                    .getChatRep()
                    .getAllChats());
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }








}

//    MainController mainController = new MainController();


//    @Override
//    public void run() {
//
//        System.out.println("Поток============================++++++++++");
//        //mainController.clickOnSend();
//
//        try {mainController.initialize();}
//
//        catch (SQLException e) {e.printStackTrace();}
//
//    }
//
//}
