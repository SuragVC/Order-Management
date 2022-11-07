package com.order.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Admin;
import com.order.entity.CurrentSessionAdmin;
import com.order.entity.CurrentSessionUser;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.repository.AdminDAO;
import com.order.repository.AdminSessionDAO;
import com.order.repository.SessionDAO;
import com.order.repository.UserDAO;

@Service
public class CurrentAdminSessionServiceImpl implements CurrentAdminSessionService{
	
	@Autowired
	private AdminSessionDAO sessionDAO;
	
	@Autowired
	private AdminDAO signUpDAO;
	
	@Override
	public CurrentSessionAdmin getCurrentAdminSession(String key) throws LoginException {
		Optional<CurrentSessionAdmin> currentSessionuser = sessionDAO.findByUuid(key);
		
		if(currentSessionuser.isPresent()) {
			return currentSessionuser.get();
		}else {
			throw new LoginException("UnAuthorized!!!");
		}
	}

	

	@Override
	public Admin getSignUpDetails(String key) throws LoginException {
		Optional<CurrentSessionAdmin> currentUser = sessionDAO.findByUuid(key);
		if(!currentUser.isPresent())
		{
			return null;
		}
		Integer SignUpUserId = currentUser.get().getId();
		return (signUpDAO.findById(SignUpUserId)).get();
	}



	@Override
	public Integer getCurrentAdminSessionId(String key) throws LoginException {
		Optional<CurrentSessionAdmin> currentAdmin = sessionDAO.findByUuid(key);
		if(!currentAdmin.isPresent())
		{
			throw new LoginException("UnAuthorized!!!");
		}
		return currentAdmin.get().getId();
	}

}
