package business;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import persistence.dao.AppUserDAO;
import persistence.dao.CityDAO;
import persistence.dao.FlightDAO;
import persistence.entities.AppUser;
import persistence.entities.Flight;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest  req, 
						 HttpServletResponse resp)
			  throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	System.out.println("IndexServlet doGet");
		System.out.println(req.getServletPath());
		System.out.println("Recieved parameters: " 
				+ "username: " + req.getParameter("username")
				+ "password: " + req.getParameter("password"));
		System.out.println("Session: " + req.getSession().getAttribute("role"));
	*/	
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest  req,
						  HttpServletResponse resp)
			  throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IndexServlet doPost");
	/*	
		System.out.println(req.getServletPath());
		System.out.println("Recieved parameters: " 
				+ "username: " + req.getParameter("username")
				+ "password: " + req.getParameter("password"));
		System.out.println("Session: " + req.getSession().getAttribute("role"));*/
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		AppUserDAO appUserDao = new AppUserDAO(sf);	
		AppUser appUser = appUserDao.findAppUserByUsername(req.getParameter("username"));
		if(appUser != null){
			if(appUser.getPassword().equals(req.getParameter("password"))){
				//	System.out.println("Authentication succesfully!");
					FlightDAO flightDao = new FlightDAO(sf);
					CityDAO cityDao = new CityDAO(sf);
					List<Flight> flightList = flightDao.findAllFlights();
					req.setAttribute("flightList", flightList);
					req.setAttribute("appUser", appUser);
					req.getSession().setAttribute("role", appUser.getRole());
					
					if(appUser.getRole().equals("admin")){
						req.getRequestDispatcher("/admin.jsp").forward(req, resp);
					}else{
						req.setAttribute("cityList", cityDao.findAllCities());
						req.getRequestDispatcher("/user.jsp").forward(req, resp);
					}
			}
			else{
				req.getSession().setAttribute("role", "");
				resp.sendRedirect("/a12/");
			}
		}else{
			req.getSession().setAttribute("role", "");
			resp.sendRedirect("/a12/");
		}

	}

}
