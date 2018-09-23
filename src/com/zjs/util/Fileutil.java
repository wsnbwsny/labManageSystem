package com.zjs.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.zjs.entity.Bookinfo;

/**
 * 文件读取工具类
 * 
 * @author zxy
 *
 */
public class Fileutil {
	private static final String BookInfoFilePath = "BookInfo.dat";

	public static void SaveBookInfoMap(Map<String, Bookinfo> bookInfoMap) {

		SaveObject(bookInfoMap, BookInfoFilePath);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Bookinfo> ReadBookInfoMap() {
		Object obj = ReadObject(BookInfoFilePath);
		if (obj == null)
			return null;

		return (Map<String, Bookinfo>) obj;

	}

	/**
	 * 从文件中读取对象
	 * 
	 * @param bookinfofilepath2
	 * @return
	 */
	private static Object ReadObject(String filePath) {
		try (FileInputStream fin = new FileInputStream(filePath); ObjectInputStream in = new ObjectInputStream(fin);) {
			return in.readObject();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通用保存的方法
	 * 
	 * @param object
	 * @param filePath
	 */
	public static void SaveObject(Object object, String filePath) {
		try (FileOutputStream fout = new FileOutputStream(filePath, false);
				ObjectOutputStream out = new ObjectOutputStream(fout);)

		{
			out.writeObject(object);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
