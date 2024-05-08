package com.hanwha.ordersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanwha.ordersystem.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
