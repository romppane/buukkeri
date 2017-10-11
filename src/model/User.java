package model;

import java.util.ArrayList;
/**
 *käyttäjä olio
 * @author Roni, Tommi, Marika, Ville
 *
 */
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

/**
 * tyhjä consturctori
 */
	public User() {

	}

/**
 * parametrillinen constructori
 * @param fname
 * @param lname
 * @param password
 * @param phone
 * @param email
 */
	public User (String fname, String lname, String password, String phone, String email) {
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;

	}
/**
 *parametrillinen constructori
 * @param id
 * @param fname
 * @param lname
 * @param password
 * @param phone
 * @param email
 */
	public User(int id, String fname, String lname, String password, String phone, String email) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}
/**
 * parametrillinen constructori
 * @param id
 * @param fname
 * @param lname
 * @param password
 * @param phone
 * @param email
 * @param dao
 */
	public User(int id, String fname, String lname, String password, String phone, String email, DAO_IF dao) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.dao = dao;
	}
/**käyttäjän id numeron getteri
 * @param id
 */
	public int getId() {
		return id;
	}
	/**käyttäjän id numeron setteri
	 * @return id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**ekan nimen getteri
	 * @return fname
	 */
	public String getFname() {
		return fname;
	}
	/**etunimen setteri
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**sukunimen getteri
	 * @return lname;
	 */
	public String getLname() {
		return lname;
	}
	/**sukunimen setteri
	 * @param lname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**salasanan getteri
	 *  @return password
	 */
	public String getPassword() {
		return password;
	}
	/**salasanan setteri
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**puhelin numeron getteri
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	/**puhelin numeron setteri
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**sähköpostin getteri
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**sähköpostin setteri
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * palauttaa varaus olio-listan
	 * @return bookings
	 */
	public Booking_IF[] getBookings() {
		return bookings;
	}
/**
 * palauttaa käyttäjän vuorot
 * @return shifts
 */
	public ArrayList<Shift_IF> getShifts() {
		return shifts;
	}
/**
 * palauttaa käyttäjän aktiviteetit
 * @return activities
 */
	public ArrayList<Activity_IF> getActivities() {
		return activities;
	}

/**
 * täyttää käyttäjän booking taulukon
 */
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

