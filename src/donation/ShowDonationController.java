package donation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.DbUtility;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ShowDonationController implements Initializable {

    Donate_panelController Dp;
    Connection con = DbUtility.dbConnect();
    Statement stm;
    ResultSet rs;

    @FXML
    private Label lb_donaterId;

    @FXML
    private Label lb_bloodType;

    @FXML
    private Label lb_donateTimes;

    @FXML
    private ListView<String> lv_donateDates;
    ObservableList<String> donateDates = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_donaterId.setText(Dp.id);
        String query1 = "SELECT `blood_type` FROM `blood_donate_records` WHERE donate_id = '"+Dp.id+"'";
        String query2 = "SELECT COUNT(donate_id) FROM `blood_donate_records` WHERE donate_id = '"+Dp.id+"'";
        String query3 = "SELECT `donate_date` FROM `blood_donate_records` WHERE donate_id = '"+Dp.id+"'";
        try {
            stm = (Statement)con.createStatement();
            rs = stm.executeQuery(query1);
            while (rs.next()){
                lb_bloodType.setText(rs.getString(1));
            }
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
        try {
            stm = (Statement)con.createStatement();
            rs = stm.executeQuery(query2);
            while (rs.next()){
                lb_donateTimes.setText(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            stm = (Statement)con.createStatement();
            rs = stm.executeQuery(query3);
            while (rs.next()){
                donateDates.addAll(rs.getString(1));
            }
            lv_donateDates.setItems(donateDates);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
