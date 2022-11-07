package com.order.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.AddressOfCustomer;
import com.order.entity.SingleOrder;
import com.order.entity.OrderOfCustomer;
import com.order.entity.Payments;
import com.order.entity.RandomIdGenerator;
import com.order.entity.RatingsToProduct;
import com.order.entity.User;
import com.order.exception.LoginException;
import com.order.repository.OrderOfCustomerDAO;
import com.order.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO signUpDAO;
	
	@Autowired
	private CurrentUserSessionService getCurrentLoginUserSession;
	@Autowired
	private OrderOfCustomerDAO orderOfcustomer;
	
	@Override
	public User createNewSignUp(User newSignUp) throws LoginException {
		
		Optional<User> opt = signUpDAO.findByMobileNo(newSignUp.getMobileNo());
		if(opt.isPresent())
		{
			throw new LoginException("User Already Exist!");
		}
		OrderOfCustomer orderOfCustomer =new OrderOfCustomer();
		orderOfCustomer.setOrderOfCustomerId(RandomIdGenerator.getRandomInteger());
		List<AddressOfCustomer>addressList=new ArrayList<>();
		orderOfCustomer.setAddreses(addressList);
		List<Payments>paymentList = new ArrayList();
		orderOfCustomer.setPaymentsDoneByCustomer(paymentList);
		List<SingleOrder>orderList = new ArrayList();
		orderOfCustomer.setListOfOrders(orderList);
		List<RatingsToProduct>ratingList = new ArrayList();
		orderOfCustomer.setRatings(ratingList);
		orderOfCustomer.setTotalPurchasedAmount(0.0);
		orderOfCustomer.setLastPurchasedAmount(0.0);
		orderOfCustomer.setDateOfCustomerRegistration(LocalDateTime.now());
		newSignUp.setUserId(RandomIdGenerator.getRandomInteger());
		newSignUp.setOrderOfcustomer(orderOfCustomer);
		orderOfCustomer.setCustomerId(newSignUp.getUserId());
		orderOfcustomer.save(orderOfCustomer);
		signUpDAO.save(newSignUp);
		return newSignUp;
	}
	@Override
	public User updateSignUpDetails(User signUp, String key) throws LoginException {
		User signUpDetails = getCurrentLoginUserSession.getSignUpDetails(key);
		
		if(signUpDetails == null)
		{
			throw new LoginException("UnAuthorized!!! No User Found....Try To login first!");
		}
		
		if(signUpDetails.getUserId() == signUp.getUserId())
			{
			signUpDAO.save(signUp);
			return signUp;
			}
		else
			throw new LoginException("Can't change UserId!!");
	}

	
	

}
