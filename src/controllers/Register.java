package controllers;

import Database.Customer;
import Database.HotelOwner;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Database.Security.generateHash;

public class Register implements Initializable
{
    @FXML
    Button sub_but;
    @FXML private TextField userfield,full_name,phone,EIN,adress,facilities;
    @FXML private PasswordField pass_field;
    @FXML
    private ChoiceBox  role;
    @FXML private HBox HB1,HB2,HB3;



    public void writeXML(Object o)
    {

        try
        {
            File file= new File("./src/database.xml");
            FileOutputStream fos=new FileOutputStream(file,true);
            XMLEncoder encoder= new XMLEncoder(fos);
            encoder.writeObject(o);
            encoder.close();
            fos.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkEmpty()
    {
        String option= (String) role.getValue();

        if(userfield.getText().isEmpty() ||
                full_name.getText().isEmpty()||
                phone.getText().isEmpty() ||
                pass_field.getText().isEmpty())
            return false;


        if(option == "HotelOwner")
        {
            if(EIN.getText().isEmpty() ||
            adress.getText().isEmpty()||
            facilities.getText().isEmpty())
                return false;
        }

        return true;
    }

    public void register() throws IOException {
        // scrie stuff in file

        String option = (String) role.getValue();

        if(checkEmpty()) {
            create_obj();
            goto_login();
        }

    }

    public void goto_login() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) sub_but.getScene().getWindow();

        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<String>();
        list.add("HotelOwner");
        list.add("Customer");

        ObservableList obList = FXCollections.observableList(list);

        role.setItems(obList);

        sub_but.visibleProperty().bind(Bindings.createBooleanBinding(() -> role.getValue() != null, role.valueProperty()));
        HB1.visibleProperty().bind(Bindings.createBooleanBinding(() -> role.getValue() == "HotelOwner", role.valueProperty()));
        HB2.visibleProperty().bind(Bindings.createBooleanBinding(() -> role.getValue() == "HotelOwner", role.valueProperty()));
        HB3.visibleProperty().bind(Bindings.createBooleanBinding(() -> role.getValue() == "HotelOwner", role.valueProperty()));

    }


    public void create_obj()
    {
        HotelOwner user;
        Customer user_c;

        String hashed_password="";

        try {
            hashed_password=generateHash(pass_field.getText());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(role.getValue() == "HotelOwner")
        {
            user=new HotelOwner(userfield.getText(),hashed_password, full_name.getText(),
                    phone.getText(),EIN.getText(),
                    adress.getText(),facilities.getText());
            user.setRole("HotelOwner");
            writeXML(user);
        }
        else
        {
            user_c=new Customer(userfield.getText(),hashed_password,full_name.getText(),
                    phone.getText());
            user_c.setRole("Customer");
            writeXML(user_c);
        }


    }


}
