package com.order.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AddressOfCustomer {
	@Id
	private Integer AddressId;
	private String City;
	private String State;
	private String Pincode;
	@JsonIgnore
	private Integer orderOfCustomerId;
	private LocalDateTime addressAddedTime;
	
}
