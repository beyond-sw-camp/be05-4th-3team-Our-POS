package com.hanwha.ordersystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanwha.ordersystem.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	@Query("SELECT m FROM Menu m WHERE m.store.id = :storeId")
	Optional<List<Menu>> findByStoreId(Long storeId);
}
