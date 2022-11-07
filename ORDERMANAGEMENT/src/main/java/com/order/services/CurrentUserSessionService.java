package com.order.services;

import com.order.entity.CurrentSessionUser;
import com.order.entity.User;
import com.order.exception.LoginException;

public interface CurrentUserSessionService {
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException;
	public Integer getCurrentUserSessionId(String key) throws LoginException;
	
	public User getSignUpDetails(String key) throws LoginException;;
}
