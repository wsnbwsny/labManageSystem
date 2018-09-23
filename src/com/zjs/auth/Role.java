package com.zjs.auth;
/**
 * 角色类
 * @author zxy
 *
 */

import java.io.Serializable;
import java.util.List;

import com.zjs.biz.BookInfoBiz;

public class Role implements Serializable {
	private static final long serialVersionUID = 3175298902110269276L;
	private String name;
	private String sky;
	private List<String> permisstions; // 权限集合
	private BookInfoBiz bookInfoBiz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSky() {
		return sky;
	}

	public void setSky(String sky) {
		this.sky = sky;
	}

	public List<String> getPermisstions() {
		return permisstions;
	}

	public void setPermisstions(List<String> permisstions) {
		this.permisstions = permisstions;
	}

	public BookInfoBiz getBookInfoBiz() {
		return bookInfoBiz;
	}

	public void setBookInfoBiz(BookInfoBiz bookInfoBiz) {
		this.bookInfoBiz = bookInfoBiz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
