package com.order.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
public class CurrentSessionUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique =  true)
	private Integer OrderOfCustomerId;
	
	private String uuid;
	
	private String mobileNo;
	
	private LocalDateTime localDateTime;

	

	public CurrentSessionUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CurrentSessionUser(Integer orderOfCustomerId, String uuid, String mobileNo,
			LocalDateTime localDateTime) {
		super();
		OrderOfCustomerId = orderOfCustomerId;
		this.uuid = uuid;
		this.mobileNo = mobileNo;
		this.localDateTime = localDateTime;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getOrderOfCustomerId() {
		return OrderOfCustomerId;
	}



	public void setOrderOfCustomerId(Integer orderOfCustomerId) {
		OrderOfCustomerId = orderOfCustomerId;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}



	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}



	@Override
	public String toString() {
		return "Log In Sussefull Your User Key is "+uuid;
	}
	
	

	
}
