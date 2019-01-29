package model;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Retrieve {
    static Connection con = DbUtility.dbConnect();
    Statement stmt;
    public static int totals;
    public static int nomale;
    public static int nofemale;
    public static int O;
    public static int A;
    public static int B;
    public static int AB;


    public void retrieveforcombo(ObservableList<String> list, ComboBox<String> combo, String table, String column) {

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " ");
            while (rs.next()) {
                list.add(rs.getString(column));
            }
            combo.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gettotal(Label totalLBL, String tablename) {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id)FROM " + tablename + " ");
            while (rs.next()) {
                totals = rs.getInt(1);
                totalLBL.setText(String.valueOf(totals));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getfemale(Label femaleLBL, String tablename) {
        String genfe = "female";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(gender) FROM " + tablename + " WHERE gender='" + genfe + "' ");
            while (rs.next()) {
                nofemale = rs.getInt(1);
                femaleLBL.setText(String.valueOf(nofemale));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getmale(Label maleLBL, String tablename) {
        String genmale = "male";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(gender) FROM " + tablename + " WHERE gender='" + genmale + "' ");
            while (rs.next()) {
                nomale = rs.getInt(1);
                maleLBL.setText(String.valueOf(nomale));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getO(Label lb_O, String tablename){
        String getO = "O";

        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT quantity FROM "+ tablename + " WHERE blood_type='" +getO+ "' ");
            while (rs.next()){
                O = rs.getInt("quantity");
                lb_O.setText(String.valueOf(O));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getA(Label lb_A, String tablename){
        String getA = "A";
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT quantity FROM "+ tablename + " WHERE blood_type='" +getA+ "' ");
            while (rs.next()){
                A = rs.getInt("quantity");
                lb_A.setText(String.valueOf(A));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAB(Label lb_AB, String tablename){
        String getAB = "AB";
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT quantity FROM "+ tablename + " WHERE blood_type='" +getAB+ "' ");
            while (rs.next()){
                AB = rs.getInt("quantity");
                lb_AB.setText(String.valueOf(AB));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getB(Label lb_B, String tablename){
        String getB = "B";
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT quantity FROM "+ tablename + " WHERE blood_type='" +getB+ "' ");
            while (rs.next()){
                B = rs.getInt("quantity");
                lb_B.setText(String.valueOf(B));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}