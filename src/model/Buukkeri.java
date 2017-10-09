package model;

public class Buukkeri {

	public static void main(String[] args) {
		
		/*SessionFactory sessionfactory = null;

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionfactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}

		catch(Exception e) {
			System.out.println("Something went wrong during the initiation!");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}


		Session session = sessionfactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			User penanasiakas = new User(0, "Pertti", "Penanen", "yolo2000", "0401227779", "asdfs@penamail.com");
			SP sp = new SP(0, "Sports Club", "asdlolwtf", "sportss.clubb@email.com", "3324245555");
			Activity act = new Activity(0, "open footballfield", sp, "Helsinki", "Fun time for anyone");
			session.saveOrUpdate(penanasiakas);
			session.saveOrUpdate(sp);
			session.saveOrUpdate(act);
			transaction.commit();
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw e;
		}
		finally {
			session.close();
		}*/


	}


}
