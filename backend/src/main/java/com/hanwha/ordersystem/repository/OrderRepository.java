package com.hanwha.ordersystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanwha.ordersystem.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o "
		+ "FROM Order o "
		+ "join fetch o.store "
		+ "join fetch o.customer "
		+ "WHERE o.store.id = :storeId")
	Optional<List<Order>> findByStoreId(Long storeId);

	@Query("SELECT o "
		+ "FROM Order o "
		+ "join fetch o.store "
		+ "join fetch o.customer "
		+ "WHERE o.customer.id = :customerId")
	Optional<List<Order>> findAllByCustomerId(Long customerId);

	@Query("SELECT o "
		+ "FROM Order o "
		+ "join fetch o.store "
		+ "join fetch o.customer "
		+ "WHERE o.id = :orderId")
	Optional<Order> findByOrderId(Long orderId);
}
