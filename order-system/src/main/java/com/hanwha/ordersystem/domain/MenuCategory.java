package com.hanwha.ordersystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MenuCategory {
	HAMBURGER("햄버거"), DESSERT("디저트"), CHICKEN("치킨"), DRINK("음료"), COFFEE("커피"), ICE_CREAM("아이스크림");

	private final String key = name();
	private final String value;

	MenuCategory(String value) {
		this.value = value;
	}
}
