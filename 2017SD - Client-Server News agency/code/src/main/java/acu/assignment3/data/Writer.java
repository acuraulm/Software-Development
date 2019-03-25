package acu.assignment3.data;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "writers")
public class Writer {
		@Id
		@Column
		@SequenceGenerator(name = "my_seq2", sequenceName = "writers_writer_id_seq", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq2")
		private int writerId;
		@Column
		private String writerUsername;

		@Column
		private String writerPassword;
		
		@JsonIgnore
		@OneToMany(mappedBy = "writer", fetch = FetchType.EAGER)
		private List<Article> articles;

		public int getWriterId() {
			return writerId;
		}

		public void setWriterId(int writerId) {
			this.writerId = writerId;
		}

		public String getWriterUsername() {
			return writerUsername;
		}

		public void setWriterUsername(String writerUsername) {
			this.writerUsername = writerUsername;
		}

		public String getWriterPassword() {
			return writerPassword;
		}

		public void setWriterPassword(String writerPassword) {
			this.writerPassword = writerPassword;
		}

		public List<Article> getArticles() {
			return articles;
		}

		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
}
