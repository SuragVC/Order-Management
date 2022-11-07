package com.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class OrderOfCustomer {
	@Id
	private Integer orderOfCustomerId;
	@JsonIgnore
	private Integer customerId;
	@OneToMany
	private List<SingleOrder>listOfOrders;
	private Double TotalPurchasedAmount;
	private Double lastPurchasedAmount;
	private LocalDateTime lastPurchasedDate;
	@OneToMany
	private List<Payments>paymentsDoneByCustomer;
	@OneToMany
	private List<RatingsToProduct>ratings;
	@OneToMany
	private List<AddressOfCustomer>addreses;
	private LocalDateTime dateOfCustomerRegistration;
}
