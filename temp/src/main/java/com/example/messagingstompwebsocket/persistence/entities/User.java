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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1694908148453666216L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private UserRole userRole;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="author")
	@JsonIgnore
	private Set<Book> writtenBooks = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="reader")
	private Set<Review> reviews = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="reader")
	private Set<BookProgress> booksInProgress = new HashSet<>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Set<Book> getWrittenBooks() {
		return writtenBooks;
	}
	public void setWrittenBooks(Set<Book> writtenBooks) {
		this.writtenBooks = writtenBooks;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	public Set<BookProgress> getBooksInProgress() {
		return booksInProgress;
	}
	public void setBooksInProgress(Set<BookProgress> booksInProgress) {
		this.booksInProgress = booksInProgress;
	}
	

}
