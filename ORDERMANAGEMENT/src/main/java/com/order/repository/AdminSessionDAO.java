package com.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.CurrentSessionAdmin;
import com.order.entity.CurrentSessionUser;


@Repository
public interface AdminSessionDAO extends JpaRepository<CurrentSessionAdmin, Integer>{
	
	public Optional<CurrentSessionAdmin> findByUserId(Integer userId);
	
	public Optional<CurrentSessionAdmin> findByUuid(String uuid);
	
	public Optional<CurrentSessionAdmin> findByMobileNo(String uuid);
	
	
	
}
