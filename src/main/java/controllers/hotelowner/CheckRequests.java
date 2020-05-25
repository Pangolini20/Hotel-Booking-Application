package controllers.hotelowner;

import Database.Hotelroom;
import Database.Request;
import Tools.EditReqXML;
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

public class CheckRequests implements Initializable {

    @FXML
    Button back;

    @FXML
    TableView table;
    @FXML TableColumn<Request,String> col_1;
    @FXML TableColumn <Request,String> col_2;
    @FXML TableColumn <Request,String> col_3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_1.setCellValueFactory((new PropertyValueFactory<Request,String>("ID")));
        col_2.setCellValueFactory((new PropertyValueFactory <Request,String> ("customer")));
        col_3.setCellValueFactory((new PropertyValueFactory <Request,String> ("status")));
        table.setItems(getRequests());
    }

    private ObservableList<Request> getRequests()
    {
        ObservableList<Request> reqs= FXCollections.observableArrayList();

        for (Request e : Remind.req_arr)
        {
            if(e.getOwner().equals(Remind.username))
            {
                reqs.add(e);
            }

        }
        return reqs;
    }

    private Request req;

    public void decline()
    {
        req=(Request) table.getSelectionModel().getSelectedItem();

        if(!(req.getStatus().equals("confirmed"))) {
            req.setStatus("declined");

            EditReqXML ed=new EditReqXML();
            ed.EditStatus(req);


            LoadReq lr= new LoadReq();
            lr.readDB();
            Remind.req_arr=lr.getReq_list();

            URL url = null;
            initialize(url, null);
        }
    }

    public void accept()
    {
        req=(Request) table.getSelectionModel().getSelectedItem();

        if((!req.getStatus().equals("confirmed"))) {
            req.setStatus("accepted");

            EditReqXML ed=new EditReqXML();
            ed.EditStatus(req);

            LoadReq lr= new LoadReq();
            lr.readDB();
            Remind.req_arr=lr.getReq_list();


            URL url = null;
            initialize(url, null);
        }
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



}
