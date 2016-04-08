package com.qiankang.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class SerializeUtil {
	/**
	 * Object序列化为字节数组
	 * Object 必须实现 serialize接口
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) throws Exception{
		byte[] bytes = null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			bytes = baos.toByteArray();
		} catch(IOException ioe){
			throw new Exception("[serialize error]"+ioe.getCause());
		}finally{
			oos.close();
			baos.close();
		}
		return bytes;
	}
	/**
	 * 从字节数组中反序列化为Object
	 * Object须实现serialize接口
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) throws Exception{
		Object obj = null;
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			throw new Exception("[unserialize error]"+e.getCause());
		} finally{
			bais.close();
		}
		return obj;
	}
}
