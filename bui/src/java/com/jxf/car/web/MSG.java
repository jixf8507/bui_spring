/**
 * @title MSG.java
 * @package com.baizhu.model
 * @projectName VolunteerAPI
 * @date 2014年5月15日
 * @Copyright: 2014 www.byzhu.com Inc. All rights reserved.
 */

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

	/**
	 * 创建出错消息
	 * 
	 * @param code
	 * @param info
	 * @return
	 */
	public static MSG createErrorMSG(int code, Object info) {
		return createMSG(code, false, info);
	}

	/**
	 * 创建成功消息
	 * 
	 * @return
	 */
	public static MSG createSuccessMSG() {
		return createMSG(0, false, "");
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the info
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(Object info) {
		this.info = info;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MSG [isSuccess=" + isSuccess + ", info=" + info + ", code="
				+ code + "]";
	}

}
