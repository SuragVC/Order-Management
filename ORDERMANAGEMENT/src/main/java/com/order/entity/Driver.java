package com.order.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Driver {
	@Id
	private Integer driverId;
	private String DriverName;
	private Integer CurentOrderId;
	private LocalDate curentOrderCompletionTime;
	private boolean onWork;
	private Integer CustomerId;
	private Double TotalPaymentColected;
}
