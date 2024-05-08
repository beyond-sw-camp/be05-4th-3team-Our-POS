package com.hanwha.ordersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanwha.ordersystem.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
