package model;

public interface DAO_IF {
	boolean createSP(SP_IF sp);
	boolean updateSP(SP_IF sp);
	boolean deleteSP(String email);
	SP[] readSPs();
	SP readSP(String email);
	boolean createActivity(Activity_IF act);
	boolean updateActivity(Activity_IF act);
	boolean deleteActivity(Activity_IF act);
	Activity[] readActivitiesById(int sp_id);
	Activity[] readActivities();
	boolean createBooking();
	boolean updateBooking();
	boolean deleteBooking();
	boolean createShift();
	boolean updateShift();
	boolean deleteShift();
	boolean createUser(USER user);
	boolean updateUser();
	boolean deleteUser();
}
