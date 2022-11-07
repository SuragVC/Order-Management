package com.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Product;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.exception.ProductException;
import com.order.services.ProductServices;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductServices productService;
	@PostMapping("/add")
	public ResponseEntity<Product> createNewSignUpHandler(@RequestParam String adminKey,@Valid @RequestBody Product product) throws LoginException, ProductException {
		Product savedProduct =productService.createProduct(adminKey, product);
		return new ResponseEntity<Product>(savedProduct,HttpStatus.CREATED);

	}
	@GetMapping("/all/{adminKey}")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam String adminKey) throws LoginException, ProductException {
		List<Product> list =productService.getAllProduct(adminKey);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	@GetMapping("/all/admin/{adminKey}")
	public ResponseEntity<List<Product>> getAllProductProductOfAdmin(@RequestParam String adminKey) throws LoginException, ProductException {
		List<Product> list =productService.getAllProductByAnAdmin(adminKey);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
}
