package com.wook.user.model.dto;

public class PhoneDto {
	
	private String number;
	private String cityCode;
	private String countryCode;
	
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
	
	public PhoneDto() {
		super();
	}
	
	public PhoneDto(String number, String cityCode, String countryCode) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return "PhoneDto [number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode + "]";
	}

}
