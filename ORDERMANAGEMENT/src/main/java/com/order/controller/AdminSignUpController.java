package com.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Admin;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.services.AdminService;
import com.order.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminSignUpController {
	
	@Autowired
	private AdminService signUpService;
	@PostMapping("/signUp")
	public ResponseEntity<Admin> createNewSignUpHandler(@Valid @RequestBody Admin  newSignUp) throws LoginException {
		Admin newSignedUp =signUpService.createNewAdminUp(newSignUp);
		return new ResponseEntity<Admin >(newSignedUp,HttpStatus.CREATED);

	}
}
