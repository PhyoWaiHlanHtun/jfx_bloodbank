package model;

import java.sql.Date;

public class Hospital_Transfer {
    private int id;
    private String hp_name;
    private String blood_type;
    private Date tf_date;
    private int bags;

    public Hospital_Transfer(int id, String hp_name, String blood_type, Date tf_date, int bags) {
        this.id = id;
        this.hp_name = hp_name;
        this.blood_type = blood_type;
        this.tf_date = tf_date;
        this.bags = bags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHp_name() {
        return hp_name;
    }

    public void setHp_name(String hp_name) {
        this.hp_name = hp_name;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public Date getTf_date() {
        return tf_date;
    }

    public void setTf_date(Date tf_date) {
        this.tf_date = tf_date;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }
}
