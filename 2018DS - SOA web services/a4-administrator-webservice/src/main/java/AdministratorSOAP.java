import javax.xml.ws.Endpoint;

import services.AdministratorOperations;



@SuppressWarnings("restriction")
public class AdministratorSOAP {

	 public static void main(String[] args) {  
	       Endpoint.publish("http://localhost:7778/ws/AdministratorOperations", new AdministratorOperations());  
	        }  
}
