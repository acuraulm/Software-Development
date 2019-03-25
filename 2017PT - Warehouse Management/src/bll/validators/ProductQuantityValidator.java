package bll.validators;

import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductQuantityValidator implements Validator<Product> {
	private static final int MIN_QUANTITY = 0;

	public void validate(Product p) {

		if (p.getQuantity() < MIN_QUANTITY) {
			throw new IllegalArgumentException("Quantity must be a positive value!");
		}

	}

}
