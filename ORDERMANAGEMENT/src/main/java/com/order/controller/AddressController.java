package com.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.AddressOfCustomer;
import com.order.entity.Product;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.ProductException;
import com.order.exception.UserNotFoundException;
import com.order.services.AddressOfCustomerService;
import com.order.services.AddressOfCustomerServiceImpl;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressOfCustomerService addressServices;
	
	@PostMapping("/add")
	public ResponseEntity<AddressOfCustomer> addAddressToCustomer(@RequestParam String userKey,@Valid @RequestBody AddressOfCustomer address) throws AddressException,  LoginException {
		AddressOfCustomer addressOfCustomer = addressServices.createAddress(userKey,address);
		return new ResponseEntity<AddressOfCustomer>(addressOfCustomer,HttpStatus.OK);
	}
	
	@GetMapping("/specific/{userKey}")
	public ResponseEntity<List<AddressOfCustomer>> getAllAddressOfCustomerByID(@RequestParam String userKey) throws LoginException, AddressException {
		List<AddressOfCustomer> addressOfCustomerlist =addressServices.listOfaddress(userKey);
		return new ResponseEntity<List<AddressOfCustomer>>(addressOfCustomerlist,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/specific/{userKey}/{addressId}")
	public ResponseEntity<AddressOfCustomer> deleteAddressOfCustomerByID(@RequestParam String userKey,@RequestParam Integer addressId) throws LoginException, AddressException {
		AddressOfCustomer addressOfCustomer =addressServices.deleteAddressFromAddressList(userKey, addressId);
		return new ResponseEntity<AddressOfCustomer>(addressOfCustomer,HttpStatus.ACCEPTED);
	}
}
