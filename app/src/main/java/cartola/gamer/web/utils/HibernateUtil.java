package cartola.gamer.web.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the session factory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("cartola/gamer/cbr/hibernate/hibernate.cfg.xml");
			configuration.addClass(cartola.gamer.cbr.descriptions.Clube.class);
			System.out.println("Hibernate Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			System.out.println("Hibernate serviceRegistry created");


			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			// SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) sessionFactory = buildSessionFactory();
		return sessionFactory;
	}
}
