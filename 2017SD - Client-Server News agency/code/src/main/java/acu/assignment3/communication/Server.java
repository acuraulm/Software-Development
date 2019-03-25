package acu.assignment3.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.WriterRepository;
import acu.assignment3.logic.ArticleService;
import acu.assignment3.logic.Mapper;
import acu.assignment3.logic.WriterService;
import acu.assignment3.presentation.ClientUI;

@Component
public class Server {
	@Autowired
	WriterRepository writerRepository;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ArticleService articleService;
	@Autowired
	WriterService writerService;
	
	 public void StartServer() throws IOException {
		 		System.out.println(articleService.findAllArticles());
		        System.out.println("The capitalization server is running.");
		        int clientNumber = 0;
		        ServerSocket listener = new ServerSocket(9001);
		        try {
		            while (true) {
		                new Capitalizer(listener.accept(), clientNumber++, articleService, writerService).start();
		            }
		        } finally {
		            listener.close();
		        }
		    }
		 private static class Capitalizer extends Thread {
		        private Socket socket;
		        private int clientNumber;
		        private BufferedReader in;
		        private PrintWriter out;
		        private ArticleService articleService;
		        private WriterService writerService;
		    	private String message;
		    	private Article article;
		    	
		        public Capitalizer(Socket socket, int clientNumber, ArticleService articleService, WriterService writerService) {
		            this.socket = socket;
		            this.clientNumber = clientNumber;
		            this.articleService  = articleService;
		            this.writerService = writerService;
		            log("New connection with client# " + clientNumber + " at " + socket);
		        }

		        /**
		         * Services this thread's client by first sending the
		         * client a welcome message then repeatedly reading strings
		         * and sending back the capitalized version of the string.
		         */
		        public void run() {
		            try {

		                // Decorate the streams so we can send characters
		                // and not just bytes.  Ensure output is flushed
		                // after every newline.
		               in = new BufferedReader(
		                        new InputStreamReader(socket.getInputStream()));
		               out = new PrintWriter(socket.getOutputStream(), true);
		           //    out.println("Citeste asta");
		                
		                // Send a welcome message to the client.
		               Gson gson = new Gson();
		                out.println("Client #" + clientNumber + " just logged in.");
		              //  out.println(gson.toJson(new Mapper(gson.toJson("Recieved_this"), gson.toJson(articleService.findAllArticles()))));
						System.out.println(articleService.findAllArticles());
						
						out.println(gson.toJson(new Mapper(gson.toJson("SHOW_ARTICLES"),new ObjectMapper().writeValueAsString(articleService.findAllArticles()))));
		                // Get messages from the client, line by line; return them
		                // capitalized
		                while (true) {
		                	readFromClient(in.readLine());
		                	System.out.println(message);
		                	gson = new Gson();
		                
		           
		                    if (message == null || message.equals(".")) {
		                        break;
		                    }
		                    if(message.equals("VIEW_ARTICLE")) {
		                    	Article a = articleService.findArticleByTitle(article.getArticleTitle());
		                    //	System.out.println(a.toString());
		                    	out.println(gson.toJson(new Mapper(gson.toJson(message),new ObjectMapper().writeValueAsString(a))));
				                
		                    	//out.println("Recieved: VIEW_ARTICLE from client number " + clientNumber);
		                    }
		                    if(message.equals("PUBLISH_ARTICLE")) {
		                    	articleService.addArticle(article);
		                    	out.println("Recieved: PUBLISH_ARTICLE from client number " + clientNumber);
		                    }
		                    out.println("RECIEVED" + message);
		                }
		            } catch (IOException e) {
		                log("Error handling client# " + clientNumber + ": " + e);
		            } finally {
		                try {
		                    socket.close();
		                } catch (IOException e) {
		                    log("Couldn't close a socket, what's going on?");
		                }
		                log("Connection with client# " + clientNumber + " closed");
		            }
		        }

		        /**
		         * Logs a simple message.  In this case we just write the
		         * message to the server applications standard output.
		         */
		        private void log(String message) {
		            System.out.println(message);
		        }
		        public void readFromClient(String input) throws JsonSyntaxException, IOException {
					 Gson gson = new Gson();
					 //System.out.println(input);
					 Mapper mapper = gson.fromJson(input, Mapper.class);
		             message = gson.fromJson(mapper.getMessage(),String.class);
		           //  System.out.println(message);
		        
		             article = gson.fromJson(mapper.getObject(), Article.class);
		           // System.out.println(article);
		            // System.out.println("~~ yes din funktyes~~");
				 }	
		        public String sendToClient(String command, Article article) {
					Gson gson = new Gson();
			    	return gson.toJson(new Mapper(gson.toJson(command), gson.toJson(article)));
				}
		       
		    }
		
}
