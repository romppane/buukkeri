package model;
import java.sql.*;
import java.util.ArrayList;
<<<<<<< HEAD

import model.DAO_IF;
import model.SP;

=======
/**Data access object
 *
 * @author Roni, Tommi, Marika, Ville
 *
 */
>>>>>>> refs/remotes/origin/develop

public class DAO implements DAO_IF{

	private Connection myCon;
/**
 * Luokan constructori
 */
	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			//TUNNELOI TÄMÄ!
			myCon = DriverManager.getConnection("jdbc:mysql://localhost:2206/vuorot", "pena", "pena");
		} catch (Exception dBException) {
			System.err.print(dBException);
			System.err.println("Virhe tietokantayhteyden muodostamisessa.");
			System.exit(-1);
		}
	}
/**
 * yhteyden sammutus metodi
 */
	@Override
	protected void finalize() {
		try {
			if (myCon != null)
				myCon.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Creates Service Provider to db.
	/**
	 * @param sp palvelun tuottajan luonti tietokantaan
	 * @return false jos ei onnistu true jos onnistuu
	 */
	@Override
	public boolean createSP(SP_IF sp) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Service_Provider values(default,?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, sp.getName());
			myStatement.setString(2, sp.getPassword());
			myStatement.setString(3, sp.getEmail());
			myStatement.setString(4, sp.getPhone());
			count = myStatement.executeUpdate();
			System.out.println("SP has been created");

		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Changes Service Provider password.
	/**
	 * @param sp palvelun tuottajan tiedot päivitys tietokannassa
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean updateSP(SP_IF sp) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Service_Provider set Password = ? where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, sp.getPassword());
			myStatement.setString(2, sp.getEmail());
			count = myStatement.executeUpdate();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Deletes Service Provider from db.
	/**
	 * @param sp tietokannasta poistettavan palvelun tuottajan tiedot
	 * @return false jos ei onnistu true jos onnistuu
	 */
	@Override
	public boolean deleteSP(SP_IF sp) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Service_Provider where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, sp.getEmail());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Returns all Service Providers
	/**
	 * Hakee kaikki palvelun tuottajat tiedokannasta
	 * @return palauttaa palvelu tuottaja listan
	 */
	@Override
	public SP_IF[] readSPs() {
		ArrayList<SP> providers = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from valuutta";
			myStatement = myCon.prepareStatement(sqlSelect);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				String name = myRs.getString("Name");
				String password = myRs.getString("Password");
				String email = myRs.getString("Email");
				String phone = myRs.getString("Phone");

				SP sp = new SP(id, name, password, email, phone);
				providers.add(sp);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		SP_IF[] palautus = new SP[providers.size()];
		return (SP_IF[])providers.toArray(palautus);
	}
	//Returns Service Provider by email
	/**hakee yhden palvelun tuottajan tiedot sähköpostin ja salasanan perusteella
	 * @param email palvelun tuottajan sähköposti tieto
	 * @param pass palvelun tuottajan salasana tieto
	 * @return sp palauttaa palvelun tuottajan
	 */
	@Override
	public SP_IF readSP(String email, String pass) {
		SP_IF provider = null;
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Service_Provider where Email = ? AND Password = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setString(1, email);
			myStatement.setString(2, pass);
			myRs = myStatement.executeQuery();

			if(myRs.next()) {
				int id = myRs.getInt("ID");
				String name = myRs.getString("Name");
				String password = myRs.getString("Password");
				String Email = myRs.getString("Email");
				String phone = myRs.getString("Phone");

				provider = new SP(id, name, password, Email, phone);
		}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		return provider;
	}
	//Creates User to db.
	/** Luo käyttäjän tietokantaan
	 * @param user luotavan käyttäjän tiedot
	 * @return false jos luonti epäonnistuu true jos luonti on onnistunut
	 */
	@Override
	public boolean createUser(User_IF user) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Account values(default,?, ?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, user.getFname());
			myStatement.setString(2, user.getLname());
			myStatement.setString(3, user.getPassword());
			myStatement.setString(4, user.getEmail());
			myStatement.setString(5, user.getPhone());
			count = myStatement.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Changes password for User.
	/** Käyttäjän tietojen päivitys
	 * @param user käyttäjän tiedot
	 * @return false jos päivitys ei onnistu true jos päivitys onnistuu
	 */
	@Override
	public boolean updateUser(User_IF user) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Account set Password = ? where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, user.getPassword());
			myStatement.setString(2, user.getEmail());
			count = myStatement.executeUpdate();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Deletes User for Password Email pair.
	/**poistaa käyttäjän tietokannasta
	 * @param user poistettavan käyttäjän tiedot
	 * @return false jos ei onnistu true jos onnistuu
	 */
	@Override
	public boolean deleteUser(User_IF user) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Account where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, user.getEmail());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Creates Booking to db
	/** varaus tietojen lisäys tietokantaan
	 * @param bk varauksen tiedot
	 * @return false jos ei onnistu true jos onnistuu
	 */
	@Override
	public boolean createBooking(Booking_IF bk) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert into Booking values(?,?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(2, bk.getUserid());
			myStatement.setInt(1, bk.getShiftid());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}

	}
	//Switches shift to another shift
	/**varauksen päivitys
	 * @param shift vuoron tiedot
	 * @param bk varauksen tiedot
	 * @return false jos ei onnistu true jos onnistuu
	 */
	@Override
	public boolean updateBooking(Shift_IF shift, Booking_IF bk) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Shift set Shift_ID = ? where User_ID = ? AND Shift_ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(1, shift.getId());
			myStatement.setInt(2, bk.getUserid());
			myStatement.setInt(3, bk.getShiftid());
			count = myStatement.executeUpdate();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Deletes Booking from db
	//NOT DONE
	/**vuoron poisto tietokannasta
	 * @param bk varauksen tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean deleteBooking(Booking_IF bk) {
		// TODO Auto-generated method stub
		return false;
	}
	//Creates a shift to db.
	/**vuoron luonti tietokantaan
	 * @param shift vuoron tiedot
	 * @return false jos epäonnistuu, true jos onnistuu
	 */
	@Override
	public boolean createShift(Shift_IF shift) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Shift values(default, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(2, shift.getShift_time());
			myStatement.setDouble(3, shift.getPrice());
			myStatement.setInt(1, shift.getActivityid());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Sets a price for a shift
	/**vuoron päivitys tietokantaan
	 * @param shift vuoron tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean updateShift(Shift_IF shift) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Shift set Price = ? where ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setDouble(1, shift.getPrice());
			myStatement.setInt(2, shift.getId());
			count = myStatement.executeUpdate();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Deletes a shift.
	/**vuoron poisto tietokannasta
	 * @param shift vuoron tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean deleteShift(Shift_IF shift) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Shift where ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(1, shift.getId());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Creates Activity
	/**aktiviteetin luonti tietokantaan
	 * @param act aktiviteetin tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean createActivity(Activity_IF act) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Activity values(default,?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, act.getName());
			myStatement.setInt(2, act.getSpid());
			myStatement.setString(3, act.getLocation());
			myStatement.setString(4, act.getDescription());
			count = myStatement.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Sets location for activity
	/**aktiviteetin päivitys tietokantaan
	 * @param act aktiviteetin tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean updateActivity(Activity_IF act) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Activity set Location = ? where ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, act.getLocation());
			myStatement.setInt(2,act.getId());
			count = myStatement.executeUpdate();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Deletes activity by ID
	/**aktiviteetin poisto tietokannasta
	 * @param act aktiviteetin tiedot
	 * @return false jos epäonnistuu true jos onnistuu
	 */
	@Override
	public boolean deleteActivity(Activity_IF act) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Activity where ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(1, act.getId());
			count = myStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(count!=1){
			return false;
		}
		else{
			return true;
		}
	}
	//Returns all activities by spID.
	/**aktiviteetin haku tietokannasta palvelun tuottajana
	 * @param sp_id palveluntuottajan id numero
	 * @return palauttaa aktiviteetti listan
	 */
	@Override
	public Activity_IF[] readActivitiesBySPId(int sp_id) {
		ArrayList<Activity_IF> activities = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Activity where SP_ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, sp_id);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				String name = myRs.getString("Name");
				int SP_ID = myRs.getInt("SP_ID");
				String location = myRs.getString("Location");
				String description = myRs.getString("Description");

				Activity_IF act = new Activity(id,name,SP_ID,location,description);
				activities.add(act);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		Activity_IF[] palautus = new Activity[activities.size()];
		return (Activity_IF[])activities.toArray(palautus);
	}
	/**aktiviteetin haku id numerolla
	 * @param ID aktiviteetin id numero
	 * @return act palauttaa aktiviteetin
	 */
	@Override
	public Activity_IF readActivityById(int ID) {
		PreparedStatement myStatement = null;
		ResultSet myRs = null;
		Activity_IF act = null;

		try{
			String sqlSelect = "Select * from Activity where ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, ID);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				String name = myRs.getString("Name");
				int SP_ID = myRs.getInt("SP_ID");
				String location = myRs.getString("Location");
				String description = myRs.getString("Description");

				act = new Activity(id,name,SP_ID,location,description);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return act;
	}

	//Returns all activities
	/**hakee kaikki aktiviteetit tietokannasta
	 * @return palauttaa aktiviteetti listan
	 */
	@Override
	public Activity_IF[] readActivities() {
		ArrayList<Activity_IF> activities = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Activity";
			myStatement = myCon.prepareStatement(sqlSelect);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				String name = myRs.getString("Name");
				int sp_id = myRs.getInt("SP_ID");
				String location = myRs.getString("Location");
				String description = myRs.getString("Description");

				Activity_IF act = new Activity(id,name,sp_id,location,description);
				activities.add(act);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		Activity_IF[] palautus = new Activity[activities.size()];
		return (Activity_IF[])activities.toArray(palautus);
	}

	//Returns all bookings for a specific user.
	/**varausten haku tietokannasta tietyllä käyttäjälle
	 * @param user_id halutun käyttäjän id numero
	 * @return palauttaa varaus listan
	 */
	@Override
	public Booking_IF[] readBookingsByUserId(int user_id) {
		ArrayList<Booking_IF> bookings = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Booking where User_ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, user_id);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int shiftid = myRs.getInt("Shift_ID");
				int userid = myRs.getInt("User_ID");

				Booking booking = new Booking(userid, shiftid);
				bookings.add(booking);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		Booking_IF[] ret = new Booking[bookings.size()];
		return (Booking_IF[])bookings.toArray(ret);
	}
	//Returns all bookings for a specific activity.
	//Kun tarkistetaan vuorojen availibilityä täytyy vertailla, shift id:tä ja katsoa onko varauksia tehty.
	/**hakee varaukset tietokannasta vuoro numerolla
	 * @param shift_id vuoron id numero
	 * @return palauttaa varaus taulun
	 */
	@Override
	public Booking_IF[] readBookingsByShiftId(int shift_id) {
		ArrayList<Booking_IF> bookings = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Booking where Shift_ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, shift_id);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int shiftid = myRs.getInt("Shift_ID");
				int userid = myRs.getInt("User_ID");

				Booking booking = new Booking(shiftid, userid);
				bookings.add(booking);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		Booking_IF[] ret = new Booking[bookings.size()];
		return (Booking_IF[])bookings.toArray(ret);
	}
	//Returns all shifts for activity.
	/**
	 * hakee kaikki vuorot aktiviteettille
	 * @param act_id aktiviteetin id numero
	 * @return palauttaa vuoro taulukon
	 */
	@Override
	public Shift_IF[] readActivityShifts(int act_id) {
		ArrayList<Shift_IF> shifts = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Shift where Activity_ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, act_id);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				int activityid = myRs.getInt("Activity_ID");
				float price = myRs.getFloat("Price");
				String stime = myRs.getString("Shift_Time");

				Shift_IF shift = new Shift(id, stime, price, activityid);
				shifts.add(shift);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		Shift_IF[] ret = new Shift[shifts.size()];
		return (Shift_IF[])shifts.toArray(ret);
	}

	// Returns user for certain unique email address.
	/**hakee tietyn käyttäjätilin
	 * @param email käyttäjän sähköposti
	 * @param pass käyttäjän salasana
	 * @return palauttaa käyttäjä olion
	 */
	@Override
	public User_IF readUser(String email, String pass) {
		User_IF user = null;
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Account where Email = ? AND Password = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setString(1, email);
			myStatement.setString(2, pass);
			myRs = myStatement.executeQuery();

			if(myRs.next()) {
				int id = myRs.getInt("ID");
				String fname = myRs.getString("Firstname");
				String lname = myRs.getString("Lastname");
				String pw = myRs.getString("Password");
				String Email = myRs.getString("Email");
				String phone = myRs.getString("Phone");

				user = new User(id, fname, lname, pw, phone, Email);
		}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		return user;
	}
	/**
	 * hakee vuoron id:n perusteella
	 * @param ID vuorn id numero
	 * @return palauttaa vuoro olion
	 */
	@Override
	public Shift_IF readShiftById(int ID) {
		Shift_IF shift = null;
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Shift where ID = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setInt(1, ID);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				int activityid = myRs.getInt("Activity_ID");
				float price = myRs.getFloat("Price");
				String stime = myRs.getString("Shift_Time");

				shift = new Shift(id, stime, price, activityid);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (myRs != null)
					myRs.close();
				if (myStatement != null)
					myStatement.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		return shift;
	}

}
