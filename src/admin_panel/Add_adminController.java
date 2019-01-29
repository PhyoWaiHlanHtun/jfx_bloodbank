package admin_panel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.DbUtility;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Add_adminController implements Initializable {

    public PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();

    @FXML
    private ImageView profilepicture;

    @FXML
    private TextField AdminnameTF;

    @FXML
    private TextField addressTF;

    @FXML
    private DatePicker dateofbirthDTP;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField phoneTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Button registerJFX;

    ObservableList<String> genders = FXCollections.observableArrayList("male","female");

    @FXML
    void register(ActionEvent event) {
        insertAdmin();
        clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.setItems(genders);
    }

    private void insertAdmin() {
        try {
            if(isAllFillup()) {
                String qry = "INSERT INTO admin(name,dob,phonenumber,password,address,gender)" + "VALUES ('"+AdminnameTF.getText().trim()+"'," +
                        "'"+dateofbirthDTP.getValue()+"','"+phoneTF.getText().trim()+"','"+passwordPF.getText().trim()+"'," +
                        "'"+addressTF.getText().trim()+"','"+gender.getValue()+"')";
                ps=(PreparedStatement) con.prepareStatement(qry);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Admin Database Stored");
                System.out.println(qry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void clear() {
        AdminnameTF.clear();
        addressTF.clear();
        dateofbirthDTP.getEditor().clear();
        passwordPF.clear();
        phoneTF.clear();
        gender.getEditor().clear();

    }

    private boolean isAllFillup(){
        boolean fillup=false;
        if (AdminnameTF.getText().isEmpty() || addressTF.getText().isEmpty() || gender.getSelectionModel().getSelectedItem().isEmpty()
                || phoneTF.getText().isEmpty() || passwordPF.getText().isEmpty() || dateofbirthDTP.getValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"field shouldn't be empty!");
            a.showAndWait();
        }else {
            fillup = true;
        }

        return fillup;
    }

}
