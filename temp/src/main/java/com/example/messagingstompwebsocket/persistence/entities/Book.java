package com.example.messagingstompwebsocket.persistence.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="book")
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6999267709332591156L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String title;
	@Column
	private Genre genre;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
	private User author;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="book")
    private Set<Review> reviews = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="reader")
	private Set<BookProgress> bookReadersProgress = new HashSet<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	public Set<BookProgress> getBookReadersProgress() {
		return bookReadersProgress;
	}
	public void setBookReadersProgress(Set<BookProgress> bookReadersProgress) {
		this.bookReadersProgress = bookReadersProgress;
	}
	
}
