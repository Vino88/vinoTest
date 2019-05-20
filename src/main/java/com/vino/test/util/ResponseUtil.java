package com.vino.test.util;

import java.util.List;

public class ResponseUtil {
	
	private boolean status;
	private List<?> data;
	private String message;
	
	public ResponseUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseUtil(boolean status, List<?> data, String message) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ResponseUtil(boolean status, String message) {
		this.status = status;
		this.message = message;
		this.data = null;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
