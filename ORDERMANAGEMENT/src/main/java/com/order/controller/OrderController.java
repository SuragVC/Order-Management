package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.SingleOrder;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.PaymentException;
import com.order.exception.ProductException;
import com.order.services.OrderServices;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServices orderServices;
	
	@PostMapping("/new")
	public ResponseEntity<SingleOrder> orderAProduct(String userKey,SingleOrder order) throws LoginException, ProductException, PaymentException, AddressException{
		SingleOrder orderedProduct = orderServices.orderAProduct(userKey, order);
		return new ResponseEntity<SingleOrder>(orderedProduct,HttpStatus.ACCEPTED);
	}
}
