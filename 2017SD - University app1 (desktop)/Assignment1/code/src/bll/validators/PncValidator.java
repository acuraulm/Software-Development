package bll.validators;

import entities.Regular;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class PncValidator implements Validator<Regular> {
	public void validate(Regular r) {

		if (r.getPnc() < 0) {
			throw new IllegalArgumentException("The PNC must be positive value!");
		}

	}

}
