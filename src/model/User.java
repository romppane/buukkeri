package model;

import java.util.ArrayList;

public class User implements User_IF  {
	private int id;
	private String fname;
	private String lname;
	private String password;
	private String phone;
	private String email;
	private DAO_IF dao;

	private Booking_IF[] bookings;
	private ArrayList<Shift_IF> shifts;
	private ArrayList<Activity_IF> activities;


	public User() {

	}


	public User (String fname, String lname, String password, String phone, String email) {
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;

	}

	public User(int id, String fname, String lname, String password, String phone, String email) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	public User(int id, String fname, String lname, String password, String phone, String email, DAO_IF dao) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.dao = dao;
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
	public Booking_IF[] getBookings() {
		return bookings;
	}

	public ArrayList<Shift_IF> getShifts() {
		return shifts;
	}

	public ArrayList<Activity_IF> getActivities() {
		return activities;
	}


	public void fillBookingData() {
		// gets bookings array
		bookings = dao.readBookingsByUserId(id);
		shifts = new ArrayList<>();
		// gets shifts with information of bookings
		for (int i = 0; i<bookings.length; i++) {
			shifts.add(dao.readShiftById(bookings[i].getShiftid()));
		}
		// creates temporary array of activity_id:s of booked shifts
		ArrayList<Integer> uniques = new ArrayList<>();
		for (int i = 0; i<shifts.size(); i++) {
			if(!uniques.contains(shifts.get(i).getActivityid()))
				uniques.add(shifts.get(i).getActivityid());
		}
		// creates a list of activities user has reservations for.
		activities = new ArrayList<>();
		for (int i = 0; i<uniques.size(); i++) {
			activities.add(dao.readActivityById(id));
		}
		}

	}

