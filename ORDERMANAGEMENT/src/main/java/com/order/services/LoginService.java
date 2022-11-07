package com.order.services;

import com.order.entity.LogIn;
import com.order.exception.LoginException;

public interface LoginService {
	
	public String logInAccount(LogIn loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
