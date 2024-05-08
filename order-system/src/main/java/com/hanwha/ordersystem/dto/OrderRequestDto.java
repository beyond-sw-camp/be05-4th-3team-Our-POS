package com.hanwha.ordersystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
	private Long customerId;
	private Long storeId;
	private List<OrderDetailDto> orderDetails;
}
