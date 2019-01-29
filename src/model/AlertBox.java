package model;

import javafx.scene.control.Alert;

public class AlertBox {
    static Alert alert;
    public static void Errormessage(String title,String information){
        alert = new Alert(Alert.AlertType.ERROR,information);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static void Informationmessage(String title,String information){
        alert = new Alert(Alert.AlertType.INFORMATION,information);
        alert.setTitle(title);
        alert.showAndWait();
    }

}
