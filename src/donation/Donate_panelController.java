package donation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DbUtility;
import model.Retrieve;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Donate_panelController implements Initializable {
    public PreparedStatement ps;
    public Connection con = DbUtility.dbConnect();
    Statement stm;
    ResultSet rs;
    Retrieve retrieve = new Retrieve();
    public static String id;
    @FXML
    private ComboBox<String> cb_donater_id;
    ObservableList<String> donater_id = FXCollections.observableArrayList();

    @FXML
    private TextField tf_blood_type;

    @FXML
    private DatePicker donate_date;

    @FXML
    private TextField tf_blood_qty;

    @FXML
    void Donate(ActionEvent event) {
        if (isAllFillUp()){
            insertData();
            clear();
        }
    }

    @FXML
    void select(ActionEvent event) {
        if (cb_donater_id.getSelectionModel().getSelectedItem().toString() != null){
            id = cb_donater_id.getSelectionModel().getSelectedItem();
            try {
                String query = "SELECT `bloodtype` FROM `donater` WHERE donaterid ='"+id+"'";
                stm =(Statement)con.createStatement();
                rs = stm.executeQuery(query);
                while (rs.next()){
                    tf_blood_type.setText(rs.getString("bloodtype"));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            showDonation();
            System.out.println(id);
        }
    }

    private void showDonation(){
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ShowDonation.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void clear(){
        cb_donater_id.getEditor().clear();
        tf_blood_type.clear();
        tf_blood_qty.clear();
    }
    private void insertData() {
        try {
                String query = "INSERT INTO `blood_donate_records`(`donate_id`, `blood_type`, `no_of_bags`, `donate_date`) VALUES('"+cb_donater_id.getSelectionModel().getSelectedItem().toString()+"','"+tf_blood_type.getText()+"','"+Integer.parseInt(tf_blood_qty.getText().trim())+"','"+ Date.valueOf(donate_date.getValue().toString())+"')";
                ps = (PreparedStatement)con.prepareStatement(query);
                ps.executeUpdate();

                System.out.println(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            String query = "UPDATE blood SET quantity=quantity+1 WHERE blood_type='"+tf_blood_type.getText()+"'";
            ps = (PreparedStatement)con.prepareStatement(query);
            ps.executeUpdate();
            System.out.println(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        retrieve.retrieveforcombo(donater_id,cb_donater_id,"donater","donaterid");
    }

    private boolean isAllFillUp(){
        boolean fillUp = false;
        if (cb_donater_id.getSelectionModel().getSelectedItem().toString().isEmpty() || tf_blood_type.getText().isEmpty() || tf_blood_qty.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"field should't be empty");
            a.showAndWait();
        }else {
            fillUp = true;
        }
        return fillUp;
    }
}
