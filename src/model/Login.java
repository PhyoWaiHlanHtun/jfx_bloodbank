package model;

import java.util.Date;

public class Login {
    private int id;
    private  String name;
    private Date dob;
    private String phonenumber;
    private String password;
    private String address;
    private String gender;

    public Login(int id, String name, Date dob, String phonenumber, String password, String address, String gender) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }


    //Get AdminData
    public Login(int id, String name, Date dob, String phonenumber, String address, String gender) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phonenumber = phonenumber;
        this.address = address;
        this.gender = gender;

    }

    // Update New Data
    public Login() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
