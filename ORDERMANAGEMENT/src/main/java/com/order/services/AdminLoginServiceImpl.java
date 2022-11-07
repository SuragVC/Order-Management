package com.order.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Admin;
import com.order.entity.AdminLogIn;
import com.order.entity.CurrentSessionAdmin;
import com.order.entity.CurrentSessionUser;
import com.order.entity.LogIn;
import com.order.entity.RandomIdGenerator;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.repository.AdminDAO;
import com.order.repository.AdminLogInDAO;
import com.order.repository.AdminSessionDAO;
import com.order.repository.LogInDAO;
import com.order.repository.UserDAO;
import com.order.repository.SessionDAO;
@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	private AdminDAO signUpDAO;
	
	@Autowired
	private AdminSessionDAO SessionDAO;
	
	@Autowired
	private CurrentAdminSessionService getCurrentLoginUserSession;
	
	@Autowired
	private AdminLogInDAO loginDAO;
	
	

	@Override
	public String logInAccount(AdminLogIn loginData) throws LoginException {
		Optional<Admin> options = signUpDAO.findByMobileNo(loginData.getAdminMobileNo());
		
		if(!options.isPresent()) {
			throw new LoginException("Invalid mobile Number ");
		}
		
		Admin newSignUp = options.get();

		Integer newSignUpId = newSignUp.getAdminId();
		Optional<CurrentSessionAdmin> currentSessionUser = SessionDAO.findByUserId(newSignUpId);
		
		if(currentSessionUser.isPresent()) {
			throw new LoginException("User already login with this userId");
		}
		
		if((newSignUp.getMobileNo().equals(loginData.getAdminMobileNo()))  && newSignUp.getPassword().equals(loginData.getPassword())) {
			String key = RandomString.getRandomString();
			CurrentSessionAdmin currentSessionUser2 = new CurrentSessionAdmin(newSignUp.getAdminId(), newSignUpId, key, newSignUp.getMobileNo(),LocalDateTime.now(), newSignUp.getAdminId());
			loginData.setUserId(RandomIdGenerator.getRandomInteger());
			loginData.setAdminId(newSignUp.getAdminId());
			loginDAO.save(loginData);
			SessionDAO.save(currentSessionUser2);
			return currentSessionUser2.toString();
		}else {
			throw new LoginException("Invalid mobile and Password");
		}
		
	}
	

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		Optional<CurrentSessionAdmin> currentSessionuserOptional = SessionDAO.findByUuid(key);
		
		if(!currentSessionuserOptional.isPresent()) {
			throw new LoginException("User has not looged in with this Userid");
		}
		
		CurrentSessionAdmin currentSessionUser =getCurrentLoginUserSession.getCurrentAdminSession(key);
		
		SessionDAO.delete(currentSessionUser);
		
		Optional<AdminLogIn> logindata = loginDAO.findById(currentSessionuserOptional.get().getUserId());
		
		loginDAO.delete(logindata.get());
		
		return "Logged Out Succefully....";
	}
	

}
