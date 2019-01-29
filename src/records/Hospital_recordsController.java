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
import java.sql.*;
import java.util.ResourceBundle;

public class Hospital_recordsController implements Initializable{
    ObservableList<Hospital_Transfer> alldata_hp = FXCollections.observableArrayList();
    public java.sql.PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();
    @FXML
    private TableView<Hospital_Transfer> tv_hp;

    @FXML
    private TableColumn<Hospital_Transfer, Integer> cl_hp_id;

    @FXML
    private TableColumn<Hospital_Transfer, String> cl_hp_name;

    @FXML
    private TableColumn<Hospital_Transfer, String> cl_hp_blood_type;

    @FXML
    private TableColumn<Hospital_Transfer, Date> cl_hp_trn_date;

    @FXML
    private TableColumn<Hospital_Transfer, Integer> cl_hp_bags;

    @FXML
    private TextField tf_hp_search;

    @FXML
    void back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
    }

    @FXML
    void search_hp(ActionEvent event) {
        FilteredList<Hospital_Transfer> filteredList = new FilteredList<Hospital_Transfer>(alldata_hp, hp -> true);
        filteredList.setPredicate(Hospital -> {
            if (tf_hp_search.getText() == null || tf_hp_search.getText().isEmpty()){
                return false;
            }
            String lowercasefilter = tf_hp_search.getText().toLowerCase();
            if (Hospital.getHp_name().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if(Hospital.getTf_date().toString().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            return false;
        });
        SortedList<Hospital_Transfer> sortedList = new SortedList<Hospital_Transfer>(filteredList);
        sortedList.comparatorProperty().bind(tv_hp.comparatorProperty());
        tv_hp.setItems(sortedList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_hp_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (tf_hp_search.getText().equals("")){
                alldata_hp.clear();
                tv_hp.getSelectionModel().clearSelection();
                try {
                    getHospital();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getHospital();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList getHospital() throws SQLException {
        try {
            String q = "SELECT * FROM hospital_transfer";
            stmt = (Statement)con.createStatement();
            rs = stmt.executeQuery(q);
            while (rs.next()){
                alldata_hp.add(new Hospital_Transfer(
                        rs.getInt("id"),
                        rs.getString("hp_name"),
                        rs.getString("blood_type"),
                        rs.getDate("tf_date"),
                        rs.getInt("no_of_bags")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        tv_hp.setItems(alldata_hp);
        cl_hp_id.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Integer>("id"));
        cl_hp_name.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,String>("hp_name"));
        cl_hp_blood_type.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,String>("blood_type"));
        cl_hp_trn_date.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Date>("tf_date"));
        cl_hp_bags.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Integer>("bags"));
        return  alldata_hp;


    }
}
