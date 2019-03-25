package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dao.ProductDAO;
import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductBLL {

	private List<Validator<Product>> validators;

	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductPriceValidator());
		validators.add(new ProductQuantityValidator());
	}

	public Product findProductById(int id) {
		Product st = ProductDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return st;
	}
	public List<Product> findAllProducts() throws SQLException{
		return ProductDAO.findAll();
	}
	public void deleteProduct(Product product) throws SQLException {
		ProductDAO.delete(product);
	}
	public void decreaseQuantity(Product product, int quant) throws SQLException{
		ProductDAO.decrease(product, quant);
	}
	public void updateProduct(Product product) throws SQLException {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		ProductDAO.update(product);
	}
	
	public int insertProduct(Product product) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		return ProductDAO.insert(product);
	}
}
