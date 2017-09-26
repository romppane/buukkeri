package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SHIFT implements SHIFT_IF {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "Shift_time")
	private String Shift_time;

	@Column(name = "Price")
	private float price;

	@ManyToOne
	@JoinColumn(name = "ACTIVITY_ID")
	private Activity activity;

	public SHIFT(int id, String shift_time, float price, Activity activity) {
		this.id = id;
		this.Shift_time = shift_time;
		this.price = price;
		this.activity = activity;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
