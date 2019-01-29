package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.List;

public class DonaterDAO {
        public static void deleteDonaterData(donater donater){
        String sqlQuery = "DELETE FROM donater WHERE id ='"+donater.getId()+"'";
            System.out.println(sqlQuery);
        DbUtility.DeleteData(sqlQuery);
    }

    // Updating Donater data from donater table
    public static void updateDonaterData(donater donater){
            String sqlQuery = "UPDATE donater SET name='"+donater.getName()+"',nrc='"+donater.getNrc()+"'," +
                    "address='"+donater.getAddress()+"',phonenumber='"+donater.getPhonenumber()+"',dob='"+donater.getDob()+"'," +
                    "donatedate='"+donater.getDonatedate()+"',bloodtype='"+donater.getBloodtype()+"',age='"+donater.getAge()+"' WHERE id='"+donater.getId()+"' ";
        DbUtility.UpdateData(sqlQuery);
        System.out.println(sqlQuery);
    }

    // Retrieve Login
    public static List<Login> viewLoginData() {
        String sqlQuery = "SELECT `name`,`password` FROM `admin`";
        ResultSet resultSet = DbUtility.RetrieveData(sqlQuery);
        System.out.println(sqlQuery);
        ObservableList<Login> list = FXCollections.observableArrayList();
        try{
            while(resultSet.next()){
                Login login = new Login();
                login.setName(resultSet.getString("name"));
                login.setPassword(resultSet.getString("password"));
                list.add(login);
            }
        }catch (Exception e){
            System.err.print(e);
            e.printStackTrace();
        }
        return list;
    }


}
