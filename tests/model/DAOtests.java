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
				(sp = bookerDAO.readSP(email)) != null);
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
		sp = bookerDAO.readSP(email);
		assertEquals("updateSP(): Password is not correct.",
				"T0Mmin PuU", sp.getPassword());


		// Deleting test user should happen
		assertTrue("deleteSP(): Removing Service Provider was not successful.",
				bookerDAO.deleteSP(sp));
		assertTrue("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
				bookerDAO.readSP(email) == null);

		// Removing random stuff should fail
		SP_IF assd = new SP();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteSP(assd));
	}

	@Test
	public void testActivityDAO() {

		String name = "Sauna";
		int spid = 1;
		String loc = "Perillisensaari";
		String desc = "Saunaa";
		Activity[] acts = null;

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
				(acts = bookerDAO.readActivitiesById(spid)) != null);
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
		acts = bookerDAO.readActivitiesById(spid);
		assertFalse("updateActivity(): Location is not correct.",
				acts[0].getLocation() == acts[1].getLocation());


		// Deleting Activities should happen
		assertTrue("deleteActivity(): Removing Activity was not successful.",
				bookerDAO.deleteActivity(acts[0]));
		assertTrue("deleteActivity(): Removing Activity was not successful.",
				bookerDAO.deleteActivity(acts[1]));
		assertFalse("deleteSP(): Removing Service Provider was not successful - Service Provider was still in database.",
				bookerDAO.readActivitiesById(spid) == null);

		// Removing random stuff should fail
		Activity_IF random = new Activity();
		assertFalse("deleteSP(): Claims to have removed Service Provider which never existed.",
				bookerDAO.deleteActivity(random));

	}
}
