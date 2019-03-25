package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dao.OrdersDAO;
import model.Orders;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class OrdersBLL {

	private List<Validator<Orders>> validators;

	public OrdersBLL() {
		validators = new ArrayList<Validator<Orders>>();
	}

	public Orders findOrdersById(int id) {
		Orders st = OrdersDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The orders with id =" + id + " was not found!");
		}
		return st;
	}

	public List<Orders> findAllOrders() throws SQLException{
		return OrdersDAO.findAll();
	}
	
	public void deleteOrders(Orders orders) throws SQLException {
		OrdersDAO.delete(orders);
	}
	
	public void updateOrders(Orders orders) throws SQLException {
		OrdersDAO.update(orders);
	}
	
	public int insertOrders(Orders orders) {
		for (Validator<Orders> v : validators) {
			v.validate(orders);
		}
		return OrdersDAO.insert(orders);
	}
}
