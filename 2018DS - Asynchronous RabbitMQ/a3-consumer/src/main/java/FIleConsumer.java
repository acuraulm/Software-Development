import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import business.FileService;
import business.MailService;
import entities.DVD;

public class FIleConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> subscribers = new ArrayList<String>();
		
		subscribers.add("acuraulm@mail.com");
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection;
			connection = factory.newConnection();
			
			FileService fileService = new FileService();
			
			Channel channel = connection.createChannel();

			channel.exchangeDeclare("EXCHANGE_NAME", "fanout");
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, "EXCHANGE_NAME", "");

			System.out.println(" [*] Waiting for messages.");

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				DVD dvd = new Gson().fromJson(message, DVD.class);
				fileService.writeFile(dvd.getTitle(), message);
			};
			
			channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
