package model;
import java.sql.*;
import java.util.ArrayList;


public class DAO implements DAO_IF{
	//ASD
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


			System.out.println("aijaa");

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

	@Override
	//RAATO PITÄÄ POHTIA MITÄ KAIKKEA VOI VAIHTAA!!!!!
	//Vaihtaa salasanaa nyt.
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

	@Override
	public boolean deleteSP(String email) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "delete from Service_Provider where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, email);
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

				SP sp = new SP(name, password, email, phone);
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
				String phone = myRs.getString("Phone");

				provider = new SP(name, password, email, phone);
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


	@Override
	public boolean createUser(USER user) {
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


			System.out.println("aijaa");

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

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createBooking() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateBooking() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteBooking() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean createShift() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateShift() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteShift() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createActivity(Activity_IF act) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Activity values(default,?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, act.getName());
			myStatement.setInt(2, act.getSp_id());
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

}
