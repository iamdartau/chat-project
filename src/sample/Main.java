package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controller.ThreadMainChat;
import sample.database.DbConfig;

import java.net.SecureCacheResponse;
import java.util.Scanner;

public class Main extends Application {

    public static  Stage login;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
        login = primaryStage;
//        Thread thread1 = new Thread(new ThreadMainChat());
//        thread1.start();
    }

//    @Override
//    public void stop() {
//        System.out.println("Stop");
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
