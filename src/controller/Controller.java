package controller;

import model.*;
import view.*;

public class Controller implements Controller_IF {
	SP_IF sp;
	User user;
	Booking booking;
	DAO dao;
	Buukkeri_view main;

	public Controller(Buukkeri_view main) {
		this.main = main;

	}
//SP OPERATIONS
//creates sp
	public SP_IF createSP(String name, String password, String email, String phone) {
		sp = new SP(name, password, email, phone);
		dao.createSP(sp);
		return dao.readSP(email, phone);

	}
//shows all sp:s
	public SP_IF[] showSPs(){
		return dao.readSPs();
	}
//shows all sp
	public SP_IF showSP(String email, String password){
		return dao.readSP(email, password);
	}
	public boolean deleteSP(SP_IF sp){

		return dao.deleteSP(sp);
	}

	public Activity_IF[] showActivities(){
		return dao.readActivities() ;

	}
	public Activity_IF showActivity(int sp_id){
		dao.readActivitiesBySPId(sp_id);
		return null;

	}





}
