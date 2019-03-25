package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.*;
import model.*;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		Client client = new Client("Client name5", "addr ct5", "client5@email.com",26);

		ClientBLL clientBll = new ClientBLL();
		
		int idC = clientBll.insertClient(client);
		if (idC > 0) {
			clientBll.findClientById(idC);
		}
		
		// Generate error
		try {
			clientBll.findClientById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		
		
		//obtain field-value pairs for object through reflection
		ReflectionExample.retrieveProperties(client);
	}
	
	

}
