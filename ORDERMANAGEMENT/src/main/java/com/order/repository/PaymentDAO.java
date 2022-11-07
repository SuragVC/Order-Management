package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Payments;

public interface PaymentDAO extends JpaRepository<Payments, Integer>{

}
