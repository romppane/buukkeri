package model;

import javax.persistence.*;

@Entity
@Table(name="Account")

public class USER implements USER_IF  {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;

	@Column(name="Firstname")
	private String fname;

	@Column(name="Lastname")
	private String lname;

	@Column(name="Password")
	private String password;

	@Column(name="Phone", unique = true)
	private String phone;

	@Column(name="Email", unique = true)
	private String email;


	public USER() {

	}

	public USER (int i, String fname, String lname, String password, String phone, String email) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
