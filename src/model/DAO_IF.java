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
	boolean updateBooking(SHIFT_IF shift, Booking_IF bk);
	boolean deleteBooking(Booking_IF bk);
	boolean createShift(SHIFT_IF shift);
	boolean updateShift(SHIFT_IF shift);
	boolean deleteShift(SHIFT_IF shift);
	boolean createUser(USER_IF user);
	boolean updateUser(USER_IF user);
	boolean deleteUser(USER_IF user);
}
