package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import persistence.dao.CityDAO;
import persistence.dao.FlightDAO;
import persistence.entities.City;
import persistence.entities.Flight;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest  req, 
						 HttpServletResponse resp)
			  throws ServletException, IOException {
		System.out.println("doGet Doing something here");
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest  req,
						  HttpServletResponse resp)
			  throws ServletException, IOException {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		FlightDAO flightDao = new FlightDAO(sf);
		CityDAO cityDao = new CityDAO(sf);
		City city = cityDao.findCityByName(req.getParameter("cityName"));
		

        URL url = new URL("http://www.new.earthtools.org/timezone/" + city.getLatitude() / 10000 + "/" + city.getLongitude() / 10000);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        
        InputStreamReader stream = new InputStreamReader(con.getInputStream());
        
        BufferedReader buff = new BufferedReader(stream);
        String buffLine = "";
        String result = "";
        while((buffLine = buff.readLine()) != null){
            result = result + buffLine;
        }
        stream.close();
        buff.close();
      //  System.out.println(result.split("<localtime>")[1].split("</localtime>")[0]);
      
		List<Flight> flightList = flightDao.findAllFlights();
		List<City> cityList = cityDao.findAllCities();
		req.setAttribute("flightList", flightList);
		req.setAttribute("cityList", cityList);
		req.setAttribute("cityName", city.getName());
		req.setAttribute("localTime", result.split("<localtime>")[1].split("</localtime>")[0]);
		//System.out.println(req.getParameter("cityName"));
		req.getRequestDispatcher("/user.jsp").forward(req, resp);
	//	super.doPost(req, resp);
	}

}
