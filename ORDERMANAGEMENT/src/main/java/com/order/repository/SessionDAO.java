package com.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.CurrentSessionUser;


@Repository
public interface SessionDAO extends JpaRepository<CurrentSessionUser, Integer>{
		
	public Optional<CurrentSessionUser> findByUuid(String uuid);
	
	public Optional<CurrentSessionUser> findByMobileNo(String uuid);
	
	
	
}
