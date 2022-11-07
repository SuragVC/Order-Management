package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.OrderOfCustomer;
import com.order.entity.User;

public interface OrderOfCustomerDAO extends JpaRepository<OrderOfCustomer, Integer>{
	OrderOfCustomer findByCustomerId(Integer customerId);
}
