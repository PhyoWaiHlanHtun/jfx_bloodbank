package donation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Retrieve;

import java.net.URL;
import java.util.ResourceBundle;

public class Donation_home_panelController implements Initializable {

    Retrieve retrieve = new Retrieve();
    @FXML
    private Label Registered_donater;

    @FXML
    private Label total_male;

    @FXML
    private Label total_female;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forNumberOfDonater();
    }
    private void forNumberOfDonater(){
        retrieve.gettotal(Registered_donater, "donater");
        retrieve.getmale(total_male, "donater");
        retrieve.getfemale(total_female, "donater");
    }

}
