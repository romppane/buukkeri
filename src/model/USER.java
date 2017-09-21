package model;

public class USER implements USER_IF  {
	private int id;
	private String fname;
	private String lname;
	private String password;
	private String phone;
	private String email;



	public USER (int i, String fname, String lname, String password, String phone, String email) {





		super();
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
