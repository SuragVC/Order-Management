package com.order.services;

import com.order.entity.Admin;
import com.order.entity.User;
import com.order.exception.LoginException;

public interface AdminService {
	public Admin createNewAdminUp(Admin signUp) throws LoginException;;
	
	
}
