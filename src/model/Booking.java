package model;

public class Booking implements Booking_IF {

	private int userid;
	private int shiftid;

	public Booking() {
	}

	public Booking(int userid, int shiftid) {
		this.userid = userid;
		this.shiftid = shiftid;
	}

	public int getUserid() {
		return userid;
	}

	public int getShiftid() {
		return shiftid;
	}





}
