package model;

import java.util.Scanner;

public class testingClass {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		DAO_IF dao = new DAO();
		User_IF user = new User("Teppo", "Testaaja", "AAaaAdDFAFSSDas", "000 234 7251", "teppo.testaaja@gmail.com");
		SP_IF sp = new SP("Corporation", "catsanddogs123", "corpo.ration@msi.com", "555 777 9999");

		dao.createSP(sp);
		dao.createUser(user);

		user = dao.readUser(user.getEmail(), user.getPassword());
		sp = dao.readSP(sp.getEmail(), sp.getPassword());

		sp.setDao(dao);

		Activity_IF act1 = new Activity("Sleepy Coding1", sp.getId(), "Helsinki, Finland", "Work work");
		Activity_IF act2 = new Activity("Sleepy Coding2", sp.getId(), "Helsinki, Finland", "Work work");
		Activity_IF act3 = new Activity("Sleepy Coding3", sp.getId(), "Helsinki, Finland", "Work work");


		dao.createActivity(act1);
		dao.createActivity(act2);
		dao.createActivity(act3);

		Activity_IF[] acts = dao.readActivitiesBySPId(sp.getId());


		System.out.println("Syötä vuoron pituus.");
		int length = scanner.nextInt();

		System.out.println("Syötä vuoron alku tunnit.");
		int starth = scanner.nextInt();
		System.out.println("Syötä vuoron alku minuutit.");
		int startmin = scanner.nextInt();

		System.out.println("Syötä vuoron viimeinen tunti");
		int endh = scanner.nextInt();
		System.out.println("Syötä vuoron viimeiset minuutit.");
		int endmin = scanner.nextInt();

		sp.createSetOfShifts(starth,startmin,endh,endmin,length,acts[0]);







	}

}
