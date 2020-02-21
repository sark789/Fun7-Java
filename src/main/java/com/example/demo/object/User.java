package com.example.demo.object;

public class User {
	private String userID;
	private String timezone;
	private String cc;
	private String apiCalls;
	
	

	public User() {
		super();
	}
		
	public User(String userID, String timezone, String cc) {
		super();
		this.userID = userID;
		this.timezone = timezone;
		this.cc = cc;
		this.apiCalls = "";
	}
	
	public String getApiCalls() {
		return apiCalls;
	}

	public void setApiCalls(String apiCalls) {
		this.apiCalls = apiCalls;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	
}
