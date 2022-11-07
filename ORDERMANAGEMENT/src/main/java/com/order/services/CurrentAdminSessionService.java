package com.order.services;

import com.order.entity.Admin;
import com.order.entity.CurrentSessionAdmin;
import com.order.entity.CurrentSessionUser;
import com.order.entity.User;
import com.order.exception.LoginException;

public interface CurrentAdminSessionService {
	public CurrentSessionAdmin getCurrentAdminSession(String key) throws LoginException;
	public Admin  getSignUpDetails(String key) throws LoginException;
	public Integer getCurrentAdminSessionId(String key) throws LoginException;
}
