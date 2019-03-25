package acu.assignment3.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.assignment3.data.Article;
import acu.assignment3.data.ArticleRepository;
import acu.assignment3.data.Writer;
import acu.assignment3.data.WriterRepository;

@Service
public class WriterService {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	WriterRepository writerRepository;
	
	public List<Writer> findAllWriters(){
		return writerRepository.findAll();
	}
	
	public void addWriter(Writer writer) {
		writer.setWriterUsername(writer.getWriterUsername());
		writer.setWriterPassword(writer.getWriterPassword());
		writer.setArticles(new ArrayList<>());
		writerRepository.save(writer);
	}
}
