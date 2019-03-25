package acu.assignment3.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.WriterRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	WriterRepository writerRepository;
	
	public List<Article> findAllArticles(){
		return articleRepository.findAll();
	}

	public void addArticle(Article article) {
		articleRepository.save(article);
	}
	public Article findArticleByTitle(String title) {
		return articleRepository.findArticleByArticleTitle(title);
	}
	
}
