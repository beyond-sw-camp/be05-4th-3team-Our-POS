package com.hanwha.ordersystem.dto;

import java.util.List;

import com.hanwha.ordersystem.domain.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

	private Long orderId;
	private Long customerId;
	private Long storeId;
	private int totalPrice;
	private List<OrderDetailDto> orderDetailDtos;

	public OrderResponseDto(Order order) {
		this.orderId = order.getId();
		this.customerId = order.getCustomer().getId();
		this.storeId = order.getStore().getId();
		this.totalPrice = order.getTotalPrice();
		this.orderDetailDtos = order.getOrderDetails().stream().map(OrderDetailDto::new).toList();
	}
}
