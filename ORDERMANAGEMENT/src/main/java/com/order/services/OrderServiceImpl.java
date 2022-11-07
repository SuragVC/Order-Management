package com.order.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.AddressOfCustomer;
import com.order.entity.CurrentSessionUser;
import com.order.entity.SingleOrder;
import com.order.entity.OrderOfCustomer;
import com.order.entity.Payments;
import com.order.entity.Product;
import com.order.entity.RandomIdGenerator;
import com.order.exception.AddressException;
import com.order.exception.LoginException;
import com.order.exception.PaymentException;
import com.order.exception.ProductException;
import com.order.repository.AddressDAO;
import com.order.repository.OrderDAO;
import com.order.repository.OrderOfCustomerDAO;
import com.order.repository.PaymentDAO;
import com.order.repository.ProductDAO;
import com.order.repository.SessionDAO;

@Service
public class OrderServiceImpl implements OrderServices{
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private SessionDAO currentSession;
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private OrderOfCustomerDAO orderOfCustomerDao;
	@Autowired
	private AddressDAO addressDao;
	@Autowired
	private PaymentDAO paymentDao;
	
	@Override
	public SingleOrder orderAProduct(String userKey,SingleOrder order) throws LoginException,ProductException,PaymentException,AddressException {
		Optional<CurrentSessionUser> currentSessionUser = currentSession.findByUuid(userKey);
		if(currentSessionUser.isPresent()) {
			Optional<OrderOfCustomer> orderOfCustomerOpt=orderOfCustomerDao.findById(currentSessionUser.get().getOrderOfCustomerId());
			order.setOrderId(RandomIdGenerator.getRandomInteger());
			Product product_1=new Product();
			Product product_2=new Product();
			Product product_3=new Product();
			Double Total =0.0;
			
			if(order.getProductId_1()!=null && order.getProductId_1()!=0) {
				Optional<Product> productOpt = productDao.findById(order.getProductId_1());
				if(productOpt.isPresent()){
					product_1=productOpt.get();
					Integer avl = product_1.getAvailableQty();
					if(avl<order.getProduct_1_Qty()) {
						throw new ProductException("!ORDER CANCELLED! Reason : Product Id "+order.getProductId_1()+" have only "+product_1.getAvailableQty()+" quatity left");
					}
					Total=Total+product_1.getPrice()*order.getProduct_1_Qty();
				}else {
					throw new ProductException("!ORDER CANCELLED! Reason : Product Id is wrong "+"Product Id : "+order.getProductId_1());
				}
			}
			
			if(order.getProductId_2()!=null && order.getProductId_2()!=0) {
				Optional<Product> productOpt = productDao.findById(order.getProductId_2());
				if(productOpt.isPresent()){
					product_2=productOpt.get();
					Integer avl = product_2.getAvailableQty();
					if(avl<order.getProduct_2_Qty()) {
						throw new ProductException("!ORDER CANCELLED! Reason : Product Id "+order.getProductId_1()+" have only "+product_2.getAvailableQty()+" quatity left");
					}
					Total=Total+product_2.getPrice()*order.getProduct_2_Qty();
				}else {
					throw new ProductException("!ORDER CANCELLED! Reason : Product Id is wrong "+"Product Id : "+order.getProductId_2());
				}
			}
			
			if(order.getProductId_3()!=null && order.getProductId_3()!=0) {
				Optional<Product> productOpt = productDao.findById(order.getProductId_3());
				if(productOpt.isPresent()){
					product_3=productOpt.get();
					Integer avl = product_3.getAvailableQty();
					if(avl<order.getProduct_3_Qty()) {
						throw new ProductException("!ORDER CANCELLED! Reason : Product Id "+order.getProductId_3()+" have only "+product_3.getAvailableQty()+" quatity left");
					}
					Total=Total+product_3.getPrice()*order.getProduct_3_Qty();
				}else {
					throw new ProductException("!ORDER CANCELLED! Reason : Product Id is wrong "+"Product Id : "+order.getProductId_3());
				}
			}
			
			if(Total>order.getPaymentAmount()) {
				throw new PaymentException("!ORDER CANCELLED! Reason : Cash is not enough to buy the product Total Amount : "+Total);
			}
			
			Optional<AddressOfCustomer> address= addressDao.findByAddressIdAndOrderOfCustomerId(order.getAddressId(),orderOfCustomerOpt.get().getOrderOfCustomerId() );
			if(address.isEmpty()) {
				throw new AddressException("!ORDER CANCELLED! Reason : Address id is wrong");
			}
			
			Payments payment=order.getPayment();
			if(Total<order.getPaymentAmount()) {
				order.setReturnedAmount(order.getPaymentAmount()-Total);
				payment.setAmountReturned(order.getPaymentAmount()-Total);
			}
			else {
				order.setReturnedAmount(0.0);
				payment.setAmountReturned(0.0);
			}
			
			
			if(product_1.getProductId()!=null) {
				product_1.setAvailableQty(product_1.getAvailableQty()-order.getProduct_1_Qty());
				product_1.setSoledCount(product_1.getSoledCount()+order.getProduct_1_Qty());
				productDao.save(product_1);
			}
			if(product_2.getProductId()!=null) {
				product_2.setAvailableQty(product_2.getAvailableQty()-order.getProduct_2_Qty());
				product_2.setSoledCount(product_2.getSoledCount()+order.getProduct_2_Qty());
				productDao.save(product_2);
			}
			
			if(product_3.getProductId()!=null) {
				product_3.setAvailableQty(product_3.getAvailableQty()-order.getProduct_3_Qty());
				product_3.setSoledCount(product_2.getSoledCount()+order.getProduct_3_Qty());
				productDao.save(product_3);
			}
			
			orderOfCustomerOpt.get().setLastPurchasedDate(LocalDateTime.now());
			orderOfCustomerOpt.get().setLastPurchasedAmount(Total);
			orderOfCustomerOpt.get().setTotalPurchasedAmount(orderOfCustomerOpt.get().getTotalPurchasedAmount()+Total);
			
			
			payment.setPaymentId(RandomIdGenerator.getRandomInteger());
			payment.setTotalAmountForProduct(Total);
			payment.setPaymentTime(LocalDateTime.now());
			List <Payments>paymentList = orderOfCustomerOpt.get().getPaymentsDoneByCustomer();
			paymentList.add(payment);
			paymentDao.save(payment);
			
			order.setDateOfPayment(LocalDateTime.now());
			List<SingleOrder>orderList=orderOfCustomerOpt.get().getListOfOrders();
			orderList.add(order);
			
			return orderDao.save(order);
		}else {
			throw new LoginException("User needs to logIn");
		}
		
	}

}
