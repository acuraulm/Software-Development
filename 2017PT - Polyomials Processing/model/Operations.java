package model;

/* ArrayList used for adding terms to the polynomial */
import java.util.ArrayList;

/* Class declaration */
public class Operations {
	/* Empty constructor, for calling the methods described below */
	public Operations() {
	}

	/* Operations on polynomials */
	public static Polynomial addPolynomials(Polynomial P1, Polynomial P2) {
		Polynomial P2A = new Polynomial(null);
		ArrayList<Monomial> termsP2A = new ArrayList<Monomial>();
		for (Monomial thisP2 : P2.getTerms())
			termsP2A.add(new Monomial(thisP2.getDegree(), thisP2.getCoefficient()));
		P2A.setTerms(termsP2A);
		Polynomial P1A = new Polynomial(null);
		ArrayList<Monomial> termsP1A = new ArrayList<Monomial>();
		for (Monomial thisP1 : P1.getTerms())
			termsP1A.add(new Monomial(thisP1.getDegree(), thisP1.getCoefficient()));
		P1A.setTerms(termsP1A);

		Polynomial PAux = new Polynomial(null);
		ArrayList<Monomial> termsPAux = new ArrayList<Monomial>();
		termsPAux.addAll(P1A.getTerms());
		termsPAux.addAll(P2A.getTerms());
		for (Monomial thisMonP1 : P1A.getTerms())
			for (Monomial thisMonP2 : P2A.getTerms()) {
				if (thisMonP1.getDegree() == thisMonP2.getDegree()) {
					termsPAux.remove(thisMonP1);
					termsPAux.remove(thisMonP2);
					if (thisMonP1.getCoefficient() + thisMonP2.getCoefficient() != 0)
						termsPAux.add(new Monomial(thisMonP1.getDegree(),
								thisMonP1.getCoefficient() + thisMonP2.getCoefficient()));
				}
			}
		PAux.setTerms(termsPAux);
		return PAux;
	}

	public static Polynomial subPolynomials(Polynomial P1, Polynomial P2) {
		Polynomial PAux = new Polynomial(null);
		ArrayList<Monomial> termsPAux = new ArrayList<Monomial>();
		termsPAux.addAll(P1.getTerms());
		Polynomial PNeg = new Polynomial(null);
		ArrayList<Monomial> termsPNeg = new ArrayList<Monomial>();
		for (Monomial thisP2 : P2.getTerms())
			termsPNeg.add(new Monomial(thisP2.getDegree(), 0 - thisP2.getCoefficient()));
		PNeg.setTerms(termsPNeg);
		termsPAux.addAll(PNeg.getTerms());
		for (Monomial thisMonP1 : P1.getTerms())
			for (Monomial thisMonP2 : PNeg.getTerms()) {
				if (thisMonP1.getDegree() == thisMonP2.getDegree()) {
					termsPAux.remove(thisMonP1);
					termsPAux.remove(thisMonP2);
					if (thisMonP1.getCoefficient() + thisMonP2.getCoefficient() != 0)
						termsPAux.add(new Monomial(thisMonP1.getDegree(),
								thisMonP1.getCoefficient() + thisMonP2.getCoefficient()));
				}
			}
		PAux.setTerms(termsPAux);
		return PAux;
	}

	public static Polynomial mulPolynomials(Polynomial P1, Polynomial P2) {
		Polynomial PAux = new Polynomial(null);
		ArrayList<Monomial> termsPAux = new ArrayList<Monomial>();
		Polynomial PAux2 = new Polynomial(null);
		for (Monomial thisMonP1 : P1.getTerms()) {
			termsPAux.clear();
			for (Monomial thisMonP2 : P2.getTerms()) {
				if ((thisMonP1.getCoefficient() * thisMonP2.getCoefficient()) != 0)
					termsPAux.add(new Monomial(thisMonP1.getDegree() + thisMonP2.getDegree(),
							thisMonP1.getCoefficient() * thisMonP2.getCoefficient()));
			}
			PAux.setTerms(termsPAux);
			PAux2 = Operations.addPolynomials(PAux, PAux2);
		}
		return PAux2;
	}

	public static ArrayList<Polynomial> divPolynomials(Polynomial P1, Polynomial P2) {
		ArrayList<Polynomial> result = new ArrayList<Polynomial>();
		Polynomial Q = new Polynomial(null);
		Polynomial R = new Polynomial(null);
		Polynomial Aux = new Polynomial(null);
		ArrayList<Monomial> Auxterms = new ArrayList<Monomial>();
		R.setTerms(P1.getTerms());
		while (maxMonomial(R).getDegree() >= maxMonomial(P2).getDegree()) {
			Auxterms.clear();
			Auxterms.add(new Monomial(maxMonomial(R).getDegree() - maxMonomial(P2).getDegree(),
					maxMonomial(R).getCoefficient() / maxMonomial(P2).getCoefficient()));
			Aux.setTerms(Auxterms);
			Q = Operations.addPolynomials(Q, Aux);
			Aux = Operations.mulPolynomials(Aux, P2);
			R = Operations.subPolynomials(R, Aux);
		}
		result.add(Q);
		if (maxMonomial(R).getCoefficient() != 0)
			result.add(R);
		return result;
	}

	public static Polynomial differentiatePolynomial(Polynomial P1) {
		Polynomial PAux = new Polynomial(null);
		ArrayList<Monomial> termsPAux = new ArrayList<Monomial>();

		for (Monomial thisP : P1.getTerms()) {
			if (thisP.getCoefficient() * thisP.getDegree() != 0)
				termsPAux.add(new Monomial(thisP.getDegree() - 1, thisP.getCoefficient() * thisP.getDegree()));
		}
		PAux.setTerms(termsPAux);
		return PAux;
	}

	public static Polynomial integratePolynomial(Polynomial P1) {
		Polynomial PAux = new Polynomial(null);
		ArrayList<Monomial> termsPAux = new ArrayList<Monomial>();
		for (Monomial thisP : P1.getTerms()) {
			if ((thisP.getCoefficient() / (thisP.getDegree() + 1)) != 0)
				termsPAux.add(new Monomial(thisP.getDegree() + 1, thisP.getCoefficient() / (thisP.getDegree() + 1)));
		}
		PAux.setTerms(termsPAux);
		return PAux;
	}

	public static String computePolynomial(Polynomial P1, Double x) {
		double value = 0.0;
		for (Monomial thisP : P1.getTerms())
			value += thisP.getCoefficient() * (Math.pow(x, thisP.getDegree()));
		String str = "" + value;
		if (value < Double.MAX_VALUE) {
			if (str.substring(0, str.length()).contains(".0")) {
				str = str.replace(".0", "");
				return str;
			} else
				return "~ " + String.format("%.2f", value);
		} else
			return "Value too high";

	}

	/*
	 * Returns the monomial with the maximum degree (the degree of the
	 * polynomial)
	 */
	public static Monomial maxMonomial(Polynomial P) {
		ArrayList<Monomial> terms = P.getTerms();
		return terms.get(0);
	}
}