package model;

public interface DAO_IF {
	boolean createSP(SP_IF sp);
	boolean updateSP(SP_IF sp);
	boolean deleteSP(SP_IF sp);
	SP_IF[] readSPs();
	SP_IF readSP(String email, String pass);
	boolean createActivity(Activity_IF act);
	boolean updateActivity(Activity_IF act);
	boolean deleteActivity(Activity_IF act);
	Activity_IF[] readActivitiesBySPId(int sp_id);
	Activity_IF readActivityById(int id);
	Activity_IF[] readActivities();
	boolean createBooking(Booking_IF bk);
	boolean updateBooking(Shift_IF shift, Booking_IF bk);
	boolean deleteBooking(Booking_IF bk);
	Booking_IF[] readBookingsByUserId(int user_id);
	Booking_IF[] readBookingsByShiftId(int shift_id);
	boolean createShift(Shift_IF shift);
	boolean updateShift(Shift_IF shift);
	boolean deleteShift(Shift_IF shift);
	Shift_IF[] readActivityShifts(int act_id);
	Shift_IF readShiftById(int ID);
	boolean createUser(User_IF user);
	boolean updateUser(User_IF user);
	boolean deleteUser(User_IF user);
	User_IF readUser(String email, String pass);
}
