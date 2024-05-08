package com.hanwha.ordersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanwha.ordersystem.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
