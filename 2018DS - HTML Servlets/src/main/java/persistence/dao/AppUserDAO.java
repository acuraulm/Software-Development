package persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import persistence.entities.AppUser;

public class AppUserDAO extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(AppUserDAO.class);
	private SessionFactory factory;
	
	public AppUserDAO(SessionFactory factory){
		this.factory = factory;
	}
	
	public AppUser addAppUser(AppUser appUser){
		int userId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userId = (Integer) session.save(appUser);
			appUser.setId(userId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}

		return appUser;
	}
	
	@SuppressWarnings("unchecked")
	public List<AppUser> findAllAppUsers() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<AppUser> appusers = new ArrayList<AppUser>();
		List<AppUser> appuserss = new ArrayList<AppUser>();
		try {
			tx = session.beginTransaction();
			appusers = session.createQuery("FROM AppUser").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		
		if(!appusers.isEmpty()){
				String thiss = appusers.toString();
			String[] thisss = thiss.split("AppUser");
			for(int j = 1; j<thisss.length; j++){
		//		System.out.println("BLABLA " + j + " " +  thisss[j]);
			AppUser appuser = new AppUser();
			appuser.setId(Integer.parseInt(thisss[j].split("id=")[1].split(",")[0]));
			appuser.setPassword(thisss[j].split("password=")[1].split(",")[0]);
			appuser.setUsername(thisss[j].split("username=")[1].split(",")[0]);
			appuser.setRole(thisss[j].split("role=")[1].split(",")[0].split("]")[0]);
			
			appuserss.add(appuser);
			}

			}
		
		return appusers;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public AppUser findAppUserById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<AppUser> appusers = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM AppUser WHERE id = :id");
			query.setParameter("id", id);
			appusers = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		if(!appusers.isEmpty()){
			AppUser appuser = new AppUser();
			appuser.setId(id);
			appuser.setPassword(appusers.toString().split("password=")[1].split(",")[0]);
			appuser.setUsername(appusers.toString().split("username=")[1].split(",")[0]);
			appuser.setRole(appusers.toString().split("role=")[1].split(",")[0].split("]")[0]);
				return appuser;
			}
			return null;
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public AppUser deleteAppUser(AppUser appUser){
		Session session = factory.openSession();
		Transaction tx = null;
		appUser = findAppUserById(appUser.getId());
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE AppUser WHERE id = :id");
			query.setParameter("id",appUser.getId());
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		return appUser;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public AppUser findAppUserByUsername(String username) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<AppUser> appusers = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM AppUser WHERE username = :username");
			query.setParameter("username", username);
			appusers = query.list();
			
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		if(!appusers.isEmpty()){
		AppUser appuser = new AppUser();
		appuser.setId(Integer.parseInt(appusers.toString().split("id=")[1].split(",")[0]));
		appuser.setPassword(appusers.toString().split("password=")[1].split(",")[0]);
		appuser.setUsername(username);
		appuser.setRole(appusers.toString().split("role=")[1].split(",")[0].split("]")[0]);
			return appuser;
		}
		return null;
		//return appusers != null && !appusers.isEmpty() ? appusers.get(0) : null;
	}
}
