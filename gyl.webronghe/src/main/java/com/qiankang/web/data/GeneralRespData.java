package com.qiankang.web.data;

public class GeneralRespData implements JsonEntity {
	protected RespCode code = RespCode.SYSTEM_EXCEPTION;
	protected String message;
	
	public RespCode getCode() {
		return code;
	}
	public void setCode(RespCode code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
