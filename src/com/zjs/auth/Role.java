package com.zjs.auth;
/**
 * 角色类
 * @author zxy
 *
 */

import java.io.IOException;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.zjs.biz.BizFactory;
import com.zjs.biz.BookInfoBiz;
import com.zjs.biz.impl.BookInfoBizImpl;
import com.zjs.exception.NoSuchOptionPermisstionException;

public class Role implements Serializable {
	private static final long serialVersionUID = 3175298902110269276L;
	private String name;
	private String key;
	private List<String> permisstions; // 权限集合
	private BookInfoBiz bookInfoBiz;

	public Role() {
		setName("默认角色");
		setKey("default");
		createPermisstion(key);
		// bookInfoBiz = new BookInfoBizImpl(); //可以使用工厂模式
		bookInfoBiz = (BookInfoBiz) BizFactory.getBiz("BookInfoBiz");
	}

	public Role(String name, String key) {
		this.name = name;
		this.key = key;
		createPermisstion(key);
		bookInfoBiz = (BookInfoBiz) BizFactory.getBiz("BookInfoBiz");
	}

	private void createPermisstion(String key) {
		// 读取当前的配置文件 获得当前角色的权限
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_Permisstions.propreties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 通过权限key拿到对应的权限
		String strPermisstion = props.getProperty(key);
		if (null == permisstions)
			permisstions = new ArrayList<>();
		permisstions.clear();
		// permisstions.addAll(Arrays.asList(strPermisstion.split(",")));

		String[] permisstionArray = strPermisstion.split(",");
		for (String permisstion : permisstionArray) {
			if ("".equals(permisstion))
				continue;
			permisstions.add(permisstion.trim());
		}
	}

	private boolean checkPermisstion(String optName) {
		if (null == permisstions || permisstions.size() == 0)
			return false;
		for (String permisstion : permisstions) {
			if (optName.equals(permisstion))
				return true;
			if (permisstion.equals("bookinfobiz.*") && optName.startsWith("bookinfobiz"))
				return true;
			if (permisstion.equals("userbiz.*") && optName.startsWith("userbiz"))
				return true;
		}
		return false;
	}

	public boolean inStore(String isbn,int inCount) {
		if(checkPermisstion("bookinfobiz.inStore")) {
			bookInfoBiz.inStore(isbn, inCount);
			return true;
			}
		else {
			throw new NoSuchOptionPermisstionException(name+"没有操作<bookinfobiz.inStore>的权限");
		}
	}
	
	public boolean outStore(String isbn,int inCount) {
		if(checkPermisstion("bookinfobiz.outStore")) {
			return bookInfoBiz.outStore(isbn, inCount);
			}
		else {
			throw new NoSuchOptionPermisstionException(name+"没有操作<bookinfobiz.outStore>的权限");
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
