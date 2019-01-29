package admin_panel;

import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.DbUtility;
import model.Login;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    @FXML
    private Label lb_adminId;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_dob;

    @FXML
    private Label lb_gender;

    @FXML
    private Label lb_address;

    @FXML
    private Label lb_phNo;

    View_adminController va;

    Connection con = DbUtility.dbConnect();
    Login login = new Login();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String query = "SELECT id,name,dob,phonenumber,address,gender FROM admin WHERE id = '"+va.id+"'";
        System.out.println(query);
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lb_adminId.setText(rs.getString("id"));
                lb_name.setText(rs.getString("name"));
                lb_dob.setText(String.valueOf(rs.getDate("dob")));
                lb_gender.setText(rs.getString("gender"));
                lb_address.setText(rs.getString("address"));
                lb_phNo.setText(rs.getString("phonenumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
