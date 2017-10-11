package model;

import java.util.ArrayList;
/**
 * palvelutuottaja olio
 * @author Roni, Tommi, Marika, Ville
 *
 */
public class SP implements SP_IF{
	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private DAO_IF dao;

	private Activity_IF[] activities;

<<<<<<< HEAD
=======
	private ArrayList<Shift_IF[]> providershifts;
/**
 * tyhjä constructori
 */
>>>>>>> refs/remotes/origin/develop
	public SP() {
	}
/**
 * parametrillinen constructori
 * @param name nimi
 * @param password salasana
 * @param email sähköposti
 * @param phone puhelin numero
 */
	public SP(String name, String password, String email, String phone) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
/**
 * parametrillinen constructori
 * @param id id numero
 * @param name nimi
 * @param password salasana
 * @param email sähköposti
 * @param phone puhelin numero
 */
	public SP(int id,String name, String password, String email, String phone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}


/**
 * parametrillinen constructori
 * @param id id numero
 * @param name nimi
 * @param password salasana
 * @param email sähköposti
 * @param phone puhelin numero
 * @param dao data acceess object
 */
	public SP(int id, String name, String password, String email, String phone, DAO_IF dao) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.dao = dao;
	}
/**nimen getteri
 * @return name
 */
	public String getName() {
		return name;
	}
	/**nimen setteri
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**salasanan getteri
	 * @return password
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
	/** puhelin numeron getteri
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
/**id numeron getteri
 * @return id
 */
	public int getId() {
		return id;
	}
/**
 * palauttaa palvelun tuottajan aktiviteetti listan
 * @return activities
 */
	public Activity_IF[] getActivities() {
		return activities;
	}
/**
 * palauttaa palvelun tuottajan vuorot
 * @return providershifts
 */
	public ArrayList<Shift_IF[]> getProvidershifts() {
		return providershifts;
	}

/**asettaa uuden data access object olion
 * @param dao
 */
	public void setDao(DAO_IF dao) {
		this.dao = dao;
	}
/**
 * täyttää palveluntuottajan aktiviteetti taulun
 */
	public void fillActivities() {
		activities = dao.readActivitiesBySPId(id);
	}
/**
 * täytää palveluntuottajan vuoro listan
 */
	public void fillShifts() {
		providershifts = new ArrayList<>();
		for(int i = 0; i < activities.length; i++) {
			providershifts.add(dao.readActivityShifts(activities[i].getId()));
		}
	}
/**luo palvelun tuottajan aktiviteettille vuoroja
 * @param starth
 * @param startmin
 * @param endh
 * @param endmin
 * @param lenght
 * @param price
 * @param act
 */
	public void createSetOfShifts(int starth, int startmin, int endh, int endmin, int length, double price, Activity_IF act) {
		boolean done = true;
		System.out.println("Looppi alkaa");
		while(done) {
			System.out.println("4Head");
			int tempmin = startmin;
			int temph = starth;
			startmin = startmin + length;
			if(startmin >= 60){
				startmin = startmin - 60;
				starth = starth + 1;
			}
			Shift_IF shift = new Shift(temph+":"+tempmin+"-"+starth+":"+startmin, price, act.getId());
			dao.createShift(shift);
			if(starth == endh && startmin == endmin)
				done = false;

		}
	}

}

