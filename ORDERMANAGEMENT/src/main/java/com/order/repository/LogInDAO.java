package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.order.entity.LogIn;





@Repository
public interface LogInDAO extends JpaRepository<LogIn, Integer>{
	
	
}
