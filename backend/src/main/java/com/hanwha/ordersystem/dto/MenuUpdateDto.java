package com.hanwha.ordersystem.dto;

import com.hanwha.ordersystem.domain.MenuCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateDto {

	private String name;
	private int price;
	private String menuPictureUrl;
	private MenuCategory menuCategory;
}
