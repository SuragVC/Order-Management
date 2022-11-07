package com.order.services;

import java.util.List;
import java.util.Optional;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.AddressOfCustomer;
import com.order.entity.CurrentSessionUser;
import com.order.entity.OrderOfCustomer;
import com.order.entity.RandomIdGenerator;
import com.order.entity.User;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.UserNotFoundException;
import com.order.repository.AddressDAO;
import com.order.repository.OrderOfCustomerDAO;
import com.order.repository.SessionDAO;
import com.order.repository.UserDAO;

@Service
public class AddressOfCustomerServiceImpl implements AddressOfCustomerService{
	@Autowired
	private AddressDAO addressDao;
	@Autowired 
	private OrderOfCustomerDAO orderOfCustomerDao;
	@Autowired
	private SessionDAO sessionDao;
	public AddressOfCustomer createAddress(String userKey,AddressOfCustomer address) throws AddressException,LoginException {
		System.out.println("dfsj");
		Optional<CurrentSessionUser> userOpt = sessionDao.findByUuid(userKey);
		if(userOpt.isEmpty()) {
			throw new LoginException("User needs to log in");
		}
		else {
			address.setAddressId(RandomIdGenerator.getRandomInteger());
			CurrentSessionUser user= userOpt.get();
			address.setOrderOfCustomerId(user.getOrderOfCustomerId());
			Optional<OrderOfCustomer> orderOfCustomer= orderOfCustomerDao.findById(user.getOrderOfCustomerId());
			
			List<AddressOfCustomer>listOfaddress = orderOfCustomer.get().getAddreses();
			if(listOfaddress.size()>=3) {
				throw new AddressException("User already have 3 address");
			}
			listOfaddress.add(address);
			addressDao.save(address);
			return address;
		}
	}
	
	@Override
	public List<AddressOfCustomer> listOfaddress(String userKey) throws AddressException, LoginException {
		Optional<CurrentSessionUser> userOpt = sessionDao.findByUuid(userKey);
		if(userOpt.isEmpty()) {
			throw new LoginException("User needs to log in");
		}
		List<AddressOfCustomer>listOfaddress=addressDao.findByOrderOfCustomerId(userOpt.get().getOrderOfCustomerId());
		if(listOfaddress.isEmpty()) {
			throw new AddressException("No address added to user");
		}else {
				return listOfaddress;
		}
	}

	@Override
	public AddressOfCustomer deleteAddressFromAddressList(String userKey,Integer addressId)throws AddressException, LoginException {
		Optional<CurrentSessionUser> userOpt = sessionDao.findByUuid(userKey);
		if(userOpt.isPresent()) {
		Optional<OrderOfCustomer> orderOfCustomer= orderOfCustomerDao.findById(userOpt.get().getOrderOfCustomerId());				
		List<AddressOfCustomer>listOfaddress = orderOfCustomer.get().getAddreses();
		if(listOfaddress.isEmpty()) {
			throw new AddressException("No address added to the User");
		}else {
				Optional<AddressOfCustomer> address = addressDao.findById(addressId);
				if(address.isPresent()) {
					listOfaddress.remove(address.get());
					addressDao.delete(address.get());
					return address.get();
				}else {
					throw new AddressException("No Address found with id "+addressId);
				}
			}
		}else {
			throw new LoginException("User needs to log in");
		}
	}
}
