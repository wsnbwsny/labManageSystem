package com.zjs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 以图书的ISBN为主键定义一本书的集合 主要抽象属性有isbn、类别、作者、出版社、出版时间、价格等
 * 
 * @author zxy
 *
 */
public class Bookinfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6533296483730778012L;

	private String isbn;
	private String type;
	private String name;
	private String author;
	private String publisher;
	private int inStoreCount;
	private Date publishDate;
	private String price;
	private List<Book> bookList;

	public Bookinfo() {
		super();
	}
/**
 * 向图书中增加一本书book
 * @param book
 */
	public void addBook(Book book) {
		if (null == bookList) {
			bookList = new ArrayList<Book>();
		}
		if (null == book)
			return;
		if (!isbn.equals(book.getIsbn()))
			return;
		// 如果同一本书不需要添加
		if (bookList.contains(book))
			return;
		bookList.add(book);
		inStoreCount++;

	}

	/**
	 * 向图书bookInfo增加count本书，通过book的默认构造新增
	 * @param isbn
	 * @param count
	 */
	public void addBooks(Bookinfo bookInfo,int count) {
	for (int i = 0; i < count; i++) {
			Book book = new Book(bookInfo.getIsbn());
			addBook(book);
		}
	
	}
	
	
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getInStoreCount() {
		return inStoreCount;
	}

	public void setInStoreCount(int inStoreCount) {
		this.inStoreCount = inStoreCount;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

}
