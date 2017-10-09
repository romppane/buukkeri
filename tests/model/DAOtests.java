package model;

/**
 * @author ronim
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class DAOtests {

	private DAO_IF bookerDAO = new DAO();
	private Activity_IF act = new Activity();
	private SP_IF sp = new SP();
	private User_IF user = new User();
	private Booking_IF booking = new Booking();


	@Test
	public void testSPDAO() {

		String name = "Testi";
		String password = "Ville123";
		String email = "testi@testi.com";
		String phone = "000-1232345";

		sp.setEmail(email);
		sp.setName(name);
		sp.setPassword(password);
		sp.setPhone(phone);

		// Add Service Provider
		assertTrue("createSP(): Adding new Service Provider was not successful.",
				bookerDAO.createSP(sp));
		assertFalse("createSP(): Adding duplicate Service Providers is possible.",
				bookerDAO.createSP(sp));

		// Service Providers fields should be correct
		assertTrue("readSP(): reading testuser data could not be done",
				(sp = bookerDAO.readSP(email, password)) != null);
		assertEquals("readSP(): Email is not correct.",
				email, sp.getEmail());
		assertEquals("readSP(): Name is not correct.",
				name, sp.getName());
		assertEquals("readSP(): Phone number is not correct.",
				phone, sp.getPhone());
		assertEquals("readSP(): Password is not correct.",
				password, sp.getPassword());

		// Changing password
		sp.setPassword("T0Mmin PuU");
		assertTrue("updateSP(): Changing password on test user was not successful.",
				bookerDAO.updateSP(sp));
		sp = bookerDAO.readSP(email, sp.getPassword());
		assertEquals("updateSP(): Password is not correct.",
				"T0Mmin PuU", sp.getPassword());


		// Deleting test user should happen
		assertTrue("deleteSP(): Removing Service Provider was not successful.",
				bookerDAO.deleteSP(sp));
		assertTrue("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
				bookerDAO.readSP(email, sp.getPassword()) == null);

		// Removing random stuff should fail
		SP_IF assd = new SP();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteSP(assd));
	}

	@Test
	public void testActivityDAO() {

		String name = "Sauna";
		int spid = 139;
		String loc = "Perillisensaari";
		String desc = "Saunaa";
		Activity_IF[] acts = null;

		act.setName(name);
		act.setSpid(spid);
		act.setDescription(desc);
		act.setLocation(loc);

		// Add Activity
		assertTrue("createActivity(): Adding new Activity was not successful.",
				bookerDAO.createActivity(act));
		assertTrue("createActivity(): Adding duplicate Activity is not possible.",
				bookerDAO.createActivity(act));

		// Activity fields should be correct
		assertTrue("readActivityById(): reading testActivity data could not be done",
				(acts = bookerDAO.readActivitiesBySPId(spid)) != null);
		act = acts[0];
		assertEquals("readActivityById(): Location is not correct.",
				loc, act.getLocation());
		assertEquals("readActivityById(): Name is not correct.",
				name, act.getName());
		assertEquals("readActivityById(): Description is not correct.",
				desc, act.getDescription());
		assertEquals("readActivityById(): SP_ID is not correct.",
				spid, act.getSpid());

		// Changing password
		act.setLocation("Asinsaari");
		assertTrue("updateActivity(): Changing location on test activity was not successful.",
				bookerDAO.updateActivity(act));
		acts = bookerDAO.readActivitiesBySPId(spid);
		assertFalse("updateActivity(): Location is not correct.",
				acts[0].getLocation() == acts[1].getLocation());


		// Deleting Activities should happen
		assertTrue("deleteActivity(): Removing Activity was not successful.",
				bookerDAO.deleteActivity(acts[0]));
		assertTrue("deleteActivity(): Removing Activity was not successful.",
				bookerDAO.deleteActivity(acts[1]));
		assertFalse("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
				bookerDAO.readActivitiesBySPId(spid) == null);

		// Removing random stuff should fail
		Activity_IF random = new Activity();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteActivity(random));

	}

	@Test
	public void testUserDAO() {

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

		// Add User
		assertTrue("createUser(): Adding new User was not successful.",
				bookerDAO.createUser(user));
		assertFalse("createUser(): Adding duplicate User is possible.",
				bookerDAO.createUser(user));

		// Users fields should be correct
		assertTrue("readUser(): reading testuser data could not be done",
				(user = bookerDAO.readUser(email, password)) != null);
		assertEquals("readUser(): Email is not correct.",
				email, user.getEmail());
		assertEquals("readUser(): Firstname is not correct.",
				fname, user.getFname());
		assertEquals("readUser(): Lastname is not correct.",
				lname, user.getLname());
		assertEquals("readUser(): Phone number is not correct.",
				phone, user.getPhone());
		assertEquals("readUser(): Password is not correct.",
				password, user.getPassword());

		// Changing password
		user.setPassword("Al0n3 in sch-o-ol");
		assertTrue("updateUser(): Changing password on test user was not successful.",
				bookerDAO.updateUser(user));
		user = bookerDAO.readUser(email, user.getPassword());
		assertEquals("updateUser(): Password is not correct.",
				"Al0n3 in sch-o-ol", user.getPassword());


		// Deleting test user should happen
		assertTrue("deleteUser(): Removing User was not successful.",
				bookerDAO.deleteUser(user));
		assertTrue("deleteUser(): Removing User was not successful - User was still in database.",
				bookerDAO.readUser(email, user.getPassword()) == null);

		// Removing random stuff should fail
		User_IF assd = new User();
		assertFalse("deleteUser(): Claims to have removed User which never existed.",
				bookerDAO.deleteUser(assd));
	}

	@Test
	public void testShifts() {
		SP_IF ser = new SP("Tommi Testing Corporation", "abcdkissawalking", "abc@dfg.com", "0201987868");
		bookerDAO.createSP(ser);
		ser = bookerDAO.readSP(ser.getEmail(), ser.getPassword());
		Activity_IF act = new Activity("Test Activity", ser.getId(), "Test City", "Tommi is sleeping");
		bookerDAO.createActivity(act);
		Activity_IF[] acts = bookerDAO.readActivitiesBySPId(ser.getId());
		act = acts[0];
		Shift_IF shift = new Shift();
		String time = "09:00 - 10:00";
		double price = 20.00;
		int actid = act.getId();
		shift.setActivityid(actid);
		shift.setPrice(price);
		shift.setShift_time(time);
		Shift_IF second = new Shift("10:00 - 11:00", (float)20.00, act.getId());
		Shift_IF[] shifts = null;
		// Add Shift
		assertTrue("createShift(): Adding new shift was not successful.",
				bookerDAO.createShift(shift));
		assertTrue("createShift(): Adding second new shift on same provider was not successful.",
				bookerDAO.createShift(second));

		// Values of Shift should be correct
		assertTrue("readActivityShifts(): reading test data could not be done",
				(shifts = bookerDAO.readActivityShifts(act.getId())) != null);
		shift = shifts[0];
		assertEquals("readActivityShifts(): Price is not correct.",
				price, shift.getPrice(), 0.000001);
		System.out.println(time);
		System.out.println(shift.getShift_time());
		assertEquals("readActivityShifts(): Shift time is not correct.",
				time, shift.getShift_time());
		assertEquals("readActivityShifts(): Activity id is not correct.",
				actid, shift.getActivityid());

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
				assertTrue("deleteShift(): Removing Shift was not successful.",
						bookerDAO.deleteShift(shifts[1]));
				Shift_IF[] rest = bookerDAO.readActivityShifts(act.getId());
				assertTrue("deleteUser(): Removing Shifts was not successful - atleast one Shift was still in database.",
						rest.length == 0);

				bookerDAO.deleteActivity(act);
				bookerDAO.deleteSP(ser);


	}
}
