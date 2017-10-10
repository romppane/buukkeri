package model;

import java.util.ArrayList;

public interface SP_IF {

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	String getEmail();

	void setEmail(String email);

	String getPhone();

	void setPhone(String phone);

	void setDao(DAO_IF dao);

	int getId();

	void fillActivities();

	void fillShifts();

	void createSetOfShifts(int starth, int startmin, int endh, int endmin, int length, double price, Activity_IF activity_IF);


}
