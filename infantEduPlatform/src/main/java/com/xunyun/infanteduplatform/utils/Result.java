package com.xunyun.infanteduplatform.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *类说明:业务方法返回结果<br/>
 *创建人:羊鑫<br/>
 *创建日期:2015年11月25日<br/> 
 * 
 */
public class Result implements Serializable{

	/**
	 * 变量类型：long    变量：serialVersionUID 
	 */
	private static final long serialVersionUID = -1980859699542376999L;

	public enum CODE {
		SUCCESS, FAILURE;
	}

	private CODE errCode = CODE.SUCCESS; // 错误编码
	private String errMsg; // 错误信息
	private String url; // 错误信息
	private Map<String, Object> model = new HashMap<String, Object>();

	public Result() {
		this(CODE.SUCCESS);
	}
	
	public Result(CODE errCode){
	    super();
	    this.errCode = errCode;
	}

	public Result(CODE errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public Result(CODE errCode, String errMsg, Map<String, Object> model) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.model = model;
	}
	
	public Result(String key, Object model){
	    this(CODE.SUCCESS);
	    this.model.put(key, model);
	}
	
	public boolean isSuccess(){
		return this.errCode.equals(CODE.SUCCESS);
	}

	public CODE getErrCode() {
		return errCode;
	}

	public void setErrCode(CODE errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
