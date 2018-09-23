package com.zjs.biz;
/**
 * 定义所有业务的通用操作
 * @author zxy
 *
 */

import java.io.Serializable;
import java.util.Map;

public interface Biz<T> extends Serializable{  //使用泛型编程
	
	public boolean add(T t);  //增加
	
	public boolean del(T t);   //删除
	
	public T update(T t);  //修改
	
	public T findById(T t);
	
	public Map<String, T> findAll();

}
