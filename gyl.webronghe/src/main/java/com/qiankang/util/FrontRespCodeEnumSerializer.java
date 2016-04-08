package com.qiankang.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.qiankang.web.data.RespCode;

public class FrontRespCodeEnumSerializer implements JsonSerializer<RespCode>,
JsonDeserializer<RespCode>{

	@Override
	public JsonElement serialize(RespCode src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.getCode());
	}

	@Override
	public RespCode deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		String code = json.getAsString();
		RespCode[] respCodeArray = RespCode.values();
		for(RespCode rc : respCodeArray){
			if(code.equals(rc.getCode())){
					return rc;
			}
		}
		return null;
	}

	
}
