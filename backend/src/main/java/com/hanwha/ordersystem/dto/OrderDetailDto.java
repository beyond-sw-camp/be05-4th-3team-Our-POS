package com.hanwha.ordersystem.dto;

import com.hanwha.ordersystem.domain.OrderDetail;
import com.hanwha.ordersystem.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
	private Long menuId;
	private String menuName;
	private int quantity;
	private OrderStatus orderStatus;

	public OrderDetailDto() {
	}

	public OrderDetailDto(OrderDetail orderDetail) {
		this.menuId = orderDetail.getMenu().getId();
		this.menuName = orderDetail.getMenu().getName();
		this.quantity = orderDetail.getQuantity();
		this.orderStatus = orderDetail.getOrderStatus();
	}
}
