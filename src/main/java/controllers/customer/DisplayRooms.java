package controllers.customer;

import Database.Hotelroom;
import Database.Request;
import Tools.ReqXML;
import controllers.Remind;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayRooms implements Initializable {

    @FXML
    Button back;
    @FXML
    TableView table;

    @FXML
    TableColumn<Hotelroom,String> col_1;
    @FXML TableColumn <Hotelroom,String> col_2;
    @FXML TableColumn <Hotelroom,String> col_3;
    @FXML TableColumn <Hotelroom,String> col_4;
    @FXML TableColumn <Hotelroom,String> col_5;

    public void goto_mm()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) back.getScene().getWindow();

        loader.setLocation(getClass().getResource("/customer/mainmenu.fxml"));
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
        col_1.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("ID")));
        col_2.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("size")));
        col_3.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("price")));
        col_4.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("nr_pers")));
        col_5.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("owner")));

        table.setItems(getRoom());
    }

    @FXML
    Label txt;

    private ObservableList<Hotelroom> getRoom()
    {
        ObservableList<Hotelroom> rooms= FXCollections.observableArrayList();

        for (Hotelroom e : Remind.room_arr)
        {
            if(e.isAvailable()==true)
                rooms.add(e);
        }

        return rooms;
    }

    public void book_room()
    {
        txt.setText("A request has been sent to the owner");

        Hotelroom room = (Hotelroom) table.getSelectionModel().getSelectedItem();
        Request req=new Request(room.getID(),room.getOwner(),Remind.username);

        ReqXML rx= new ReqXML();
        rx.createReq(req);
    }
}
