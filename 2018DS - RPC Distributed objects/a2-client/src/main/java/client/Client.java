package client;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.Car;
import serviceInterfaces.IPriceService;
import serviceInterfaces.ITaxService;

public class Client {

	private JFrame frame;
	private JTextField txtYear;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JLabel lblTax;
	private JLabel lblSellingPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 266, 206);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblYear = new JLabel("Fabrication Year");
		lblYear.setHorizontalAlignment(SwingConstants.TRAILING);
		lblYear.setBounds(21, 11, 107, 14);
		frame.getContentPane().add(lblYear);
		
		JLabel lblSize = new JLabel("Engine Size");
		lblSize.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSize.setBounds(21, 36, 107, 14);
		frame.getContentPane().add(lblSize);
		
		JLabel lblPrice = new JLabel("Purchasing Price");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setBounds(21, 61, 107, 14);
		frame.getContentPane().add(lblPrice);
		
		txtYear = new JTextField("2012");
		txtYear.setBounds(138, 8, 86, 20);
		frame.getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		txtSize = new JTextField("1900");
		txtSize.setBounds(138, 33, 86, 20);
		frame.getContentPane().add(txtSize);
		txtSize.setColumns(10);
		
		txtPrice = new JTextField("242.11");
		txtPrice.setBounds(138, 58, 86, 20);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnCompute = new JButton("Submit");
		btnCompute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
							
				try {
					Registry registry = LocateRegistry.getRegistry(1099);
					
					IPriceService priceService = (IPriceService) registry.lookup("priceService");
					ITaxService taxService = (ITaxService) registry.lookup("taxService");
					
					Car car = new Car(Integer.parseInt(txtYear.getText()), Integer.parseInt(txtSize.getText()), Double.parseDouble(txtPrice.getText()));
					
					Double tax = taxService.computeTax(car);
					Double price = priceService.computePrice(car);
					
					lblTax.setText("Tax: " + tax.toString());
					lblSellingPrice.setText("Price: " + price.toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NumberFormatException e3) {
					e3.printStackTrace();
					System.out.println("Invalid input number");
				}
				
				
			}
		});
		btnCompute.setBounds(21, 86, 203, 23);
		frame.getContentPane().add(btnCompute);
		
		lblTax = new JLabel("Tax:");
		lblTax.setBounds(75, 120, 149, 14);
		frame.getContentPane().add(lblTax);
		
		lblSellingPrice = new JLabel("Price:");
		lblSellingPrice.setBounds(75, 145, 143, 14);
		frame.getContentPane().add(lblSellingPrice);
	}
}
