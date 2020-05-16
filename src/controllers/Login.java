package controllers;

import Database.User;
import Tools.LoadDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class Login implements Initializable
{
    @FXML Button log_button;
    @FXML TextField user_text;
    @FXML PasswordField pass_field;
    @FXML Button reg_button;

    private List<User> list;

    private void check_credentials()
    {

    }

    public void authentification()
    {

    }

     public void goto_register()
    {
        Stage stage;
        Parent root=null;


        stage = (Stage) reg_button.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadDatabase.readDB();
    }
}
