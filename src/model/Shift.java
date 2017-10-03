package model;

public class Shift implements Shift_IF {
	private int id;
	private String Shift_time;
	private double price;
	private int activityid;


	public Shift() {

	}

	public Shift(String shift_time, double price, int activityid) {
		this.Shift_time = shift_time;
		this.price = price;
		this.activityid = activityid;
	}

	public Shift(int id, String shift_time, double price, int activityid) {
		this.id = id;
		this.Shift_time = shift_time;
		this.price = price;
		this.activityid = activityid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShift_time() {
		return Shift_time;
	}

	public void setShift_time(String shift_time) {
		Shift_time = shift_time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

}
