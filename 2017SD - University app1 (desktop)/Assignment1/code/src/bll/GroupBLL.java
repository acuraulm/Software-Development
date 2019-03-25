package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.GroupDAO;
import entities.Group;


public class GroupBLL {

	private List<Validator<Group>> validators;

	public GroupBLL() {
		validators = new ArrayList<Validator<Group>>();
	//	validators.add(new EmailValidatorGroup());
	//	validators.add(new GroupAgeValidator());
	}

	public Group findGroupById(int id) {
		Group st = GroupDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The group with id =" + id + " was not found!");
		}
		return st;
	}
	public List<Group> findAllGroups() throws SQLException{
		return GroupDAO.findAll();
	}

	public void deleteGroup(Group group) throws SQLException {
		GroupDAO.delete(group);
	}
	
	public void updateGroup(Group group) throws SQLException {
		for (Validator<Group> v : validators) {
			v.validate(group);
		}
		GroupDAO.update(group);
	}
	
	public int insertGroup(Group group) {
		for (Validator<Group> v : validators) {
			v.validate(group);
		}
		return GroupDAO.insert(group);
	}
}
