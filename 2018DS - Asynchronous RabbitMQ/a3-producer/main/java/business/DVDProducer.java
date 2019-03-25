package business;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import entities.DVD;

public class DVDProducer {

	public void publishDVD(DVD dvd){
	 ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = factory.newConnection();
	         Channel channel = connection.createChannel()) {
	        channel.exchangeDeclare("EXCHANGE_NAME", "fanout");

	        String message = new Gson().toJson(dvd);
	        
	        channel.basicPublish("EXCHANGE_NAME", "", null, message.getBytes("UTF-8"));
	        System.out.println(" [x] Sent '" + message + "'");
	    } catch (TimeoutException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
