package sample.controller;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

import javax.management.ListenerNotFoundException;
import java.sql.SQLException;

public class ListenerMessages extends Thread {
    private int countMess = 0;

    private Tab tab;

    private TextArea textArea;

    MainController mainController;

    ListenerMessages(MainController mainController) throws SQLException {
        this.mainController = mainController;
    }

    ListenerMessages(MainController mainController, Tab tab) throws SQLException {
        this.mainController = mainController;
        this.tab = tab;
        textArea = (TextArea) tab.getContent();


    }

    ListenerMessages () throws SQLException{
        
        
    }

    @Override
    public void run() {

//        isTrueforMessege();

        try {
            isTrueforMessege();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

/*
        while (true)
        {
            if (mainController.getTa() != null) {
                try {
                    countMess = mainController.getMeesegeRep()
                            .getCount(mainController.getSentChatId());

                    if (mainController.getCountMessage() != countMess) {

                        mainController.getTa().setText(mainController.getMeesegeRep().getAllMessage(mainController.getSentChatId()));
                        Thread.yield();
                    }
                } catch (SQLException e) {e.printStackTrace();}
            }


        }
*/
//    }

    ListenerMessages listenerMessages = new ListenerMessages();
    String text = mainController.getMeesegeRep().getAllMessage(mainController.getSentChatId());
    
    

    public void isTrueforMessege() throws InterruptedException {
        //setDaemon(true);
        System.out.println("Поток ListnerMesseges работает");


        Thread.sleep(1000);
        //loop:
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this) {
                    //String text = mainController.getMeesegeRep().getAllMessage(mainController.getSentChatId());
                    System.out.println(text);


                }
                Thread.sleep(100);
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    System.out.println("error");
                    Thread.currentThread().interrupt();
                }
//            System.out.println(text);
            } finally {
                continue ;//loop;
            }
        }

    }


}
