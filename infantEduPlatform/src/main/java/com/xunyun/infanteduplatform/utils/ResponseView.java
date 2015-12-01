package com.xunyun.infanteduplatform.utils;

/**
 * Ajax返回数据
 * 
 *
 */
public class ResponseView {

	// 成功标志
	private Integer result;
	// 消息
	private String message;
	// 数据
	private Object data;

	public ResponseView(Integer result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public ResponseView(Integer result) {
		super();
		this.result = result;
	}

	public ResponseView() {
		super();
	}

	/**
	 * @return the result
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
