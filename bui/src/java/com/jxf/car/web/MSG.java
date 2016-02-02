package com.jxf.car.web;

public class MSG {
	private boolean isSuccess = true;
	private Object info = "成功";
	private int code = 0;

	public static MSG createMSG(int code, boolean isSuccess, Object info) {
		MSG msg = new MSG();
		msg.isSuccess = isSuccess;
		msg.code = code;
		msg.info = info;
		return msg;
	}

	public static MSG createErrorMSG(int code, Object info) {
		return createMSG(code, false, info);
	}

	public static MSG createSuccessMSG() {
		return createMSG(0, true, "");
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MSG [isSuccess=" + isSuccess + ", info=" + info + ", code="
				+ code + "]";
	}

}
