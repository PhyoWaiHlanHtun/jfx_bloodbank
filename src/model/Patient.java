package model;

import java.sql.Date;

public class Patient {
    private int id;
    private String p_name;
    private String p_nrc;
    private String bloodtype;
    private Date trn_date;
    private int bags;

    public Patient(int id, String p_name, String bloodtype, Date trn_date, int bags) {
        this.id = id;
        this.p_name = p_name;
        this.bloodtype = bloodtype;
        this.trn_date = trn_date;
        this.bags = bags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_nrc() {
        return p_nrc;
    }

    public void setP_nrc(String p_nrc) {
        this.p_nrc = p_nrc;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Date getTrn_date() {
        return trn_date;
    }

    public void setTrn_date(Date trn_date) {
        this.trn_date = trn_date;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }
}
