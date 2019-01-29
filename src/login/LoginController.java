package login;

import fxml_loader.fxmlL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.AlertBox;
import model.DbUtility;
import model.DonaterDAO;
import model.Login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Connection connection = DbUtility.dbConnect();
    Statement stm;
    ResultSet rs;

    @FXML
    private AnchorPane parent;

    @FXML
    private HBox hbox;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    void login_Action(ActionEvent event) {
        try {
            String q = "SELECT `name`, `password` FROM `admin`;";
            stm = (Statement)connection.createStatement();
            rs = stm.executeQuery(q);
            System.out.println(q);
            while (rs.next()){
                if (Username.getText().equals(rs.getString(1)) && Password.getText().equals(rs.getString(2))){
                    try {
                        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    clearTF();
                    AlertBox.Informationmessage("AlertBox","Success to login");
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    void clearTF(){
        Username.setText("");
        Password.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setevent(hbox);
    }
}
