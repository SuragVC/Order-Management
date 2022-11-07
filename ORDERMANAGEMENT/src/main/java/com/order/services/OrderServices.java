package com.order.services;

import com.order.entity.SingleOrder;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.PaymentException;
import com.order.exception.ProductException;

public interface OrderServices {
	public SingleOrder orderAProduct(String userKey,SingleOrder order)throws LoginException,ProductException,PaymentException,AddressException; 
}
