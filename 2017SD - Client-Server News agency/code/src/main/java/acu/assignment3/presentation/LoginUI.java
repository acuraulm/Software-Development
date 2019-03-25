package acu.assignment3.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import acu.assignment3.communication.Client;
import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.Writer;
import acu.assignment3.data.WriterRepository;
import acu.assignment3.logic.ArticleService;
import acu.assignment3.logic.WriterService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Controller
@Component
public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	@Autowired
	WriterRepository writerRepository;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ArticleService articleService;
	@Autowired
	WriterService writerService;
	
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 222, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername = new JTextField();
		txtUsername.setText("username");
		txtUsername.setBounds(82, 11, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(10, 14, 62, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(10, 45, 62, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(82, 42, 86, 20);
		contentPane.add(txtPassword);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Writer writer = writerRepository.findByWriterUsername(txtUsername.getText());
					System.out.println(writer.toString());
					if(writer.getWriterPassword().equals(txtPassword.getText())) {
							Client client = new Client(new WriterUI());
				        client.connectToServerAsWriter();
					}
				       // System.out.println(articleService.findAllArticles());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		/*		Writer writer = writerRepository.findByUsername(txtUsername.getText());
				if(writer.getPassword().equals(txtPassword.getText())){
					new WriterUI(writerRepository.findByUsername(txtUsername.getText())).setVisible(true);
				}
		*/	}
		});
		btnLogIn.setBounds(107, 75, 89, 23);
		contentPane.add(btnLogIn);
		
		btnNewButton = new JButton("Client");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			        try {
			        	//articleRepository.save(new Article());
			        	//writerRepository.save(new Writer());
			        	//System.out.println(articleService.findAllArticles());
			        	//System.out.println(writerRepository.findAll());
						 Client client = new Client(new ClientUI());
					        client.connectToServerAsReader();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(10, 75, 86, 23);
		contentPane.add(btnNewButton);
		
	}
}
