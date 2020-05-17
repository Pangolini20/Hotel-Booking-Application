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

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Database.Security.generateHash;

public class Login implements Initializable
{

    @FXML Button log_button;
    @FXML TextField user_text;
    @FXML PasswordField pass_field;
    @FXML Button reg_button;

    private ArrayList<User> database;

    private boolean check_credentials() throws NoSuchAlgorithmException {
        for(User e : database)
        {
            String input_password=generateHash(pass_field.getText());

            if(e.getUsername().equals(user_text.getText()))
                if(e.getPassword().equals(input_password))
                    return true;
                else
                    return false;
        }

        return false;
    }

    public void authentification()
    {
        try {
            System.out.println(check_credentials());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
       LoadDatabase obj=new LoadDatabase();
        obj.readDB();
        database=obj.getArray();

       /* for(User e : database)
        {
            System.out.println(e.toString());
        }*/
    }
}
