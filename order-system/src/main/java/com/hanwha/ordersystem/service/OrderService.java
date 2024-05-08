package com.hanwha.ordersystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanwha.ordersystem.domain.Customer;
import com.hanwha.ordersystem.domain.Menu;
import com.hanwha.ordersystem.domain.Order;
import com.hanwha.ordersystem.domain.OrderDetail;
import com.hanwha.ordersystem.domain.Store;
import com.hanwha.ordersystem.dto.OrderDetailDto;
import com.hanwha.ordersystem.dto.OrderRequestDto;
import com.hanwha.ordersystem.dto.OrderResponseDto;
import com.hanwha.ordersystem.repository.CustomerRepository;
import com.hanwha.ordersystem.repository.MenuRepository;
import com.hanwha.ordersystem.repository.OrderRepository;
import com.hanwha.ordersystem.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

	public static final String ORDER_NOT_FOUND_MESSAGE = "Order not found";

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final MenuRepository menuRepository;
	private final StoreRepository storeRepository;

	@Transactional
	public void order(OrderRequestDto orderDto) {
		Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElseThrow(
			() -> new IllegalArgumentException("Customer not found"));

		Store store = storeRepository.findById(orderDto.getStoreId()).orElseThrow(
			() -> new IllegalArgumentException("Store not found"));

		List<OrderDetail> orderDetails = getOrderDetails(orderDto);

		Order order = Order.builder()
			.customer(customer)
			.store(store)
			.orderDetails(orderDetails)
			.build();

		orderRepository.save(order);
	}

	private List<OrderDetail> getOrderDetails(OrderRequestDto orderRequestDto) {
		List<OrderDetailDto> orderDetailDtos = orderRequestDto.getOrderDetails();

		List<OrderDetail> orderDetails = new ArrayList<>();
		for (OrderDetailDto orderDetailDto : orderDetailDtos) {
			Menu menu = menuRepository.findById(orderDetailDto.getMenuId()).orElseThrow(
				() -> new IllegalArgumentException("Menu not found"));

			OrderDetail item = OrderDetail.builder()
				.menu(menu)
				.quantity(orderDetailDto.getQuantity())
				.build();

			orderDetails.add(item);
		}

		return orderDetails;
	}

	@Transactional
	public void accept(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		order.getOrderDetails().forEach(OrderDetail::acceptOrder);
	}

	@Transactional
	public void cancel(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		order.getOrderDetails().forEach(OrderDetail::cancelOrder);
	}

	@Transactional
	public void complete(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		order.getOrderDetails().forEach(OrderDetail::completeOrder);
	}

	public List<OrderResponseDto> findAllByStoreId(Long storeId) {
		List<Order> orders = orderRepository.findByStoreId(storeId)
			.orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		return orders.stream()
			.map(OrderResponseDto::new)
			.toList();
	}

	public List<OrderResponseDto> findAllByCustomId(Long customerId) {
		List<Order> orders = orderRepository.findAllByCustomerId(customerId).orElseThrow(
			() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		return orders.stream()
			.map(OrderResponseDto::new)
			.toList();
	}

	public OrderResponseDto getOrder(Long orderId) {
		Order order = orderRepository.findByOrderId(orderId)
			.orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND_MESSAGE));

		return new OrderResponseDto(order);
	}
}
