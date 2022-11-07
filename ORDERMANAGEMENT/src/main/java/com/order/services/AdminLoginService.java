package com.order.services;

import com.order.entity.AdminLogIn;
import com.order.entity.LogIn;
import com.order.exception.LoginException;

public interface AdminLoginService {
	
	public String logInAccount(AdminLogIn loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
