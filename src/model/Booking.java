package model;
/**Varaus luokan olio
 *
 * @author Roni, Tommi, Marika,Ville
 *
 */
public class Booking implements Booking_IF {

	private int userid;
	private int shiftid;
	/**
	 * tyhjä varaus constructori
	 */
	public Booking() {
	}
	/**
	 * parametrillinen constructori
	 * @param userid käyttäjän id numero
	 * @param shiftid vuoron id numero
	 */
	public Booking(int userid, int shiftid) {
		this.userid = userid;
		this.shiftid = shiftid;
	}
	/**
	 * @return userid palauttaa käyttäjän id arvon
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @return palauttaa vuoron id numeron
	 */
	public int getShiftid() {
		return shiftid;
	}





}
