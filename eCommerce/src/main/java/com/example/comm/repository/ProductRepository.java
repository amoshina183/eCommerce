package com.example.comm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.comm.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
