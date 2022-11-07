package com.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Admin;
import com.order.entity.User;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
	public Optional<Admin> findByAdminName(String userName);
	public Optional<Admin>findByMobileNo(String mobileNo);
}
