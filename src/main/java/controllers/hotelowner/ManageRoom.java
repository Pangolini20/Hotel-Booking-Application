package controllers.hotelowner;

import Database.Hotelroom;
import Tools.DeleteRoomXML;

import Tools.EditRoomXML;
import Tools.LoadRooms;
import controllers.Remind;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Tools.RoomCreatorXML.createRoom;

public class ManageRoom implements Initializable
{

    @FXML TableView table;
    @FXML TableColumn <Hotelroom,String> col_1;
    @FXML TableColumn <Hotelroom,String> col_2;
    @FXML TableColumn <Hotelroom,String> col_3;
    @FXML TableColumn <Hotelroom,String> col_4;
    @FXML TableColumn <Hotelroom,String> col_5;
    @FXML TableColumn <Hotelroom,String> col_6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       col_1.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("ID")));
       col_2.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("size")));
       col_3.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("price")));
       col_4.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("nr_pers")));
       col_5.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("available")));
       col_6.setCellValueFactory((new PropertyValueFactory <Hotelroom,String> ("usr_occup")));
       table.setItems(getRoom());
    }

    private ObservableList<Hotelroom> getRoom()
    {
        ObservableList<Hotelroom> rooms= FXCollections.observableArrayList();

        for (Hotelroom e : Remind.room_arr)
        {
            if(e.getOwner().equals(Remind.username))
                rooms.add(e);
        }

        return rooms;
    }

    @FXML
    Button back;
    @FXML
    TextField tf_id,tf_size,tf_nrperson,tf_price;

    private boolean check_repeat(Hotelroom r)
    {
        for( Hotelroom e : Remind.room_arr)
        {
            if(r.getOwner().equals(e.getOwner())
                    && r.getID().equals(e.getID()))
                return  false;
        }

        return true;
    }

    public void back_mm()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) back.getScene().getWindow();

        loader.setLocation(getClass().getResource("/hotelowner/mainmenu.fxml"));
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

    //String ID, String owner, String size, String price, String nr_pers
    public void add_room()
    {
        Hotelroom new_room=new Hotelroom(tf_id.getText(), Remind.username,tf_size.getText(),
                tf_price.getText(),tf_nrperson.getText());

        if(check_repeat(new_room)==true) {

            createRoom(new_room);

            table.getItems().add(new_room);
            tf_id.clear();
            tf_size.clear();
            tf_price.clear();
            tf_nrperson.clear();

            LoadRooms lr=new LoadRooms();
            lr.readDB();
            Remind.room_arr=lr.getRoomlist();

            URL url=null;

            initialize(url, null);
        }
        //reload;
    }

    public void delete_room()
    {
        Hotelroom obj= (Hotelroom) table.getSelectionModel().getSelectedItem();

        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());

        DeleteRoomXML f=new DeleteRoomXML();
        f.deleteRoom(obj);
    }

    public void edit_room()
    {

        Hotelroom obj= (Hotelroom) table.getSelectionModel().getSelectedItem();
        Hotelroom edit=new Hotelroom(tf_id.getText(),Remind.username,tf_size.getText(),
                tf_price.getText(),tf_nrperson.getText());

        if(check_repeat(edit)==true){

        EditRoomXML ed=new EditRoomXML();
        ed.editRoom(obj,edit);

        tf_id.clear();
        tf_size.clear();
        tf_price.clear();
        tf_nrperson.clear();

        LoadRooms lr=new LoadRooms();
        lr.readDB();
        Remind.room_arr=lr.getRoomlist();

        URL url=null;

        initialize(url, null);}
    }
}