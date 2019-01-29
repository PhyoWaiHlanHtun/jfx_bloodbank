package admin_panel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DbUtility;
import model.Login;
import model.LoginDAO;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustomizeController implements Initializable {
    @FXML
    private TextField admin_id;

    @FXML
    private TextField tfdob;

    @FXML
    private TextField tfaddress;

    @FXML
    private TextField tfph_no;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfgender;

    Statement stm;
    ResultSet rs;
    Connection con = DbUtility.dbConnect();

    View_adminController va;
    @FXML
    void Update(ActionEvent event) {
        newData();
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Admin Update SucessFully" );
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String q = "SELECT id,name,dob,phonenumber,address,gender FROM admin WHERE id = '"+va.id+"'";
        try {
            stm = (Statement)con.createStatement();
            rs = stm.executeQuery(q);
            while (rs.next()){
                admin_id.setText(rs.getInt("id")+"");
                tfname.setText(rs.getString("name"));
                tfdob.setText(rs.getString("dob"));
                tfph_no.setText(rs.getString("phonenumber"));
                tfaddress.setText(rs.getString("address"));
                tfgender.setText(rs.getString("gender"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void newData(){
        int id = Integer.parseInt(admin_id.getText().trim());
        String name = tfname.getText().trim();
        String address = tfaddress.getText().trim();
        String phone = tfph_no.getText().trim();
        String dob = tfdob.getText().trim();
        String gender = tfgender.getText().trim();

        if (!(name.isEmpty()) || !(address.isEmpty()) || !(phone.isEmpty()) ||
                !(dob.isEmpty()) || !(gender.isEmpty())){
            Login login = new Login();
            login.setId(id);
            login.setName(name);
            login.setAddress(address);
            login.setPhonenumber(phone);
            login.setDob(java.sql.Date.valueOf(dob));
            login.setGender(gender);
            LoginDAO.updateAdminData(login);

        }
    }

    private void clearFields(){
        admin_id.setText("");
        tfname.setText("");
        tfaddress.setText("");
        tfph_no.setText("");
        tfdob.setText("");
        tfgender.setText("");
    }


}
