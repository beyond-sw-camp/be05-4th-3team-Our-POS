package com.hanwha.ordersystem.dto;

import com.hanwha.ordersystem.domain.Menu;
import com.hanwha.ordersystem.domain.MenuCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponseDto {

	private Long menuId;
	private int price;
	private String name;
	private MenuCategory menuCategory;
	private String menuPictureUrl;

	public MenuResponseDto(Menu menu) {
		this.menuId = menu.getId();
		this.name = menu.getName();
		this.price = menu.getPrice();
		this.menuCategory = menu.getCategory();
		this.menuPictureUrl = menu.getMenuPictureUrl();
	}
}
