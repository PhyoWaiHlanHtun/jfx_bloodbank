package donation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import fxml_loader.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DbUtility;
import model.DonaterDAO;
import model.donater;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class View_donater_panelController implements Initializable{

    @FXML
    private TableView<donater> doanter_tv;

    @FXML
    private TableColumn<donater, Integer> Id;

    @FXML
    private TableColumn<donater, String> DonateID;

    @FXML
    private TableColumn<donater, String> donater_name_cl;

    @FXML
    private TableColumn<donater, String> nrc_cl;

    @FXML
    private TableColumn<donater, Date> dob_cl;

    @FXML
    private TableColumn<donater, String> gender_cl;

    @FXML
    private TableColumn<donater, String> address_cl;

    @FXML
    private TableColumn<donater, String> phNo_cl;

    @FXML
    private TableColumn<donater, String> bloodTpye_cl;

    @FXML
    private TableColumn<donater, Date> donateDate_cl;

    @FXML
    private TextField searchTF;

    @FXML
    private AnchorPane anchor;
    static int ID;

    ObservableList<donater> alldata = FXCollections.observableArrayList();
    public java.sql.PreparedStatement ps;
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();

    @FXML
    void custom_action(ActionEvent event) {
        new Utilities().sceneChangeShowAndWait("Blood Donation Edit Page","/donation/Customize.fxml");
    }

    @FXML
    void delete_action(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Donater List");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            donater selectdonater = doanter_tv.getSelectionModel().getSelectedItem();
            DonaterDAO.deleteDonaterData(selectdonater);
            alldata.remove(selectdonater);
        }
        doanter_tv.getSelectionModel().clearSelection();
    }


    @FXML
    void detail_action(ActionEvent event) {
        new Utilities().sceneChangeShowAndWait("Blood Donation Details","/donation/Details.fxml");
    }

    @FXML
    void refresh_action(ActionEvent event) throws SQLException {
        for (int i = 0; i < doanter_tv.getItems().size(); i++) {
            doanter_tv.getItems().clear();
            getDonaterData();
        }
    }


    @FXML
    void search_action(ActionEvent event) {
        FilteredList<donater> filtereddata = new FilteredList<donater>(alldata, p -> true);
        filtereddata.setPredicate(donateradd -> {
            if (searchTF.getText() == null || searchTF.getText().isEmpty()){
                return true;
            }
            String lowercasefilter = searchTF.getText().toLowerCase();

            if (donateradd.getName().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if (donateradd.getNrc().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            else if (donateradd.getDonatedate().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            else if (donateradd.getPhonenumber().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            else if (donateradd.getDonaterid().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            return false;

        });
        // Wrap the filtered list in a sorted list//
        SortedList<donater> sorteddata = new SortedList<donater>(filtereddata);
        // Bind the SortedList comparator to the TableView comparator.
        sorteddata.comparatorProperty().bind(doanter_tv.comparatorProperty());
        // Add sorted (and filtered) data to the table
        doanter_tv.setItems(sorteddata);

    }


    public ObservableList getDonaterData() throws SQLException {
            try {
                stmt = (Statement) con.createStatement();
                rs = stmt.executeQuery("SELECT id,donaterid,name,nrc,dob,gender,address,phonenumber,bloodtype,donatedate FROM donater");
                while (rs.next()){
                    alldata.addAll(new donater(
                            rs.getInt("id"),
                            rs.getString("donaterid"),
                            rs.getString("name"),
                            rs.getString("nrc"),
                            rs.getDate("dob"),
                            rs.getString("gender"),
                            rs.getString("address"),
                            rs.getString("phonenumber"),
                            rs.getString("bloodtype"),
                            rs.getDate("donatedate")
                    ));

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        doanter_tv.setItems(alldata);
            Id.setCellValueFactory(new PropertyValueFactory<donater,Integer>("id"));
            DonateID.setCellValueFactory(new PropertyValueFactory<donater,String>("donaterid"));
        donater_name_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("name"));
        nrc_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("nrc"));
        dob_cl.setCellValueFactory(new PropertyValueFactory<donater, Date>("dob"));
        gender_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("gender"));
        address_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("address"));
        phNo_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("phonenumber"));
        bloodTpye_cl.setCellValueFactory(new PropertyValueFactory<donater, String>("bloodtype"));
        donateDate_cl.setCellValueFactory(new PropertyValueFactory<donater, Date>("donatedate"));

        return alldata;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        doanter_tv.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            ID = doanter_tv.getSelectionModel().getSelectedItem().getId();
        });
        //if you modify search method you would need to modify this method too
        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchTF.getText().equals("")){
                alldata.clear();
                doanter_tv.getSelectionModel().clearSelection();
                try {
                    getDonaterData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            getDonaterData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

