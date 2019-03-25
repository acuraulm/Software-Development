package business;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import entities.DVD;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest  req, 
						 HttpServletResponse resp)
			  throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IndexServlet doGet");
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest  req,
						  HttpServletResponse resp)
			  throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IndexServlet doPost");
		
		DVDProducer producer = new DVDProducer();
		DVD dvd= new DVD(req.getParameter("Title"), Integer.parseInt(req.getParameter("Year")), Double.parseDouble(req.getParameter("Price")));
		producer.publishDVD(dvd);
		
		    resp.sendRedirect("/assignment-three-two/");

	}

}
