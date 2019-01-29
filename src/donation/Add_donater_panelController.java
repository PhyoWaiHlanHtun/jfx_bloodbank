package donation;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

public class Add_donater_panelController implements Initializable{
	
	public PreparedStatement ps;
	public Statement stmt;
	public ResultSet rs;
	public Connection con = DbUtility.dbConnect();
	String generteid;
	

    @FXML
    private TextField donaterName_tf;

    @FXML
    private DatePicker DOB_dp;

    @FXML
    private ComboBox<String> gender_cbo;

    @FXML
    private TextField fatherName_tf;

    @FXML
    private TextField nrc_tf;

    @FXML
    private TextField age_tf;

    @FXML
    private TextField address_tf;

    @FXML
    private DatePicker donate_start_date_dp;

    @FXML
    private TextField phNO_tf;

    @FXML
    private ComboBox<String> bloodType_cbo;

    @FXML
    private ComboBox<String> region_state_cbo;

    @FXML
    private TextField JobPosition_tf;

    ObservableList<String> gender = FXCollections.observableArrayList("male","female");
    ObservableList<String> bloodType = FXCollections.observableArrayList("A","B","O","AB");
    ObservableList<String> region = FXCollections.observableArrayList("Yangon","Mandalay","Chin");


    @FXML
    void register(ActionEvent event) {
    	insertDonater();
    	clear();
    }
       
    public void genroll() {
    	try {
			String qry="select donaterid from donater order by donaterid DESC  LIMIT 1";
			ps=(PreparedStatement) con.prepareStatement(qry);
			rs=ps.executeQuery();
			if(rs.next()) {
				String rl =rs.getString("donaterid");
				int ln =rl.length();
                String stxt =rl.substring(0, 2);
                String snum=rl.substring(2,ln);
				int n=Integer.parseInt(snum);
				n++;
				snum =Integer.toString(n);
				generteid=stxt+snum;
			}else {
                generteid="BD11111";
			}
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}
    }

    private void insertDonater() {
    	try {
    		genroll();
			if(isAllFillup()) {
				String qry = "INSERT INTO donater(donaterid,name,fathername,nrc,age,dob,gender,address,phonenumber,bloodtype,region,donatedate,jobposition)"
                        + "VALUES('"+generteid+"','"+donaterName_tf.getText().trim()+"','"+fatherName_tf.getText().trim()+"','"+nrc_tf.getText().trim()+"',"
								+ "'"+Integer.parseInt(age_tf.getText().trim())+"','"+DOB_dp.getValue()+"','"+gender_cbo.getValue()+"',"
										+ "'"+address_tf.getText().trim()+"','"+phNO_tf.getText().trim()+"',"
												+ "'"+bloodType_cbo.getValue()+"','"+region_state_cbo.getValue()+"'," +
                        "'"+donate_start_date_dp.getValue()+"','"+JobPosition_tf.getText().trim()+"')";
				ps=(PreparedStatement) con.prepareStatement(qry);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Database Stored");
				System.out.println(qry);
			}
		} catch (Exception e) {
    	    e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }

    private void clear() {
        donaterName_tf.clear();
        fatherName_tf.clear();
        DOB_dp.getEditor().clear();
        nrc_tf.clear();
        age_tf.clear();
        address_tf.clear();
        donate_start_date_dp.getEditor().clear();
        phNO_tf.setText("");
        bloodType_cbo.getEditor().clear();
        JobPosition_tf.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender_cbo.setItems(gender);
        bloodType_cbo.setItems(bloodType);
        region_state_cbo.setItems(region);
        generteid="'";
    }

    private boolean isAllFillup(){
        boolean fillup=false;
        if (donaterName_tf.getText().isEmpty() || fatherName_tf.getText().isEmpty() || nrc_tf.getText().isEmpty() || age_tf.getText().isEmpty() || DOB_dp.getValue().toString().isEmpty() || gender_cbo.getSelectionModel().getSelectedItem().isEmpty() || address_tf.getText().isEmpty() || phNO_tf.getText().isEmpty() || bloodType_cbo.getSelectionModel().getSelectedItem().isEmpty() || region_state_cbo.getSelectionModel().getSelectedItem().isEmpty() || donate_start_date_dp.getValue().toString().isEmpty() || JobPosition_tf.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"field shouldn't be empty!");
            a.showAndWait();
        }else {
            fillup = true;
        }

        return fillup;
    }
}
