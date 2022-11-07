package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.SingleOrder;

public interface OrderDAO extends JpaRepository<SingleOrder, Integer>{

}
