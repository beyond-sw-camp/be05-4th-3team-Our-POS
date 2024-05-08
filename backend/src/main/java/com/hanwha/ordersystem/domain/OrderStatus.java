package com.hanwha.ordersystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum OrderStatus {
	WAITING("대기"), PROCESSING("조리 중"), COMPLETED("서빙 완료"), CANCELED("주문 취소");

	private final String key = name();
	private final String value;

	OrderStatus(String value) {
		this.value = value;
	}
}
