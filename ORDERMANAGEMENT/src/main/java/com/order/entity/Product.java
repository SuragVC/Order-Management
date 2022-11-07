package com.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Product {
	@Id
	@Column(unique = true)
	private Integer productId;
	private String productName;
	private Integer availableQty;
	private Double price;
	private Integer soledCount;
	@JsonIgnore
	private Integer adminId;
	@OneToMany
	@JsonIgnore
	private List<RatingsToProduct> rating=new ArrayList<>();
	
}
