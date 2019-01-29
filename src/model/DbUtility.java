package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DbUtility {
	private static  Connection connection;
    private static Statement statement;
    private static final String url = "jdbc:mysql://localhost/fx_bloodbank";
    private  static final String user = "root";
    private static final String pass = "";



    //get connection to the database table
    public static Connection dbConnect(){
        if(connection==null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = (Connection) DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
                System.err.print("Dirver Class not found exception");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.print("Database' table not found exception");
                e.printStackTrace();
            }
        }
        return connection;
    }

    //close the connection
    public static void  dbClose() throws SQLException {

        if(connection != null || !connection.isClosed()){

            connection.close();
        }
    }

    //insert data to the database
    public static void InsertData(String sqlQuery){
        dbConnect();
        try{
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            System.err.print("Cannot insert user data");
            e.printStackTrace();
        }
    }


    //Using prepared statement to insert product data
    public static PreparedStatement executeTogetPrepareStatement(String sql){

        dbConnect();
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.err.println("Error at executeTogetPrepareStatement");
            e.printStackTrace();
        }
        return ps;
    }


    //updating data to the database
    public static void UpdateData(String sqlQuery){
        dbConnect();
        try{
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(sqlQuery);

        }catch (SQLException e){
            System.err.print("Cannot update data.");
            e.printStackTrace();
        }
    }


    //deleting data to the te database
    public static void DeleteData(String sqlQuery){
        dbConnect();
        try{
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            System.err.print("Cannot delete data.");
            System.out.println(sqlQuery);
            e.printStackTrace();
        }
    }


    //retrieving data from the database
    public static ResultSet RetrieveData(String sqlQuery){
        ResultSet rs = null;
        dbConnect();
        try{
            statement = (Statement) connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
        }catch (SQLException e){
            System.err.println("Cannot retrieve data.");
            e.printStackTrace();
        }
        return rs;
    }
}
