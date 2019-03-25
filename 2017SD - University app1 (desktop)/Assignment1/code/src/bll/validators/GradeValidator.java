package bll.validators;

import entities.Grade;

public class GradeValidator implements Validator<Grade> {
	private static final int MIN_GRADE = 1;
	private static final int MAX_GRADE = 10;

	public void validate(Grade g) {

		if (g.getValue() < MIN_GRADE || g.getValue() > MAX_GRADE) {
			throw new IllegalArgumentException("The grade limit is not respected!");
		}

	}

}
