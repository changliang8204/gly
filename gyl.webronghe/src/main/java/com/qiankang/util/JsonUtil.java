package com.qiankang.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.qiankang.web.data.RespCode;

public class JsonUtil {

	private static Gson gson = new GsonBuilder().registerTypeAdapter(
			RespCode.class, new FrontRespCodeEnumSerializer()).create();

	/**
	 * @MethodName : toJson
	 * @Description : 将对象转为JSON串，此方法能够满足大部分需求
	 * @param src
	 *            :将要被转化的对象
	 * @return :转化后的JSON串
	 */
	public static String toJson(Object src) {
		if (src == null) {
			return gson.toJson(JsonNull.INSTANCE);
		}
		return gson.toJson(src);
	}

	/**
	 * @MethodName : toObject
	 * @Description : 将JSON串转为对象，此方法能够满足大部分需求
	 * @param json
	 *            :JSON串
	 * @return :转化后的对象
	 */
	public static Object toObject(String json, Class<?> type) {
		return gson.fromJson(json, type);
	}
}
