package model;

import java.sql.Date;

public class Hospital {

    private int id;
    private String hp_name;
    private String hp_adr;
    private String blood_type;
    private Date trn_date;
    private int bags;

    public Hospital(int id, String hp_name, String blood_type, Date trn_date, int bags){
        this.id = id;
        this.hp_name = hp_name;
        this.blood_type = blood_type;
        this.trn_date = trn_date;
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

    public String getHp_adr() {
        return hp_adr;
    }

    public void setHp_adr(String hp_adr) {
        this.hp_adr = hp_adr;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
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
