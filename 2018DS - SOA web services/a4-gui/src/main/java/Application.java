import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import entities.Appuser;
import frames.AdministratorWindow;
import frames.ClientWindow;
import serviceInterfaces.ILoginAndRegister;  

@SuppressWarnings("restriction")
public class Application {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private ILoginAndRegister loginAndRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Application window = new Application();
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
	public Application() {
	
		try {
			URL urlLoginAndRegister = new URL("http://localhost:7779/ws/LoginAndRegister?wsdl");	
	        QName qnameLoginAndRegister  = new QName("http://services/", "LoginAndRegisterService");  	        
	        Service serviceLoginAndRegister = Service.create(urlLoginAndRegister, qnameLoginAndRegister);  
	        loginAndRegister = serviceLoginAndRegister.getPort(ILoginAndRegister.class);    
	        
	        initialize();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			frame.dispose();
		} 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 230, 197);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Appuser client = loginAndRegister.login(txtUsername.getText(), txtPassword.getText());
				System.out.println(client.toString());
				boolean status = client.getIsAdmin();
				if(status){
					System.out.println("Logged in as administrator.");
					new AdministratorWindow().setVisible(true);
				}
				else{
					System.out.println("Logged in as client");
					new ClientWindow(client.getId()).setVisible(true);
				}	        
			}
		});
		btnLogIn.setBounds(115, 119, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(loginAndRegister.register(txtUsername.getText(), txtPassword.getText()))
					System.out.println("Registered succesfully. You can now log in!");
				else
					System.out.println("Registration failed.");
			}
		});
		btnRegister.setBounds(10, 119, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(81, 31, 123, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(81, 77, 123, 20);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(10, 34, 61, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(10, 80, 61, 14);
		frame.getContentPane().add(lblPassword);
	}
}
