package com.hanwha.ordersystem.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Long id;

	@Column(name = "menu_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@Enumerated(EnumType.STRING)
	private MenuCategory category;

	private int price;
	private String menuPictureUrl;

	@Builder
	public Menu(String name, Store store, MenuCategory category, int price, String menuPictureUrl) {
		this.name = name;
		this.store = store;
		this.category = category;
		this.price = price;
		this.menuPictureUrl = menuPictureUrl;
	}

	public void update(String name, int price, MenuCategory menuCategory, String menuPictureUrl) {
		this.name = name;
		this.price = price;
		this.category = menuCategory;
		this.menuPictureUrl = menuPictureUrl;
	}
}
