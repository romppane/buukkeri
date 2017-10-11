package model;

/**
 * @author ronim
 */

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DAOtests {

	private static DAO_IF bookerDAO = new DAO();
	private static Activity_IF act = new Activity();
	private static SP_IF sp = new SP();
	private static User_IF user = new User();
	private static Shift_IF[] shifts;
	private static Booking_IF booking = new Booking();
	private static Shift_IF shift = null;
	private static Activity_IF[] acts = null;

	@BeforeClass
	public static void initiate() {
		//Set service provider, put to db and fetch it with id to use in tests.
		sp.setName("Tommi Testing Corporation");
		sp.setEmail("abc@dfg.com");
		sp.setPassword("abcdkissawalking");
		sp.setPhone("0201987868");
		bookerDAO.createSP(sp);
		sp = bookerDAO.readSP(sp.getEmail(), sp.getPassword());
		sp.setDao(bookerDAO);
		//Set activity data and the same things with sp.
		act.setName("Test Activity");
		act.setDescription("Tommi is sleeping");
		act.setLocation("Test City");
		act.setSpid(sp.getId());
		bookerDAO.createActivity(act);
		sp.fillActivities();
		Activity_IF[] acts = sp.getActivities();

		//Only way to get activities is in array.
		act = acts[0];
		System.out.println(act.getId());
		//Create set of shifts for activity made before by using sp:s method.
		sp.createSetOfShifts(11, 00, 13, 30, 30,20.00, act);
		//Does not check if it possible to make the shifts, HAS TO BE MADE SAFER!
		shifts = bookerDAO.readActivityShifts(act.getId());
		shift = shifts[0];

		//Create test user and put to db and fetch it
		String fname = "Tester";
		String lname = "Test";
		String password = "VilleIsSick";
		String email = "ville@home.com";
		String phone = "000-7777777";

		user.setEmail(email);
		user.setFname(fname);
		user.setLname(lname);
		user.setPassword(password);
		user.setPhone(phone);

		bookerDAO.createUser(user);
		user = bookerDAO.readUser(email, password);
		user.setDao(bookerDAO);

		//Tee book shift.

	}


	@AfterClass
	public static void end() {
		Booking_IF[] bookings = bookerDAO.readBookingsByUserId(user.getId());
		if(bookings.length > 0) {
			for(int i = 0; i<bookings.length; i++) {
				bookerDAO.deleteBooking(bookings[i]);
			}
		}
		// Try to delete all the shifts made if not yet deleted.
		shifts = bookerDAO.readActivityShifts(act.getId());
		if(shifts.length > 0) {
			for(int i = 0; i<shifts.length; i++) {
				bookerDAO.deleteShift(shifts[i]);
			}
		}

		// Deleting Activities should happen
		assertTrue("deleteActivity(): Removing Activity was not successful.",
				bookerDAO.deleteActivity(acts[0]));
		assertFalse("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
				bookerDAO.readActivitiesBySPId(sp.getId()) == null);

		// Deleting test user should happen
				assertTrue("deleteUser(): Removing User was not successful.",
						bookerDAO.deleteUser(user));
				assertTrue("deleteUser(): Removing User was not successful - User was still in database.",
						bookerDAO.readUser(user.getEmail(), user.getPassword()) == null);
		// Deleting test user should happen
				assertTrue("deleteSP(): Removing Service Provider was not successful.",
						bookerDAO.deleteSP(sp));
				assertTrue("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
						bookerDAO.readSP(sp.getEmail(), sp.getPassword()) == null);
	}



	@Test
	public void testSPDAO() {

		// Try to add duplicate Service Provider, should not be possible
		assertFalse("createSP(): Adding duplicate Service Providers is possible.",
				bookerDAO.createSP(sp));

		// Changing password
				sp.setPassword("T0Mmin PuU");
				assertTrue("updateSP(): Changing password on test user was not successful.",
						bookerDAO.updateSP(sp));
				sp = bookerDAO.readSP(sp.getEmail(), sp.getPassword());
				assertEquals("updateSP(): Password is not correct.",
						"T0Mmin PuU", sp.getPassword());

		// Service Providers fields should be correct
		assertTrue("readSP(): reading testuser data could not be done",
				(sp = bookerDAO.readSP(sp.getEmail(), sp.getPassword())) != null);
		assertEquals("readSP(): Email is not correct.",
				"abc@dfg.com", sp.getEmail());
		assertEquals("readSP(): Name is not correct.",
				"Tommi Testing Corporation", sp.getName());
		assertEquals("readSP(): Phone number is not correct.",
				"0201987868", sp.getPhone());

		// Removing random stuff should fail
		SP_IF assd = new SP();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteSP(assd));
	}

	@Test
	public void testActivityDAO() {

		// Activity fields should be correct
		assertTrue("readActivityById(): reading testActivity data could not be done",
				(acts = bookerDAO.readActivitiesBySPId(sp.getId())) != null);
		act = acts[0];
		assertEquals("readActivityById(): Location is not correct.",
				"Test City", act.getLocation());
		assertEquals("readActivityById(): Name is not correct.",
				"Test Activity", act.getName());
		assertEquals("readActivityById(): Description is not correct.",
				"Tommi is sleeping", act.getDescription());
		assertEquals("readActivityById(): SP_ID is not correct.",
				sp.getId(), act.getSpid());

		// Changing password
		act.setLocation("Asinsaari");
		assertTrue("updateActivity(): Changing location on test activity was not successful.",
				bookerDAO.updateActivity(act));
		acts = bookerDAO.readActivitiesBySPId(sp.getId());
		assertFalse("updateActivity(): Location is not correct.",
				acts[0].getLocation() == "Test City");

		// Removing random stuff should fail
		Activity_IF random = new Activity();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteActivity(random));

	}

	@Test
	public void testUserDAO() {
		//Adding duplicates should fail.
		assertFalse("createUser(): Adding duplicate User is possible.",
				bookerDAO.createUser(user));

		// Changing password
				user.setPassword("Al0n3 in sch-o-ol");
				assertTrue("updateUser(): Changing password on test user was not successful.",
						bookerDAO.updateUser(user));
				user = bookerDAO.readUser(user.getEmail(), user.getPassword());
				assertEquals("updateUser(): Password is not correct.",
						"Al0n3 in sch-o-ol", user.getPassword());

		// Users fields should be correct
		assertTrue("readUser(): reading testuser data could not be done",
				(user = bookerDAO.readUser(user.getEmail(), user.getPassword())) != null);
		assertEquals("readUser(): Email is not correct.",
				"ville@home.com", user.getEmail());
		assertEquals("readUser(): Firstname is not correct.",
				"Tester", user.getFname());
		assertEquals("readUser(): Lastname is not correct.",
				"Test", user.getLname());
		assertEquals("readUser(): Phone number is not correct.",
				"000-7777777", user.getPhone());
		assertEquals("readUser(): Password is not correct.",
				"Al0n3 in sch-o-ol", user.getPassword());

		// Removing random stuff should fail
		User_IF assd = new User();
		assertFalse("deleteUser(): Claims to have removed User which never existed.",
				bookerDAO.deleteUser(assd));
	}

	@Test
	public void testShifts() {


		// Values of Shift should be correct
		assertTrue("readActivityShifts(): reading test data could not be done",
				(shifts = bookerDAO.readActivityShifts(act.getId())) != null);
		shift = shifts[0];
		assertEquals("readActivityShifts(): Price is not correct.",
				20.00, shift.getPrice(), 0.000001);
		assertEquals("readActivityShifts(): Shift time is not correct.",
				"11:0-11:30", shift.getShift_time());
		assertEquals("readActivityShifts(): Activity id is not correct.",
				act.getId(), shift.getActivityid());

		// Changing price of Shift
				shift.setPrice(30.00);
				assertTrue("updateShift(): Changing price on test shift was not successful.",
						bookerDAO.updateShift(shift));
				shifts = bookerDAO.readActivityShifts(act.getId());
				shift = shifts[0];
				assertEquals("updateShift(): Price is not correct.",
						30.00, shift.getPrice(), 0.0000001);


				// Deleting test user should happen
				assertTrue("deleteShift(): Removing Shift was not successful.",
						bookerDAO.deleteShift(shifts[0]));
				for(int i = 1; i<shifts.length; i++) {
					bookerDAO.deleteShift(shifts[i]);
				}
				Shift_IF[] rest = bookerDAO.readActivityShifts(act.getId());
				assertTrue("deleteUser(): Removing Shifts was not successful - atleast one Shift was still in database.",
						rest.length == 0);


	}

	@Test
	public void testBooking() {
		// Create Booking with user
		user.setDao(bookerDAO);
		System.out.println(shift.getShift_time());
		user.bookShift(shift);
		Booking_IF[] bookings = null;
		//user.fillBookingData();
		Booking_IF[] userbooks = user.getBookings();

		// Read the bookings made by user
		assertTrue("readBookingsByUserId(): Reading bookings made by user was not successfull",
				(bookings = bookerDAO.readBookingsByUserId(user.getId())) != null);
		System.out.println(bookings[0].getUserid());
		bookerDAO.deleteBooking(bookings[0]);
		//assertEquals("fillBookingData(): Reading bookings straight from dao gave differes from readBookingsByUserId()", bookings[0].getShiftid(), userbooks[0].getShiftid());



	}
}
