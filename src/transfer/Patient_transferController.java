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

public class Patient_transferController implements Initializable {

    Retrieve retrieve = new Retrieve();
    public Connection con = DbUtility.dbConnect();
    private PreparedStatement ps;

    @FXML
    private ComboBox<String> cb_patient_name;
    ObservableList<String> patient_name = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cb_blood_type;
    ObservableList<String> blood_type = FXCollections.observableArrayList("O","A","B","AB");

    @FXML
    private TextField tf_blood_qty;

    @FXML
    private DatePicker dp_transfer_date;

    private static String blood;

    @FXML
    void submit(ActionEvent event) {
        if (cb_patient_name.getSelectionModel().getSelectedItem().toString().isEmpty() || cb_blood_type.getSelectionModel().getSelectedItem().toString().isEmpty() || tf_blood_qty.getText().isEmpty()){
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
                System.out.println(query);
                JOptionPane.showMessageDialog(null, "Database Stored");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try{
                String query = "INSERT INTO `patient_transfer`(`p_name`, `bl`, `tf_`, `no_of_bags`) VALUES ('"+cb_patient_name.getSelectionModel().getSelectedItem().toString()+"','"+cb_blood_type.getSelectionModel().getSelectedItem().toString()+"','"+ Date.valueOf(dp_transfer_date.getValue().toString())+"','"+Integer.parseInt(tf_blood_qty.getText().trim())+"')";
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
        retrieve.retrieveforcombo(patient_name,cb_patient_name,"patient","p_name");
    }
}
