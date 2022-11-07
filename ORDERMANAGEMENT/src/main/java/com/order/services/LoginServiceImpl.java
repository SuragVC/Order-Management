package com.order.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.CurrentSessionUser;
import com.order.entity.LogIn;
import com.order.entity.RandomIdGenerator;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.repository.LogInDAO;
import com.order.repository.UserDAO;
import com.order.repository.SessionDAO;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserDAO signUpDAO;
	
	@Autowired
	private SessionDAO SessionDAO;
	
	@Autowired
	private CurrentUserSessionService getCurrentLoginUserSession;
	
	@Autowired
	private LogInDAO loginDAO;
	
	

	@Override
	public String logInAccount(LogIn loginData) throws LoginException {
		
		Optional<User> options = signUpDAO.findByMobileNo(loginData.getMobileNo());
		
		if(!options.isPresent()) {
			throw new LoginException("Invalid mobile Number ");
		}
		
		User newSignUp = options.get();

		Optional<CurrentSessionUser> currentSessionUser = SessionDAO.findByMobileNo(newSignUp.getMobileNo());
	
		if(currentSessionUser.isPresent()) {
			throw new LoginException("User already login with this userId");
		}
		
		if((newSignUp.getMobileNo().equals(loginData.getMobileNo()))  && newSignUp.getPassword().equals(loginData.getPassword())) {
			
			String key = RandomIdGenerator.getRandomStringSessionId();		
			CurrentSessionUser currentSessionUser2 = new CurrentSessionUser(newSignUp.getOrderOfcustomer().getOrderOfCustomerId(), key, newSignUp.getMobileNo(),LocalDateTime.now());
			System.out.println(loginData);
			loginDAO.save(loginData);
			SessionDAO.save(currentSessionUser2);
			return currentSessionUser2.toString();
		}else {
			throw new LoginException("Invalid mobile and Password");
		}
		
	}
	

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		Optional<CurrentSessionUser> currentSessionuserOptional = SessionDAO.findByUuid(key);
		
		if(!currentSessionuserOptional.isPresent()) {
			throw new LoginException("User has not looged in with this Userid");
		}
		
		CurrentSessionUser currentSessionUser =getCurrentLoginUserSession.getCurrentUserSession(key);
		
		SessionDAO.delete(currentSessionUser);
		
		Optional<LogIn> logindata = loginDAO.findById(currentSessionuserOptional.get().getOrderOfCustomerId());
		
		loginDAO.delete(logindata.get());
		
		return "Logged Out Succefully....";
	}
	

}
