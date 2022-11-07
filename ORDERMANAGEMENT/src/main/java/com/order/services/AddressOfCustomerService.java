package com.order.services;

import java.util.List;

import com.order.entity.AddressOfCustomer;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.UserNotFoundException;

public interface AddressOfCustomerService {
	public AddressOfCustomer createAddress(String userKey,AddressOfCustomer address)throws AddressException,LoginException;
	public List<AddressOfCustomer> listOfaddress(String userKey) throws AddressException, LoginException;
	public AddressOfCustomer deleteAddressFromAddressList(String userKey,Integer AddressId)throws AddressException,LoginException;
}
