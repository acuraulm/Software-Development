package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.RegularDAO;
import entities.Regular;


public class RegularBLL {

	private List<Validator<Regular>> validators;

	public RegularBLL() {
		validators = new ArrayList<Validator<Regular>>();
		validators.add(new PncValidator());
	}

	public Regular findRegularById(int id) {
		Regular st = RegularDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The regular with id =" + id + " was not found!");
		}
		return st;
	}
	public Regular findRegularByUsername(String username) {
		Regular st = RegularDAO.findByUsername(username);
		if (st == null) {
			System.out.println("The regular with username =" + username + " was not found!");
		}
		return st;
	}
	public Regular findRegularByName(String username) {
		Regular st = RegularDAO.findByName(username);
		if (st == null) {
			System.out.println("The regular with name =" + username + " was not found!");
		}
		return st;
	}
	public List<Regular> findAllRegulars() throws SQLException{
		return RegularDAO.findAll();
	}

	public void deleteRegular(Regular regular) throws SQLException {
		RegularDAO.delete(regular);
	}
	
	public void updateRegular(Regular regular) throws SQLException {
		for (Validator<Regular> v : validators) {
			v.validate(regular);
		}
		RegularDAO.update(regular);
	}
	
	public int insertRegular(Regular regular) {
		for (Validator<Regular> v : validators) {
			v.validate(regular);
		}
		return RegularDAO.insert(regular);
	}
}
