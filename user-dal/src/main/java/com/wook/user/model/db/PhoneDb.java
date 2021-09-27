package com.wook.user.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phone_db")
public class PhoneDb {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="number", nullable = false)
	private String number;
	
	@Column(name="cityCode", nullable = false)
	private String cityCode;
	
	@Column(name="countryCode", nullable = false)
	private String countryCode;
	
	@Column(name="user_id")
	private Long userId;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public PhoneDb() {
	}


	public PhoneDb(String number, String cityCode, String countryCode) {
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}

	public PhoneDb(String number, String cityCode, String countryCode, Long userId) {
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.userId = userId;
	}
	
	
	
}
