package model;

import java.util.ArrayList;

public class SP implements SP_IF{

	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private DAO_IF dao;

	private Activity_IF[] activities;

	private ArrayList<Shift_IF[]> providershifts;

	public SP() {
	}

	public SP(String name, String password, String email, String phone) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public SP(int id,String name, String password, String email, String phone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}



	public SP(int id, String name, String password, String email, String phone, DAO_IF dao) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.dao = dao;
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

	public Activity_IF[] getActivities() {
		return activities;
	}

	public ArrayList<Shift_IF[]> getProvidershifts() {
		return providershifts;
	}

	public void fillActivities() {
		activities = dao.readActivitiesBySPId(id);
	}

	public void fillShifts() {
		providershifts = new ArrayList<>();
		for(int i = 0; i < activities.length; i++) {
			providershifts.add(dao.readActivityShifts(activities[i].getId()));
		}
	}


}

