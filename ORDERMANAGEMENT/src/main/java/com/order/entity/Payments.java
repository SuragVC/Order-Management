package com.order.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class Payments {
	@Id
	@Column(unique=true)
	@JsonIgnore
	private Integer paymentId;
	@JsonIgnore
	private Integer orderId;
	private Double totalPayment;
	private Double totalAmountForProduct;
	private Double amountReturned;
	private PaymentMethod paymentType;
	@JsonIgnore
	private LocalDateTime paymentTime;
	
}
