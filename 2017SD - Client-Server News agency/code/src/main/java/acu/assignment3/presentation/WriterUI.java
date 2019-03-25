package acu.assignment3.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import acu.assignment3.data.Article;
import acu.assignment3.data.Writer;
import acu.assignment3.data.WriterRepository;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

@Controller
@Component
public class WriterUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2562136957348840348L;
	private JPanel contentPane;
	private JTextField txtTitle;
	@Autowired
	WriterRepository writerRepository;
	private Writer writer;
	
	private JButton btnPublish;
	public JTextField getTxtTitle() {
		return txtTitle;
	}

	public void setTxtTitle(JTextField txtTitle) {
		this.txtTitle = txtTitle;
	}

	public JTextArea getTxtAbstract() {
		return txtAbstract;
	}

	public void setTxtAbstract(JTextArea txtAbstract) {
		this.txtAbstract = txtAbstract;
	}

	public JTextArea getTxtrBody() {
		return txtrBody;
	}

	public void setTxtrBody(JTextArea txtrBody) {
		this.txtrBody = txtrBody;
	}

	private JTextArea txtAbstract;
	private JTextArea txtrBody;
	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WriterUI frame = new WriterUI(writer);
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
	 * @param writer 
	 */
	@Autowired
	public WriterUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 405, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtTitle = new JTextField();
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtTitle.setBounds(10, 11, 366, 28);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAbstract = new JTextArea();
		txtAbstract.setBounds(20, 72, 341, 119);
		contentPane.add(txtAbstract);
		
		txtrBody = new JTextArea();
		txtrBody.setBounds(20, 202, 341, 310);
		contentPane.add(txtrBody);
		
		btnPublish = new JButton("Publish");
		btnPublish.setBounds(10, 522, 349, 23);
		contentPane.add(btnPublish);
	}

	public JButton getBtnPublish() {
		return btnPublish;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}
}
