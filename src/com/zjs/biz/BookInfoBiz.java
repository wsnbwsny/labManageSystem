package com.zjs.biz;

import com.zjs.entity.Bookinfo;

public interface BookInfoBiz extends Biz<Bookinfo> {
	/**
	 * 通过isbn查询图书
	 * @return
	 */
	public Bookinfo findByIsbn(String isbn);
	/**
	 * 出库
	 * 
	 * @param isbn
	 * @param outCount
	 * @return
	 */
	public boolean outStore(String isbn, int outCount);

	/**
	 * 入库
	 * 
	 * @param isbn
	 * @param outCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount);

}
