package model;
import java.sql.*;
import java.util.ArrayList;


public class DAO implements DAO_IF{

	private Connection myCon;

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
	@Override
	public SP[] readSPs() {
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

		SP[] palautus = new SP[providers.size()];
		return (SP[])providers.toArray(palautus);
	}
	//Returns Service Provider by email
	@Override
	public SP readSP(String email) {
		SP provider = null;
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Service_Provider where Email = ?";
			myStatement = myCon.prepareStatement(sqlSelect);
			myStatement.setString(1, email);
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
	@Override
	public boolean createUser(User_IF user) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Service_Provider values(default,?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(0, user.getId());
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
	@Override
	public boolean deleteUser(User_IF user) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Account where Email = ? AND Password = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, user.getEmail());
			myStatement.setString(2, user.getPassword());
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
	@Override
	public boolean createBooking(Booking_IF bk) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert into Booking values(?,?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setInt(1, bk.getUserid());
			myStatement.setInt(2, bk.getShiftid());
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
	@Override
	public boolean deleteBooking(Booking_IF bk) {
		// TODO Auto-generated method stub
		return false;
	}
	//Creates a shift to db.
	@Override
	public boolean createShift(Shift_IF shift) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Shift values(default, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, shift.getShift_time());
			myStatement.setFloat(2, shift.getPrice());
			myStatement.setInt(2, shift.getActivityid());
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
	@Override
	public boolean updateShift(Shift_IF shift) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "update Shift set Price = ? where ID = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setFloat(1, shift.getPrice());
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
	@Override
	public Activity[] readActivitiesById(int sp_id) {
		ArrayList<Activity> activities = new ArrayList();
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

				Activity act = new Activity(id,name,SP_ID,location,description);
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

		Activity[] palautus = new Activity[activities.size()];
		return (Activity[])activities.toArray(palautus);
	}
	//Returns all activities
	@Override
	public Activity[] readActivities() {
		ArrayList<Activity> activities = new ArrayList();
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

				Activity act = new Activity(id,name,sp_id,location,description);
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

		Activity[] palautus = new Activity[activities.size()];
		return (Activity[])activities.toArray(palautus);
	}

	//Returns all bookings for a specific user.
	@Override
	public Booking[] readBookingsByUserId(int user_id) {
		ArrayList<Booking> bookings = new ArrayList();
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

		Booking[] ret = new Booking[bookings.size()];
		return (Booking[])bookings.toArray(ret);
	}
	//Returns all bookings for a specific activity.
	//Kun tarkistetaan vuorojen availibilityä täytyy vertailla, shift id:tä ja katsoa onko varauksia tehty.
	//TYNKÄ

	@Override
	public Booking[] readBookingsByShiftId(int shift_id) {
		// TODO Auto-generated method stub
		return null;
	}
	//Returns all shifts for activity.

	@Override
	public Shift[] readActivityShifts(int act_id) {
		ArrayList<Shift> shifts = new ArrayList();
		PreparedStatement myStatement = null;
		ResultSet myRs = null;

		try{
			String sqlSelect = "Select * from Shift";
			myStatement = myCon.prepareStatement(sqlSelect);
			myRs = myStatement.executeQuery();

			while(myRs.next()) {
				int id = myRs.getInt("ID");
				int activityid = myRs.getInt("Activity_ID");
				float price = myRs.getFloat("Price");
				String stime = myRs.getString("Shift_Time");

				Shift shift = new Shift(id, stime, price, activityid);
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

		Shift[] ret = new Shift[shifts.size()];
		return (Shift[])shifts.toArray(ret);
	}

}
