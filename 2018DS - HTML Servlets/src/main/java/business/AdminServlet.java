package business;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import persistence.dao.CityDAO;
import persistence.dao.FlightDAO;
import persistence.entities.Flight;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest  req, 
						 HttpServletResponse resp)
			  throws ServletException, IOException {
		
		
		System.out.println("Session:" + req.getSession().getAttribute("role"));
		
		if(req.getSession().getAttribute("role") != null){
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			FlightDAO flightDao = new FlightDAO(sf);
			List<Flight> flightList = flightDao.findAllFlights();
			req.setAttribute("flightList", flightList);
			switch(req.getSession().getAttribute("role").toString()){
			case "admin":
				req.getRequestDispatcher("/admin.jsp").forward(req, resp);
				break;
			case "appuser":
				req.getRequestDispatcher("/user.jsp").forward(req, resp);
				break;
			default:
				resp.sendRedirect("/a12/");
				break;
			}
		}
		else
			resp.sendRedirect("/a12/");
		
	}

	@Override
	protected void doPost(HttpServletRequest  req,
						  HttpServletResponse resp)
			  throws ServletException, IOException {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		FlightDAO flightDao = new FlightDAO(sf);
		CityDAO cityDao = new CityDAO(sf);
		Flight flight = new Flight();
		
		if(req.getSession().getAttribute("role").equals("admin")){
			
			switch(req.getParameter("action")){
			case "Delete":	
		//			System.out.println("doGet: id= " + req.getParameter("id") + " number: " + req.getParameter("number") + " type: " + req.getParameter("type"));
					flightDao.deleteFlight(Integer.parseInt(req.getParameter("id")));
				
					
					
				break;
			case "Edit":

				cityDao = new CityDAO(sf);
				flight = new Flight();
				flight.setId(Integer.parseInt(req.getParameter("id")) );
				flight.setNumber(Integer.parseInt(req.getParameter("number")) );
				flight.setType(req.getParameter("type"));
				flight.setDepartureCity(cityDao.findCityByName(req.getParameter("departureCity")));
				flight.setArrivalCity(cityDao.findCityByName(req.getParameter("arrivalCity")));
				
					SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					flight.setArrivalDate(new Date());
					flight.setDepartureDate(new Date());
					//flight.setArrivalDate(parser.parse(req.getParameter("arrivalDate")));
				//	flight.setDepartureDate(parser.parse(req.getParameter("departureDate")));		
				flightDao.updateFlight(flight);
				
				
				break;
			case "Add":
/*
				System.out.println("doGet: id= " + req.getParameter("id") 
							+ " number: " 		 + req.getParameter("number") 
							+ " type: " 		 + req.getParameter("type")
							+ " departureCity: " + req.getParameter("departureCity")
							+ " departureDate: " + req.getParameter("departureDate")
							+ " arrivalCity: "   + req.getParameter("arrivalCity")
							+ " arrivalDate: "   + req.getParameter("arrivalDate")
				);*/
				
				cityDao = new CityDAO(sf);
				flight = new Flight();
				flight.setId(1);
				flight.setNumber(Integer.parseInt(req.getParameter("number")) );
				flight.setType(req.getParameter("type"));
				
				
					parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					flight.setArrivalDate(new Date());
					flight.setDepartureDate(new Date());
				try {
					flight.setDepartureCity(cityDao.findCityByName(req.getParameter("departureCity")));
					flight.setArrivalCity(cityDao.findCityByName(req.getParameter("arrivalCity")));
					flight.setArrivalDate(parser.parse(req.getParameter("arrivalDate")));
					flight.setDepartureDate(parser.parse(req.getParameter("departureDate")));		
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				flightDao.addFlight(flight);
				
			}
		}else
		{
		//	System.out.println("You do not have rights");
			resp.sendRedirect("/a12/");
		}
		
	//	System.out.println("Session:" + req.getSession().getAttribute("role"));
		 
		List<Flight> flightList = flightDao.findAllFlights();
		req.setAttribute("flightList", flightList);
		//req.setAttribute("appUser", appUser);
		//req.getSession().setAttribute("role", appUser.getRole());
		req.getRequestDispatcher("/admin.jsp").forward(req, resp);
	}

}
