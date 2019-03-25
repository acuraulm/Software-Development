package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import start.ReflectionExample;
import start.Start;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dacee {

	private JFrame frame;
	public static JTable tableClients;
	private JTextField txtClientAddName;
	private JTextField txtClientAddAddress;
	private JTextField txtClientAddEmail;
	private JTextField txtClientAddAge;
	private JTextField txtClientEditName;
	private JTextField txtClientEditAddress;
	private JTextField txtClientEditEmail;
	private JTextField txtClientEditAge;
	private JTextField txtClientEditID;
	private JTextField txtClientRemoveID;
	private JTextField txtProductRemoveID;
	public static JTable tableProducts;
	private JTextField txtProductEditName;
	private JTextField txtProductEditPrice;
	private JTextField txtProductEditQuantity;
	private JTextField txtProductAddName;
	private JTextField txtProductAddPrice;
	private JTextField txtProductAddQuantity;
	private JTextField txtProductEditID;
	public static JTable tableOrders;
	private JPanel Panel1;
	private JPanel panelClients;
	private JPanel panelProducts;
	private JPanel panelOrders;
	private JPanel panelAddClient;
	private JPanel panelEditClient;
	private JPanel panelRemoveClient;
	private JPanel panelAddProduct;
	private JPanel panelEditProduct;
	private JPanel panelRemoveProduct;
	private JPanel panelAddOrder;
	private JPanel panelRemoveOrder;
	private JPanel panelEditOrder;
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
	private JTextField txtOrderAddIdclient;
	private JTextField txtOrderAddIdproduct;
	private JTextField txtOrderAddQuantity;
	private JTextField txtOrderEditIdclient;
	private JTextField txtOrderEditIdproduct;
	private JTextField txtOrderEditQuantity;
	private JTextField txtOrderEditID;
	private JTextField txtOrderRemoveID;
	ProductBLL productBll = new ProductBLL();
	OrdersBLL ordersBll = new OrdersBLL();
	ClientBLL clientBll = new ClientBLL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dacee window = new Dacee();
					window.frame.setVisible(true);
					window.frame.setSize(600, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dacee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1426, 1319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
																																			/* Panels */
		Panel1 = new JPanel();
		Panel1.setBounds(10, 0, 545, 69);
		frame.getContentPane().add(Panel1);
		Panel1.setLayout(null);
			
		panelClients = new JPanel();
		panelClients.setBounds(10, 80, 545, 333);
		frame.getContentPane().add(panelClients);
		panelClients.setLayout(null);
		panelClients.setVisible(false);
		
		panelProducts = new JPanel();
		panelProducts.setLayout(null);
		panelProducts.setBounds(10, 80, 545, 333);
		frame.getContentPane().add(panelProducts);
		panelProducts.setVisible(false);
		
		panelOrders = new JPanel();
		panelOrders.setLayout(null);
		panelOrders.setBounds(10, 80, 545, 333);
		frame.getContentPane().add(panelOrders);
		panelOrders.setVisible(false);
		
		panelAddClient = new JPanel();
		panelAddClient.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelAddClient);
		panelAddClient.setLayout(null);
		panelAddClient.setVisible(false);
		
		panelEditClient = new JPanel();
		panelEditClient.setLayout(null);
		panelEditClient.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelEditClient);
		panelEditClient.setVisible(false);
		
		panelRemoveClient = new JPanel();
		panelRemoveClient.setLayout(null);
		panelRemoveClient.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelRemoveClient);
		panelRemoveClient.setVisible(false);
		
		panelAddProduct = new JPanel();
		panelAddProduct.setLayout(null);
		panelAddProduct.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelAddProduct);
		panelAddProduct.setVisible(false);
		
		panelEditProduct = new JPanel();
		panelEditProduct.setLayout(null);
		panelEditProduct.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelEditProduct);
		panelEditProduct.setVisible(false);
		
		panelRemoveProduct = new JPanel();
		panelRemoveProduct.setLayout(null);
		panelRemoveProduct.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelRemoveProduct);
		panelRemoveProduct.setVisible(false);
		
		panelAddOrder = new JPanel();
		panelAddOrder.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelAddOrder);
		panelAddOrder.setLayout(null);
		panelAddOrder.setVisible(false);
		
		panelRemoveOrder = new JPanel();
		panelRemoveOrder.setLayout(null);
		panelRemoveOrder.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelRemoveOrder);
		panelRemoveOrder.setVisible(false);
		
		panelEditOrder = new JPanel();
		panelEditOrder.setLayout(null);
		panelEditOrder.setBounds(10, 424, 545, 98);
		frame.getContentPane().add(panelEditOrder);
		panelEditOrder.setVisible(false);
		
																																						/* Text fields */
		txtClientAddName = new JTextField();
		txtClientAddName.setBounds(66, 8, 189, 20);
		panelAddClient.add(txtClientAddName);
		txtClientAddName.setColumns(10);
						
		txtClientAddAddress = new JTextField();
		txtClientAddAddress.setColumns(10);
		txtClientAddAddress.setBounds(66, 36, 189, 20);
		panelAddClient.add(txtClientAddAddress);
		
		txtClientAddEmail = new JTextField();
		txtClientAddEmail.setColumns(10);
		txtClientAddEmail.setBounds(343, 8, 189, 20);
		panelAddClient.add(txtClientAddEmail);		
		
		txtClientAddAge = new JTextField();
		txtClientAddAge.setColumns(10);
		txtClientAddAge.setBounds(343, 36, 86, 20);
		panelAddClient.add(txtClientAddAge);
			
		txtClientEditName = new JTextField();
		txtClientEditName.setColumns(10);
		txtClientEditName.setBounds(66, 8, 189, 20);
		panelEditClient.add(txtClientEditName);
					
		txtClientEditAddress = new JTextField();
		txtClientEditAddress.setColumns(10);
		txtClientEditAddress.setBounds(66, 36, 189, 20);
		panelEditClient.add(txtClientEditAddress);
					
		txtClientEditEmail = new JTextField();
		txtClientEditEmail.setColumns(10);
		txtClientEditEmail.setBounds(343, 8, 189, 20);
		panelEditClient.add(txtClientEditEmail);
						
		txtClientEditAge = new JTextField();
		txtClientEditAge.setColumns(10);
		txtClientEditAge.setBounds(343, 36, 86, 20);
		panelEditClient.add(txtClientEditAge);
					
		txtClientEditID = new JTextField();
		txtClientEditID.setColumns(10);
		txtClientEditID.setBounds(37, 67, 66, 20);
		panelEditClient.add(txtClientEditID);
				
		txtClientRemoveID = new JTextField();
		txtClientRemoveID.setColumns(10);
		txtClientRemoveID.setBounds(261, 31, 66, 20);
		panelRemoveClient.add(txtClientRemoveID);
						
		txtProductAddName = new JTextField();
		txtProductAddName.setColumns(10);
		txtProductAddName.setBounds(62, 33, 195, 20);
		panelAddProduct.add(txtProductAddName);
		
		txtProductAddPrice = new JTextField();
		txtProductAddPrice.setColumns(10);
		txtProductAddPrice.setBounds(303, 33, 76, 20);
		panelAddProduct.add(txtProductAddPrice);
					
		txtProductAddQuantity = new JTextField();
		txtProductAddQuantity.setColumns(10);
		txtProductAddQuantity.setBounds(456, 33, 76, 20);
		panelAddProduct.add(txtProductAddQuantity);
									
		txtProductEditName = new JTextField();
		txtProductEditName.setBounds(119, 33, 159, 20);
		panelEditProduct.add(txtProductEditName);
		txtProductEditName.setColumns(10);
						
		txtProductEditPrice = new JTextField();
		txtProductEditPrice.setColumns(10);
		txtProductEditPrice.setBounds(322, 33, 76, 20);
		panelEditProduct.add(txtProductEditPrice);
					
		txtProductEditQuantity = new JTextField();
		txtProductEditQuantity.setColumns(10);
		txtProductEditQuantity.setBounds(459, 33, 76, 20);
		panelEditProduct.add(txtProductEditQuantity);
				
		txtProductEditID = new JTextField();
		txtProductEditID.setColumns(10);
		txtProductEditID.setBounds(37, 33, 30, 20);
		panelEditProduct.add(txtProductEditID);
							
		txtProductRemoveID = new JTextField();
		txtProductRemoveID.setColumns(10);
		txtProductRemoveID.setBounds(261, 31, 66, 20);
		panelRemoveProduct.add(txtProductRemoveID);
		
		
																																			/* Tables */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 525, 271);
		panelClients.add(scrollPane);
		
		tableClients = new JTable();
		scrollPane.setViewportView(tableClients);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 525, 271);
		panelProducts.add(scrollPane_1);
		
		tableProducts = new JTable();
		scrollPane_1.setViewportView(tableProducts);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 525, 271);
		panelOrders.add(scrollPane_2);
		
		tableOrders = new JTable();
		scrollPane_2.setViewportView(tableOrders);
		
		
		
																																			/* Buttons */
		JButton btnManageClients = new JButton("Clients");
		btnManageClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnManageClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelClients.setVisible(true);
				panelProducts.setVisible(false);
				panelOrders.setVisible(false);
				updateAllTables();
			}
		});
		btnManageClients.setBounds(10, 11, 168, 47);
		Panel1.add(btnManageClients);
		
		JButton btnManageProducts = new JButton("Products");
		btnManageProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelClients.setVisible(false);
				panelProducts.setVisible(true);
				panelOrders.setVisible(false);
				updateAllTables();
			}
		});
		btnManageProducts.setBounds(188, 11, 168, 47);
		Panel1.add(btnManageProducts);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelClients.setVisible(false);
				panelOrders.setVisible(true);
				panelProducts.setVisible(false);
				updateAllTables();
			}
		});
		btnOrder.setBounds(366, 11, 168, 47);
		Panel1.add(btnOrder);
		
		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelAddClient.setVisible(true);
			}
		});
		btnAddClient.setBounds(10, 293, 157, 32);
		panelClients.add(btnAddClient);
		
		JButton btnEditClient = new JButton("Edit Client");
		btnEditClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelEditClient.setVisible(true);

			}
		});
		btnEditClient.setBounds(193, 293, 157, 32);
		panelClients.add(btnEditClient);
		
		JButton btnRemoveClient = new JButton("Remove Client");
		btnRemoveClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelRemoveClient.setVisible(true);

			}
		});
		btnRemoveClient.setBounds(378, 293, 157, 32);
		panelClients.add(btnRemoveClient);
					
		JButton btnSubmitClient = new JButton("Submit Client");
		btnSubmitClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client product = new Client(txtClientAddName.getText(),txtClientAddAddress.getText(), txtClientAddEmail.getText(),Integer.parseInt(txtClientAddAge.getText()));
				ClientBLL productBll = new ClientBLL();
				int idC = productBll.insertClient(product);
				if (idC > 0) {
					productBll.findClientById(idC);
				}
				try {
					productBll.findClientById(idC-1);
					updateAllTables();
				} catch (Exception ex) {
					LOGGER.log(Level.INFO, ex.getMessage());
				}		
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnSubmitClient.setBounds(10, 64, 522, 23);
		panelAddClient.add(btnSubmitClient);
		
		JButton btnUpdateClient = new JButton("Update Client");
		btnUpdateClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Client product = new Client(Integer.parseInt(txtClientEditID.getText()),txtClientEditName.getText(),txtClientEditAddress.getText(), txtClientEditEmail.getText(),Integer.parseInt(txtClientEditAge.getText()));
				ClientBLL productBll = new ClientBLL();
				try {
					productBll.updateClient(product);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnUpdateClient.setBounds(113, 66, 419, 23);
		panelEditClient.add(btnUpdateClient);
		
		JButton btnDeleteClient = new JButton("Remove Client");
		btnDeleteClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Client product = new Client(Integer.parseInt(txtClientRemoveID.getText()));
				ClientBLL productBll = new ClientBLL();
				try {
					productBll.deleteClient(product);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnDeleteClient.setBounds(10, 64, 522, 23);
		panelRemoveClient.add(btnDeleteClient);
		
		JButton btnSubmitProduct = new JButton("Submit Product");
		btnSubmitProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Product product = new Product(txtProductAddName.getText(), Double.parseDouble(txtProductAddPrice.getText()),Integer.parseInt(txtProductAddQuantity.getText()));
				ProductBLL productBll = new ProductBLL();
				int idC = productBll.insertProduct(product);
				if (idC > 0) {
					productBll.findProductById(idC);
				}
				try {
					productBll.findProductById(1);
					updateAllTables();
				} catch (Exception ex) {
					LOGGER.log(Level.INFO, ex.getMessage());
				}		
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnSubmitProduct.setBounds(10, 64, 522, 23);
		panelAddProduct.add(btnSubmitProduct);
		
		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Product product = new Product(Integer.parseInt(txtProductEditID.getText()),txtProductEditName.getText(),Double.parseDouble(txtProductEditPrice.getText()),Integer.parseInt(txtProductEditQuantity.getText()));
				ProductBLL productBll = new ProductBLL();
				try {
					productBll.updateProduct(product);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnUpdateProduct.setBounds(10, 64, 522, 23);
		panelEditProduct.add(btnUpdateProduct);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelAddProduct.setVisible(true);

			}
		});
		btnAddProduct.setBounds(10, 293, 157, 32);
		panelProducts.add(btnAddProduct);
		
		JButton btnEditProduct = new JButton("Edit Product");
		btnEditProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelEditProduct.setVisible(true);
			}
		});
		btnEditProduct.setBounds(193, 293, 157, 32);
		panelProducts.add(btnEditProduct);
		
		JButton btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelRemoveProduct.setVisible(true);
			}
		});
		btnRemoveProduct.setBounds(378, 293, 157, 32);
		panelProducts.add(btnRemoveProduct);
		
		JButton btnRemoveProduct_1 = new JButton("Remove Product");
		btnRemoveProduct_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Product product = new Product(Integer.parseInt(txtProductRemoveID.getText()));
				ProductBLL productBll = new ProductBLL();
				try {
					productBll.deleteProduct(product);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(product);
			}
		});
		btnRemoveProduct_1.setBounds(10, 64, 522, 23);
		panelRemoveProduct.add(btnRemoveProduct_1);
		
		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelAddOrder.setVisible(true);
			}
		});
		btnAddOrder.setBounds(10, 293, 157, 32);
		panelOrders.add(btnAddOrder);
		
		JButton btnEditOrder = new JButton("Edit Order");
		btnEditOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelEditOrder.setVisible(true);

			}
		});
		btnEditOrder.setBounds(193, 293, 157, 32);
		panelOrders.add(btnEditOrder);
		
		JButton btnRemoveOrder = new JButton("Remove Order");
		btnRemoveOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				invisibleAll();
				panelRemoveOrder.setVisible(true);

			}
		});
		btnRemoveOrder.setBounds(378, 293, 157, 32);
		panelOrders.add(btnRemoveOrder);
		
																																			/* Labels */
		JLabel lblClientName = new JLabel("Name:");
		lblClientName.setBounds(10, 11, 46, 14);
		panelAddClient.add(lblClientName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 39, 46, 14);
		panelAddClient.add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(287, 11, 46, 14);
		panelAddClient.add(lblEmail);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(287, 39, 46, 14);
		panelAddClient.add(lblAge);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(10, 11, 46, 14);
		panelEditClient.add(label);
		
		JLabel label_1 = new JLabel("Address:");
		label_1.setBounds(10, 39, 46, 14);
		panelEditClient.add(label_1);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setBounds(287, 11, 46, 14);
		panelEditClient.add(label_2);
		
		JLabel label_3 = new JLabel("Age:");
		label_3.setBounds(287, 39, 46, 14);
		panelEditClient.add(label_3);
		
		JLabel label_4 = new JLabel("ID:");
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(10, 70, 17, 14);
		panelEditClient.add(label_4);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblId_1.setBounds(217, 30, 51, 23);
		panelRemoveClient.add(lblId_1);
		
		JLabel label_5 = new JLabel("Name:");
		label_5.setBounds(22, 36, 46, 14);
		panelAddProduct.add(label_5);
		
		JLabel label_6 = new JLabel("Price:");
		label_6.setBounds(267, 36, 46, 14);
		panelAddProduct.add(label_6);
		
		JLabel label_7 = new JLabel("Quantity:");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setBounds(391, 36, 55, 14);
		panelAddProduct.add(label_7);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(75, 36, 46, 14);
		panelEditProduct.add(lblName);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(288, 36, 46, 14);
		panelEditProduct.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantity.setBounds(394, 36, 55, 14);
		panelEditProduct.add(lblQuantity);
		
		JLabel label_8 = new JLabel("ID:");
		label_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_8.setBounds(10, 36, 17, 14);
		panelEditProduct.add(label_8);
		
		JLabel label_15 = new JLabel("ID:");
		label_15.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_15.setBounds(217, 30, 51, 23);
		panelRemoveProduct.add(label_15);
			
		JLabel lblOrderIdClient = new JLabel("Client ID:");
		lblAddress.setBounds(10, 39, 46, 14);
		panelAddOrder.add(lblOrderIdClient);
		
		JLabel lblOrderIdProduct = new JLabel("Product ID:");
		lblEmail.setBounds(287, 11, 46, 14);
		panelAddOrder.add(lblOrderIdProduct);
		
		JLabel lbOrderQuantity = new JLabel("Quantity:");
		lblAge.setBounds(287, 39, 46, 14);
		panelAddOrder.add(lbOrderQuantity);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(10, 27, 46, 14);
		panelAddOrder.add(lblClientId);
		
		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setBounds(186, 27, 67, 14);
		panelAddOrder.add(lblProductId);
		
		JLabel lblQuantity_1 = new JLabel("Quantity:");
		lblQuantity_1.setBounds(368, 27, 67, 14);
		panelAddOrder.add(lblQuantity_1);
		
		txtOrderAddIdclient = new JTextField();
		txtOrderAddIdclient.setBounds(66, 24, 58, 20);
		panelAddOrder.add(txtOrderAddIdclient);
		txtOrderAddIdclient.setColumns(10);
		
		txtOrderAddIdproduct = new JTextField();
		txtOrderAddIdproduct.setColumns(10);
		txtOrderAddIdproduct.setBounds(254, 24, 58, 20);
		panelAddOrder.add(txtOrderAddIdproduct);
		
		txtOrderAddQuantity = new JTextField();
		txtOrderAddQuantity.setColumns(10);
		txtOrderAddQuantity.setBounds(425, 24, 58, 20);
		panelAddOrder.add(txtOrderAddQuantity);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setBounds(10, 64, 525, 23);
		btnSubmitOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Orders orders = new Orders(Integer.parseInt(txtOrderAddIdclient.getText()), Integer.parseInt(txtOrderAddIdproduct.getText()), Integer.parseInt(txtOrderAddQuantity.getText()));
				OrdersBLL ordersBll = new OrdersBLL();
				ClientBLL clientBll = new ClientBLL();
				ProductBLL productBll = new ProductBLL();
				if(orders.getQuantity() <= productBll.findProductById(Integer.parseInt(txtOrderAddIdproduct.getText())).getQuantity()){
					int idC = ordersBll.insertOrders(orders);
				if (idC > 0) {
					ordersBll.findOrdersById(idC);
				}
				try {
					ordersBll.findOrdersById(idC-1);
					productBll.decreaseQuantity(productBll.findProductById(Integer.parseInt(txtOrderAddIdproduct.getText())), Integer.parseInt(txtOrderAddQuantity.getText()));
					FileWriter fw = null;
					BufferedWriter bw = null;
					try {						
						String content = "Client: " + clientBll.findClientById(Integer.parseInt(txtOrderAddIdclient.getText())).getName()
								+" has ordered product: " + productBll.findProductById(Integer.parseInt(txtOrderAddIdproduct.getText())).getName()
								+"\t Quantity: "+ orders.getQuantity()
								+"\t Total price: " + orders.getQuantity()*productBll.findProductById(Integer.parseInt(txtOrderAddIdproduct.getText())).getPrice();
						fw = new FileWriter("bill.txt");
						bw = new BufferedWriter(fw);
						bw.write(content);

						System.out.println("Done");

					} catch (IOException x) {

						x.printStackTrace();

					} finally {

						try {

							if (bw != null)
								bw.close();

							if (fw != null)
								fw.close();

						} catch (IOException ex) {

							ex.printStackTrace();

						}

					}
					
					updateAllTables();
				} catch (Exception ex) {
					LOGGER.log(Level.INFO, ex.getMessage());
				}
				}else{
					System.out.println("Not enough items");
				}
				ReflectionExample.retrieveProperties(orders);
			}
		});
		panelAddOrder.add(btnSubmitOrder);
		
		JLabel label_1o = new JLabel("Client ID:");
		label_1.setBounds(10, 39, 46, 14);
		panelRemoveOrder.add(label_1o);
		
		JLabel label_2o = new JLabel("Product ID:");
		label_2.setBounds(287, 11, 46, 14);
		panelRemoveOrder.add(label_2o);
		
		JLabel label_3o = new JLabel("Quantity:");
		label_3.setBounds(287, 39, 46, 14);
		panelRemoveOrder.add(label_3o);
		
		JLabel label_4o = new JLabel("ID:");
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(10, 70, 17, 14);
		panelRemoveOrder.add(label_4o);
		
		JLabel label_11 = new JLabel("ID:");
		label_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_11.setBounds(218, 36, 46, 14);
		panelRemoveOrder.add(label_11);
		
		txtOrderRemoveID = new JTextField();
		txtOrderRemoveID.setColumns(10);
		txtOrderRemoveID.setBounds(261, 33, 72, 20);
		panelRemoveOrder.add(txtOrderRemoveID);
		
		JButton btnDeleteOrder = new JButton("Remove Order");
		btnDeleteOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Orders orders = new Orders(Integer.parseInt(txtOrderRemoveID.getText()));
				OrdersBLL ordersBll = new OrdersBLL();
				try {
					ordersBll.deleteOrders(orders);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(orders);
			}
		});
		btnDeleteOrder.setBounds(10, 64, 525, 23);
		panelRemoveOrder.add(btnDeleteOrder);
		
		JLabel lblId_1o = new JLabel("ID:");
		lblId_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblId_1.setBounds(217, 30, 51, 23);
		panelEditOrder.add(lblId_1o);
		
		JLabel label_9 = new JLabel("Client ID:");
		label_9.setBounds(10, 26, 46, 14);
		panelEditOrder.add(label_9);
		
		txtOrderEditIdclient = new JTextField();
		txtOrderEditIdclient.setColumns(10);
		txtOrderEditIdclient.setBounds(66, 23, 58, 20);
		panelEditOrder.add(txtOrderEditIdclient);
		
		JButton btnUpdateOrder = new JButton("Update Order");
		btnUpdateOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Orders orders = new Orders(Integer.parseInt(txtOrderEditID.getText()),Integer.parseInt(txtOrderEditIdclient.getText()), Integer.parseInt(txtOrderEditIdproduct.getText()), Integer.parseInt(txtOrderEditQuantity.getText()));
				OrdersBLL ordersBll = new OrdersBLL();
				try {
					ordersBll.updateOrders(orders);
					updateAllTables();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//obtain field-value pairs for object through reflection
				ReflectionExample.retrieveProperties(orders);
			}
		});
		btnUpdateOrder.setBounds(117, 64, 418, 23);
		panelEditOrder.add(btnUpdateOrder);
		
		JLabel label_10 = new JLabel("Product ID:");
		label_10.setBounds(188, 26, 67, 14);
		panelEditOrder.add(label_10);
		
		txtOrderEditIdproduct = new JTextField();
		txtOrderEditIdproduct.setColumns(10);
		txtOrderEditIdproduct.setBounds(256, 23, 58, 20);
		panelEditOrder.add(txtOrderEditIdproduct);
		
		JLabel lblOrderEditQuantity = new JLabel("Quantity:");
		lblOrderEditQuantity.setBounds(367, 26, 67, 14);
		panelEditOrder.add(lblOrderEditQuantity);
		
		txtOrderEditQuantity = new JTextField();
		txtOrderEditQuantity.setColumns(10);
		txtOrderEditQuantity.setBounds(426, 23, 58, 20);
		panelEditOrder.add(txtOrderEditQuantity);
		
		txtOrderEditID = new JTextField();
		txtOrderEditID.setColumns(10);
		txtOrderEditID.setBounds(35, 65, 72, 20);
		panelEditOrder.add(txtOrderEditID);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblId.setBounds(10, 68, 46, 14);
		panelEditOrder.add(lblId);
	}
	
	private void invisibleAll(){
		panelAddClient.setVisible(false);
		panelEditClient.setVisible(false);
		panelRemoveClient.setVisible(false);
		panelAddProduct.setVisible(false);
		panelEditProduct.setVisible(false);
		panelRemoveProduct.setVisible(false);
		panelAddOrder.setVisible(false);
		panelEditOrder.setVisible(false);
		panelRemoveOrder.setVisible(false);
	}
	private void updateAllTables(){
		
		try {
			System.out.println(productBll.findAllProducts().toString());
			System.out.println(clientBll.findAllClients().toString());
			System.out.println(ordersBll.findAllOrders().toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
