package com.order.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Admin;
import com.order.entity.AdminLogIn;
import com.order.entity.CurrentSessionAdmin;
import com.order.entity.Product;
import com.order.entity.RandomIdGenerator;
import com.order.entity.RatingsToProduct;
import com.order.exception.LoginException;
import com.order.exception.ProductException;
import com.order.repository.AdminDAO;
import com.order.repository.AdminLogInDAO;
import com.order.repository.AdminSessionDAO;
import com.order.repository.ProductDAO;

@Service
public class ProductServiceImpl implements ProductServices{
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private AdminSessionDAO adminLogin;
	@Autowired
	private AdminDAO adminDao;
	@Override
	public Product createProduct(String adminKey,Product product) throws ProductException,LoginException {
		Optional<CurrentSessionAdmin> adminOpt = adminLogin.findByUuid(adminKey);
		if(adminOpt.isPresent()) {
			product.setProductId(RandomIdGenerator.getRandomProductId());
			product.setAdminId(adminOpt.get().getAdminId());
			List<RatingsToProduct> rating=new ArrayList<>();
			product.setRating(rating);
			Optional<Admin> admin=adminDao.findById(adminOpt.get().getAdminId());
			List<Product>productList=admin.get().getProductList();
			productList.add(product);
			return productDao.save(product);
		}else {
			throw new LoginException("Admin needs to logIn to add a product");
		}
		
	}
	@Override
	public List<Product> getAllProduct(String adminKey) throws ProductException,LoginException {
		Optional<CurrentSessionAdmin> adminOpt = adminLogin.findByUuid(adminKey);
		if(adminOpt.isPresent()) {
			List<Product>productList=productDao.findAll();
			return productList;
		}else {
			throw new LoginException("Admin needs to login");
		}		
	}
	@Override
	public List<Product> getAllProductByAnAdmin(String adminKey) throws ProductException, LoginException {
		Optional<CurrentSessionAdmin> adminOpt = adminLogin.findByUuid(adminKey);
		if(adminOpt.isPresent()) {
			Optional<Admin> admin=adminDao.findById(adminOpt.get().getAdminId());
			List<Product>productList=admin.get().getProductList();
			if(productList.size()==0) {
				throw new ProductException("Admin not added any product");
			}
			return productList;
		}else {
			throw new LoginException("Admin needs to login");
		}	
		
	}
}
