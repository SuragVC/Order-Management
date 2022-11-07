package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
