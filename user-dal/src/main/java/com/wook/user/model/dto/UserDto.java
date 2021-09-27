package com.wook.user.model.dto;

import java.util.Date;
import java.util.List;

public class UserDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private Date createDate;
	private Date updateDate;
	private Date lastLogin;
	private String token;
	private boolean active;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<PhoneDto> getPhones() {
		return phones;
	}
	
	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
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
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public UserDto() {
	}

	public UserDto(String id, String name, String email, String password, List<PhoneDto> phones, Date createDate,
			Date updateDate, Date lastLogin, String token, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.lastLogin = lastLogin;
		this.token = token;
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones="
				+ phones + ", createDate=" + createDate + ", updateDate=" + updateDate + ", lastLogin=" + lastLogin
				+ ", token=" + token + ", active=" + active + "]";
	}
    
}
