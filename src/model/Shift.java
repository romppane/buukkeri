package model;
/**
 *vuoro olio
 * @author Roni, Tommi, Marika, Ville
 *
 */
public class Shift implements Shift_IF {
	private int id;
	private String Shift_time;
	private double price;
	private int activityid;

	/**
	 * tyhjä constructori
	 */
	public Shift() {

	}
	/**
	 * parametrillinen constructori
	 * @param shift_time vuoron aika
	 * @param price vuoron hinta
	 * @param activityid aktiviteetin id numero
	 */
	public Shift(String shift_time, double price, int activityid) {
		this.Shift_time = shift_time;
		this.price = price;
		this.activityid = activityid;
	}
	/**
	 * parametrillinen constuctori
	 * @param id id numero
	 * @param shift_time vuoron aika
	 * @param price vuoron hinta
	 * @param activityid aktiviteetin id
	 */
	public Shift(int id, String shift_time, double price, int activityid) {
		this.id = id;
		this.Shift_time = shift_time;
		this.price = price;
		this.activityid = activityid;
	}
	/**
	 * @return palauttaa id numeron
	 */
	public int getId() {
		return id;
	}
/**
 * @param id asettaa vuorolle id numeron
 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Shift_time palauttaa vuoron ajan
	 */
	public String getShift_time() {
		return Shift_time;
	}
/**
 * @param shift_time asettaa vuorolle uuden ajan
 */
	public void setShift_time(String shift_time) {
		Shift_time = shift_time;
	}
/**
 * @return price palauttaa vuoron hinnan
 */
	public double getPrice() {
		return price;
	}
/**
 * @param price asettaa vuorolle uuden hinnan
 */
	public void setPrice(double price) {
		this.price = price;
	}
/**
 * @return activityid palauttaa aktiviteetin id numeron
 */
	public int getActivityid() {
		return activityid;
	}
/**
 * @param activityid asettaa uuden aktiviteetti id numeron
 */
	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

}
