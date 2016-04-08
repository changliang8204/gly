package com.qiankang.spring;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.qiankang.util.JsonUtil;
import com.qiankang.web.data.JsonEntity;

public class GsonHttpMessageConverter extends AbstractHttpMessageConverter {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	@Override
	protected boolean supports(Class clazz) {
		boolean result = false;
		if(JsonEntity.class.isAssignableFrom(clazz))
			result = true;
		return result;
	}

	@Override
	protected boolean canRead(MediaType mediaType) {
		return true;
	}

	@Override
	protected boolean canWrite(MediaType mediaType) {
		return true;
	}

	@Override
	protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return StreamUtils.copyToString(inputMessage.getBody(), DEFAULT_CHARSET);
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();
		String text = JsonUtil.toJson(t);
		byte[] bytes = text.getBytes(DEFAULT_CHARSET);
		out.write(bytes);
	}

}
