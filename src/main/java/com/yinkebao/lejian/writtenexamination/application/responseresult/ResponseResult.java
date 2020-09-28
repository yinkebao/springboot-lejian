package com.yinkebao.lejian.writtenexamination.application.responseresult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName ResponseResult
 * @Description 统一返回值结构
 * @Author ykb
 * @Date 2020/9/28
 */
public class ResponseResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String NO_MESSAGE = "no message";
	private String msg = "no message";
	private T data;
	private LocalDateTime timestamp = LocalDateTime.now();
	private String url;
	protected Integer code;
	protected String errorCode;
	protected boolean success;

	private ResponseResult() {
	}

	protected static ResponseResult instance() {
		return new ResponseResult();
	}

	public JSONObject loadData() {
		return (JSONObject) JSON.toJSON(this.data);
	}

	public <M> M loadData(Class<M> mClass) {
		return JSON.parseObject(JSON.toJSONString(this.data), mClass);
	}

	public T loadData(TypeReference<T> typeReference) {
		return !(this.data instanceof Collection) && !(this.data instanceof Map) ? this.data : JSON.parseObject(JSON.toJSONString(this.data), typeReference, new Feature[0]);
	}

	public String getMsg() {
		return this.msg;
	}

	public T getData() {
		return this.data;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public String getUrl() {
		return this.url;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
