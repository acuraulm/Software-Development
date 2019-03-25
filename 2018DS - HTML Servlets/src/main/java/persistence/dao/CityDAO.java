package persistence.dao;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import persistence.entities.City;
import persistence.entities.City;

public class CityDAO extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(CityDAO.class);
	private SessionFactory factory;
	
	public CityDAO(SessionFactory factory){
		this.factory = factory;
	}
	
	public City addCity(City city){
		int cityId = -1;
		
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cityId = (Integer) session.save(city);
			city.setId(cityId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}

		return city;
	}
	
	@SuppressWarnings("unchecked")
	public List<City> findAllCities() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<City> cities = null;
		try {
			tx = session.beginTransaction();
			cities = session.createQuery("FROM City").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		return cities;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public City findCityById(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<City> cities = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM City WHERE id = :id");
			query.setParameter("id", id);
			cities = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		return cities != null && !cities.isEmpty() ? cities.get(0) : null;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public City findCityByName(String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<City> cities = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM City WHERE name = :name");
			query.setParameter("name", name);
			cities = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("", e);
		} finally {
			session.close();
		}
		if(!cities.isEmpty()){
			City city = new City();
			city.setId(Integer.parseInt(cities.toString().split("id=")[1].split(",")[0]));
			city.setName(cities.toString().split("name=")[1].split(",")[0]);
			city.setLatitude(Double.parseDouble(cities.toString().split("latitude=")[1].split(",")[0]));
			city.setLongitude(Double.parseDouble(cities.toString().split("longitude=")[1].split(",")[0].split("]")[0]));
				return city;
			}
			return null;
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public City deleteCity(City city){
		Session session = factory.openSession();
		Transaction tx = null;
		city = findCityById(city.getId());
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE City WHERE id = :id");
			query.setParameter("id",city.getId());
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
		return city;
	}
}
