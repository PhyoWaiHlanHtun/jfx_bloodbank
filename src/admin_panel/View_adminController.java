package admin_panel;

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
import model.DbUtility;
import model.Login;
import model.LoginDAO;

import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class View_adminController implements Initializable {

    public static int id;
    @FXML
    private TextField searchTF;

    @FXML
    private TableView<Login> adminTV;

    @FXML
    private TableColumn<Login, Integer> idcolumn;

    @FXML
    private TableColumn<Login, String> adminnamecolumn;

    @FXML
    private TableColumn<Login, String> gendercolumn;

    @FXML
    private TableColumn<Login, String> phonecolumn;

    @FXML
    private TableColumn<Login, Date> datecolumn;

    @FXML
    private TableColumn<Login, String> addresscolumn;

    ObservableList<Login> alldata = FXCollections.observableArrayList();
    public Statement stmt;
    public ResultSet rs;
    public Connection con = DbUtility.dbConnect();

    @FXML
    void custom_action(ActionEvent event) {
        new Utilities().sceneChangeShowAndWait("Admin Edit Page","/admin_panel/Customize.fxml");
    }

    @FXML
    void delete_action(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Admin List");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Login selectdonater = adminTV.getSelectionModel().getSelectedItem();
            LoginDAO.deleteLoginData(selectdonater);
            alldata.remove(selectdonater);
        }
        adminTV.getSelectionModel().clearSelection();
    }

    @FXML
    void detail_action(ActionEvent event) {
        new Utilities().sceneChangeShowAndWait("Admin Details","/admin_panel/Details.fxml");
    }

    @FXML
    void refresh_action(ActionEvent event) throws SQLException {
        for (int i = 0; i < adminTV.getItems().size(); i++) {
            adminTV.getItems().clear();
            getAdminData();
        }
    }

    @FXML
    void search_action(ActionEvent event) {
        FilteredList<Login> filtereddata = new FilteredList<Login>(alldata, p -> true);
        filtereddata.setPredicate(loginadd -> {
            if (searchTF.getText() == null || searchTF.getText().isEmpty()){
                return true;
            }
            String lowercasefilter = searchTF.getText().toLowerCase();

            if (loginadd.getName().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if (loginadd.getAddress().toLowerCase().contains(lowercasefilter)){
                return true;
            }else if (loginadd.getPhonenumber().toLowerCase().contains(lowercasefilter)){
                return true;
            }
            return false;

        });
        // Wrap the filtered list in a sorted list//
        SortedList<Login> sorteddata = new SortedList<Login>(filtereddata);
        // Bind the SortedList comparator to the TableView comparator.
        sorteddata.comparatorProperty().bind(adminTV.comparatorProperty());
        // Add sorted (and filtered) data to the table
        adminTV.setItems(sorteddata);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminTV.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            id = adminTV.getSelectionModel().getSelectedItem().getId();
        });

        //if you modify search method you would need to modify this method too
        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchTF.getText().equals("")){
                alldata.clear();
                adminTV.getSelectionModel().clearSelection();
                try {
                    getAdminData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            getAdminData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public ObservableList getAdminData() throws SQLException {
        try {
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("SELECT id,name,dob,phonenumber,address,gender FROM admin");
            while (rs.next()){
                alldata.addAll(new Login(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getString("phonenumber"),
                        rs.getString("address"),
                        rs.getString("gender")
                ));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        adminTV.setItems(alldata);
        idcolumn.setCellValueFactory(new PropertyValueFactory<Login,Integer>("id"));
        adminnamecolumn.setCellValueFactory(new PropertyValueFactory<Login, String>("name"));
        gendercolumn.setCellValueFactory(new PropertyValueFactory<Login, String>("nrc"));
        phonecolumn.setCellValueFactory(new PropertyValueFactory<Login,String>("phonenumber"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<Login, Date>("dob"));
        addresscolumn.setCellValueFactory(new PropertyValueFactory<Login, String>("address"));
        gendercolumn.setCellValueFactory(new PropertyValueFactory<Login, String>("gender"));

        return alldata;
    }

}
