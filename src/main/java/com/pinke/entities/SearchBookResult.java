package com.pinke.entities;

import java.util.List;
/**
 * @author liudao
 *
 */
public class SearchBookResult {
	/**
	 */
	private int error;
	private String query;
	public void setquery(String query) {
		this.query = query;
	}
	public String getquery() {
		return query;
	}
	
	
	
	/**
	 *  绗﹀悎鏌ヨ鏉′欢鐨勪功绫嶉泦鍚�
	 */
	private List<Book> books;
	
	public void seterror(int error) {
		this.error = error;
	}
	public int geterror() {
		return error;
	}
	
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	@Override
	public String toString() {
		return "SearchBookResult [error=" + error + ", books=" + books + ", query=" + query + "]";
	}
}
