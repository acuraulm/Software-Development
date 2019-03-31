package frames;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import entities.Package;

import serviceInterfaces.IClientOperations;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("restriction")
public class ClientWindow extends JFrame {

	private static final long serialVersionUID = 6451266087600388034L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private IClientOperations clientOperations;
	private JScrollPane scrollPane;
	

	public ClientWindow(final int clientId){
	
		try {
			URL urlClientOperations = new URL("http://localhost:7779/ws/ClientOperations?wsdl");
			QName qnameClientOperations  = new QName("http://services/", "ClientOperationsService");  
	        Service serviceClientOperations = Service.create(urlClientOperations, qnameClientOperations);  
	        clientOperations = serviceClientOperations.getPort(IClientOperations.class);
	        
	        initialize(clientId);
	        
	        drawTable(clientOperations.findOwnPackages(clientId));
	        
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
        
	}
	
	private void initialize(final int clientId){
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 769, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 45, 676, 291);
		contentPane.add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(44, 350, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawTable(clientOperations.searchRelatedPackages(clientId,textField.getText()));
			}
		});
		btnNewButton.setBounds(189, 349, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawTable(clientOperations.findOwnPackages(clientId));
			}
		});
		btnRefresh.setBounds(189, 382, 89, 23);
		contentPane.add(btnRefresh);
		
		JButton btnCheckStatus = new JButton("Check status");
		btnCheckStatus.addMouseListener(new CheckStatus());
		btnCheckStatus.setBounds(565, 347, 155, 56);
		contentPane.add(btnCheckStatus);
	}
	private class CheckStatus extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(table.getModel().getValueAt(table.getSelectedRow(), 7).toString().equals("true"))
				try {
					new StatusWindow(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()),false).setVisible(true);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
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
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
	}
}
