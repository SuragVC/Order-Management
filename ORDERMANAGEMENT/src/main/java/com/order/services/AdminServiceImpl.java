package com.order.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.entity.Admin;
import com.order.entity.Driver;
import com.order.entity.Product;
import com.order.exception.LoginException;
import com.order.repository.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO signUpDAO;
	
	
	@Override
	public Admin createNewAdminUp(Admin signUp) throws LoginException {
		Optional<Admin> opt = signUpDAO.findByMobileNo(signUp.getMobileNo());
		if(opt.isPresent())
		{
			throw new LoginException("Admin Already Exist!");
		}
		List<Product>productList=new ArrayList();
		signUp.setProductList(productList);
		List<Driver>driverList=new ArrayList();
		signUp.setDriverList(driverList);
		return signUpDAO.save(signUp);
	}
}
