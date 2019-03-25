package logic;

/* JUnit related imports */
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.*;

/* Class definition */
public class PolynomialTest {
	/* Objects declaration */
	private Polynomial P1 = new Polynomial(null);
	private Polynomial P2 = new Polynomial(null);
	private Polynomial PRes = new Polynomial(null);
	private Polynomial PExp = new Polynomial(null);

	/* Using the same Polynomials for every operation test */
	@Before
	public void init() {
		P1 = StringModifier.readString("+17x^2 + 3x");
		P2 = StringModifier.readString("+1x + 1");
	}

	/*
	 * Void method for printing the Expected result and the Result of the
	 * operation
	 */
	public void printResults() {
		System.out.println("Result: " + PRes.toString() + " at " + PRes.hashCode());
		System.out.println("Expected result: " + PExp.toString() + " at " + PExp.hashCode());
		System.out.println();
	}

	/* Test methods of operations */
	@Test
	public void testAdd() {
		System.out.print("Addition ");
		init();
		PRes = Operations.addPolynomials(P1, P2);
		PExp = StringModifier.readString("+ 17x^2 + 4x + 1 ");
		printResults();
		assertEquals(PRes, PExp);
	}

	@Test
	public void testSub() {
		System.out.print("Subtraction ");
		init();
		PRes = Operations.subPolynomials(P1, P2);
		PExp = StringModifier.readString("+ 17x^2 + 2x - 1 ");
		printResults();
	}

	@Test
	public void testMul() {
		System.out.print("Multiplication ");
		init();
		PRes = Operations.mulPolynomials(P1, P2);
		PExp = StringModifier.readString("+ 17x^3 + 20x^2 + 3x ");
		printResults();
		assertEquals(PRes, PExp);
	}

	@Test
	public void testDiv() {
		System.out.print("Division ");
		ArrayList<Polynomial> PRes = Operations.divPolynomials(P1, P2);
		ArrayList<Polynomial> PExp = new ArrayList<Polynomial>();
		PExp.add(StringModifier.readString("+17x - 14"));
		PExp.add(StringModifier.readString("+14"));
		System.out.println("Result: " + PRes.get(0) + " Remainder: " + PRes.get(1) + " at " + PRes.hashCode());
		System.out.println("Expected result: " + PExp.get(0) + " Remainder: " + PExp.get(1) + " at " + PExp.hashCode());
		System.out.println();
		assertEquals(PRes, PExp);
	}

	@Test
	public void testDif() {
		System.out.print("Differentiation ");
		init();
		PRes = Operations.differentiatePolynomial(P1);
		PExp = StringModifier.readString("+ 34x + 3 ");
		printResults();
		assertEquals(PRes, PExp);
	}

	@Test
	public void testInt() {
		System.out.print("Integration ");
		init();
		PRes = Operations.integratePolynomial(P2);
		PExp = StringModifier.readString("+ 0.5x^2 + 1x ");
		printResults();
		assertEquals(PRes, PExp);
	}
}
