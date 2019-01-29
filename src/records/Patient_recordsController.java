package records;

import fxml_loader.fxmlL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DbUtility;
import model.Hospital_Transfer;
import model.Patient_Transfer;
import model.donate_records;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Patient_recordsController implements Initializable{
    ObservableList<Patient_Transfer> alldata_p = FXCollections.observableArrayList();
    public java.sql.PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();
    @FXML
    private TableView<Patient_Transfer> p_tv;

    @FXML
    private TableColumn<Patient_Transfer, Integer> cl_p_id;

    @FXML
    private TableColumn<Patient_Transfer, String> cl_p_name;

    @FXML
    private TableColumn<Patient_Transfer, String> cl_p_blood_type;

    @FXML
    private TableColumn<Patient_Transfer, Date> cl_p_trn_date;

    @FXML
    private TableColumn<Patient_Transfer, Integer> cl_p_no_of_bags;

    @FXML
    private TextField tf_p_search;

    @FXML
    void back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
    }

    @FXML
    void search_p(ActionEvent event) {
        System.out.println("a");
        FilteredList<Patient_Transfer> filteredList = new FilteredList<Patient_Transfer>(alldata_p, p -> true);
        filteredList.setPredicate((Patient_Transfer) -> {
            if (tf_p_search.getText() == null || tf_p_search.getText().isEmpty()){
                return true;
            }
            String lowercasefilter = tf_p_search.getText().toLowerCase();
            if (Patient_Transfer.getP_name().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if(Patient_Transfer.getTf_().toString().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            return false;
        });
        SortedList<Patient_Transfer> sortedList = new SortedList<Patient_Transfer>(filteredList);
        sortedList.comparatorProperty().bind(p_tv.comparatorProperty());
        p_tv.setItems(sortedList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_p_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (tf_p_search.getText().equals("")){
                alldata_p.clear();
                p_tv.getSelectionModel().clearSelection();
                try {
                    getPatient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getPatient();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList getPatient(){
        try {
            String q = "SELECT * FROM `patient_transfer`";
            stmt = (Statement)con.createStatement();
            rs = stmt.executeQuery(q);
            while (rs.next()){
                alldata_p.addAll(new Patient_Transfer(
                        rs.getInt("id"),
                        rs.getString("p_name"),
                        rs.getString("bl"),
                        rs.getDate("tf_"),
                        rs.getInt("no_of_bags")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        p_tv.setItems(alldata_p);
        cl_p_id.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Integer>("id"));
        cl_p_name.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,String>("p_name"));
        cl_p_blood_type.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,String>("bl"));
        cl_p_trn_date.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Date>("tf_"));
        cl_p_no_of_bags.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Integer>("bags"));
        return  alldata_p;

    }
}
