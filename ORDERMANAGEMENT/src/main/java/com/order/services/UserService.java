package com.order.services;

import com.order.entity.User;
import com.order.exception.LoginException;

public interface UserService {
public User createNewSignUp(User signUp) throws LoginException;;
	
	public User updateSignUpDetails(User signUp,String key) throws LoginException;
	
}
