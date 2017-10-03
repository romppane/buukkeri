package model;

public interface DAO_IF {
	boolean createSP(SP_IF sp);
	boolean updateSP(SP_IF sp);
	boolean deleteSP(SP_IF sp);
	SP[] readSPs();
	SP readSP(String email);
	boolean createActivity(Activity_IF act);
	boolean updateActivity(Activity_IF act);
	boolean deleteActivity(Activity_IF act);
	Activity[] readActivitiesById(int sp_id);
	Activity[] readActivities();
	boolean createBooking(Booking_IF bk);
	boolean updateBooking(Shift_IF shift, Booking_IF bk);
	boolean deleteBooking(Booking_IF bk);
	Booking[] readBookingsByUserId(int user_id);
	Booking[] readBookingsByShiftId(int shift_id);
	boolean createShift(Shift_IF shift);
	boolean updateShift(Shift_IF shift);
	boolean deleteShift(Shift_IF shift);
	Shift[] readActivityShifts(int act_id);
	boolean createUser(User_IF user);
	boolean updateUser(User_IF user);
	boolean deleteUser(User_IF user);
	User readUser(String email);
}
