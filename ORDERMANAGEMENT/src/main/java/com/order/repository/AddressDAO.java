package com.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.entity.AddressOfCustomer;

public interface AddressDAO extends JpaRepository<AddressOfCustomer, Integer>{
	List<AddressOfCustomer> findByOrderOfCustomerId(Integer orderOfCustomerId);
	@Query("Select a from AddressOfCustomer a where  address_id = ?1 and order_of_customer_id = ?2")
	Optional<AddressOfCustomer>findByAddressIdAndOrderOfCustomerId(Integer addressId,Integer orderOfCustomerId);
}
