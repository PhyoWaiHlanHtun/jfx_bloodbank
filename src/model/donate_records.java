package model;

import java.sql.Date;

public class donate_records {
    private int blood_id;
    private String donater_id;
    private String blood_type;
    private int qty;
    private Date donate_date;

    public donate_records(int blood_id, String donater_id, String blood_type, int qty, Date donate_date) {
        this.blood_id = blood_id;
        this.donater_id = donater_id;
        this.blood_type = blood_type;
        this.qty = qty;
        this.donate_date = donate_date;
    }

    public int getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(int blood_id) {
        this.blood_id = blood_id;
    }

    public String getDonater_id() {
        return donater_id;
    }

    public void setDonater_id(String donater_id) {
        this.donater_id = donater_id;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDonate_date() {
        return donate_date;
    }

    public void setDonate_date(Date donate_date) {
        this.donate_date = donate_date;
    }
}
