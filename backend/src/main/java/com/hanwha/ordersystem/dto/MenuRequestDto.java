package com.hanwha.ordersystem.dto;

import com.hanwha.ordersystem.domain.MenuCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestDto {

	private Long storeId;
	private String name;
	private int price;
	private MenuCategory menuCategory;
	private String menuPictureUrl;

	public MenuRequestDto() {
	}

	public MenuRequestDto(String name, Long storeId, int price, MenuCategory menuCategory) {
		this.name = name;
		this.storeId = storeId;
		this.price = price;
		this.menuCategory = menuCategory;
	}
}
