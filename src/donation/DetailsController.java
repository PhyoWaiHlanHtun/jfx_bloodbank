package donation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.DbUtility;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import model.donater;

public class DetailsController implements Initializable {

    @FXML
    private Label lb_donaterID;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_nrc;

    @FXML
    private Label lb_dob;

    @FXML
    private Label lb_gender;

    @FXML
    private Label lb_address;

    @FXML
    private Label lb_phNo;

    @FXML
    private Label lb_bloodType;

    @FXML
    private Label lb_donateDate;

    Connection con = DbUtility.dbConnect();
    View_donater_panelController vd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String query = "SELECT id,donaterid,name,nrc,dob,gender,address,phonenumber,bloodtype,donatedate FROM donater WHERE id ='"+vd.ID+"'";
        System.out.println(query);
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lb_donaterID.setText(rs.getString("id"));
                lb_name.setText(rs.getString("name"));
                lb_nrc.setText(rs.getString("nrc"));
                lb_dob.setText(String.valueOf(rs.getDate("dob")));
                lb_gender.setText(rs.getString("gender"));
                lb_address.setText(rs.getString("address"));
                lb_phNo.setText(rs.getString("phonenumber"));
                lb_bloodType.setText(rs.getString("bloodtype"));
                lb_donateDate.setText(String.valueOf(rs.getDate("donatedate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
