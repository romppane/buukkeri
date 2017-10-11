package model;

public interface User_IF {
	public int getId();
	public String getFname();
	public void setFname(String fname);
	public String getLname() ;
	public void setLname(String lname) ;
	public String getPassword();
	public void setPassword(String password);
	public String getPhone() ;
	public void setPhone(String phone) ;
	public String getEmail();
	public void setEmail(String email) ;
	public void setDao(DAO_IF dao);
	public void bookShift(Shift_IF shift);
	public Booking_IF[] getBookings();
	public void fillBookingData();


}
