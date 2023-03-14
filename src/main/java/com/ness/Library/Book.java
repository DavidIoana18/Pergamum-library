package com.ness.Library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Book {

	private @Id
	@GeneratedValue Long id;
	private String title;
	private String author;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public Book() {

	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;


	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Book))
			return false;
		Book book = (Book) o;
		return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title)
				&& Objects.equals(this.author, book.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.author);
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + this.id + ", title='" + this.title + '\'' + ", author='" + this.author + '\'' + '}';
	}
}

