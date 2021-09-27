package com.wook.user.model.dto;

import java.util.Date;

public class ResponseDto {
	
	private Long id;
	private Date createDate;
	private Date updateDate;
	private Date lastLogin;
	private String token;
	private boolean isActive;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public ResponseDto() {
	}

	public ResponseDto(Long id, Date createDate, Date updateDate, Date lastLogin, String token, boolean isActive) {
		this.id = id;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.lastLogin = lastLogin;
		this.token = token;
		this.isActive = isActive;
	}

}
