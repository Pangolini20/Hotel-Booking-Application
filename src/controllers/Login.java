package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login
{
    @FXML Button log_button;
    @FXML TextField user_text;
    @FXML PasswordField pass_field;
    @FXML Button reg_button;

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
}
