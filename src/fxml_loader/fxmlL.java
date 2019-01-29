package fxml_loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class fxmlL {

    public fxmlL(Stage stg, String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            stg.setResizable(false);
            stg.setTitle("BloodBox");
            stg.setScene(new Scene(root));
            stg.centerOnScreen();
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
