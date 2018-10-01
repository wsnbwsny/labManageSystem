package com.zjs.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjs.auth.Role;
import com.zjs.biz.BookInfoBiz;
import com.zjs.biz.impl.BookInfoBizImpl;
import com.zjs.entity.Book;
import com.zjs.entity.Bookinfo;
import com.zjs.util.Fileutil;

public class Test {
	/**
	 * 测试Main类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// FileClassTest();
		// BookTest();
		 //BookInfoTest();
		TestRole();

	}

	private static void TestRole() {
		Role role1 = new Role();
		System.out.println(role1.getName() + "\t" + role1.getKey());
		System.out.println("权限集合：" + role1.getPermisstions());

		Role role2 = new Role("超级操作员", "oparator");
		System.out.println(role2.getName() + "\t" + role2.getKey());
		System.out.println("权限集合：" + role2.getPermisstions());
		
		Role role3 = new Role("超级管理员", "administrators");
		System.out.println(role2.getName() + "\t" + role3.getKey());
		System.out.println("权限集合：" + role3.getPermisstions());
		
		role3.inStore("123-456", 4);
		System.out.println(role3.outStore("123-456", 3));
		ShowAllBookInfo(Fileutil.ReadBookInfoMap());
	}

	public static void BookInfoTest() {
		BookInfoBiz bookinfoBiz = new BookInfoBizImpl();

		Bookinfo bookInfo = new Bookinfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("三体");
		bookInfo.addBooks(bookInfo, 5);

		Map<String, Bookinfo> bookInfoMap = new HashMap<>();
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);

		Fileutil.SaveBookInfoMap(bookInfoMap);

		ShowAllBookInfo(Fileutil.ReadBookInfoMap());

		bookinfoBiz.inStore("123-456", 3);

		ShowAllBookInfo(Fileutil.ReadBookInfoMap());
		// Bookinfo bookinfo = bookinfoBiz.findByIsbn("123-456");
		// bookinfoBiz.inStore("123-456", 15);
		// bookinfoBiz.outStore("123-456", 7);

	}

	public static void BookTest() {
		Bookinfo bookInfo = new Bookinfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("三体");

		Book book = new Book("123-456");

		bookInfo.addBook(book);
		bookInfo.setInStoreCount(bookInfo.getInStoreCount() + 1);

		System.out.println(bookInfo.getIsbn() + '\t' + bookInfo.getName());
		// System.out.println(book.getBookId());

		Map<String, Bookinfo> bookInfoMap = new HashMap<>();
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);

		ShowAllBookInfo(bookInfoMap);
		Fileutil.SaveBookInfoMap(bookInfoMap);

		ShowAllBookInfo(Fileutil.ReadBookInfoMap());

	}

	public static void ShowAllBookInfo(Map<String, Bookinfo> bookInfoMap) {
		System.out.println("图书馆共同的图书种类：\t" + bookInfoMap.size());
		System.out.println("所有的图书信息");
		for (String isbn : bookInfoMap.keySet()) {
			System.out.println(bookInfoMap.get(isbn).getIsbn() + "---" + bookInfoMap.get(isbn).getName() + "---"
					+ bookInfoMap.get(isbn).getInStoreCount());
			List<Book> bookList = bookInfoMap.get(isbn).getBookList();
			System.out.println("该图书的总本书：\t" + bookList.size());
			for (int i = 0; i < bookList.size(); i++) {
				System.out.println(
						"第" + (i + 1) + "本书：" + bookList.get(i).getBookId() + ":" + bookList.get(i).getState());

			}

		}
	}

	public static void FileClassTest() {

	}

}
