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
import entities.Package;

public class PackageDAO{
	

	private static SessionFactory factory;
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(PackageDAO.class);

	public PackageDAO(SessionFactory factory){
		this.factory = factory;
	}
	
	public List<Package> findAllPackages() {
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Package> packagees = new ArrayList<Package>();
		try {
			packagees = (ArrayList<Package>) session.createQuery("FROM Package").list();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packagees;
	}
	
	public List<Package> findPackagesBySenderId(int id){
		Session session = factory.openSession();
		List<Package> packagees = new ArrayList<Package>();
		try {
			Query query = session.createQuery("FROM Package WHERE sender_id=:id OR receiver_id=:id");
			query.setParameter("id", id);
			packagees = query.list();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packagees;
	}
	
	public List<Package> findPackagesBySenderIdAndName(int id, String name){
		Session session = factory.openSession();
		List<Package> packagees = new ArrayList<Package>();
		try {
			Query query = session.createQuery("FROM Package WHERE (sender_id=:id OR receiver_id=:id) AND name LIKE :string");
			query.setParameter("id", id);
			query.setParameter("string", "%" + name + "%");
			packagees = query.list();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packagees;
	}
	public List<Package> findPackagesByName(String name){
		Session session = factory.openSession();
		List<Package> packagees = new ArrayList<Package>();
		try {
			Query query = session.createQuery("FROM Package WHERE name LIKE :string");
			query.setParameter("string", "%" + name + "%");
			packagees = query.list();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packagees;
	}
	
	public void addPackage(Package packagee){
		int packageId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			packageId = (Integer) session.save(packagee);
			packagee.setId(packageId);
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
	
	public void registerPackage(int id){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE Package SET tracking=:track WHERE id=:id");
			query.setParameter("track", true);
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
	public void removePackage(int id){
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE Package WHERE id=:id");
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

	public Package findPackagebyId(int packageId) {
		Session session = factory.openSession();
		Package packagee = new Package();
		try {
			Query query = session.createQuery("FROM Package WHERE id=:id");
			query.setParameter("id", packageId);
			packagee = (Package)query.uniqueResult();
			
		} catch (HibernateException e) {
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packagee;
	}
}
