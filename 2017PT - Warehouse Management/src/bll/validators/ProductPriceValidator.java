package bll.validators;

import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductPriceValidator implements Validator<Product> {
	public void validate(Product p) {

		if (p.getPrice() < 0) {
			throw new IllegalArgumentException("The Product Price must be positive value!");
		}

	}

}
