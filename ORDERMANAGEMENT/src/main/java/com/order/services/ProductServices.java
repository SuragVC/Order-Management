package com.order.services;

import java.util.List;

import com.order.entity.Product;
import com.order.exception.LoginException;
import com.order.exception.ProductException;

public interface ProductServices {
	public Product createProduct(String adminKey,Product product)throws ProductException,LoginException;
	public List<Product>getAllProduct(String userKey)throws ProductException,LoginException;
	public List<Product>getAllProductByAnAdmin(String adminKey)throws ProductException,LoginException;
}
