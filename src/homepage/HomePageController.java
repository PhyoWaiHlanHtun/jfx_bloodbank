package homepage;

import fxml_loader.Utilities;
import fxml_loader.fxmlL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// FB : https://www.facebook.com/mr.phyowaihlanhtun
// Developer : PhyoWaiHlanHtun(Cryptonym)
// Contact : phyowaihlanhtun@gmail.com

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox vb;

    private String file_location;


    @FXML
    void logout(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/login/Login.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : vb.getChildren() ){
            HBox hb = (HBox) node;
            for (Node node1 : hb.getChildren()){
                if (node1.getAccessibleText() != null){
                    node1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                        switch (node1.getAccessibleText()){
                            case "blood":
                                file_location = "/blood/Blood_panel.fxml";
                                break;
                            case "donation":
                                file_location = "/donation/Donation_home.fxml";
                                break;
                            case "records":
                                file_location = "/records/Records_panel.fxml";
                                break;
                            case "admin":
                                file_location = "/admin_panel/Admin_home.fxml";
                                break;
                            case "transfer":
                                file_location = "/transfer/Transfer_home.fxml";
                                break;
                        }
                        new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),file_location);
                    });
                }
            }
        }
    }
}
