
import javax.xml.ws.Endpoint;

import services.ClientOperations;
import services.LoginAndRegister; 
@SuppressWarnings("restriction")
public class ClientSOAP {
	
	public static void main(String[] args) {  
	       Endpoint.publish("http://localhost:7779/ws/LoginAndRegister", new LoginAndRegister());  
	       Endpoint.publish("http://localhost:7779/ws/ClientOperations", new ClientOperations());  
	        }  

}
