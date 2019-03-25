package acu.assignment3.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import acu.assignment3.data.Article;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ArticleUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private static Article article;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticleUI frame = new ArticleUI(new Article());
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
	public ArticleUI(Article article) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setText(article.getArticleTitle());
		txtTitle.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtTitle.setBounds(10, 11, 366, 28);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JTextArea txtAbstract = new JTextArea();
		txtAbstract.setEditable(false);
		txtAbstract.setText(article.getArticleAbstract());
		txtAbstract.setBounds(20, 72, 341, 119);
		contentPane.add(txtAbstract);
		
		JTextArea txtrBody = new JTextArea();
		txtrBody.setEditable(false);
		txtrBody.setText(article.getArticleBody());
		txtrBody.setBounds(20, 202, 341, 310);
		contentPane.add(txtrBody);
	}
}
