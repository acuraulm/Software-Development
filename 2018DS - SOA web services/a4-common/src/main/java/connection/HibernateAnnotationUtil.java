package connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateAnnotationUtil {
	
	private static SessionFactory sessionFactory;
	 
	private SessionFactory buildSessionFactory() {
	      
	    Configuration configuration = new Configuration();
	    configuration.configure("hibernate-annotation.cfg.xml");
	    System.out.println("Hibernate Annotation Configuration loaded");
	             
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	      .applySettings(configuration.getProperties()).build();
	    System.out.println("Hibernate Annotation serviceRegistry created");
	             
	    SessionFactory sessionFactory 
	      = configuration.buildSessionFactory(serviceRegistry);
	             
	    return sessionFactory;
	}   
	 
	public SessionFactory getSessionFactory() {
	    if(sessionFactory == null) sessionFactory = buildSessionFactory();
	    return sessionFactory;
	}
	
}
