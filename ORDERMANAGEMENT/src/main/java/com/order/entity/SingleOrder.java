package com.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class SingleOrder {
	@Id
	@Column(unique = true)
	private Integer orderId;
	private Double paymentAmount;
	private Double returnedAmount;
	private Integer addressId;
	private Integer productId_1;
	private Integer product_1_Qty;
	private Integer productId_2;
	private Integer product_2_Qty;
	private Integer productId_3;
	private Integer product_3_Qty;
	@JsonIgnore
	private Integer orderOfCustomerId;
	@JsonIgnore
	private LocalDateTime dateOfPayment;
	@OneToOne
	private Payments payment;
}
