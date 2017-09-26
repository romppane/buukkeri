package model;

import javax.persistence.*;

@Entity
@Table(name="Service_Provider")
public class SP implements SP_IF{

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Password")
	private String password;
	@Column(name="Email", unique = true)
	private String email;
	@Column(name="Phone", unique = true)
	private String phone;



	public SP() {
	}

	public SP(int i,String name, String password, String email, String phone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}


}

