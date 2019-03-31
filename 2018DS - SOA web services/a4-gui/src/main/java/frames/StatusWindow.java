package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import serviceInterfaces.IAdministratorOperations;
import transfer.RouteDTO;
import transfer.StatusDTO;

@SuppressWarnings("restriction")
public class StatusWindow extends JFrame {

	private static final long serialVersionUID = 8541138766931276471L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextPane txtpnHi;
	private IAdministratorOperations administratorOperations;
	private JLabel lblPackageId_value;
	private JLabel lblStatusId;
	private StatusDTO status;

	public StatusWindow(int packageId, boolean admin) {
		try {
			URL urlAdministratorOperations = new URL("http://localhost:7778/ws/AdministratorOperations?wsdl");
			QName qnameAdministratorOperations = new QName("http://services/", "AdministratorOperationsService");
			Service serviceAdministratorOperations = Service.create(urlAdministratorOperations,
					qnameAdministratorOperations);
			administratorOperations = serviceAdministratorOperations.getPort(IAdministratorOperations.class);
			
			status = administratorOperations.checkPackage(packageId);
			
			initialize(status, admin);
			drawRoutes(status);

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			this.dispose();
		}

	}

	private void initialize(StatusDTO status, Boolean admin) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 285, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 254, 176);
		contentPane.add(scrollPane);

		txtpnHi = new JTextPane();
		scrollPane.setViewportView(txtpnHi);
		txtpnHi.setText("Routes:");
		txtpnHi.setEditable(false);

		JLabel lblPackageId = new JLabel("Package Id:");
		lblPackageId.setBounds(10, 9, 67, 14);
		contentPane.add(lblPackageId);

		lblPackageId_value = new JLabel("0");
		lblPackageId_value.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackageId_value.setBounds(73, 9, 46, 14);
		lblPackageId_value.setText(status.getPackageId() + "");

		contentPane.add(lblPackageId_value);

		lblStatusId = new JLabel("" + status.getId());
		lblStatusId.setEnabled(false);
		lblStatusId.setVisible(false);
		lblStatusId.setBounds(139, 9, 46, 14);
		contentPane.add(lblStatusId);

		if (admin) {
			JLabel lblCity = new JLabel("City:");
			lblCity.setHorizontalAlignment(SwingConstants.TRAILING);
			lblCity.setBounds(20, 221, 35, 14);
			contentPane.add(lblCity);

			textField = new JTextField();
			textField.setBounds(65, 218, 103, 20);
			contentPane.add(textField);
			textField.setColumns(10);

			JButton btnNewButton = new JButton("Update");
			btnNewButton.addMouseListener(new UpdateStatus());
			btnNewButton.setBounds(186, 217, 78, 23);
			contentPane.add(btnNewButton);

		}
	}

	private class UpdateStatus extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

			try {
				RouteDTO routeDTO = new RouteDTO();
				routeDTO.setId(0);
				routeDTO.setCity(textField.getText());
				routeDTO.setStatus_id(Integer.parseInt(lblStatusId.getText()));
				//System.out.println("@@@@ status_id: " + Integer.parseInt(lblStatusId.getText()));
				routeDTO.setTime(new Date());
				administratorOperations.addRoute(routeDTO);

				StatusDTO statuss = administratorOperations
						.checkPackage(Integer.parseInt(lblPackageId_value.getText()));
				drawRoutes(statuss);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private void drawRoutes(StatusDTO status) {
		lblStatusId.setText(status.getId() + "");
		lblStatusId.setVisible(false);
		txtpnHi.setText("Routes:");
		if (status.getRoutes() != null)
			for (RouteDTO route : status.getRoutes()){
				 SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 txtpnHi.setText(txtpnHi.getText() + "\n(" + route.getCity() + "," + dt.format(route.getTime()) + ")");
			}
				
		this.revalidate();
		this.repaint();
	}
}
