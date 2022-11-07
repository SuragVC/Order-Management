package com.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class RatingsToProduct {
	@Id
	private Integer ratingsToProductId;
	private Integer rating;
	private Integer productId;
	private Integer userId;
	@ManyToOne
	private OrderOfCustomer customer;
}
