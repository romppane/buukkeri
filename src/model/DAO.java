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
			myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/vuorot.db", "pena", "pena");
		} catch (Exception e) {
			System.err.print(e);
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
	public boolean createSP(SP sp) {
		PreparedStatement myStatement = null;
		String query = null;
		int count = 0;
		try{
			query = "insert ignore into Service_Provider values(?, ?, ?, ?);";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, sp.getName());
			myStatement.setString(2, sp.getPassword());
			myStatement.setString(3, sp.getEmail());
			myStatement.setString(4, sp.getPhone());
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
	//RAATO PITÄÄ POHTIA MITÄ KAIKKEA VOI VAIHTAA!!!!!
	//Vaihtaa salasanaa nyt.
	public boolean updateSP(SP sp) {
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
			query = "delete from Service_provider where Email = ?";
			myStatement = myCon.prepareStatement(query);
			myStatement.setString(1, email);
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

				provider = new SP(id, name, password, email, phone);
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
	public boolean createUser() {
		// TODO Auto-generated method stub
		return false;
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
	public boolean createActivity() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateActivity() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteActivity() {
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

}
