package sample.controller;

import com.sun.org.apache.regexp.internal.RE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Controller;
import sample.model.User;
import sample.repository.UsersRep;
import sun.security.util.Password;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class RegistrationPanelController {

    Pattern pattern;
    Pattern pattern2;

    UsersRep usersRep = new UsersRep();
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField password2;

    String Name, Surname, Login, Password, Password2;
    ArrayList<User> users;

    @FXML
    public void initialize() throws SQLException {
        users = usersRep.getAll();
    }
    public void clickOnNext(ActionEvent actionEvent) throws SQLException {
        Name = name.getText();
        Surname = surname.getText();
        Login = login.getText();
        Password = password.getText();
        Password2 = password2.getText();

        if (!Password.equals(Password2)) {
            showAlertWithDefaultHeaderTextPassword();
            password.setText("");
        }
        else {
            if (!isLogNotExists(Login)) {
                usersRep.sign_up(new User(Name, Surname, Login, Password, "Y"));
                Controller.registerStage.close();
            } else {
                showAlertWithDefaultHeaderText();
                login.setText("");
            }
        }
    }

    private void showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");



        // alert.setHeaderText("Results:");
        alert.setContentText("User is already exist");


        alert.showAndWait();
    }

    private void showAlertWithDefaultHeaderTextPassword(){

        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setTitle("Error");
        alert2.setContentText("passwords are not the same");
        alert2.showAndWait();
    }

    public boolean isLogNotExists(String log)
    {
        for (User u: users
             ) {
            if(u.getLogin().equals(log))
                return true;

        }
        return false;
    }

    public void clickOnBack(ActionEvent actionEvent) {
        Controller.registerStage.close();
    }

    public boolean isPasswordRight(String psw){

        String pattern = "((?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[@#$%]).{6,20})";
        boolean Checked = psw.matches(pattern);

        if (Checked){
            return true;
        }
        else {
            return false;
        }

    }

    public boolean IsLoginRighht(String lgn){

        String pattern2 = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
        boolean Checked = lgn.matches(pattern2);

        if (Checked){
            return true;
        }
        else {
            return false;
        }

    }
}
