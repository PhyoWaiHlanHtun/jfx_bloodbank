package transfer;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.DbUtility;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();



    @FXML
    private TextField h_Name;

    @FXML
    private ComboBox<String> h_region;

    @FXML
    private DatePicker h_reg_date;

    @FXML
    private TextField h_address;

    @FXML
    private TextField h_email;

    @FXML
    private TextField h_ph;

    @FXML
    private TextField p_Name;

    @FXML
    private ComboBox<String> p_Gender;

    @FXML
    private DatePicker p_Rg_Date;

    @FXML
    private TextField p_Nrc;

    @FXML
    private TextField p_Ph;

    @FXML
    private TextField p_Address;

    ObservableList<String> genders = FXCollections.observableArrayList("male","female");

    ObservableList<String> region = FXCollections.observableArrayList("Yangon","Mandalay","NayPyiTaw");


    @FXML
    void register_hospital(ActionEvent event) {
        insertHospital();
        clearHP();
    }

    @FXML
    void register_patient(ActionEvent event) {
        insertPatient();
        clearPatient();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        p_Gender.setItems(genders);
        h_region.setItems(region);
    }



  //    ||  ********************************************  Hospital  *************************************   ||

    private void insertHospital() {
        try {
            if(isAllFillupHP()) {
                String qry = "INSERT INTO hospital(`hp_name`, `hp_address`, `hp_email`, `hp_registerdate`, `hp_region`, `hp_phone`)" +
                        "VALUES ('"+h_Name.getText().trim()+"'," +
                        "'"+h_address.getText().trim()+"','"+h_email.getText().trim()+"','"+h_reg_date.getValue()+"'," +
                        "'"+h_region.getValue()+"','"+h_ph.getText().trim()+"')";
                ps=(PreparedStatement) con.prepareStatement(qry);
                System.out.println(qry);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Hospital Database Stored");
                System.out.println(qry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    private boolean isAllFillupHP(){
        boolean fillup=false;
        if (h_Name.getText().isEmpty() || h_address.getText().isEmpty() ||
                h_region.getSelectionModel().getSelectedItem().isEmpty()
                || h_ph.getText().isEmpty() || h_email.getText().isEmpty() || h_reg_date.getValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Hospital field shouldn't be empty!");
            a.showAndWait();
        }else {
            fillup = true;
        }

        return fillup;
    }

    private void clearHP() {
        h_Name.clear();
        h_address.clear();
        h_reg_date.getEditor().clear();
        h_email.clear();
        h_ph.clear();
        h_region.getEditor().clear();

    }


// ||  ******************************************End Hospital******************************  ||


//    ||    *******************************    Patient  *************************************       ||


    private void insertPatient() {
        try {
            if(isAllFillupPatient()) {
                String qry = "INSERT INTO patient(`p_name`, `p_nrc`, `p_phone`, `p_address`, `p_gender`, `p_registerdate`)" +
                        "VALUES ('"+p_Name.getText().trim()+"'," +
                        "'"+p_Nrc.getText().trim()+"','"+p_Ph.getText().trim()+"','"+p_Address.getText().trim()+"'," +
                        "'"+p_Gender.getValue()+"','"+p_Rg_Date.getValue()+"')";
                ps=(PreparedStatement) con.prepareStatement(qry);
                System.out.println(qry);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Patient Database Stored");
                System.out.println(qry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    private boolean isAllFillupPatient(){
        boolean fillup=false;
        if (p_Name.getText().isEmpty() || p_Address.getText().isEmpty() ||
                p_Gender.getSelectionModel().getSelectedItem().isEmpty()
                || p_Ph.getText().isEmpty() || p_Nrc.getText().isEmpty() || p_Rg_Date.getValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Patient field shouldn't be empty!");
            a.showAndWait();
        }else {
            fillup = true;
        }

        return fillup;
    }


    private void clearPatient() {
        p_Name.clear();
        p_Address.clear();
        p_Rg_Date.getEditor().clear();
        p_Nrc.clear();
        p_Ph.clear();
        p_Gender.getEditor().clear();

    }


//          ||  *******************************End Patient***********************************************    ||

}
