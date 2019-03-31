package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import entities.Package;
import serviceInterfaces.IAdministratorOperations;

@SuppressWarnings("restriction")
public class AdministratorWindow extends JFrame {

	private static final long serialVersionUID = 477978175890690900L;
	private JPanel contentPane;
	private JTable table;
	private IAdministratorOperations administratorOperations;

	private JScrollPane scrollPane;
	private JLabel lblSender;
	private JTextField txtSender;
	private JTextField txtReceiver;
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtOrigin;
	private JTextField txtDestination;
	private JButton btnStatus;

	public AdministratorWindow() {
		try {
			URL urlAdministratorOperations = new URL("http://localhost:7778/ws/AdministratorOperations?wsdl");
			QName qnameAdministratorOperations  = new QName("http://services/", "AdministratorOperationsService");  
			Service serviceAdministratorOperations = Service.create(urlAdministratorOperations, qnameAdministratorOperations);  
			administratorOperations = serviceAdministratorOperations.getPort(IAdministratorOperations.class);
			
			initialize();
			
			drawTable(administratorOperations.findAllPackages());
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			this.dispose();
		}	
	}

	private void initialize(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 637, 233);
		contentPane.add(scrollPane);

		lblSender = new JLabel("Sender:");
		lblSender.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSender.setBounds(15, 260, 57, 14);
		contentPane.add(lblSender);

		txtSender = new JTextField();
		txtSender.setBounds(76, 257, 46, 20);
		contentPane.add(txtSender);
		txtSender.setColumns(10);

		JLabel lblReceiver = new JLabel("Receiver:");
		lblReceiver.setHorizontalAlignment(SwingConstants.TRAILING);
		lblReceiver.setBounds(15, 286, 57, 14);
		contentPane.add(lblReceiver);

		txtReceiver = new JTextField();
		txtReceiver.setColumns(10);
		txtReceiver.setBounds(76, 283, 46, 20);
		contentPane.add(txtReceiver);

		txtName = new JTextField();
		txtName.setBounds(202, 257, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setBounds(149, 260, 46, 14);
		contentPane.add(lblName);

		txtDescription = new JTextField();
		txtDescription.setBounds(202, 283, 86, 20);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescription.setBounds(120, 286, 75, 14);
		contentPane.add(lblDescription);

		txtOrigin = new JTextField();
		txtOrigin.setColumns(10);
		txtOrigin.setBounds(368, 257, 86, 20);
		contentPane.add(txtOrigin);

		txtDestination = new JTextField();
		txtDestination.setColumns(10);
		txtDestination.setBounds(368, 283, 86, 20);
		contentPane.add(txtDestination);

		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDestination.setBounds(278, 286, 83, 14);
		contentPane.add(lblDestination);

		JLabel lblSenderCity = new JLabel("Origin:");
		lblSenderCity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenderCity.setBounds(280, 260, 81, 14);
		contentPane.add(lblSenderCity);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new AddPackage());
		btnAdd.setBounds(464, 257, 86, 23);
		contentPane.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new RemovePackage());
		btnRemove.setBounds(557, 257, 86, 23);
		contentPane.add(btnRemove);

		btnStatus = new JButton("Status");
		btnStatus.addMouseListener(new CheckStatus());
		btnStatus.setBounds(464, 283, 178, 23);
		contentPane.add(btnStatus);
		
	}
	private class CheckStatus extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {

			int packageId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
			if(table.getModel().getValueAt(table.getSelectedRow(), 7).toString().equals("false")){
				administratorOperations.trackPackage(packageId);
				//administratorOperations.addStatus(packageId);
				drawTable(administratorOperations.findAllPackages());

			}
			new StatusWindow(packageId,true).setVisible(true);

		}
	}
	
	private class RemovePackage extends MouseAdapter{	
		@Override
		public void mouseClicked(MouseEvent e) {
			administratorOperations.removePackage(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
			drawTable(administratorOperations.findAllPackages());
		}
	}
	
	private class AddPackage extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {

			Package packagee = new Package();
			packagee.setDescription(txtDescription.getText());
			packagee.setDestinationCity(txtDestination.getText());
			packagee.setSenderCity(txtOrigin.getText());
			packagee.setName(txtName.getText());
			packagee.setTracking(false);
			
			try{
				int receiverId = Integer.parseInt(txtReceiver.getText());
				int senderId = Integer.parseInt(txtSender.getText());

				packagee.setReceiver(administratorOperations.getAppuserById(receiverId));
				packagee.setSender(administratorOperations.getAppuserById(senderId));

				administratorOperations.addPackage(packagee);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
			}

			drawTable(administratorOperations.findAllPackages());				
		}
	}
	
	private void drawTable(Package[] packages){

		List<String> columns = new ArrayList<String>();
		List<String[]> values = new ArrayList<String[]>();

		columns.add("Id");
		columns.add("Sender");
		columns.add("Reciever");
		columns.add("Name");
		columns.add("Description");
		columns.add("Sender City");
		columns.add("Destination City");
		columns.add("Tracking");

		for(Package packagee:packages){
			values.add(new String[] {packagee.getId() + "",
					packagee.getSender().getUsername(),
					packagee.getReceiver().getUsername(),
					packagee.getName(),
					packagee.getDescription(),
					packagee.getSenderCity(),
					packagee.getDestinationCity(),
					packagee.getTracking().toString()});
			System.out.println(packagee.toString());
		}

		TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());       
		table = new JTable(tableModel);
		table.setBounds(44, 45, 600, 251);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		this.revalidate();
		this.repaint();
	}
}
