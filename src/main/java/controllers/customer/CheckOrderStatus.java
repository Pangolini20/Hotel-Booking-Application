package controllers.customer;

import Database.Hotelroom;
import Database.Request;
import Tools.EditReqXML;
import Tools.EditRoomXML;
import Tools.LoadReq;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckOrderStatus implements Initializable
{
    @FXML
    TableView table;
    @FXML
    TableColumn<Request,String> col_1;
    @FXML TableColumn <Request,String> col_2;
    @FXML TableColumn <Request,String> col_3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_1.setCellValueFactory((new PropertyValueFactory<Request, String>("ID")));
        col_2.setCellValueFactory((new PropertyValueFactory<Request, String>("owner")));
        col_3.setCellValueFactory((new PropertyValueFactory<Request, String>("status")));
        table.setItems(getRequests());
    }
        private ObservableList<Request> getRequests()
        {
            ObservableList<Request> reqs= FXCollections.observableArrayList();

            for (Request e : Remind.req_arr)
            {
                if(e.getCustomer().equals(Remind.username))
                {
                    reqs.add(e);
                }

            }
            return reqs;
        }


    @FXML
    Button back;

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

    private Hotelroom search(String ID,String owner)
    {
        for(Hotelroom e : Remind.room_arr)
        {
            if(e.getOwner().equals(owner) && e.getID().equals(ID))
                return e;
        }

        return null;
    }

    public void confirm()
    {
        Request req;
        req=(Request) table.getSelectionModel().getSelectedItem();

        if(req.getStatus().equals("accepted"))
        {
            req.setStatus("confirmed");

            EditReqXML ed=new EditReqXML();
            ed.EditStatus(req);

            Hotelroom modify=search(req.getID(),req.getOwner());
            if(modify!=null)
            {
                EditRoomXML edit=new EditRoomXML();
                edit.editOccupy(modify,req);
            }
            LoadRooms lod=new LoadRooms();
            lod.readDB();
            Remind.room_arr=lod.getRoomlist();

            LoadReq lr= new LoadReq();
            lr.readDB();
            Remind.req_arr=lr.getReq_list();

            URL url = null;
            initialize(url, null);
        }
    }

}
