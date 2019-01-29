package model;

import java.sql.Date;

public class Patient_Transfer {
    private int id;
    private String p_name;
    private String bl;
    private Date tf_;
    private int bags;

    public Patient_Transfer(int id, String p_name, String bl, Date tf_, int bags) {
        this.id = id;
        this.p_name = p_name;
        this.bl = bl;
        this.tf_ = tf_;
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

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public Date getTf_() {
        return tf_;
    }

    public void setTf_(Date tf_) {
        this.tf_ = tf_;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }
}
