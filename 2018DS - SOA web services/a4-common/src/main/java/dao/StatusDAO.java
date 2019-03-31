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

import entities.Status;

public class StatusDAO{


	private static SessionFactory factory;
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(StatusDAO.class);

	public StatusDAO(SessionFactory factory){
		this.factory = factory;
	}
	@Transactional
	public List<Status> findAllStatuses() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Status> statuses = new ArrayList<Status>();
		try {
			statuses = session.createQuery("FROM Status").list();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return statuses;
	}
	
	public Status findStatusByPackageId(int packageId){
		Session session = factory.openSession();
		Status status = null;
		try {
			Query query = session.createQuery("FROM Status WHERE packageId=:packageId");
			query.setParameter("packageId", packageId);
			status = (Status)query.uniqueResult();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	public void addStatus(Status status){
		int packageId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			packageId = (Integer) session.save(status);
			status.setId(packageId);
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
	
	public void updateStatus(Status status){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(status);
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
	public void removeStatus(int id){
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE Status WHERE id=:id");
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
	public Status findStatusById(int id) {
		Session session = factory.openSession();
		Status status = null;
		try {
			Query query = session.createQuery("FROM Status WHERE id=:id");
			query.setParameter("id", id);
			status = (Status)query.uniqueResult();
		} catch (HibernateException e) {
			
			LOGGER.error("", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
}
