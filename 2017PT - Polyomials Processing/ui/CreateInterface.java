package ui;

/* Interface-related imports */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*Imports the classes used*/
import model.*;

/* Class declaration,extending JFrame used for creating the interface */
public class CreateInterface extends JFrame {
	public static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	/* Labels */
	private JLabel labelPolynomial1 = new JLabel("X: ");
	private JLabel labelValueX = new JLabel("Value: ");
	private JLabel labelValueY = new JLabel("Value: ");
	private JLabel labelPolynomial2 = new JLabel("Y: ");
	private JLabel labelResult = new JLabel("Result: ");
	private JLabel labelRules = new JLabel("Input polynomials should always start with a sign ( +ax^n or -ax^n ). No duplicates!");
	/* Text fields */
	private JTextField textPolynomial1 = new JTextField("+17x^2 + 3x");
	private JTextField textPolynomial2 = new JTextField("+1x + 1");
	private JTextField textResult = new JTextField("");
	private JTextField textValueX = new JTextField("0.0");
	private JTextField textValueY = new JTextField("0.0");
	/* Buttons */
	private JButton buttonComputeX = new JButton("X(Value)");
	private JButton buttonComputeY = new JButton("Y(Value)");
	private JButton buttonAdd = new JButton("Add");
	private JButton buttonSubtract = new JButton("Subtract");
	private JButton buttonMultiply = new JButton("Multiply");
	private JButton buttonDivide = new JButton("Divide");
	private JButton buttonDifferentiateX = new JButton("Differentiate X");
	private JButton buttonDifferentiateY = new JButton("Differentiate Y");
	private JButton buttonIntegrateX = new JButton("Integrate X");
	private JButton buttonIntegrateY = new JButton("Integrate Y");
	/* Objects */
	Polynomial X = new Polynomial(null);
	Polynomial Y = new Polynomial(null);

	/* Interface creator Constructor */
	public CreateInterface() {
		interfaceProperties();
		initComponents();
		addComponents();
		addListeners();
	}

	/* Initialization of components */
	private void initComponents() {
		// setBounds(x, y, width, height); /* Components dimension */
		// .setHorizontalAlignment(SwingConstants.RIGHT/CENTER/LEFT); /* Text
		// alignment */
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.BLACK);
		labelPolynomial1.setBounds(30, 50, 35, 35);
		labelPolynomial2.setBounds(30, 100, 35, 35);
		labelResult.setBounds(50, 235, 100, 35);
		labelValueX.setBounds(485, 50, 100, 35);
		labelValueY.setBounds(485, 100, 100, 35);
		/*
		 * Choosing the font and color for the instructions of writing the
		 * polynomials
		 */
		labelRules.setBounds(20, 0, 640, 35);
		labelRules.setForeground(Color.RED);
		labelRules.setFont(new Font("Arial", Font.PLAIN, 17));

		labelPolynomial1.setHorizontalAlignment(SwingConstants.RIGHT);	
		labelPolynomial2.setHorizontalAlignment(SwingConstants.RIGHT);
		labelResult.setHorizontalAlignment(SwingConstants.LEFT);
		labelValueY.setForeground(Color.WHITE);
		labelValueX.setForeground(Color.WHITE);
		labelResult.setForeground(Color.WHITE);
		labelPolynomial1.setForeground(Color.WHITE);
		labelPolynomial2.setForeground(Color.WHITE);
		textPolynomial1.setForeground(Color.WHITE);
		textPolynomial2.setForeground(Color.WHITE);
		textResult.setForeground(Color.WHITE);
		textValueX.setForeground(Color.WHITE);
		textValueY.setForeground(Color.WHITE);

		textPolynomial1.setForeground(Color.WHITE);
		textPolynomial1.setBackground(Color.BLACK);
		textPolynomial2.setForeground(Color.WHITE);
		textPolynomial2.setBackground(Color.BLACK);
		textResult.setForeground(Color.WHITE);
		textResult.setBackground(Color.BLACK);
		textValueX.setForeground(Color.WHITE);
		textValueX.setBackground(Color.BLACK);
		textValueY.setForeground(Color.WHITE);
		textValueY.setBackground(Color.BLACK);

		buttonAdd.setForeground(Color.WHITE);
		buttonSubtract.setForeground(Color.WHITE);
		buttonMultiply.setForeground(Color.WHITE);
		buttonDivide.setForeground(Color.WHITE);
		buttonDifferentiateX.setForeground(Color.WHITE);
		buttonDifferentiateY.setForeground(Color.WHITE);
		buttonIntegrateX.setForeground(Color.WHITE);
		buttonIntegrateY.setForeground(Color.WHITE);
		buttonComputeX.setForeground(Color.WHITE);
		buttonComputeY.setForeground(Color.WHITE);
		buttonAdd.setBackground(Color.BLACK);
		buttonSubtract.setBackground(Color.BLACK);
		buttonMultiply.setBackground(Color.BLACK);
		buttonDivide.setBackground(Color.BLACK);
		buttonDifferentiateX.setBackground(Color.BLACK);
		buttonDifferentiateY.setBackground(Color.BLACK);
		buttonIntegrateX.setBackground(Color.BLACK);
		buttonIntegrateY.setBackground(Color.BLACK);
		buttonComputeX.setBackground(Color.BLACK);
		buttonComputeY.setBackground(Color.BLACK);

		textPolynomial1.setBounds(95, 50, 385, 35);
		textPolynomial2.setBounds(95, 100, 385, 35);
		textResult.setBounds(95, 235, 480, 35);
		textValueX.setBounds(525, 50, 50, 35);
		textValueY.setBounds(525, 100, 50, 35);

		textPolynomial1.setHorizontalAlignment(SwingConstants.CENTER);
		textPolynomial2.setHorizontalAlignment(SwingConstants.CENTER);
		textResult.setHorizontalAlignment(SwingConstants.CENTER);
		textValueX.setHorizontalAlignment(SwingConstants.CENTER);
		textValueY.setHorizontalAlignment(SwingConstants.CENTER);

		buttonAdd.setBounds(50, 150, 100, 35);
		buttonSubtract.setBounds(50, 186, 100, 35);
		buttonMultiply.setBounds(152, 150, 100, 35);
		buttonDivide.setBounds(152, 186, 100, 35);
		buttonDifferentiateX.setBounds(253, 150, 120, 35);
		buttonDifferentiateY.setBounds(253, 186, 120, 35);
		buttonIntegrateX.setBounds(374, 150, 100, 35);
		buttonIntegrateY.setBounds(374, 186, 100, 35);
		buttonComputeX.setBounds(475, 150, 100, 35);
		buttonComputeY.setBounds(475, 186, 100, 35);
	}

	/* Adding the components to the mainPanel, and to the JFrame itself */
	private void addComponents() {
		// .add(Component) /* Add the component to panel */
		mainPanel.add(labelPolynomial1);
		mainPanel.add(labelPolynomial2);
		mainPanel.add(labelResult);
		mainPanel.add(labelRules);
		mainPanel.add(labelValueX);
		mainPanel.add(labelValueY);

		mainPanel.add(textPolynomial1);
		mainPanel.add(textPolynomial2);
		mainPanel.add(textResult);
		mainPanel.add(textValueX);
		mainPanel.add(textValueY);

		mainPanel.add(buttonAdd);
		mainPanel.add(buttonSubtract);
		mainPanel.add(buttonMultiply);
		mainPanel.add(buttonDivide);
		mainPanel.add(buttonDifferentiateX);
		mainPanel.add(buttonDifferentiateY);
		mainPanel.add(buttonIntegrateX);
		mainPanel.add(buttonIntegrateY);
		mainPanel.add(buttonComputeX);
		mainPanel.add(buttonComputeY);
	}

	/* Properties of the JFrame */
	private void interfaceProperties() {
		this.setLocation(650, 250);
		this.setSize(640, 340);
		this.setTitle("Polynomials Operations");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(mainPanel);
	}

	/* Setting MouseListeners for buttons */
	private void addListeners() {
		textValueX.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				textValueX.setText(null);
			}
		});
		textValueY.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				textValueY.setText(null);
			}
		});
		textPolynomial1.addKeyListener( new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				 if(e.getKeyCode()== KeyEvent.VK_ESCAPE)
					 textPolynomial1.setText(null);
			}
		});
		textPolynomial2.addKeyListener( new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				 if(e.getKeyCode()== KeyEvent.VK_ESCAPE)
					 textPolynomial2.setText(null);
			}
		});
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty() && Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.addPolynomials(X, Y).toString());
				System.out.println("Addition: " + Operations.addPolynomials(X, Y).toString());
				}
			}
		});
		buttonSubtract.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty() && Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.subPolynomials(X, Y).toString());
				System.out.println("Subtraction: " + Operations.subPolynomials(X, Y).toString());
				}
			}
		});
		buttonMultiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty() || Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.mulPolynomials(X, Y).toString());
				System.out.println("Multiplication: " + Operations.mulPolynomials(X, Y).toString());
				}
			}
		});
		buttonDivide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if (Y.getTerms().isEmpty())
					textResult.setText("You cannot divide by 0");
				else if(X.getTerms().isEmpty())
					textResult.setText("0");
				else{
					textResult.setText(Operations.divPolynomials(X, Y).get(0) + " Remainder: "
							+ Operations.divPolynomials(X, Y).get(1));
					System.out.println("Division: " + Operations.divPolynomials(X, Y).get(0) + " Remainder: "
							+ Operations.divPolynomials(X, Y).get(1));
				}
			}
		});
		buttonDifferentiateX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.differentiatePolynomial(X).toString());
				System.out.println("Differentiation of X: " + Operations.differentiatePolynomial(X).toString());
				}
				}
		});
		buttonDifferentiateY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.differentiatePolynomial(Y).toString());
				System.out.println("Differentiation of Y: " + Operations.differentiatePolynomial(Y).toString());
			}}
		});
		buttonIntegrateX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.integratePolynomial(X).toString());
				System.out.println("Integration of X: " + Operations.integratePolynomial(X).toString());
			}}
		});
		buttonIntegrateY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				textResult.setText(Operations.integratePolynomial(Y).toString());
				System.out.println("Integration of Y: " + Operations.integratePolynomial(Y).toString());
				}
			}
		});
		buttonComputeX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(X.getTerms().isEmpty())
					textResult.setText("0");
				else{
				if(textValueX.getText().isEmpty())
					textResult.setText("Please enter a value!");
				else{
				Double Value = Double.parseDouble(textValueX.getText());
				textResult.setText(Operations.computePolynomial(X, Value));
				System.out.println("X(value): " + Operations.computePolynomial(X, Value));
				}}
			}
		});
		buttonComputeY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitPolynomials();
				if(Y.getTerms().isEmpty())
					textResult.setText("0");
				else{
				if(textValueY.getText().isEmpty())
					textResult.setText("Please enter a value!");
				else{
				Double Value = Double.parseDouble(textValueY.getText());
				textResult.setText(Operations.computePolynomial(Y, Value));
				System.out.println("Y(value): " + Operations.computePolynomial(Y, Value));
				}
				}
			}
		});
	}

	/*
	 * Void method for reading the strings and setting the values of the
	 * polynomials
	 */
	private void submitPolynomials() {
		X = StringModifier.readString(textPolynomial1.getText());
		System.out.println("X: " + X.toString());
		Y = StringModifier.readString(textPolynomial2.getText());
		System.out.println("Y: " + Y.toString());
	}

	/* Creation of the interface, main method */
	public static void main(String[] args) {
		new CreateInterface();

	}
}