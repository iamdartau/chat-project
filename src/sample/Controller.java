package sample;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.controller.MainController;
import sample.model.User;
import sample.repository.UsersRep;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;


public class Controller {

    public static Scene mainScene;
    public  static String login;
    @FXML
    public TextField login_tf;
    @FXML
    public TextField password_tf;

    public static Stage registerStage;

    private void showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");

        // alert.setHeaderText("Results:");
        alert.setContentText("User is not define");

        alert.showAndWait();
    }

    public UsersRep usersRep;

    public void ClickOnLogin(ActionEvent actionEvent) throws SQLException, IOException {
        usersRep = new UsersRep();
        User user = usersRep.getUserbyloginandPassword(login_tf.getText(),password_tf.getText());

        if (user != null)
        {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/mainWindow.fxml"));
            login = user.getLogin();
            usersRep.updateStatusOnYes(user.getId());
            Parent root1 = (Parent) fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("Hello World Registration");
            stage.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(root1);
            stage.setScene( scene);
            this.mainScene = scene;
//            scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER)
//                {
//                    System.out.println("A key was pressed");
//                }
//            }
//        });
            stage.setOnHidden(e -> {
                try {
                    controller.shutdown();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            stage.show();
            Main.login.close();
            System.out.println(user);
        }
        else
            showAlertWithDefaultHeaderText();
    }

    public void ClickOnSignUP (ActionEvent actionEvent) throws SQLException{

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/registrationPanel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Hello World Registration");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene( new Scene(root1));
            stage.show();
            registerStage = stage;
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

}
