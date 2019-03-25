package acu.assignment3.communication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.Writer;
import acu.assignment3.data.WriterRepository;
import acu.assignment3.logic.ArticleService;
import acu.assignment3.logic.Mapper;
import acu.assignment3.logic.WriterService;
import acu.assignment3.presentation.ArticleUI;
import acu.assignment3.presentation.ClientUI;
import acu.assignment3.presentation.WriterUI;


import com.google.gson.reflect.TypeToken;

public class Client {
		private BufferedReader in;
	    private PrintWriter out;
	    private ClientUI clientUI;
	    public Client() {
	    }
	    public Client(ClientUI clientUI) {
	    	this.clientUI = clientUI;
	    	clientUI.setVisible(true);
	    	clientUI.getBtnView().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	Article article = new Article();
	            	article .setArticleTitle(clientUI.getTable().getModel().getValueAt(clientUI.getTable().getSelectedRow(),clientUI.getTable().getSelectedColumn()).toString());
	                out.println(sendToServer("VIEW_ARTICLE", article));//+ clientUI.getTable().getModel().getValueAt(clientUI.getTable().getSelectedRow(), 0));
	                   String response;
	                try {
	                    response = in.readLine();
	                    if (response == null || response.equals("")) {
	                         System.out.println("No response from server");
	                    	
	                      }
	                    else {
	                    	Gson gson = new Gson();
	             	        Mapper mapper = gson.fromJson(response, Mapper.class);
	             	        System.out.println(mapper.getMessage());
	             	        System.out.println(mapper.getObject());
	              	        Article articleReturned = gson.fromJson(mapper.getObject(),Article.class);
	              	        if(articleReturned != null) {
	              	        	new ArticleUI(articleReturned).setVisible(true);
	              	        }
	                    }
	                    } catch (IOException ex) {
	                       response = "Error: " + ex;
	                }
	            }
	        });
	    }

	    public Client(WriterUI writerUI) {
	    	 writerUI.setVisible(true);
		     writerUI.getBtnPublish().addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	Writer writer = new Writer();
		            	writer.setWriterId(11);
		            	Article article = new Article();
		            	article.setArticleTitle(writerUI.getTxtTitle().getText());
		            	article.setArticleBody(writerUI.getTxtrBody().getText());
		            	article.setArticleAbstract(writerUI.getTxtAbstract().getText());
		            	article.setWriter(writer);
		            	publishArticle(article);
		            }
		        });
		    }
	    protected void publishArticle(Article article) {
	    	    	out.println(sendToServer("PUBLISH_ARTICLE", article));
	    	String response;
             try {
                 response = in.readLine();
                 if (response == null || response.equals("")) {
                      System.out.println("No response from server");
                 	//System.exit(0);
                   }
                 else
                 	System.out.println(response);
             } catch (IOException ex) {
                    response = "Error: " + ex;
             }
		}

		public void connectToServerAsReader() throws IOException {	        // Get the server address from a dialog box.
	     
	        Socket socket = new Socket("localhost", 9001);
	        in = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);
	      //  out.println(sendToServer("NEW_CLIENT",new Article()));
	        // Consume the initial welcoming messages from the server
	        for (int i = 0; i < 1; i++) {
	          System.out.println(in.readLine());
	        }
	       // System.out.println(in.readLine());
	        Gson gson = new Gson();
	        Mapper mapper = gson.fromJson(in.readLine(), Mapper.class);
	        System.out.println(mapper.getMessage());
	        System.out.println(mapper.getObject());
	        List<Article> articles = gson.fromJson(mapper.getObject(), new TypeToken<ArrayList<Article>>(){}.getType());
	         DefaultTableModel model = new DefaultTableModel();
    		model.addColumn("Article Title");
    		for(Article a : articles){
    			Object[] o={a.getArticleTitle()
    				};
    				model.addRow(o);	
    				}
    		clientUI.getTable().setModel(model);
	    }
		public void connectToServerAsWriter() throws IOException {	        // Get the server address from a dialog box.
		     
	        Socket socket = new Socket("localhost", 9001);
	        in = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);
	      //  out.println(sendToServer("NEW_CLIENT",new Article()));
	        // Consume the initial welcoming messages from the server
	        for (int i = 0; i < 1; i++) {
	          System.out.println(in.readLine());
	        }
	        System.out.println(in.readLine());
	       
	    }
		
		public String sendToServer(String command, Object object) {
			Gson gson = new Gson();
	    	return gson.toJson(new Mapper(gson.toJson(command), gson.toJson(object)));
		}
		public List<Article> readArticlesFromServer(String input){
			Gson gson = new Gson(); 
			Mapper mapper = gson.fromJson(input, Mapper.class);
			return gson.fromJson(mapper.getObject(), new TypeToken<ArrayList<Article>>(){}.getType());
		}
}
