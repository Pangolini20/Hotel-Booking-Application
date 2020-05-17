package controllers.hotelowner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageRoom {

    @FXML
    Button back;

    public void back_mm()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage= (Stage) back.getScene().getWindow();

        loader.setLocation(getClass().getResource("/fxml/hotelowner/mainmenu.fxml"));
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
