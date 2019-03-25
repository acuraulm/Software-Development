package acu.assignment3;

import java.io.IOException;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import acu.assignment3.communication.Client;
import acu.assignment3.communication.Server;
import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.Writer;
import acu.assignment3.logic.ArticleService;
import acu.assignment3.logic.WriterService;
import acu.assignment3.presentation.ClientUI;
import acu.assignment3.presentation.LoginUI;
import acu.assignment3.presentation.WriterUI;


@SpringBootApplication
@EnableJSONDoc
@EnableJpaRepositories
public class Assignment3Application  implements CommandLineRunner {
	@Autowired
	ArticleService articleService;
	@Autowired
	WriterService writerService;
	@Autowired
	LoginUI loginUI;

	@Autowired
	Server server;

	
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Assignment3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loginUI.setVisible(true);
		//Server server = new Server();
		server.StartServer();
		
		
	}

}
