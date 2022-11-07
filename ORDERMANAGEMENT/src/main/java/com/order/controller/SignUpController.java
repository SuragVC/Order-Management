package com.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.services.UserService;



@CrossOrigin(origins = "*")
@RestController
public class SignUpController {
	
	@Autowired
	private UserService signUpService;
	
	@PostMapping("/signUp")
	public ResponseEntity<User> createNewSignUpHandler(@Valid @RequestBody User newSignUp) throws LoginException {
		User newSignedUp =signUpService.createNewSignUp(newSignUp);
		return new ResponseEntity<User>(newSignedUp,HttpStatus.CREATED);

	}
	
	@PutMapping("/signUp/update")
	public ResponseEntity<User> updateSignUpDetailsHandler(@Valid @RequestBody User signUp, @RequestParam String key) throws LoginException{
		User newUpdatedSignUp = signUpService.updateSignUpDetails(signUp,key);
		return new ResponseEntity<User>(newUpdatedSignUp,HttpStatus.ACCEPTED);
	}
}
