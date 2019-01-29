package model;

import java.sql.Date;

public class donater {
	 int id;
	String donaterid;
	String name;
	String fathername;
	String nrc;
	int age;
	Date dob;
	String gender;
	String address;
	String phonenumber;
	String bloodtype;
	String region;
	Date donatedate;
	String jobposition;

//Default Constructor
	public donater() {
		this.id = id;
		this.donaterid = donaterid;
		this.name = name;
		this.fathername = fathername;
		this.nrc = nrc;
		this.age = age;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.phonenumber = phonenumber;
		this.bloodtype = bloodtype;
		this.region = region;
		this.donatedate = donatedate;
		this.jobposition = jobposition;
	}

	// View Donater
	public donater(int id, String donaterid, String name, String nrc, Date dob, String gender, String address, String phonenumber, String bloodtype, Date donatedate) {
		this.id=id;
		this.donaterid=donaterid;
		this.name = name;
		this.nrc = nrc;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.phonenumber = phonenumber;
		this.bloodtype = bloodtype;
		this.donatedate = donatedate;
	}

    public donater(int id,String name,String nrc,String address,String phonenumber,Date dob,Date donatedate,String bloodtype,String gender,int age) {
		this.id = id;
		this.name = name;
		this.nrc = nrc;
		this.address = address;
		this.phonenumber = phonenumber;
		this.dob = dob;
		this.donatedate = donatedate;
		this.bloodtype = bloodtype;
		this.gender = gender;
		this.age = age;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDonaterid() {
		return donaterid;
	}

	public void setDonaterid(String donaterid) {
		this.donaterid = donaterid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = this.age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDonatedate() {
		return String.valueOf(donatedate);
	}

	public void setDonatedate(String donatedate) {
		this.donatedate = Date.valueOf(donatedate);
	}

	public String getJobposition() {
		return jobposition;
	}

	public void setJobposition(String jobposition) {
		this.jobposition = jobposition;
	}
}
