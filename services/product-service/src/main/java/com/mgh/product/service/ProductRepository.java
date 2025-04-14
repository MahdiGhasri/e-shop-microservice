package com.mgh.product.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgh.product.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> ids);
}