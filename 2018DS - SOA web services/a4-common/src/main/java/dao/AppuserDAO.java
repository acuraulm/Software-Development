package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.Appuser;


public class AppuserDAO{
	
	private static SessionFactory factory;
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(AppuserDAO.class);

	public AppuserDAO(SessionFactory factory){
		this.factory = factory;
	}
	
	public List<Appuser> findAllAppusers() {
		Session session = factory.openSession();
		List<Appuser> appusers = new ArrayList<Appuser>();
		try {
			appusers = session.createQuery("FROM Appuser").list();
		} catch (HibernateException e) {
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appusers;
	}
	public void addAppuser(Appuser appuser){
		int userId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userId = (Integer) session.save(appuser);
			appuser.setId(userId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public Appuser findAppuserByUsernameAndPassword(String username, String password){
		Session session = factory.openSession();
		Appuser appuser = null;
		try {
			Query query = session.createQuery("FROM Appuser WHERE username=:user AND password=:pass");
			query.setParameter("user", username);
			query.setParameter("pass", password);
			appuser = (Appuser)query.uniqueResult();
			
		} catch (HibernateException e) {
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appuser;
	}

	public Appuser findAppuserById(int id) {
		Session session = factory.openSession();
		Appuser appuser = null;
		try {
			Query query = session.createQuery("FROM Appuser WHERE id=:id");
			query.setParameter("id", id);
			appuser = (Appuser)query.uniqueResult();
			
		} catch (HibernateException e) {
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appuser;
	}

	public Appuser findAppuserByName(String name) {
		Session session = factory.openSession();
		Appuser appuser = null;
		try {
			Query query = session.createQuery("FROM Appuser WHERE username=:user");
			query.setParameter("user", name);
			appuser = (Appuser)query.uniqueResult();
			
		} catch (HibernateException e) {
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return appuser;
	}
	
}
