package controllers.hotelowner;

import Tools.LoadReq;
import Tools.LoadRooms;
import Tools.ReqXML;
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

    @FXML Button logg_off,display_rooms,manage_room,check_req;

    public void goto_login()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) logg_off.getScene().getWindow();

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

    public void goto_displayroom()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) display_rooms.getScene().getWindow();

        loader.setLocation(getClass().getResource("/hotelowner/displayroom.fxml"));
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

    public void goto_managerooms()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) manage_room.getScene().getWindow();

        loader.setLocation(getClass().getResource("/hotelowner/manageroom.fxml"));
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

    public void goto_checkrequest()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) check_req.getScene().getWindow();

        loader.setLocation(getClass().getResource("/hotelowner/checkrequests.fxml"));
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

    @FXML
    Label welcome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcome.setText("Welcome " + Remind.username +" !");
        LoadRooms rd=new LoadRooms();
        rd.readDB();
        Remind.room_arr=rd.getRoomlist();

        LoadReq lr= new LoadReq();
        lr.readDB();
        Remind.req_arr=lr.getReq_list();
    }
}
