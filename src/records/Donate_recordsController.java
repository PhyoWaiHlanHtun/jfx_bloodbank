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
import model.donate_records;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Donate_recordsController implements Initializable{
    ObservableList<donate_records> alldata_d = FXCollections.observableArrayList();
    public java.sql.PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();
    @FXML
    private TableView<donate_records> tv_donate_records;

    @FXML
    private TableColumn<donate_records, Integer> cl_blood_id;

    @FXML
    private TableColumn<donate_records, String> cl_donater_id;

    @FXML
    private TableColumn<donate_records, String> cl_blood_type;

    @FXML
    private TableColumn<donate_records, Integer> cl_qty;

    @FXML
    private TableColumn<donate_records, Date> cl_donate_date;

    @FXML
    private TextField tf_search_donor;

    @FXML
    void back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
    }

    @FXML
    void search_d(ActionEvent event) {
        FilteredList<donate_records> filteredList = new FilteredList<donate_records>(alldata_d, d-> true);
        filteredList.setPredicate(donate_records -> {
            if (tf_search_donor.getText() == null || tf_search_donor.getText().isEmpty()){
                return true;
            }
            String lowercasefilter = tf_search_donor.getText().toLowerCase();
            if (donate_records.getDonater_id().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if(donate_records.getDonate_date().toString().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            return false;
        });
        SortedList<donate_records> sortedList = new SortedList<donate_records>(filteredList);
        sortedList.comparatorProperty().bind(tv_donate_records.comparatorProperty());
        tv_donate_records.setItems(sortedList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search_donor.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (tf_search_donor.getText().equals("")){
                alldata_d.clear();
                tv_donate_records.getSelectionModel().clearSelection();
                try {
                    getDonateRecords();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }));
        try {
            getDonateRecords();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private ObservableList getDonateRecords(){
        try {
            String query = "SELECT * FROM `blood_donate_records`";
            stmt = (Statement)con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                alldata_d.addAll(new donate_records(
                        rs.getInt("blood_id"),
                        rs.getString("donate_id"),
                        rs.getString("blood_type"),
                        rs.getInt("no_of_bags"),
                        rs.getDate("donate_date")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        tv_donate_records.setItems(alldata_d);
        cl_blood_id.setCellValueFactory(new PropertyValueFactory<donate_records,Integer>("blood_id"));
        cl_donater_id.setCellValueFactory(new PropertyValueFactory<donate_records,String>("donater_id"));
        cl_blood_type.setCellValueFactory(new PropertyValueFactory<donate_records,String>("blood_type"));
        cl_qty.setCellValueFactory(new PropertyValueFactory<donate_records,Integer>("qty"));
        cl_donate_date.setCellValueFactory(new PropertyValueFactory<donate_records,Date>("donate_date"));
        return alldata_d;
    }
}
