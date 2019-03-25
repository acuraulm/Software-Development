package model;

/* ArrayList used for adding terms to the polynomial, Collections and Comparator used for sorting the terms of the polynomial */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* Class declaration */
public class Polynomial {
	/* Fields */
	private ArrayList<Monomial> terms = new ArrayList<Monomial>();

	/* Constructor, getters and setters for fields */
	public Polynomial(ArrayList<Monomial> terms) {
		terms = new ArrayList<Monomial>();
	}

	public ArrayList<Monomial> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<Monomial> terms) {
		this.terms = terms;
	}

	public void addTerm(Monomial addedTerm) {
		boolean equalDegree = false;
		for (Monomial curMonP : terms)
			if (curMonP.getDegree() == addedTerm.getDegree()) {
				curMonP.setCoefficient(curMonP.getCoefficient() + addedTerm.getCoefficient());
				equalDegree = true;
			}
		if (!equalDegree)
			terms.add(addedTerm);
	}

	public void removeTerm(Monomial rmvTerm) {
		terms.remove(rmvTerm);
	}

	/* Method for printing the polynomial */
	@Override
	public String toString() {
		Collections.sort(terms, new sortTerms());
		String finalPolynomial = "";
		for (Monomial existingTerm : terms)
			finalPolynomial += existingTerm.toString() + " ";
		return finalPolynomial;
	}

	/* Method for sorting the terms of the polynomial */
	public class sortTerms implements Comparator<Monomial> {
		@Override
		public int compare(Monomial m1, Monomial m2) {
			if (m1.getDegree() < m2.getDegree())
				return 1;
			else
				return -1;
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
		Polynomial other = (Polynomial) obj;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}
}
