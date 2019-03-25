package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dao.ClientDAO;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBLL {

	private List<Validator<Client>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidatorClient());
		validators.add(new ClientAgeValidator());
	}

	public Client findClientById(int id) {
		Client st = ClientDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}
	public List<Client> findAllClients() throws SQLException{
		return ClientDAO.findAll();
	}

	public void deleteClient(Client client) throws SQLException {
		ClientDAO.delete(client);
	}
	
	public void updateClient(Client client) throws SQLException {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		ClientDAO.update(client);
	}
	
	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return ClientDAO.insert(client);
	}
}
