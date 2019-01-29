package donation;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DbUtility;
import model.DonaterDAO;
import model.donater;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

public class CustomizeController implements Initializable {

    @FXML
    private TextField tf_donaterID;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_nrc;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_phNo;

    @FXML
    private TextField tf_dob;

    @FXML
    private TextField tf_donateDate;

    @FXML
    private TextField tf_bloodType;

    @FXML
    private TextField tf_gender;

    @FXML
    private TextField tf_age;

    static Stage stage;
    Statement stm;
    PreparedStatement pstm;
    Connection con = DbUtility.dbConnect();

    View_donater_panelController vd;

    @FXML
    void Update(ActionEvent event) {
        newData();
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Update SucessFully" );
        alert.showAndWait();
        tf_name.getScene().getWindow().hide();
    }

    private void newData(){
        int id = vd.ID;
        String donaterID = tf_donaterID.getText().trim();
        String name = tf_name.getText().trim();
        String nrc = tf_nrc.getText().trim();
        int age = Integer.parseInt(tf_age.getText());
        String dob = tf_dob.getText().trim();
        String gender = tf_gender.getText().trim();
        String address = tf_address.getText().trim();
        String donatedate = tf_donateDate.getText().trim();
        String phone = tf_phNo.getText().trim();
        String bloodType = tf_bloodType.getText().trim();

        try {
            String q = "UPDATE `donater` SET `donaterid`='"+donaterID+"',`name`='"+name+"',`nrc`='"+nrc+"',`age`='"+age+"'," +
                    "`dob`='"+dob+"',`gender`='"+gender+"',`address`='"+address+"',`phonenumber`='"+phone+"'," +
                    "`bloodtype`='"+bloodType+"'WHERE id = '"+vd.ID+"'";
            pstm = (PreparedStatement)con.prepareStatement(q);
            pstm.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void clearFields(){
        tf_donaterID.setText("");
        tf_name.setText("");
        tf_nrc.setText("");
        tf_address.setText("");
        tf_phNo.setText("");
        tf_dob.setText("");
        tf_donateDate.setText("");
        tf_bloodType.setText("");
        tf_gender.setText("");
        tf_age.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            String query = "SELECT id,donaterid,name,nrc,age,dob,gender,address,phonenumber,bloodtype,donatedate FROM donater WHERE id = '" + vd.ID + "'";
            try {

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    tf_donaterID.setText(rs.getString("donaterid"));
                    tf_name.setText(rs.getString("name"));
                    tf_nrc.setText(rs.getString("nrc"));
                    tf_age.setText(rs.getInt("age")+"");
                    tf_dob.setText(String.valueOf(rs.getDate("dob")));
                    tf_gender.setText(rs.getString("gender"));
                    tf_address.setText(rs.getString("address"));
                    tf_phNo.setText(rs.getString("phonenumber"));
                    tf_bloodType.setText(rs.getString("bloodtype"));
                    tf_donateDate.setText(String.valueOf(rs.getDate("donatedate")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
