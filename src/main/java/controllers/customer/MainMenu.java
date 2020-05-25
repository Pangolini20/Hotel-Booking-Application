package controllers.customer;

import Tools.LoadRooms;
import controllers.Remind;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    @FXML
    Button logoff;

    @FXML
    Label welcome;

    public void goto_login()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) logoff.getScene().getWindow();

        loader.setLocation(getClass().getResource("/login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void goto_disp_room()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) logoff.getScene().getWindow();

        loader.setLocation(getClass().getResource("/customer/displayrooms.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goto_checkord()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) logoff.getScene().getWindow();

        loader.setLocation(getClass().getResource("/customer/checkorderstatus.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcome.setText("Welcome " + Remind.username +" !");
        LoadRooms lr=new LoadRooms();
        lr.readDB();
        Remind.room_arr=lr.getRoomlist();
    }
}
