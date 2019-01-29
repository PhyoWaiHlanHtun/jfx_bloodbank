package transfer;

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
import model.Retrieve;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

// FB : https://www.facebook.com/mr.phyowaihlanhtun
// Developer : PhyoWaiHlanHtun(Cryptonym)
// Contact : phyowaihlanhtun@gmail.com

public class Hospital_transferController implements Initializable {

    Retrieve retrieve = new Retrieve();
    public Connection con = DbUtility.dbConnect();
    private PreparedStatement ps;

    @FXML
    private ComboBox<String> cb_hospital_name;
    ObservableList<String> hospital_name = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cb_blood_type;
    ObservableList<String> blood_type = FXCollections.observableArrayList("O","A","B","AB");

    @FXML
    private DatePicker dp_transfer_date;

    @FXML
    private TextField tf_blood_qty;

    private static String blood;

    @FXML
    void submit(ActionEvent event) {
        if (cb_hospital_name.getSelectionModel().getSelectedItem().toString().isEmpty() || cb_blood_type.getSelectionModel().getSelectedItem().toString().isEmpty() || tf_blood_qty.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"fields shouldn't be empty1");
        }else {
            switch (cb_blood_type.getSelectionModel().getSelectedItem().toString()){
                case "O":
                    blood = "O";
                    break;
                case "A":
                    blood = "A";
                    break;
                case "B":
                    blood = "B";
                    break;
                case "AB":
                    blood = "AB";
                    break;
            }
            try{
                String query = "UPDATE blood SET quantity=quantity-1 WHERE blood_type='"+blood+"'";
                ps = (PreparedStatement)con.prepareStatement(query);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Database Stored");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try {
                String query = "INSERT INTO `hospital_transfer`(`hp_name`, `blood_type`, `tf_date`, `no_of_bags`) VALUES ('"+cb_hospital_name.getSelectionModel().getSelectedItem().toString()+"','"+cb_blood_type.getSelectionModel().getSelectedItem().toString()+"','"+ Date.valueOf(dp_transfer_date.getValue().toString())+"','"+Integer.parseInt(tf_blood_qty.getText().trim())+"')";
                ps = (PreparedStatement)con.prepareStatement(query);
                ps.executeUpdate();
                System.out.println(query);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb_blood_type.setItems(blood_type);
        retrieve.retrieveforcombo(hospital_name,cb_hospital_name,"hospital","hp_name");
    }
}
