package records;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import setNode.SetNode;

import java.net.URL;
import java.util.ResourceBundle;

public class Records_panelController implements Initializable {

    @FXML
    private StackPane hospital;

    @FXML
    private StackPane patient;

    @FXML
    private StackPane donate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SetNode(hospital, "/records/Hospital_records.fxml");
        new SetNode(patient, "/records/Patient_records.fxml");
        new SetNode(donate, "/records/Donate_records.fxml");
    }

}
