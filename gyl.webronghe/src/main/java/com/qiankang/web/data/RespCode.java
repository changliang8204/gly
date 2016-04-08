package com.qiankang.web.data;

public enum RespCode {
	SUCCESS("00","处理成功"),
	CHECK_FAIL("01","验证失败"),
	AUTH_FAIL("02","授权失败"),
	PARAM_ILLEGAL("03","参数非法"),
	OPERATE_ILLEGAL("04","操作非法"),
	OBJECT_NOT_EXIST("05","对象不存在"),
	DATA_CONFLICT("06","数据冲突"),
	NO_LOGIN("07","未登录"),
	SYSTEM_EXCEPTION("96","系统内部异常");
	
	private String code;
	private String desp;
	
	private RespCode(String code, String desp){
		this.code = code;
		this.desp = desp;
	}

	public String getCode() {
		return code;
	}

	public String getDesp() {
		return desp;
	}
}
