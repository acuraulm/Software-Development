package dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.Package;
import entities.Route;

public class RouteDAO {


	private static SessionFactory factory;
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(RouteDAO.class);

	@SuppressWarnings("static-access")
	public RouteDAO(SessionFactory factory){
		this.factory = factory;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public List<Route> findAllRoutes() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Route> routes = new ArrayList<Route>();
		try {
			routes = session.createQuery("FROM Route").list();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return routes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Route> findRoutesByStatusId(int statusId){
		Session session = factory.openSession();
		List<Route> routes = new ArrayList<Route>();
		try {
			Query query = session.createQuery("FROM Route WHERE status_id=:id");
			query.setParameter("id", statusId);
			routes = query.list();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return routes;
	}
	
	public void addRoute(Route route){
		int packageId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			packageId = (Integer) session.save(route);
			route.setId(packageId);
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
	
	public void updateRoute(Route route){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(route);
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
	public void removeRoute(int id){
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE Route WHERE id=:id");
			query.setParameter("id", id);
			query.executeUpdate();
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
}
