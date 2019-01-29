package blood;

import fxml_loader.fxmlL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Retrieve;

import java.net.URL;
import java.util.ResourceBundle;

public class Blood_panelController implements Initializable {
    Retrieve retrieve = new Retrieve();

    @FXML
    private Label lb_qty_a;

    @FXML
    private Label lb_qty_b;

    @FXML
    private Label lb_qty_o;

    @FXML
    private Label lb_qty_ab;

    @FXML
    void refrash(ActionEvent event) {
        retrieve.O = 0;
        retrieve.A = 0;
        retrieve.B = 0;
        retrieve.AB = 0;
        forNumberOfBlood();
    }

    @FXML
    void back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
    }

    private void forNumberOfBlood(){
        retrieve.getO(lb_qty_o, "blood");
        retrieve.getA(lb_qty_a, "blood");
        retrieve.getB(lb_qty_b, "blood");
        retrieve.getAB(lb_qty_ab, "blood");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forNumberOfBlood();
    }
}
