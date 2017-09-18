package model;
public interface DAO_IF {
	boolean createSP(SP sp);
	boolean updateSP(SP sp);
	boolean deleteSP(String email);
	SP[] readSPs();
	SP readSP(String email);
	boolean createUser();
	boolean updateUser();
	boolean deleteUser();
	boolean createActivity();
	boolean updateActivity();
	boolean deleteActivity();
	boolean createBooking();
	boolean updateBooking();
	boolean deleteBooking();
	boolean createShift();
	boolean updateShift();
	boolean deleteShift();
}
