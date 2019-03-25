package persistence.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import persistence.entities.AppUser;
import persistence.entities.Flight;

public class FlightDAO{

	private static final Log LOGGER = LogFactory.getLog(FlightDAO.class);
	private SessionFactory factory;
	
	public FlightDAO(SessionFactory factory){
		this.factory = factory;
	}
	
	public Flight addFlight(Flight flight){
		int flightId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			flightId = (Integer) session.save(flight);
			flight.setId(flightId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}

		return flight;
	}
	
	@SuppressWarnings("unchecked")
	public List<Flight> findAllFlights() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Flight> flights = null;
		try {
			tx = session.beginTransaction();
			flights = session.createQuery("FROM Flight").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		return flights;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public Flight findFlightById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Flight> flights = null;
		try {
			tx = session.beginTransaction();
			flights = session.createQuery("FROM Flight").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}/*
		if(!flights.isEmpty()){
			Flight flight = new Flight();
			flight.setId(id);
			flight.setNumber(number);
			flight.setType(type);
			
			flight.setPassword(flights.toString().split("password=")[1].split(",")[0]);
			flight.setUsername(flights.toString().split("username=")[1].split(",")[0]);
			flight.setRole(flights.toString().split("role=")[1].split(",")[0].split("]")[0]);
				return flight;
			}*/
			return null;
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public List<Flight> deleteFlight(int id){
		Session session = factory.openSession();
		Transaction tx = null;
		//flight = findFlightById(flight.getId());
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE Flight WHERE id = :id");
			query.setParameter("id",id);
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
		return findAllFlights();
	}
	
	public void updateFlight(Flight flight){

		flight.setArrivalDate(new Date());
		flight.setDepartureDate(new Date());
		System.out.println(flight.toString());
		
		Session session = factory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            session.update(flight);
         //   session.saveOrUpdate(flight);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            session.close();
        }

    }
}
