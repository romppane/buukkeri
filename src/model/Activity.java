package model;

public class Activity implements Activity_IF{
	private int id;
	private String name;
	private int sp_id;
	private String location;
	private String description;



	public Activity() {
	}
	public Activity(int id, String name, int sp_id, String location, String description) {
		this.id = id;
		this.name = name;
		this.sp_id = sp_id;
		this.location = location;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
