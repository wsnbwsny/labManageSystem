package com.zjs.biz.impl;
/**
 * 定义bookInfo的实现类
 */

import java.util.Map;

import com.zjs.biz.BookInfoBiz;
import com.zjs.entity.Book;
import com.zjs.entity.Bookinfo;
import com.zjs.util.Fileutil;

public class BookInfoBizImpl implements BookInfoBiz {
	private static final long serialVersionUID = -1801769865657586017L;

	@Override
	public boolean add(Bookinfo bookInfo) {
		// 1、将所有的BookInfo读入
		// 2、向Map中添加bookInfo
		// 3、将map写会文件
		if (null == bookInfo)
			return false;
		Map<String, Bookinfo> bookInfoMap = findAll();
		if (null == bookInfoMap)
			return false;
		if (bookInfoMap.containsKey(bookInfo.getIsbn()))
			return false;
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		Fileutil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean del(Bookinfo bookInfo) {
		if (null == bookInfo)
			return false;
		Map<String, Bookinfo> bookInfoMap = findAll();
		if (null == bookInfoMap)
			return false;
		if (!bookInfoMap.containsKey(bookInfo.getIsbn()))
			return false;
		bookInfoMap.remove(bookInfo.getIsbn());
		Fileutil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public Bookinfo update(Bookinfo bookInfo) {
		if (null == bookInfo)
			return null;
		Map<String, Bookinfo> bookInfoMap = findAll();
		if (null == bookInfoMap)
			return null;
		if (!bookInfoMap.containsKey(bookInfo.getIsbn()))
			return null;
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		Fileutil.SaveBookInfoMap(bookInfoMap);
		return bookInfo;
	}

	@Override
	public Bookinfo findById(Bookinfo t) {
		throw new UnsupportedOperationException("不支持该操作");
	}

	@Override
	public Map<String, Bookinfo> findAll() {

		return Fileutil.ReadBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		Bookinfo bookinfo = findByIsbn(isbn);
		if (null == bookinfo || bookinfo.getInStoreCount() < outCount) {
			try {
				throw new Exception("图书不存在或者数量不足");
			} catch (Exception e) {

				e.printStackTrace();
			}
			return false;

		}
		// bookinfo.setInStoreCount(bookinfo.getInStoreCount() - outCount);
		bookinfo.deleteBooks(bookinfo, outCount);
		Map<String, Bookinfo> bookInfoMap = findAll();
		bookInfoMap.put(isbn, bookinfo);
		Fileutil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		Bookinfo bookinfo = findByIsbn(isbn);
		if (null == bookinfo)
			return false;
		bookinfo.addBooks(bookinfo, inCount);
		Map<String, Bookinfo> bookInfoMap = findAll();
		bookInfoMap.put(isbn, bookinfo);
		Fileutil.SaveBookInfoMap(bookInfoMap);
		return true;

	}

	@Override
	public Bookinfo findByIsbn(String isbn) {
		if (isbn.isEmpty() || null == isbn)
			return null;
		Map<String, Bookinfo> bookInfoMap = findAll();
		if (null == bookInfoMap)
			return null;
		if (!bookInfoMap.containsKey(isbn))
			return null;
		return bookInfoMap.get(isbn);
	}

}
