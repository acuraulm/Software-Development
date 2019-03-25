package model;

/* Class declaration */
public class Monomial {
	/* Fields */
	private int degree;
	private double coefficient;

	/* Constructor, getters and setters for fields */
	public Monomial(int degree, double coefficient) {
		this.degree = degree;
		this.coefficient = coefficient;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/* Method that verifies if the coefficient is an int or a double */
	public boolean isInt(double coefficient) {
		String str = "" + coefficient;
		if (str.substring(0, str.length()).contains(".0"))
			return true;
		else
			return false;
	}

	/* Method for printing the monomial */
	@Override
	public String toString() {
		if (isInt(coefficient)) {
			if (coefficient != 0) {
				if (degree == 0)
					if (coefficient > 0)
						return "+ " + (int) coefficient;
					else
						return "- " + (int) coefficient * (-1);
				else if (degree == 1)
					if (coefficient > 0)
						return "+ " + (int) coefficient + "x";
					else
						return "- " + (int) coefficient * (-1) + "x";
				else if (coefficient > 0)
					return "+ " + (int) coefficient + "x^" + degree;
				else
					return "- " + (int) coefficient * (-1) + "x^" + degree;
			} else
				return "";
		} else {
			if (coefficient != 0) {
				if (degree == 0)
					if (coefficient > 0)
						return "+ " + coefficient;
					else
						return "- " + coefficient * (-1);
				else if (degree == 1)
					if (coefficient > 0)
						return "+ " + coefficient + "x";
					else
						return "- " + coefficient * (-1) + "x";
				else if (coefficient > 0)
					return "+ " + String.format("%.1f", coefficient) + "x^" + degree;
				else
					return "- " + String.format("%.1f", coefficient * (-1)) + "x^" + degree;
			} else
				return "";
		}
	}

	/* Overriding equals method, for JUnit testing */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monomial other = (Monomial) obj;
		if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
			return false;
		if (degree != other.degree)
			return false;
		return true;
	}

}