package com.zjs.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6729815025189338878L;
	private String isbn;
	private String bookId; // 一本书的id
	// private boolean isCanBoorow; //是否可借
	private BookState state;
	private Bookinfo bookInfo;

	public Book() {
		super();
		this.state = BookState.可借;
		setRandomBookId();
	}

	public Book(String isbn) {
		this.isbn = isbn;
		this.state = BookState.可借;
		setRandomBookId();
	}

	/**
	 * 一本书的bookid  通过日期+4位随机数的方式；
	 */
	private void setRandomBookId() {
		String str1 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String str2 = new DecimalFormat("0000").format(Math.random() * 1000).toString();
		String str = str1.concat(str2);
		this.bookId = str;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Book))
			return false;
		Book book = (Book) obj;
		return bookId.equals(book.getBookId());
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookid) {
		this.bookId = bookid;
	}

	public BookState getState() {
		return state;
	}

	public void setState(BookState state) {
		this.state = state;
	}

	public Bookinfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(Bookinfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	/**
	 * 图书状态 可借、不可借、缺货、损坏
	 */
	enum BookState {
		可借, 不可借, 缺货, 损坏
	}
}
