package controllers;

import Database.User;
import Tools.LoadUsers;
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

    private boolean check_credentials(User e) throws NoSuchAlgorithmException {

            String input_password=generateHash(pass_field.getText());

            if(e.getUsername().equals(user_text.getText()))
                if(e.getPassword().equals(input_password))
                    return true;
                else
                    return false;


        return false;
    }

    private User auth()
    {
        for(User e : database)
        {
            try {
                if(check_credentials(e))
                    return e;
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public void authentification()
    {
        User login=auth();

        if(login !=null)
        {
            if(login.getRole().equals("Customer"))
            {
                Remind.username=login.getUsername();
                goto_mm_customer();
            }
            else{
                Remind.username=login.getUsername();
                goto_mm_hotel_owner();
               }
        }
        else
            System.out.println("Invalid credentials");



    }

    public void goto_mm_hotel_owner()
    {
        Stage stage;
        Parent root=null;

        stage = (Stage) log_button.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/hotelowner/mainmenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goto_mm_customer()
    {
        Stage stage;
        Parent root=null;

        stage = (Stage) log_button.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/customer/mainmenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     public void goto_register()
    {
        Stage stage;
        Parent root=null;


        stage = (Stage) reg_button.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       LoadUsers obj=new LoadUsers();
        obj.readDB();
        database=obj.getArray();
    }
}
