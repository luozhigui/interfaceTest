package com.pinke.entities;
/**
 * @author liudao
 *
 */
public class Authorization {
	/**
	 */
	private String status;
	/**
	 */
	private String login;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return login;
	}
	public void setToken(String token) {
		this.login = token;
	}
	@Override
	public String toString() {
		return "Authorization [status=" + status + ", token=" + login + "]";
	}
}
