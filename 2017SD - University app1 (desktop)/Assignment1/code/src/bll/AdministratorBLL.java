package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.AdministratorDAO;
import entities.Administrator;

public class AdministratorBLL {

	private List<Validator<Administrator>> validators;

	public AdministratorBLL() {
		validators = new ArrayList<Validator<Administrator>>();
	//	validators.add(new EmailValidatorAdministrator());
	//	validators.add(new AdministratorAgeValidator());
	}

	public Administrator findAdministratorById(int id) {
		Administrator st = AdministratorDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The administrator with id =" + id + " was not found!");
		}
		return st;
	}
	public Administrator findAdministratorByUsername(String username) {
		Administrator st = AdministratorDAO.findByUsername(username);
		if (st == null) {
			System.out.println("The administrator with username =" + username + " was not found!");
		}
		return st;
	}
	public List<Administrator> findAllAdministrators() throws SQLException{
		return AdministratorDAO.findAll();
	}
	
	public void updateAdministrator(Administrator administrator) throws SQLException {
		for (Validator<Administrator> v : validators) {
			v.validate(administrator);
		}
		AdministratorDAO.update(administrator);
	}
	
	public int insertAdministrator(Administrator administrator) {
		for (Validator<Administrator> v : validators) {
			v.validate(administrator);
		}
		return AdministratorDAO.insert(administrator);
	}
}
