package model;

public class Buukkeri {

	public static void main(String[] args) {
		DAO_IF test = new DAO();
		SP pena = new SP(0,"vuorot", "pena", "ronim@metropolia.fi", "0405353169");
		test.createSP(pena);


	}

}
